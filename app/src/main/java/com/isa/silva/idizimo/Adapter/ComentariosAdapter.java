package com.isa.silva.idizimo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.isa.silva.idizimo.R;

public class ComentariosAdapter extends RecyclerView.Adapter<ComentariosAdapter.ViewHolder> {

    private TextView txt_comentar;
    private TextView txt_nome;

    private Context mContext;


    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);

            txt_comentar = itemView.findViewById(R.id.txt_comentar);
            txt_nome = itemView.findViewById(R.id.txt_nome);


        }
    }

    public ComentariosAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_comentarios, parent, false);



        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.setIsRecyclable(true);

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}