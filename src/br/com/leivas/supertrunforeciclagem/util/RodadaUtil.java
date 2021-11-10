package br.com.leivas.supertrunforeciclagem.util;

import br.com.leivas.supertrunforeciclagem.model.Rodada;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Classe utilitária de Rodadas.
 * Foi criado um mecanismo para gerar randomicamente sem tantas repetições
 * as escolhas de ataque que o jogador irá fazer automaticamente na simulação.
 */
public class RodadaUtil {

    private int ultimoTipoRodada;
    private int ultimoTipoRodadaStreak;

    /**
     * Método útil para gerara randomicamente um tipo
     * de jogada escolhida pelo jogador.
     *
     * @return TipoRodada da próxima rodada.
     */
    public Rodada.TipoRodada randomTipoRodada() {
        this.verificaStreakJogadasRepetidas(ThreadLocalRandom.current().nextInt(0, 4));
        return Rodada.TipoRodada.values()[this.ultimoTipoRodada];
    }

    /**
     * Verifica se um determinado tipo de ataque não teve
     * uma streak de repetições.
     *
     * @param tipoRodadaIndex tipoRodadaIndex não repetida mais que 3 vezes.
     */
    private void verificaStreakJogadasRepetidas(int tipoRodadaIndex) {
        if (this.ultimoTipoRodada == tipoRodadaIndex && this.ultimoTipoRodadaStreak < 3) {
            this.aumentaStreakDeRepeticao();
        } else {
            this.ultimoTipoRodada = tipoRodadaIndex;
            this.ultimoTipoRodadaStreak = 0;
        }
    }

    private void aumentaStreakDeRepeticao() {
        this.ultimoTipoRodadaStreak++;
    }
}
