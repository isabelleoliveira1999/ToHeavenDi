package com.isa.silva.idizimo.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.isa.silva.idizimo.R;

public class OracoesAdapter extends RecyclerView.Adapter<OracoesAdapter.ViewHolder> {


public class ViewHolder extends RecyclerView.ViewHolder {

    public ViewHolder(View itemView) {
        super(itemView);

    }
}

    public OracoesAdapter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_oracoes, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}