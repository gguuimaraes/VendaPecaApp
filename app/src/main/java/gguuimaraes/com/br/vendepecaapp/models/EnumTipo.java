package gguuimaraes.com.br.vendepecaapp.models;

public enum EnumTipo {
    PORCA,
    PARAFUSO,
    ARRUELA;

    public Integer getDesconto() {
        return 10 * (ordinal() + 1);
    }
}
