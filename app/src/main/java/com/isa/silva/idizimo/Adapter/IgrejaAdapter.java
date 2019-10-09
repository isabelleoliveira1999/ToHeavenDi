package com.isa.silva.idizimo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.isa.silva.idizimo.R;

public class IgrejaAdapter extends RecyclerView.Adapter<IgrejaAdapter.ViewHolder> {

    private TextView txt_nome;
    private TextView txt_data;
    private TextView txt_mural;
    private ImageView img_mural;
    private Context mContext;
public class ViewHolder extends RecyclerView.ViewHolder {

    public ViewHolder(View itemView) {
        super(itemView);

    }
}

    public IgrejaAdapter(Context context) {
        mContext = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_igreja, parent, false);


        txt_mural = itemView.findViewById(R.id.txt_mural);
        txt_nome = itemView.findViewById(R.id.txt_nome);
        txt_data = itemView.findViewById(R.id.txt_data);
        img_mural = itemView.findViewById(R.id.img_mural);


        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}