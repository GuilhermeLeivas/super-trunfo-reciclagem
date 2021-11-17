package br.com.leivas.supertrunforeciclagem.util;

import br.com.leivas.supertrunforeciclagem.model.Carta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CorUtil {

    /**
     * Método que faz a comparação entre a cor de uma carta
     * com a cor de outra carta.
     *
     * @param cor1 Primeira carta
     * @param cor2 Segunda carta
     * @return qual carta é maior.
     */
    public int compareCores(Carta.Cor cor1, Carta.Cor cor2) {
        List<Carta.Cor> coresMenores1 = this.findCoresMenores(cor1);
        List<Carta.Cor> coresMenores2 = this.findCoresMenores(cor2);
        if (coresMenores1 == null || coresMenores2 == null) {
            return coresMenores1 == null ? -1 : 1;
        } else if (coresMenores1.isEmpty() || coresMenores2.isEmpty()) {
            return coresMenores1.isEmpty() ? -1 : 1;
        } else {
            boolean cor1GanhaDeCor2 = coresMenores1.stream().anyMatch(cor -> cor == cor2);
            boolean cor2GanhaDeCor1 = coresMenores2.stream().anyMatch(cor -> cor == cor1);
            if (cor1GanhaDeCor2 && cor2GanhaDeCor1) {
                return 0;
            } else {
                return cor1GanhaDeCor2 ? 1 : -1;
            }
        }
    }

    /**
     * Método responsável por dizer quais cores que
     * o @param cor ganha.
     *
     * @param cor Cor que se deseja obter as cores.
     * @return As cores que perdem para o @param cor
     */
    private List<Carta.Cor> findCoresMenores(Carta.Cor cor) {
        List<Carta.Cor> cores = null;
        switch (cor) {
            case MENOR -> cores = new ArrayList<>();
            case ROXO -> cores = List.of(Carta.Cor.AZUL, Carta.Cor.VERMELHO, Carta.Cor.AMARELO, Carta.Cor.VERDE, Carta.Cor.MARROM);
            case BRANCO -> cores = List.of(Carta.Cor.LARANJA, Carta.Cor.ROXO, Carta.Cor.AZUL, Carta.Cor.VERMELHO, Carta.Cor.AMARELO);
            case PRETO -> cores = List.of(Carta.Cor.BRANCO, Carta.Cor.LARANJA, Carta.Cor.ROXO, Carta.Cor.AZUL, Carta.Cor.VERMELHO);
            case LARANJA -> cores = List.of(Carta.Cor.ROXO, Carta.Cor.AZUL, Carta.Cor.VERMELHO, Carta.Cor.AMARELO, Carta.Cor.VERDE);
            case CINZA -> cores = List.of(Carta.Cor.PRETO, Carta.Cor.BRANCO, Carta.Cor.LARANJA, Carta.Cor.ROXO, Carta.Cor.AZUL);
            case MARROM -> cores = List.of(Carta.Cor.CINZA, Carta.Cor.PRETO, Carta.Cor.BRANCO, Carta.Cor.LARANJA, Carta.Cor.ROXO);
            case VERDE -> cores = List.of(Carta.Cor.MARROM, Carta.Cor.CINZA, Carta.Cor.PRETO, Carta.Cor.BRANCO, Carta.Cor.LARANJA);
            case AMARELO -> cores = List.of(Carta.Cor.VERDE, Carta.Cor.MARROM, Carta.Cor.CINZA, Carta.Cor.PRETO, Carta.Cor.BRANCO);
            case VERMELHO -> cores = List.of(Carta.Cor.AMARELO, Carta.Cor.VERDE, Carta.Cor.MARROM, Carta.Cor.CINZA, Carta.Cor.PRETO);
            case AZUL -> cores = List.of(Carta.Cor.VERMELHO, Carta.Cor.AMARELO, Carta.Cor.VERDE, Carta.Cor.MARROM, Carta.Cor.CINZA);
            case MAIOR -> cores = Arrays.stream(Carta.Cor.values()).toList();
            case INDEFINIDA -> {
                return null;
            }
        }
        return cores;
    }
}
