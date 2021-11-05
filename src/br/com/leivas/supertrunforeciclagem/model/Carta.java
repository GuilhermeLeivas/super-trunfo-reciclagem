package br.com.leivas.supertrunforeciclagem.model;

import java.io.Serializable;

/**
 * Classe que representa uma carta no sistema
 */
public abstract class Carta implements Serializable {

    public enum Cor {
        VERDE("verde"), CINZA("cinza"), LARANJA("laranja"), VERMELHO("vermelho"),
        AZUL("azul"), AMARELO("amarelo"), MARROM("marrom"), ROXO("roxo"),
        BRANCO("branco"), PRETO("preto"), MAIOR("maior"), MENOR("menor"),
        INDEFINIDA("indefinida");

        private final String cor;

        Cor(String cor) {
            this.cor = cor;
        }

        @Override
        public String toString() {
            return cor;
        }

    }

    private Integer codigo;
    private String nome;
    private String descricao;
    private String tipo;
    private Cor cor;
    private Double decomposicao;
    private Integer ataque;

    public abstract boolean ehReciclavel();

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    public Double getDecomposicao() {
        return decomposicao;
    }

    public void setDecomposicao(Double decomposicao) {
        this.decomposicao = decomposicao;
    }

    public Integer getAtaque() {
        return ataque;
    }

    public void setAtaque(Integer ataque) {
        this.ataque = ataque;
    }

    @Override
    public String toString() {
        return "Carta{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", tipo='" + tipo + '\'' +
                ", cor=" + cor +
                ", decomposicao=" + decomposicao +
                ", ataque=" + ataque +
                '}';
    }
}
