package com.isa.silva.idizimo.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import com.isa.silva.idizimo.R;
import com.isa.silva.idizimo.Utils.Util;

public class PerfilFragment extends Fragment {

    private EditText edt_mail;
    private EditText edt_tel;
    private EditText edt_nome;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        edt_mail = view.findViewById(R.id.edt_mail);
        edt_tel = view.findViewById(R.id.edt_tel);
        edt_nome = view.findViewById(R.id.edt_user);


        edt_nome.setText("teste");
        edt_nome.setEnabled(false);
        edt_tel.addTextChangedListener(Util.mask(edt_tel, Util.FORMAT_FONE));


        return view;

    }
}
