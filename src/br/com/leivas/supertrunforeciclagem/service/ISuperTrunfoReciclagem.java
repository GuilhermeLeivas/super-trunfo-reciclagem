package br.com.leivas.supertrunforeciclagem.service;

import br.com.leivas.supertrunforeciclagem.model.Baralho;
import br.com.leivas.supertrunforeciclagem.model.Carta;
import br.com.leivas.supertrunforeciclagem.model.Jogador;
import br.com.leivas.supertrunforeciclagem.model.Rodada;

import java.util.List;

/**
 * CLasse que representa Abstrata do jogo SuperTrunfo.
 */
public abstract class ISuperTrunfoReciclagem {

    /**
     * Enum que representa o status atual do jogo.
     */
    public enum StatusJogo {
        NAO_INICIADO,
        EM_ANDAMENTO,
        FINALIZADO
    }

    private Jogador vencedorPartida;
    private Baralho baralho;
    private List<Rodada> rodadas;
    private List<Carta> cartasNaMesa;
    private StatusJogo statusJogo = StatusJogo.NAO_INICIADO;

    abstract public void iniciaJogo(String nomeJogador1, String nomeJogador2, Rodada.TipoRodada tipoPrimeiraRodada);

    abstract public void proximaJogada(Rodada.TipoRodada tipoRodada);

    abstract public void verificaTerminoJogo();

    public Jogador getVencedorPartida() {
        return vencedorPartida;
    }

    protected void setVencedorPartida(Jogador vencedorPartida) {
        this.vencedorPartida = vencedorPartida;
    }

    public Baralho getBaralho() {
        return baralho;
    }

    protected void setBaralho(Baralho baralho) {
        this.baralho = baralho;
    }

    public List<Rodada> getRodadas() {
        return rodadas;
    }

    protected void setRodadas(List<Rodada> rodadas) {
        this.rodadas = rodadas;
    }

    public List<Carta> getCartasNaMesa() {
        return cartasNaMesa;
    }

    protected void setCartasNaMesa(List<Carta> cartasNaMesa) {
        this.cartasNaMesa = cartasNaMesa;
    }

    public StatusJogo getStatusJogo() {
        return statusJogo;
    }

    protected void setStatusJogo(StatusJogo statusJogo) {
        this.statusJogo = statusJogo;
    }
}
