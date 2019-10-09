package com.isa.silva.idizimo.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.isa.silva.idizimo.R;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private TextView txt_nome;
    private TextView txt_data;
    private TextView txt_mural;
    private TextView txt_curtir;
    private TextView txt_comentar;
    private ImageView img_comentar;
    private ImageView img_curtir;
    private ImageView img_mural;


    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);

        }
    }

    public HomeAdapter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_home, parent, false);

        txt_comentar = itemView.findViewById(R.id.txt_comentar);
        txt_nome = itemView.findViewById(R.id.txt_nome);
        txt_data = itemView.findViewById(R.id.txt_data);
        txt_mural = itemView.findViewById(R.id.txt_mural);
        txt_curtir = itemView.findViewById(R.id.txt_curtir);
        img_comentar = itemView.findViewById(R.id.img_comentar);
        img_curtir = itemView.findViewById(R.id.img_curtir);
        img_mural = itemView.findViewById(R.id.img_mural);




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