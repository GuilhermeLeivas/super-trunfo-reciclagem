package br.com.leivas.supertrunforeciclagem.model;

public class CartaoNaoReciclavel extends Carta {
    @Override
    public boolean ehReciclavel() {
        return false;
    }
}
