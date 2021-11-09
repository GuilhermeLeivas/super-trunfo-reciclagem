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

    public enum StatusRodada {
        NAO_DEFINIDA,
        VITORIA_PLAYER1,
        VITORIA_PLAYER2,
        EMPATE;

        public static StatusRodada rodadaResultToStatus(int result) {
            return switch (result) {
                case 1 -> VITORIA_PLAYER1;
                case -1 -> VITORIA_PLAYER2;
                case 0 -> EMPATE;
                default -> NAO_DEFINIDA;
            };
        }
    }

    private Jogador vencedorRodada;
    private TipoRodada tipoRodada;
    private StatusRodada statusRodada = StatusRodada.NAO_DEFINIDA;

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

    public StatusRodada getStatusRodada() {
        return statusRodada;
    }

    public void setStatusRodada(StatusRodada statusRodada) {
        this.statusRodada = statusRodada;
    }
}
