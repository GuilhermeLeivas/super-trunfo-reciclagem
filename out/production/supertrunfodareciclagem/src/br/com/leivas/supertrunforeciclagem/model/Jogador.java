package br.com.leivas.supertrunforeciclagem.model;

import br.com.leivas.supertrunforeciclagem.util.RodadaUtil;

import java.util.Locale;
import java.util.Objects;
import java.util.Queue;

/**
 * Classe que representa um jogador no sistema
 */
public class Jogador {

    private final Integer codigo;
    private final String nome;
    private Queue<Carta> cartas;


    private RodadaUtil rodadaUtil;

    public Jogador(Integer codigo, String nome, Queue<Carta> cartas) {
        this.codigo = codigo;
        this.nome = nome;
        this.cartas = cartas;
    }

    /**
     * Verifica se o jogador possui todas cartas do baralho
     * obs: O ideal seria fazer uma verificação mais aprofundada
     * e não somente se baseando na quantidade de cartas.
     *
     * @param baralho Baralho usado na partida.
     * @return True para caso tenha todas cartas, False caso não.
     */
    public boolean possuiTodasCartas(Baralho baralho) {
        return this.temCartas() && this.getCartas().containsAll(baralho.getCartas());
    }

    public int numeroDeCartas() {
        return this.cartas == null ? 0 : this.cartas.size();
    }

    public boolean temCartas() {
        return this.cartas != null && !this.cartas.isEmpty();
    }

    public void incluir(Carta carta) {
        this.cartas.add(carta);
    }

    public Carta proximaCarta() {
        return this.cartas.poll();
    }

    public Rodada.TipoRodada escolherRodadaRandomicamente() {
        if (this.rodadaUtil == null) {
            this.rodadaUtil = new RodadaUtil();
        }
        return this.rodadaUtil.randomTipoRodada();
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public Queue<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(Queue<Carta> cartas) {
        this.cartas = cartas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jogador jogador = (Jogador) o;
        return codigo.equals(jogador.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return String.format("--- Jogador #%d %s ---", this.codigo, this.nome.toUpperCase(Locale.ROOT));
    }
}
