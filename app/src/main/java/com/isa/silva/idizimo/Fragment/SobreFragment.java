package com.isa.silva.idizimo.Fragment;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.isa.silva.idizimo.Adapter.IgrejaAdapter;
import com.isa.silva.idizimo.R;

public class SobreFragment extends Fragment {
    private TextView txt_version;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sobre, container, false);

        txt_version = view.findViewById(R.id.txt_version);
        try {
            txt_version.setText("Versão: " + getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0).versionName + "  Build: " + getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0).versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return view;

    }
}
