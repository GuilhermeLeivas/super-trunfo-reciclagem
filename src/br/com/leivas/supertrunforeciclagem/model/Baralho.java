package br.com.leivas.supertrunforeciclagem.model;

import java.util.Stack;

/**
 * Classe que representa um baralho de cartas no sistema.
 */
public class Baralho {

    private final Stack<Carta> cartas;

    public Baralho(Stack<Carta> cartas) {
        this.cartas = cartas;
    }

    public Carta selecionarCarta() {
        return this.cartas.peek();
    }

    public int tamanhoBaralho() {
        return this.cartas.size();
    }

    public Stack<Carta> getCartas() {
        return cartas;
    }
}
