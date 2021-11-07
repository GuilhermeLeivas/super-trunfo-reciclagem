package br.com.leivas.supertrunforeciclagem.model;

/**
 * Classe que representa cada rodada do jogo no sistema.
 */
public class Rodada {

    public enum TipoRodada {
        TIPO,
        DECOMPOSICAO,
        RECICLAVEL,
        ATAQUE
    }

    private Jogador vencedorRodada;
    private TipoRodada tipoRodada;

    public Rodada(Jogador vencedorRodadaAnterior, Jogador vencedorRodada, TipoRodada tipoRodada) {
        this.vencedorRodada = vencedorRodada;
        this.tipoRodada = tipoRodada;
    }

    public Rodada() {

    }

    public Jogador getVencedorRodada() {
        return vencedorRodada;
    }

    public void setVencedorRodada(Jogador vencedorRodada) {
        this.vencedorRodada = vencedorRodada;
    }

    public TipoRodada getTipoRodada() {
        return tipoRodada;
    }

    public void setTipoRodada(TipoRodada tipoRodada) {
        this.tipoRodada = tipoRodada;
    }
}
