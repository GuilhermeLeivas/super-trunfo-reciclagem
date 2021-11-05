package br.com.leivas.supertrunforeciclagem.model;

import java.util.Queue;

public class Jogador {

    private final String nome;
    private Queue<Carta> cartas;

    public Jogador(String nome, Queue<Carta> cartas) {
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

    public String getNome() {
        return nome;
    }

    public Queue<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(Queue<Carta> cartas) {
        this.cartas = cartas;
    }
}
