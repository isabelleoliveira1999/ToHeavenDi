package com.isa.silva.idizimo.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.isa.silva.idizimo.Adapter.OracoesAdapter;
import com.isa.silva.idizimo.R;

public class OracoesFragment extends Fragment {

    private RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_oracoes, container, false);


        recyclerView = view.findViewById(R.id.rv_oracoes);

        OracoesAdapter oracoesAdapter = new OracoesAdapter();

        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(oracoesAdapter);

        return view;

    }
}
