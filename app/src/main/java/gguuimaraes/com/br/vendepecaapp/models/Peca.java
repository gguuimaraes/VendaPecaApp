package gguuimaraes.com.br.vendepecaapp.models;

import java.util.Objects;

public class Peca {
    private EnumTipo tipo;
    private Double valor;
    private Integer quantidade;

    public Peca() {
    }

    public EnumTipo getTipo() {
        return tipo;
    }

    public void setTipo(EnumTipo tipo) {
        this.tipo = tipo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Peca valor(Double valor) {
        setValor(valor);
        return this;
    }

    public Peca quantidade(Integer quantidade) {
        setQuantidade(quantidade);
        return this;
    }

    public Peca tipo(EnumTipo tipo) {
        setTipo(tipo);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Peca peca = (Peca) o;
        return tipo == peca.tipo &&
                Objects.equals(valor, peca.valor) &&
                Objects.equals(quantidade, peca.quantidade);
    }

    @Override
    public int hashCode() {

        return Objects.hash(tipo, valor, quantidade);
    }
}
