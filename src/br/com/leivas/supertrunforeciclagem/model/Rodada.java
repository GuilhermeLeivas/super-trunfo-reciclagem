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

    /**
     * Método responsável por alterar o status da rodada dependendo do resultado no combate
     * das duas cartas usadas na rodada atual.
     *
     * @param cartaJogador1 Carta da vez do jogador1
     * @param cartaJogador2 Carta da vez do jogador2
     */
    public void defineResultadoRodada(Carta cartaJogador1, Carta cartaJogador2) {
        System.out.println("Comparando " + this.tipoRodada);
        int result = 0;
        if (cartaJogador1 != null && cartaJogador2 != null) {
            switch (this.tipoRodada) {
                case TIPO -> result = cartaJogador1.compareToTipo(cartaJogador2);
                case DECOMPOSICAO -> result = cartaJogador1.compareToDecomposicao(cartaJogador2);
                case RECICLAVEL -> result = cartaJogador1.compareToEhReciclavel(cartaJogador2);
                case ATAQUE -> result = cartaJogador1.compareToAtaque(cartaJogador2);
            }
        }
        this.statusRodada = StatusRodada.rodadaResultToStatus(result);
    }

    /**
     * Método que define o vencedor da partida baseando-se no
     * status da rodada após gerar o resultado em @defineResultadoRodada.
     *
     * @param jogador1 Jogador1 da partida
     * @param jogador2 Jogador2 da partida
     */
    public void defineVencedorRodada(Jogador jogador1, Jogador jogador2) {
        switch (this.statusRodada) {
            case VITORIA_PLAYER1 -> this.vencedorRodada = jogador1;
            case VITORIA_PLAYER2 -> this.vencedorRodada = jogador2;
            case EMPATE -> this.vencedorRodada = null;
        }
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
