package br.com.leivas.supertrunforeciclagem.service;

import br.com.leivas.supertrunforeciclagem.main.SuperTrunfoDaReciclagemMain;
import br.com.leivas.supertrunforeciclagem.io.BaralhoFileReader;
import br.com.leivas.supertrunforeciclagem.model.Baralho;
import br.com.leivas.supertrunforeciclagem.model.Carta;
import br.com.leivas.supertrunforeciclagem.model.Jogador;
import br.com.leivas.supertrunforeciclagem.model.Rodada;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * CLasse que representa o funcionamento do jogo, suas regras e suas etapas.
 */
public class SuperTrunfoDaReciclagem {

    /**
     * Enum que representa o status atual do jogo.
     */
    public enum StatusJogo {
        NAO_INICIADO,
        EM_ANDAMENTO,
        FINALIZADO
    }

    private Jogador jogador1;
    private Jogador jogador2;
    private Jogador vencedorPartida;
    private Baralho baralho;
    private List<Rodada> rodadas;
    private List<Carta> cartasNaMesa;
    private StatusJogo statusJogo = StatusJogo.NAO_INICIADO;

    /**
     * Método responsável por:
     * inicializar um novo jogo com 2 jogadores.
     * Distribuir as cartas para os 2 jogadores.
     * Criar rodada inicial.
     * Alterar o status do jogo.
     *
     * @param nomeJogador1       Nome do jogador1
     * @param nomeJogador2       Nome do jogador2
     * @param tipoPrimeiraRodada Escolha do primeiro tipo de rodada
     */
    public void iniciaJogo(String nomeJogador1, String nomeJogador2, Rodada.TipoRodada tipoPrimeiraRodada) {
        try {
            if (this.statusJogo == StatusJogo.NAO_INICIADO) {
                this.baralho = BaralhoFileReader.getInstance().readBaralhoFile();
                assert this.baralho != null;
                int numeroDeCartas = this.baralho.getCartas().size();
                int numeroDeCartasPorJogador = numeroDeCartas / 2;
                this.jogador1 = new Jogador(1, nomeJogador1,
                        this.adicionaCartasJogador(this.baralho, 0, numeroDeCartasPorJogador));
                this.jogador2 = new Jogador(2, nomeJogador2,
                        this.adicionaCartasJogador(this.baralho, numeroDeCartasPorJogador, numeroDeCartas));
                this.statusJogo = StatusJogo.EM_ANDAMENTO;
                this.proximaJogada(tipoPrimeiraRodada);
            }
        } catch (Exception ex) {
            Logger.getLogger(SuperTrunfoDaReciclagemMain.class.getName()).log(Level.SEVERE, String.format("Falha ao iniciar jogo %s", ex.getMessage()));
        }
    }

    /**
     * Método responsável por passar o jogo para próxima rodada.
     *
     * @param tipoRodada Tipo de rodada escolhida pelo ganhador da rodada anterior.
     */
    public void proximaJogada(Rodada.TipoRodada tipoRodada) {
        if (this.statusJogo == StatusJogo.EM_ANDAMENTO) {
            Rodada novaRodada = new Rodada();
            novaRodada.setTipoRodada(tipoRodada);
            Carta cartaJogador1 = this.jogador1.proximaCarta();
            Carta cartaJogador2 = this.jogador2.proximaCarta();
            this.adicionaCartasNaMesa(cartaJogador1, cartaJogador2);
            novaRodada.defineResultadoRodada(cartaJogador1, cartaJogador2);
            novaRodada.defineVencedorRodada(this.jogador1, this.jogador2);
            this.adicionaRodadaNaPartida(novaRodada);
            this.cartasNaMesaParaVencedorRodada(novaRodada.getVencedorRodada());
            this.verificaTerminoJogo();
        }
    }

    /**
     * A cada rodada é verificado se o jogo tem um ganhador.
     * Isso ocorrerá quando um jogador tiver todas cartas do baralho.
     */
    public void verificaTerminoJogo() {
        boolean jogador1TemTodasCartas = this.jogador1.possuiTodasCartas(this.baralho);
        boolean jogador2TemTodasCartas = this.jogador2.possuiTodasCartas(this.baralho);
        if (jogador1TemTodasCartas || jogador2TemTodasCartas) {
            this.statusJogo = StatusJogo.FINALIZADO;
            this.vencedorPartida = jogador1TemTodasCartas ? jogador1 : jogador2;
        }
    }

    public Jogador getJogador1() {
        return jogador1;
    }

    public Jogador getJogador2() {
        return jogador2;
    }

    public Baralho getBaralho() {
        return baralho;
    }

    public List<Rodada> getRodadas() {
        return rodadas;
    }

    public List<Carta> getCartasNaMesa() {
        return cartasNaMesa;
    }

    public StatusJogo getStatusJogo() {
        return statusJogo;
    }

    public Jogador getVencedorPartida() {
        return vencedorPartida;
    }

    /**
     * Adiciona as cartas para um jogador.
     *
     * @param baralho        Baralho que possui as cartas
     * @param startPoint     De qual carta começa
     * @param numeroDeCartas Até qual carta
     * @return As cartas do jogador
     */
    private Queue<Carta> adicionaCartasJogador(Baralho baralho, int startPoint, int numeroDeCartas) {
        LinkedList<Carta> cartasJogador = new LinkedList<>();
        for (int i = startPoint; i < numeroDeCartas; i++) {
            cartasJogador.add(baralho.getCartas().get(i));
        }
        return cartasJogador;
    }

    /**
     * @return Método que retorna a última jogada da partida/jogo.
     */
    private Rodada ultimaRodada() {
        return this.rodadas.get(this.rodadas.size() - 1);
    }

    /**
     * Adiciona uma nova rodada ao jogo.
     *
     * @param rodada Nova rodada.
     */
    private void adicionaRodadaNaPartida(Rodada rodada) {
        if (this.rodadas == null) {
            this.rodadas = new ArrayList<>();
        }
        this.rodadas.add(rodada);
    }

    /**
     * Adiona as cartas de do cima do monte
     * de cada jogador participando da rodada na mesa.
     */
    private void adicionaCartasNaMesa(Carta cartaJogador1, Carta cartaJogador2) {
        if (this.cartasNaMesa == null) {
            this.cartasNaMesa = new ArrayList<>();
        }
        this.cartasNaMesa.add(cartaJogador1);
        this.cartasNaMesa.add(cartaJogador2);
    }

    /**
     * Adiciona as cartas da mesa ao jogador vencedor da rodada.
     *
     * @param jogador Jogador vencedor da rodada atual.
     */
    private void cartasNaMesaParaVencedorRodada(Jogador jogador) {
        Rodada ultimaRodada = this.ultimaRodada();
        // Em caso de empate na rodada, as cartas continuam na mesa.
        if (ultimaRodada.getVencedorRodada() != null) {
            this.cartasNaMesa.forEach(jogador::incluir);
        }
    }
}
