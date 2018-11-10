package gguuimaraes.com.br.vendepecaapp.models;

import java.util.ArrayList;
import java.util.List;

public class Venda {
    private String nomeCliente;
    private List<Peca> pecas = new ArrayList<>();

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public List<Peca> getPecas() {
        return pecas;
    }

    public Venda nomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
        return this;
    }

    public Venda addPeca(Peca peca) {
        pecas.add(peca);
        return this;
    }

    public Venda remPeca(Peca peca) {
        pecas.remove(peca);
        return this;
    }

    public Double getValor() {
        Double soma = 0.0;
        for (Peca peca : pecas) {
            soma += peca.getValor() * peca.getQuantidade();
        }
        return soma;
    }

    public Double getDescontoTotal() {
        Double soma = 0.0;
        for (Peca peca : pecas) {
            soma += peca.getValor() * peca.getQuantidade() * peca.getTipo().getDesconto() / 100;
        }
        return soma;
    }

    public Integer getQuantidadeByTipo(EnumTipo tipo) {
        Integer soma = 0;
        for (Peca peca : pecas) {
            if (peca.getTipo() == tipo) {
                soma += peca.getQuantidade();
            }
        }
        return soma;
    }
}
