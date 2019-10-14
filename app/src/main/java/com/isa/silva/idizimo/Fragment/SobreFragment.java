package com.isa.silva.idizimo.Fragment;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.isa.silva.idizimo.Activity.CadastroActivity;
import com.isa.silva.idizimo.Adapter.IgrejaAdapter;
import com.isa.silva.idizimo.R;

public class SobreFragment extends Fragment {
    private TextView txt_version;
    private TextView txt_politicas;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sobre, container, false);

        txt_version = view.findViewById(R.id.txt_version);
        txt_politicas = view.findViewById(R.id.txt_termos);
        try {
            txt_version.setText("Versão: " + getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0).versionName + "  Build: " + getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0).versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        SpannableString ss = new SpannableString("Termos e politicas de uso");

        ss.setSpan(new CustomClickableSpan(), 0, 25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        txt_politicas.setText(ss);
        txt_politicas.setMovementMethod(LinkMovementMethod.getInstance());
        return view;

    }
    class CustomClickableSpan extends ClickableSpan {
        @Override
        public void onClick(View textView) {
            DialogFragment pop = new DialogFragment();
            pop.show(getFragmentManager(), "Sobre");
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(Color.BLUE);//cor do texto
            ds.setUnderlineText(true); //remove sublinhado
        }
    }
}
