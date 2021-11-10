package br.com.leivas.supertrunforeciclagem.service;

import br.com.leivas.supertrunforeciclagem.io.BaralhoFileReader;
import br.com.leivas.supertrunforeciclagem.main.SuperTrunfoDaReciclagemSimulacao;
import br.com.leivas.supertrunforeciclagem.model.Baralho;
import br.com.leivas.supertrunforeciclagem.model.Carta;
import br.com.leivas.supertrunforeciclagem.model.Jogador;
import br.com.leivas.supertrunforeciclagem.model.Rodada;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * CLasse que representa o funcionamento do jogo, suas regras e suas etapas.
 */
public class SuperTrunfoDaReciclagem extends ISuperTrunfoReciclagem {

    private Jogador jogador1;
    private Jogador jogador2;

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
    @Override
    public void iniciaJogo(String nomeJogador1, String nomeJogador2, Rodada.TipoRodada tipoPrimeiraRodada) {
        try {
            if (this.getStatusJogo() == StatusJogo.NAO_INICIADO) {
                this.setBaralho(BaralhoFileReader.getInstance().readBaralhoFile());
                assert this.getBaralho() != null;
                int numeroDeCartas = this.getBaralho().getCartas().size();
                int numeroDeCartasPorJogador = numeroDeCartas / 2;
                this.jogador1 = new Jogador(1, nomeJogador1,
                        this.adicionaCartasJogador(this.getBaralho(), 0, numeroDeCartasPorJogador));
                this.jogador2 = new Jogador(2, nomeJogador2,
                        this.adicionaCartasJogador(this.getBaralho(), numeroDeCartasPorJogador, numeroDeCartas));
                this.setStatusJogo(StatusJogo.EM_ANDAMENTO);
                this.proximaJogada(tipoPrimeiraRodada);
            }
        } catch (Exception ex) {
            Logger.getLogger(SuperTrunfoDaReciclagemSimulacao.class.getName()).log(Level.SEVERE, String.format("Falha ao iniciar jogo %s", ex.getMessage()));
        }
    }

    /**
     * Método responsável por passar o jogo para próxima rodada.
     *
     * @param tipoRodada Tipo de rodada escolhida pelo ganhador da rodada anterior.
     */
    @Override
    public void proximaJogada(Rodada.TipoRodada tipoRodada) {
        if (this.getStatusJogo() == StatusJogo.EM_ANDAMENTO) {
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
    @Override
    public void verificaTerminoJogo() {
        boolean jogador1TemTodasCartas = this.jogador1.possuiTodasCartas(this.getBaralho());
        boolean jogador2TemTodasCartas = this.jogador2.possuiTodasCartas(this.getBaralho());
        if (jogador1TemTodasCartas || jogador2TemTodasCartas) {
            this.setStatusJogo(StatusJogo.FINALIZADO);
            this.setVencedorPartida(jogador1TemTodasCartas ? this.jogador1 : this.jogador2);
        }
    }

    public Jogador getJogador1() {
        return jogador1;
    }

    public Jogador getJogador2() {
        return jogador2;
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
        return this.getRodadas().get(this.getRodadas().size() - 1);
    }

    /**
     * Adiciona uma nova rodada ao jogo.
     *
     * @param rodada Nova rodada.
     */
    private void adicionaRodadaNaPartida(Rodada rodada) {
        if (this.getRodadas() == null) {
            this.setRodadas(new ArrayList<>());
        }
        this.getRodadas().add(rodada);
    }

    /**
     * Adiona as cartas de do cima do monte
     * de cada jogador participando da rodada na mesa.
     */
    private void adicionaCartasNaMesa(Carta cartaJogador1, Carta cartaJogador2) {
        if (this.getCartasNaMesa() == null) {
            this.setCartasNaMesa(new ArrayList<>());
        }
        this.getCartasNaMesa().add(cartaJogador1);
        this.getCartasNaMesa().add(cartaJogador2);
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
            this.getCartasNaMesa().forEach(jogador::incluir);
        }
    }
}
