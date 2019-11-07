package com.isa.silva.idizimo.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.isa.silva.idizimo.Adapter.HomeAdapter;
import com.isa.silva.idizimo.Adapter.IgrejaAdapter;
import com.isa.silva.idizimo.Model.Igrejas;
import com.isa.silva.idizimo.R;

import java.util.ArrayList;
import java.util.List;

public class IgrejasFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<Igrejas> igrejaPost = new ArrayList<Igrejas>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_igrejas, container, false);
        recyclerView = view.findViewById(R.id.rv_mural);










        DatabaseReference dbPosts = FirebaseDatabase.getInstance().getReference("IgrejaPosts");

        dbPosts.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Igrejas c = postSnapshot.getValue(Igrejas.class);
                        Log.i("URL FOTO", c.getUrl());
                        igrejaPost.add(c);
                    }
                    IgrejaAdapter muralAdapter = new IgrejaAdapter(getContext(), igrejaPost);
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
