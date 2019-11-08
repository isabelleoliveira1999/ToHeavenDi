package com.isa.silva.idizimo.Fragment;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.isa.silva.idizimo.Adapter.IgrejaAdapter;
import com.isa.silva.idizimo.Adapter.OracoesAdapter;
import com.isa.silva.idizimo.Model.Igrejas;
import com.isa.silva.idizimo.Model.Oracoes;
import com.isa.silva.idizimo.R;

import java.util.ArrayList;
import java.util.List;

public class OracoesFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Oracoes> oracoesPost = new ArrayList<Oracoes>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_oracoes, container, false);


        recyclerView = view.findViewById(R.id.rv_oracoes);

        DatabaseReference dbPosts = FirebaseDatabase.getInstance().getReference("OracoesPosts");
        oracoesPost.clear();
        dbPosts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    final Oracoes c = postSnapshot.getValue(Oracoes.class);
                        oracoesPost.add(c);

                }
                OracoesAdapter muralAdapter = new OracoesAdapter(getContext(), oracoesPost);
                RecyclerView.LayoutManager layoutManager;
                layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(muralAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;

    }
}
