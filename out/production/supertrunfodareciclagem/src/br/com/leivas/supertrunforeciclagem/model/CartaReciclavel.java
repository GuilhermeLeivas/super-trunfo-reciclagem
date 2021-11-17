package br.com.leivas.supertrunforeciclagem.model;

/**
 * Classe que representa uma carta não reciclavel no sistema
 */
public class CartaReciclavel extends Carta {

    public CartaReciclavel(String codigo, String nome, String descricao, String tipo, Cor cor, Double decomposicao, Integer ataque) {
        super(codigo, nome, descricao, tipo, cor, decomposicao, ataque);
    }

    @Override
    public boolean ehReciclavel() {
        return true;
    }
}
