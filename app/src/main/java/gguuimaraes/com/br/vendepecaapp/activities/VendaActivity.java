package gguuimaraes.com.br.vendepecaapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import gguuimaraes.com.br.vendepecaapp.R;
import gguuimaraes.com.br.vendepecaapp.adapters.PecaAdapter;
import gguuimaraes.com.br.vendepecaapp.models.EnumTipo;
import gguuimaraes.com.br.vendepecaapp.models.Peca;
import gguuimaraes.com.br.vendepecaapp.models.Venda;

public class VendaActivity extends AppCompatActivity {

    private Venda venda = new Venda();

    private EditText txtNomeCliente = null;
    private EditText txtValor = null;
    private EditText txtQuantidade = null;
    private ListView listViewPecas = null;
    private TextView lblQtdPorca = null;
    private TextView lblQtdParafuso = null;
    private TextView lblQtdArruela = null;
    private TextView lblValorTotal = null;
    private TextView lblValorPecas = null;
    private TextView lblValorDesconto = null;
    private Spinner cbTipo = null;
    private PecaAdapter pecaAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venda);

        txtNomeCliente = findViewById(R.id.txtNomeCliente);
        txtValor = findViewById(R.id.txtValor);
        txtQuantidade = findViewById(R.id.txtQuantidade);
        cbTipo = findViewById(R.id.cbTipo);
        listViewPecas = findViewById(R.id.listViewPecas);
        lblQtdPorca = findViewById(R.id.lblQtdPorca);
        lblQtdParafuso = findViewById(R.id.lblQtdParafuso);
        lblQtdArruela = findViewById(R.id.lblQtdArruela);
        lblValorPecas = findViewById(R.id.lblValorPecas);
        lblValorTotal = findViewById(R.id.lblValorTotal);
        lblValorDesconto = findViewById(R.id.lblValorDesconto);
        cbTipo.setAdapter(new ArrayAdapter<EnumTipo>(this, android.R.layout.simple_spinner_item, EnumTipo.values()));
    }

    public void adicionar(View view) {
        if (txtValor.getText().toString().isEmpty() || txtQuantidade.getText().toString().isEmpty())
            return;

        EnumTipo tipo = (EnumTipo) cbTipo.getSelectedItem();
        Double valor = Double.valueOf(txtValor.getText().toString());
        Integer quantidade = Integer.valueOf(txtQuantidade.getText().toString());

        Peca peca = new Peca().tipo(tipo).valor(valor).quantidade(quantidade);
        venda.addPeca(peca);

        cbTipo.setSelection(0);
        txtValor.getText().clear();
        txtQuantidade.getText().clear();
        txtValor.requestFocus();

        pecaAdapter = new PecaAdapter(this, venda, lblQtdPorca, lblQtdParafuso, lblQtdArruela, lblValorPecas, lblValorDesconto, lblValorTotal);
        listViewPecas.setAdapter(pecaAdapter);

        lblQtdPorca.setText(String.format("Porcas: %d", venda.getQuantidadeByTipo(EnumTipo.PORCA)));
        lblQtdParafuso.setText(String.format("Parafusos: %d", venda.getQuantidadeByTipo(EnumTipo.PARAFUSO)));
        lblQtdArruela.setText(String.format("Arruelas: %d", venda.getQuantidadeByTipo(EnumTipo.ARRUELA)));
        lblValorPecas.setText(String.format("Valor: R$ %.2f", venda.getValor()));
        lblValorDesconto.setText(String.format("Desconto: R$ %.2f", venda.getDescontoTotal()));
        lblValorTotal.setText(String.format("Valor Total: R$ %.2f", venda.getValor() - venda.getDescontoTotal()));
    }

    public void vender(View view) {
        if (txtNomeCliente.getText().toString().isEmpty()) {
            Toast.makeText(this, "Insira o Nome do Cliente!", Toast.LENGTH_LONG).show();
            return;
        }
        if (venda.getPecas().isEmpty()) {
            Toast.makeText(this, "Insira ao menos uma Peça para vender!", Toast.LENGTH_LONG).show();
            return;
        }

        String nomeCliente = txtNomeCliente.getText().toString();
        venda.nomeCliente(nomeCliente);
    }
}
