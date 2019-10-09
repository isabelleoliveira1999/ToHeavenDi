package com.isa.silva.idizimo.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.isa.silva.idizimo.R;
import com.joanzapata.pdfview.PDFView;

public class BibliaFragment extends Fragment {
    private WebView pdfView;
    private Button btn_biblia;
    private Button btn_bible;
    private CardView buttons;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_biblia, container, false);


        pdfView = view.findViewById(R.id.pdfview);
        btn_bible = view.findViewById(R.id.btn_bible);
        btn_biblia = view.findViewById(R.id.btn_biblia);
        buttons= view.findViewById(R.id.buttons);



        btn_biblia.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                WebSettings ws = pdfView.getSettings();
                ws.setJavaScriptEnabled(true);
                ws.setSupportZoom(false);
                ws.setAllowFileAccess(true);
                String pdf = "https://drive.google.com/open?id=1kcbPrv6D08nLOP8ZgouiETBP0WA9adbv";
                pdfView.loadUrl( pdf);
                pdfView.setWebViewClient(new WebViewClient());
                buttons.setVisibility(View.GONE);
                pdfView.setVisibility(View.VISIBLE);
            }
        });
        btn_bible.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                WebSettings ws = pdfView.getSettings();
                ws.setJavaScriptEnabled(true);
                ws.setSupportZoom(false);
                ws.setAllowFileAccess(true);
                String pdf = "https://drive.google.com/open?id=1lUNNurvDI9gN6lIRbrJIQiPWOB0MYDuC";
                pdfView.loadUrl( pdf);
                pdfView.setWebViewClient(new WebViewClient());
                buttons.setVisibility(View.GONE);
                pdfView.setVisibility(View.VISIBLE);
            }
        });
        return view;

    }
}
