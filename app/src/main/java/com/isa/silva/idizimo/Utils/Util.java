package com.isa.silva.idizimo.Utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    public  static String BASE_URL = "https://idizimo-46038.firebaseio.com/";
    public  static String BASE_URL_IMAGENS ="https://firebasestorage.googleapis.com/v0/b/idizimo-46038.appspot.com/o/imagens_home%2Fimg_3.jpeg?alt=media&token=a28057fe-3799-49e4-a17d-ec819bfd37ac";
    public static void showMessage(Context context, String title, String message) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(context);
        dlgAlert.setMessage(message);
        dlgAlert.setTitle(title);
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(false);
        dlgAlert.create().show();
    }

    public static boolean isCPF(String CPF) {
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
       String cpf =  CPF.replace(".", "");
        cpf = cpf.replace("-","");
        if (cpf.equals("00000000000") ||
                cpf.equals("11111111111") ||
                cpf.equals("22222222222") || cpf.equals("33333333333") ||
                cpf.equals("44444444444") || cpf.equals("55555555555") ||
                cpf.equals("66666666666") || cpf.equals("77777777777") ||
                cpf.equals("88888888888") || cpf.equals("99999999999") ||
                (cpf.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int)(cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)))
                return(true);
            else return(false);
        } catch (Exception erro) {
            return(false);
        }
    }
    public static boolean isValidEmailAddressRegex(String email) {
        boolean isEmailIdValid = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isEmailIdValid = true;
            }
        }
        return isEmailIdValid;
    }

    public static boolean isValidSenha(String senha) {
        boolean isEmailIdValid = false;
        if (senha != null && senha.length() == 8) {
            isEmailIdValid = true;
        }
        return isEmailIdValid;
    }

    public static boolean validarTelefone(String tel) {


        if((tel == null) || (tel.length()>14))
            return false;

        else
            return true;
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
        public static final String FORMAT_FONE = "(##)#####-####";
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
