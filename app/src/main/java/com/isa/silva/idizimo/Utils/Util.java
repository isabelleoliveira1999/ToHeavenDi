package com.isa.silva.idizimo.Utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class Util {

    public static void showMessage(Context context, String title, String message) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(context);
        dlgAlert.setMessage(message);
        dlgAlert.setTitle(title);
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(false);
        dlgAlert.create().show();
    }


    public static void esconderTeclado(Context context, View view){
        ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                view.getWindowToken(), 0);
    }
    public static ProgressDialog createAndShowProgressDialog(Context mContext) {
        ProgressDialog pd;
        pd = new ProgressDialog(mContext);
        pd.setMessage("Aguarde...");
        pd.setCancelable(false);
        pd.setIndeterminate(true);
        pd.show();
        return pd;
    }

    public static void dismissProgressDialog(ProgressDialog pd) {
        if (pd != null && pd.isShowing()) {
            try {
                pd.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

        public static final String FORMAT_CPF = "###.###.###-##";
        public static final String FORMAT_FONE = "(###)####-#####";
        public static final String FORMAT_CEP = "#####-###";
        public static final String FORMAT_DATE = "##/####";
        public static final String FORMAT_HOUR = "##:##";
        public static final String FORMAT_CARD = "####.####.####.####";

        /**
         * Método que deve ser chamado para realizar a formatação
         *
         * @param ediTxt
         * @param mask
         * @return
         */
        public static TextWatcher mask(final EditText ediTxt, final String mask) {
            return new TextWatcher() {
                boolean isUpdating;
                String old = "";

                @Override
                public void afterTextChanged(final Editable s) {}

                @Override
                public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {}

                @Override
                public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                    final String str = unmask(s.toString());
                    String mascara = "";
                    if (isUpdating) {
                        old = str;
                        isUpdating = false;
                        return;
                    }
                    int i = 0;
                    for (final char m : mask.toCharArray()) {
                        if (m != '#' && str.length() > old.length()) {
                            mascara += m;
                            continue;
                        }
                        try {
                            mascara += str.charAt(i);
                        } catch (final Exception e) {
                            break;
                        }
                        i++;
                    }
                    isUpdating = true;
                    ediTxt.setText(mascara);
                    ediTxt.setSelection(mascara.length());
                }
            };
        }

        public static String unmask(final String s) {
            return s.replaceAll("[.]", "").replaceAll("[-]", "").replaceAll("[/]", "").replaceAll("[(]", "").replaceAll("[ ]","").replaceAll("[:]", "").replaceAll("[)]", "");
        }


}
