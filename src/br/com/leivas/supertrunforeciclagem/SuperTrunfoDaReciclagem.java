package br.com.leivas.supertrunforeciclagem;

import br.com.leivas.supertrunforeciclagem.io.BaralhoFileReader;

/**
 * Classe main do sistema.
 */
public class SuperTrunfoDaReciclagem {

    public static void main(String[] args) {

        BaralhoFileReader.getInstance().readBaralhoFile();

    }
}
