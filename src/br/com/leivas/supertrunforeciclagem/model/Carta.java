package br.com.leivas.supertrunforeciclagem.model;

import br.com.leivas.supertrunforeciclagem.util.CorUtil;

import java.io.Serializable;

/**
 * Classe que representa uma carta abstrata no sistema
 */
public abstract class Carta implements Serializable {

    public enum Cor {
        MENOR("menor"),
        ROXO("roxo"),
        BRANCO("branco"),
        PRETO("preto"),
        LARANJA("laranja"),
        CINZA("cinza"),
        MARROM("marrom"),
        VERDE("verde"),
        AMARELO("amarelo"),
        VERMELHO("vermelho"),
        AZUL("azul"),
        MAIOR("maior"),
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

    private String codigo;
    private String nome;
    private String descricao;
    private String tipo;
    private Cor cor;
    private Double decomposicao;
    private Integer ataque;
    private static CorUtil corUtil;

    public Carta(String codigo, String nome, String descricao, String tipo, Cor cor, Double decomposicao, Integer ataque) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
        this.cor = cor;
        this.decomposicao = decomposicao;
        this.ataque = ataque;
    }

    /**
     * Método que diz se a carta é do tipo reciclavel ou não
     *
     * @return true para sim, false para não
     */
    public abstract boolean ehReciclavel();

    /**
     * Compara o tipo entre duas cartas
     * isso é feito comparando suas cores.
     *
     * @param carta carta para comparar
     * @return 1 se a carta dessa instancia for maior que o @param carta
     * 0 se forem iguais e -1 se o @param carta for maior.
     */
    public int compareToTipo(Carta carta) {
        if (corUtil == null) {
            corUtil = new CorUtil();
        }
        return corUtil.compareCores(this.getCor(), carta.getCor());
    }

    /**
     * Compara a decomposicao entre duas cartas
     *
     * @param carta carta para comparar
     * @return 1 se a carta dessa instancia for maior que o @param carta
     * 0 se forem iguais e -1 se o @param carta for maior.
     */
    public int compareToDecomposicao(Carta carta) {
        return this.decomposicao.compareTo(carta.getDecomposicao());
    }

    /**
     * Compara se ehReciclavel entre duas cartas
     *
     * @param carta carta para comparar
     * @return 1 se a carta dessa instancia for maior que o @param carta
     * 0 se forem iguais e -1 se o @param carta for maior.
     */
    public int compareToEhReciclavel(Carta carta) {
        return this.ehReciclavel() && carta.ehReciclavel() ? 0 : this.ehReciclavel() ? 1 : -1;
    }

    /**
     * Compara o ataque entre duas cartas
     *
     * @param carta carta para comparar
     * @return 1 se a carta dessa instancia for maior que o @param carta
     * 0 se forem iguais e -1 se o @param carta for maior.
     */
    public int compareToAtaque(Carta carta) {
        return this.ataque.compareTo(carta.getAtaque());
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
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
                ", tipo='" + tipo + '\'' +
                ", cor=" + cor +
                ", decomposicao=" + decomposicao +
                ", ataque=" + ataque +
                '}';
    }
}
