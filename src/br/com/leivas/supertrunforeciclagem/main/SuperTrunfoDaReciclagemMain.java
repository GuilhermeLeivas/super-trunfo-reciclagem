package br.com.leivas.supertrunforeciclagem.main;

import br.com.leivas.supertrunforeciclagem.io.BaralhoFileReader;

/**
 * Classe main do sistema.
 */
public class SuperTrunfoDaReciclagemMain {

    public static void main(String[] args) {

        BaralhoFileReader.getInstance().readBaralhoFile();

    }
}
