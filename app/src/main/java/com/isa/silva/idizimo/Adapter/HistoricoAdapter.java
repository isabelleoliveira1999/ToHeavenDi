package com.isa.silva.idizimo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.isa.silva.idizimo.Model.Contribuicoes;
import com.isa.silva.idizimo.R;

import java.util.ArrayList;
import java.util.List;

public class HistoricoAdapter extends RecyclerView.Adapter<HistoricoAdapter.ViewHolder> {

    private TextView txt_valor;
    private TextView txt_data;
    private TextView txt_card;
    private Context mContext;
    private List<Contribuicoes> historico = new ArrayList<Contribuicoes>();

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);

        }
    }

    public HistoricoAdapter(Context context, List<Contribuicoes> historico) {
        mContext = context;
        this.historico = historico;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_historico, parent, false);

        txt_valor = itemView.findViewById(R.id.txt_valor);
        txt_data = itemView.findViewById(R.id.txt_data);
        txt_card = itemView.findViewById(R.id.txt_valor);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return historico.size();
    }
}