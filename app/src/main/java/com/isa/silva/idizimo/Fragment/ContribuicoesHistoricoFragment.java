package com.isa.silva.idizimo.Fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.isa.silva.idizimo.Adapter.HistoricoAdapter;
import com.isa.silva.idizimo.Adapter.OracoesAdapter;
import com.isa.silva.idizimo.Model.Contribuicoes;
import com.isa.silva.idizimo.Model.Oracoes;
import com.isa.silva.idizimo.R;
import com.isa.silva.idizimo.Utils.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import cieloecommerce.sdk.Merchant;
import cieloecommerce.sdk.ecommerce.CieloEcommerce;
import cieloecommerce.sdk.ecommerce.Customer;
import cieloecommerce.sdk.ecommerce.Environment;
import cieloecommerce.sdk.ecommerce.Payment;
import cieloecommerce.sdk.ecommerce.Sale;
import cieloecommerce.sdk.ecommerce.request.CieloError;
import cieloecommerce.sdk.ecommerce.request.CieloRequestException;

import static com.isa.silva.idizimo.Utils.Constants.MerchantId;
import static com.isa.silva.idizimo.Utils.Constants.MerchantKey;

public class ContribuicoesHistoricoFragment extends Fragment {
    private Button btn_contribuir;
    private RecyclerView rv_historico;
    private List<Contribuicoes> historico = new ArrayList<Contribuicoes>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_historico_contribuicoes, container, false);

        btn_contribuir = view.findViewById(R.id.btn_contribuir);

        rv_historico = view.findViewById(R.id.rv_contribuicoes);


        DatabaseReference dbPosts = FirebaseDatabase.getInstance().getReference("Contribuicoes");
        historico.clear();
        dbPosts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    final Contribuicoes c = postSnapshot.getValue(Contribuicoes.class);
                    historico.add(c);

                }

                HistoricoAdapter historicoAdapter = new HistoricoAdapter(getContext(), historico);

                RecyclerView.LayoutManager layoutManager;
                layoutManager = new LinearLayoutManager(getContext());
                rv_historico.setLayoutManager(layoutManager);
                rv_historico.setItemAnimator(new DefaultItemAnimator());
                rv_historico.setAdapter(historicoAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        btn_contribuir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
        }});

        return view;
    }

}
