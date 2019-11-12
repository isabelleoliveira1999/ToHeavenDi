package com.isa.silva.idizimo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.isa.silva.idizimo.Model.Home;
import com.isa.silva.idizimo.Model.Igrejas;
import com.isa.silva.idizimo.Model.Oracoes;
import com.isa.silva.idizimo.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class OracoesAdapter extends RecyclerView.Adapter<OracoesAdapter.ViewHolder> {

    private TextView txt_oracao;
    private TextView txt_data;
    private Context mContext;
    private List<Oracoes> oracoesPost = new ArrayList<Oracoes>();

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);

        }
    }

    public OracoesAdapter(Context context, List<Oracoes> igrejaPost) {
        mContext = context;
        oracoesPost= igrejaPost;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_oracoes, parent, false);

        txt_oracao = itemView.findViewById(R.id.txt_oracao);
        txt_data = itemView.findViewById(R.id.txt_data);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.setIsRecyclable(false);

        final Oracoes post = oracoesPost.get(position);
        txt_oracao.setText(post.getMensagem());
        txt_data.setText("Orações do Dia " +post.getData());
    }

    @Override
    public int getItemCount() {
        return oracoesPost.size();
    }
}