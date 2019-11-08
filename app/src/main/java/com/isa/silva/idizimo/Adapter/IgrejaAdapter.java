package com.isa.silva.idizimo.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.isa.silva.idizimo.Model.Igrejas;
import com.isa.silva.idizimo.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class IgrejaAdapter extends RecyclerView.Adapter<IgrejaAdapter.ViewHolder> {

    private TextView txt_nome;
    private TextView txt_data;
    private TextView txt_mural;
    private ImageView img_mural;
    private Context mContext;
    private List<Igrejas> igrejaPost = new ArrayList<Igrejas>();
public class ViewHolder extends RecyclerView.ViewHolder {

    public ViewHolder(View itemView) {
        super(itemView);

    }
}

    public IgrejaAdapter(Context context, List<Igrejas> igrejaPost) {
        mContext = context;
        this.igrejaPost = igrejaPost;
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

    holder.setIsRecyclable(false);

        final Igrejas post = igrejaPost.get(position);
        txt_nome.setText(post.getAutor());
        txt_data.setText(post.getData());
        txt_mural.setText(post.getMensagem());
        if(!post.getUrl().isEmpty()) {
            Picasso.with(mContext).load(post.getUrl())
                    .into(img_mural);
        }else {
            img_mural.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return igrejaPost.size();
    }
}