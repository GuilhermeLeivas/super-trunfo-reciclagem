package br.com.leivas.supertrunforeciclagem.service;

import br.com.leivas.supertrunforeciclagem.SuperTrunfoDaReciclagem;
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

public class SuperTrunfoDaReciclagemService {

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
    private StatusJogo statusJogo = StatusJogo.NAO_INICIADO;

    public void iniciaJogo(String nomeJogador1, String nomeJogador2, Rodada.TipoRodada tipoPrimeiraRodada) {
        try {
            this.baralho = BaralhoFileReader.getInstance().readBaralhoFile();
            assert this.baralho != null;
            int numeroDeCartas = this.baralho.getCartas().size();
            int numeroDeCartasPorJogador = numeroDeCartas / 2;
            this.jogador1 = new Jogador(nomeJogador1, this.adicionaCartasJogador(this.baralho, 0, numeroDeCartasPorJogador));
            this.jogador2 = new Jogador(nomeJogador2, this.adicionaCartasJogador(this.baralho, numeroDeCartasPorJogador, numeroDeCartas));
            Rodada primeiraRodada = new Rodada();
            primeiraRodada.setTipoRodada(tipoPrimeiraRodada);
            if (this.rodadas == null) {
                this.rodadas = new ArrayList<>();
                this.rodadas.add(primeiraRodada);
            }
            this.statusJogo = StatusJogo.EM_ANDAMENTO;

        } catch (Exception ex) {
            Logger.getLogger(SuperTrunfoDaReciclagem.class.getName()).log(Level.SEVERE, String.format("Falha ao iniciar jogo %s", ex.getMessage()));
        }
    }

    public int proximaJogada(Rodada.TipoRodada tipoRodada) {
        final Rodada ultimaRodada = this.ultimaRodada();
        int result = 0;
        switch (tipoRodada) {
            case TIPO -> {
                result = this.jogador1.getCartas().element().compareToTipo(this.jogador2.getCartas().element());
            }
            case DECOMPOSICAO -> {
                result = this.jogador1.getCartas().element().compareToDecomposicao(this.jogador2.getCartas().element());
            }
            case RECICLAVEL -> {
                result = this.jogador1.getCartas().element().compareToEhReciclavel(this.jogador2.getCartas().element());
            }
            case ATAQUE -> {
                result = this.jogador1.getCartas().element().compareToAtaque(this.jogador2.getCartas().element());
            }
        }
        ultimaRodada.setVencedorRodada(result == 1 ? this.jogador1 : result == -1 ? this.jogador2 : null);
        return result;
    }

    public void verificaTerminoJogo() {
        boolean jogador1TemTodasCartas = this.jogador1.numeroDeCartas() == this.baralho.tamanhoBaralho();
        boolean jogador2TemTodasCartas = this.jogador2.numeroDeCartas() == this.baralho.tamanhoBaralho();
        if (jogador1TemTodasCartas || jogador2TemTodasCartas) {
            this.statusJogo = StatusJogo.FINALIZADO;
            this.vencedorPartida = jogador1TemTodasCartas ? jogador1 : jogador2;
        }
    }

    private Queue<Carta> adicionaCartasJogador(Baralho baralho, int startPoint, int numeroDeCartas) {
        LinkedList<Carta> cartasJogador = new LinkedList<>();
        for (int i = startPoint; i < numeroDeCartas; i++) {
            cartasJogador.add(baralho.getCartas().get(i));
        }
        return cartasJogador;
    }

    private Rodada ultimaRodada() {
        return this.rodadas.get(this.rodadas.size() - 1);
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

    public StatusJogo getStatusJogo() {
        return statusJogo;
    }

    public Jogador getVencedorPartida() {
        return vencedorPartida;
    }
}
