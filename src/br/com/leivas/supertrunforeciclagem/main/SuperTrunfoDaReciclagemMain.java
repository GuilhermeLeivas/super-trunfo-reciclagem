package br.com.leivas.supertrunforeciclagem.main;

import br.com.leivas.supertrunforeciclagem.io.BaralhoFileReader;

/**
  CLASSE MAIN DO SISTEMA
 */
public class SuperTrunfoDaReciclagemMain {
    UI ui = new UI(this);

    public static void main(String[] args) {

        new SuperTrunfoDaReciclagemMain();
        BaralhoFileReader.getInstance().readBaralhoFile();

    }
    public SuperTrunfoDaReciclagemMain(){
    }
}
