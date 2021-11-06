package br.com.leivas.supertrunforeciclagem.service;

import br.com.leivas.supertrunforeciclagem.SuperTrunfoDaReciclagem;
import br.com.leivas.supertrunforeciclagem.io.BaralhoFileReader;
import br.com.leivas.supertrunforeciclagem.model.Baralho;
import br.com.leivas.supertrunforeciclagem.model.Carta;
import br.com.leivas.supertrunforeciclagem.model.Jogador;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SuperTrunfoDaReciclagemService {

    public enum TipoJogada {
        TIPO,
        DECOMPOSICAO,
        RECICLAVEL,
        ATAQUE
    }

    public enum StatusJogo {
        NAO_INICIADO,
        EM_ANDAMENTO,
        FINALIZADO
    }

    private Jogador jogador1;
    private Jogador jogador2;
    private Baralho baralho;
    private StatusJogo statusJogo = StatusJogo.NAO_INICIADO;
    private Jogador vencedor;

    public void iniciaJogo(String nomeJogador1, String nomeJogador2) {
        try {
            this.baralho = BaralhoFileReader.readBaralhoFile();
            int numeroDeCartas = this.baralho.getCartas().size();
            int numeroDeCartasPorJogador = numeroDeCartas / 2;
            this.jogador1 = new Jogador(nomeJogador1, this.adicionaCartasJogador(this.baralho, 0, numeroDeCartasPorJogador));
            this.jogador2 = new Jogador(nomeJogador2, this.adicionaCartasJogador(this.baralho, numeroDeCartasPorJogador, numeroDeCartas));
            this.statusJogo = StatusJogo.EM_ANDAMENTO;

        } catch (Exception ex) {
            Logger.getLogger(SuperTrunfoDaReciclagem.class.getName()).log(Level.SEVERE, String.format("Falha ao iniciar jogo %s", ex.getMessage()));
        }
    }

    public int proximaJogada(TipoJogada tipoJogada) {
        int result = 0;
        switch (tipoJogada) {
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
        return result;
    }

    public void verificaTerminoJogo() {
        boolean jogador1TemTodasCartas = this.jogador1.numeroDeCartas() == this.baralho.tamanhoBaralho();
        boolean jogador2TemTodasCartas = this.jogador2.numeroDeCartas() == this.baralho.tamanhoBaralho();
        if (jogador1TemTodasCartas || jogador2TemTodasCartas) {
            this.statusJogo = StatusJogo.FINALIZADO;
            this.vencedor = jogador1TemTodasCartas ? jogador1 : jogador2;
        }
    }

    private Queue<Carta> adicionaCartasJogador(Baralho baralho, int startPoint, int numeroDeCartas) {
        LinkedList<Carta> cartasJogador = new LinkedList<>();
        for (int i = startPoint; i < numeroDeCartas; i++) {
            cartasJogador.add(baralho.getCartas().get(i));
        }
        return cartasJogador;
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

    public Jogador getVencedor() {
        return vencedor;
    }
}
