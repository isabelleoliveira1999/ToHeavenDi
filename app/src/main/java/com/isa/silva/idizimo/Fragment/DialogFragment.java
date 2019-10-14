package com.isa.silva.idizimo.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.isa.silva.idizimo.R;

public class DialogFragment extends androidx.fragment.app.DialogFragment {

    private Button btnBack;
    private Button btnAceitar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.terms_popup, container, false);

        loadLayout(view);
        return view;
    }

    private void loadLayout(View view) {

        btnBack = (Button) view.findViewById(R.id.btnBack);
        btnAceitar = (Button) view.findViewById(R.id.btnAccept);


        btnBack.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        dismiss();
                    }
                });

        btnAceitar.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        dismiss();
                    }
                });
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        // request a window without the title
        //dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }
}
