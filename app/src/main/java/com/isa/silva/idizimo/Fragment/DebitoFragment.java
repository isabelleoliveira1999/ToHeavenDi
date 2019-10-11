package com.isa.silva.idizimo.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.isa.silva.idizimo.R;

public class DebitoFragment extends Fragment {
    private WebView pdfView;
    private Button back;
    private String url;
    private String paymentId;
    private int paymentValor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        if(bundle != null) {
            url = bundle.getString("urlAul");
            paymentId = bundle.getString("paymentId");
            paymentValor = bundle.getInt("valor");
        }
        View view = inflater.inflate(R.layout.fragment_debito, container, false);




        back = view.findViewById(R.id.btn_autenticou);
        pdfView = view.findViewById(R.id.pdfview);

                WebSettings ws = pdfView.getSettings();
                ws.setJavaScriptEnabled(true);
                ws.setSupportZoom(false);
                ws.setAllowFileAccess(true);
                pdfView.loadUrl(url);
                pdfView.setWebViewClient(new WebViewClient());




        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ContribuicoesFragment debito = new ContribuicoesFragment();
                Bundle bundles = new Bundle();
                bundles.putString("paymentId", paymentId);
                bundles.putString("paymentId", paymentId);
                bundles.putInt("valor", paymentValor);
                debito.setArguments(bundles);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, debito )
                        .commit();
            }});


        return view;

    }
}
