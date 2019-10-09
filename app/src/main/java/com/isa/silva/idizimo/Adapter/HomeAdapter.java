package com.isa.silva.idizimo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
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
    private LinearLayout lin_curtir;
    private LinearLayout lin_comentar;
    private Context mContext;


    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);

            txt_comentar = itemView.findViewById(R.id.txt_comentar);
            txt_nome = itemView.findViewById(R.id.txt_nome);
            txt_data = itemView.findViewById(R.id.txt_data);
            txt_mural = itemView.findViewById(R.id.txt_mural);
            txt_curtir = itemView.findViewById(R.id.txt_curtir);
            img_comentar = itemView.findViewById(R.id.img_comentar);
            img_curtir = itemView.findViewById(R.id.img_curtir);
            img_mural = itemView.findViewById(R.id.img_mural);
            lin_comentar = itemView.findViewById(R.id.lin_comentar);
            lin_curtir = itemView.findViewById(R.id.lin_comentar);
        }
    }

    public HomeAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_home, parent, false);



        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.setIsRecyclable(false);

        txt_curtir.setTag(position);
        txt_curtir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                img_curtir.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_curtiu));
                txt_curtir.setText("Curtiu");
                txt_curtir.setTextColor(mContext.getResources().getColor(R.color.primary_dark));
            }
        });
        img_curtir.setTag(position);
        img_curtir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                img_curtir.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_curtiu));
                txt_curtir.setText("Curtiu");
                txt_curtir.setTextColor(mContext.getResources().getColor(R.color.primary_dark));
            }
        });
        lin_curtir.setTag(position);
        lin_curtir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                img_curtir.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_curtiu));
                txt_curtir.setText("Curtiu");
                txt_curtir.setTextColor(mContext.getResources().getColor(R.color.primary_dark));
            }
        });
        txt_comentar.setTag(position);
        txt_comentar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(mContext);
                mBottomSheetDialog.setContentView(R.layout.bottom_sheet_comentar);
                RecyclerView comentariosRv = mBottomSheetDialog.findViewById(R.id.rv_comentarios);
                ComentariosAdapter muralAdapter = new ComentariosAdapter(mContext);

                RecyclerView.LayoutManager layoutManager;
                layoutManager = new LinearLayoutManager(mContext);
                comentariosRv.setLayoutManager(layoutManager);
                comentariosRv.setItemAnimator(new DefaultItemAnimator());
                comentariosRv.setAdapter(muralAdapter);

                final EditText comentarioEdit = mBottomSheetDialog.findViewById(R.id.edt_comentario);
                ImageView SendImg = mBottomSheetDialog.findViewById(R.id.img_comentar);
                SendImg.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if (comentarioEdit.getText().toString().isEmpty()){
                            Toast.makeText(mContext,"Comentario não pode ser vazio", Toast.LENGTH_SHORT).show();
                        }else{
                            comentarioEdit.getText().toString();
                        }
                    }
                });

            }
        });
        img_comentar.setTag(position);
        img_comentar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(mContext);
                mBottomSheetDialog.setContentView(R.layout.bottom_sheet_comentar);
                mBottomSheetDialog.show();
                RecyclerView comentariosRv = mBottomSheetDialog.findViewById(R.id.rv_comentarios);
                ComentariosAdapter muralAdapter = new ComentariosAdapter(mContext);

                RecyclerView.LayoutManager layoutManager;
                layoutManager = new LinearLayoutManager(mContext);
                comentariosRv.setLayoutManager(layoutManager);
                comentariosRv.setItemAnimator(new DefaultItemAnimator());
                comentariosRv.setAdapter(muralAdapter);

                final EditText comentarioEdit = mBottomSheetDialog.findViewById(R.id.edt_comentario);
                ImageView SendImg = mBottomSheetDialog.findViewById(R.id.img_comentar);
                SendImg.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if (comentarioEdit.getText().toString().isEmpty()){
                            Toast.makeText(mContext,"Comentario não pode ser vazio", Toast.LENGTH_SHORT).show();
                        }else{
                            comentarioEdit.getText().toString();
                        }
                    }
                });
            }
        });
        lin_comentar.setTag(position);
        lin_comentar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(mContext);
                mBottomSheetDialog.setContentView(R.layout.bottom_sheet_comentar);

                RecyclerView comentariosRv = mBottomSheetDialog.findViewById(R.id.rv_comentarios);
                ComentariosAdapter muralAdapter = new ComentariosAdapter(mContext);

                RecyclerView.LayoutManager layoutManager;
                layoutManager = new LinearLayoutManager(mContext);
                comentariosRv.setLayoutManager(layoutManager);
                comentariosRv.setItemAnimator(new DefaultItemAnimator());
                comentariosRv.setAdapter(muralAdapter);

                final EditText comentarioEdit = mBottomSheetDialog.findViewById(R.id.edt_comentario);
                ImageView SendImg = mBottomSheetDialog.findViewById(R.id.img_comentar);
                SendImg.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                      if (comentarioEdit.getText().toString().isEmpty()){
                          Toast.makeText(mContext,"Comentario não pode ser vazio", Toast.LENGTH_SHORT).show();
                      }else{
                          comentarioEdit.getText().toString();
                      }
                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}