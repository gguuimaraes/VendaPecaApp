package gguuimaraes.com.br.vendepecaapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import gguuimaraes.com.br.vendepecaapp.R;
import gguuimaraes.com.br.vendepecaapp.models.EnumTipo;
import gguuimaraes.com.br.vendepecaapp.models.Peca;
import gguuimaraes.com.br.vendepecaapp.models.Venda;

public class PecaAdapter extends BaseAdapter {
    private Context context;
    private Venda venda;
    private TextView lblQtdPorca = null;
    private TextView lblQtdParafuso = null;
    private TextView lblQtdArruela = null;
    private TextView lblValorTotal = null;
    private TextView lblValorPecas = null;
    private TextView lblValorDesconto = null;

    public PecaAdapter(Context context, Venda venda, TextView lblQtdPorca, TextView lblQtdParafuso, TextView lblQtdArruela, TextView lblValorPecas, TextView lblValorDesconto, TextView lblValorTotal) {
        this.context = context;
        this.venda = venda;
        this.lblQtdPorca = lblQtdPorca;
        this.lblQtdParafuso = lblQtdParafuso;
        this.lblQtdArruela = lblQtdArruela;
        this.lblValorTotal = lblValorTotal;
        this.lblValorPecas = lblValorPecas;
        this.lblValorDesconto = lblValorDesconto;
    }

    @Override
    public int getCount() {
        return venda.getPecas().size();
    }

    @Override
    public Object getItem(int position) {
        return venda.getPecas().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.peca_item, parent, false);

        final Peca peca = (Peca) getItem(position);

        TextView lblTipo = convertView.findViewById(R.id.lblTipo);
        TextView lblValor = convertView.findViewById(R.id.lblValor);
        TextView lblDesconto = convertView.findViewById(R.id.lblDesconto);
        TextView lblQuantidade = convertView.findViewById(R.id.lblQuantidade);
        Button btnExcluir = convertView.findViewById(R.id.btnExcluir);

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                venda.getPecas().remove(position);

                lblQtdPorca.setText(String.format("Porcas: %d", venda.getQuantidadeByTipo(EnumTipo.PORCA)));
                lblQtdParafuso.setText(String.format("Parafusos: %d", venda.getQuantidadeByTipo(EnumTipo.PARAFUSO)));
                lblQtdArruela.setText(String.format("Arruelas: %d", venda.getQuantidadeByTipo(EnumTipo.ARRUELA)));
                lblValorPecas.setText(String.format("Valor: R$ %.2f", venda.getValor()));
                lblValorDesconto.setText(String.format("Desconto: R$ %.2f", venda.getDescontoTotal()));
                lblValorTotal.setText(String.format("Valor Total: R$ %.2f", venda.getValor() - venda.getDescontoTotal()));
                notifyDataSetChanged();
            }
        });

        lblTipo.setText(peca.getTipo().toString());
        lblValor.setText(String.format("R$ %.2f", peca.getValor()));
        lblDesconto.setText(String.format("- %d%%", peca.getTipo().getDesconto()));
        lblQuantidade.setText(String.format("%d UN", peca.getQuantidade()));

        return convertView;
    }
}
