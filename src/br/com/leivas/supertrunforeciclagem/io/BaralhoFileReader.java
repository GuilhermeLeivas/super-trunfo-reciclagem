package br.com.leivas.supertrunforeciclagem.io;

import br.com.leivas.supertrunforeciclagem.SuperTrunfoDaReciclagem;
import br.com.leivas.supertrunforeciclagem.model.Baralho;
import br.com.leivas.supertrunforeciclagem.model.Carta;
import br.com.leivas.supertrunforeciclagem.model.CartaReciclavel;
import br.com.leivas.supertrunforeciclagem.model.CartaoNaoReciclavel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class BaralhoFileReader {

    private static final String fileSrc = "resources/Baralho.csv";

    private static BaralhoFileReader instance = null;

    private BaralhoFileReader() {

    }


    public Baralho readBaralhoFile() {
        Path path = Paths.get(fileSrc);
        try (final Stream<String> lines = Files.lines(path)) {
            Baralho baralho = this.convertFileToBaralho(lines);
            baralho.getCartas().forEach(System.out::println);
        } catch (IOException ex) {
            Logger.getLogger(SuperTrunfoDaReciclagem.class.getName()).log(Level.SEVERE, String.format("Falha ler arquivo do baralho %s", ex.getMessage()));
        }
        return null;
    }

    public static BaralhoFileReader getInstance() {
        if (instance == null) {
            instance = new BaralhoFileReader();
        }
        return instance;
    }

    private Baralho convertFileToBaralho(Stream<String> fileLines) {
        Baralho baralho;
        Stack<Carta> cartasBaralho = new Stack<>();
        fileLines.forEach(line -> {
            Carta carta;
            String[] lineSplitted = line.split(";");
            String codigo = lineSplitted[0];
            String nome = lineSplitted[1];
            String descricao = lineSplitted[2];
            String tipo = lineSplitted[3];
            Carta.Cor cor = Carta.Cor.valueOf(lineSplitted[4].toUpperCase(Locale.ROOT));
            Double decomposicao = Double.valueOf(lineSplitted[5]);
            Integer ataque = Integer.valueOf(lineSplitted[6]);
            boolean ehReciclavel = Boolean.parseBoolean(lineSplitted[7]);
            carta = ehReciclavel ?
                    new CartaReciclavel(codigo, nome, descricao, tipo, cor, decomposicao, ataque) :
                    new CartaoNaoReciclavel(codigo, nome, descricao, tipo, cor, decomposicao, ataque);
            cartasBaralho.add(carta);
        });
        baralho = new Baralho(cartasBaralho);
        return baralho;
    }
}
