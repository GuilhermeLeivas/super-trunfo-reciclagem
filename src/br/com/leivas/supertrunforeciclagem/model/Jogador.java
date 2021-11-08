package br.com.leivas.supertrunforeciclagem.model;

import java.util.Objects;
import java.util.Queue;

/**
 * Classe que representa um jogador no sistema
 */
public class Jogador {

    private final Integer codigo;
    private final String nome;
    private Queue<Carta> cartas;

    public Jogador(Integer codigo, String nome, Queue<Carta> cartas) {
        this.codigo = codigo;
        this.nome = nome;
        this.cartas = cartas;
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

    public void excluir() {
        this.cartas.remove();
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
}
