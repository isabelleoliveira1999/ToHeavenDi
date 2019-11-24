package com.isa.silva.idizimo.Fragment;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.isa.silva.idizimo.Adapter.HistoricoAdapter;
import com.isa.silva.idizimo.Adapter.HomeAdapter;
import com.isa.silva.idizimo.Adapter.OracoesAdapter;
import com.isa.silva.idizimo.Model.Contribuicoes;
import com.isa.silva.idizimo.Model.Home;
import com.isa.silva.idizimo.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<Home> home = new ArrayList<Home>();
    private ProgressDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.rv_mural);
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Aguarde...");
        dialog.show();
        DatabaseReference dbPosts = FirebaseDatabase.getInstance().getReference("Home");
        home.clear();
        dbPosts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    final Home c = postSnapshot.getValue(Home.class);

                        home.add(c);

                }
                HomeAdapter muralAdapter = new HomeAdapter(getContext(), home);
                RecyclerView.LayoutManager layoutManager;
                layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(muralAdapter);
                dialog.hide();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return view;

    }
}
