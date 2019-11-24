package com.isa.silva.idizimo.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.isa.silva.idizimo.Activity.CadastroActivity;
import com.isa.silva.idizimo.Activity.HomeActivity;
import com.isa.silva.idizimo.Model.Contribuicoes;
import com.isa.silva.idizimo.R;
import com.isa.silva.idizimo.Utils.Util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import cieloecommerce.sdk.Merchant;
import cieloecommerce.sdk.ecommerce.CieloEcommerce;
import cieloecommerce.sdk.ecommerce.Customer;
import cieloecommerce.sdk.ecommerce.Environment;
import cieloecommerce.sdk.ecommerce.Payment;
import cieloecommerce.sdk.ecommerce.Sale;
import cieloecommerce.sdk.ecommerce.request.CieloError;
import cieloecommerce.sdk.ecommerce.request.CieloRequestException;
import cieloecommerce.sdk.ecommerce.request.UpdateSaleResponse;

import static com.isa.silva.idizimo.Utils.Constants.MerchantId;
import static com.isa.silva.idizimo.Utils.Constants.MerchantKey;

public class ContribuicoesFragment extends Fragment {
    private Button btn_contribuir;
    private Button btn_historico;
    private EditText edt_cartao_nome;
    private EditText edt_cartao_numero;
    private EditText edt_cartao_codigo;
    private EditText edt_data_cartao;
    private Spinner spn_bandeira_cartao;
    private Spinner spn_funcao;
    private EditText edt_doacao;
    private String paymentId;
    private int paymentValor;
    private FirebaseAuth firebase = new FirebaseAuth(FirebaseApp.getInstance());
    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contribuicoes, container, false);
        Bundle bundle = getArguments();
        if(bundle != null) {
            paymentId = bundle.getString("paymentId");
            paymentValor = bundle.getInt("valor");
            Merchant merchant = new Merchant(MerchantId, MerchantKey);
            try {
                Sale resul = new CieloEcommerce(merchant, Environment.SANDBOX).querySale(paymentId);
                if(resul.getPayment().getReturnMessage() == "Não autenticada"){
                    Util.showMessage(getContext(), "Ops", "Você precisa autenticar para que sua contribuição seja efetuada com sucesso. \n Tente novamente!!");
                }else {
                    Calendar data = Calendar.getInstance();
                    writeNewContribuicoes(firebase.getUid(), paymentValor, "", "Debito", data.toString(), "");
                    Util.showMessage(getContext(), "Parabéns", "Sua contribuição no valor de R$" + paymentValor +",00 foi efetuada com sucesso!!");
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (CieloRequestException e) {
                e.printStackTrace();
            }

        }
        btn_contribuir = view.findViewById(R.id.btn_contribuir);
        btn_historico = view.findViewById(R.id.btn_historico);
        edt_cartao_nome = view.findViewById(R.id.cartao_nome);
        edt_cartao_numero = view.findViewById(R.id.cartao_numero);
        edt_cartao_codigo = view.findViewById(R.id.cartao_codigo);
        edt_data_cartao = view.findViewById(R.id.data_cartao);
        spn_bandeira_cartao = view.findViewById(R.id.cartao_bandeira);
        spn_funcao = view.findViewById(R.id.cartao_funcao);
        edt_doacao =view.findViewById(R.id.valor);

        edt_cartao_numero.addTextChangedListener(Util.mask(edt_cartao_numero, Util.FORMAT_CARD));
        edt_data_cartao.addTextChangedListener(Util.mask(edt_data_cartao, Util.FORMAT_DATE));
        Spinner();
        SpinnerFuncao();
        btn_contribuir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Util.esconderTeclado(getContext(), btn_contribuir);
                if(spn_funcao.getSelectedItem() == "Debito") {
                    if (!edt_doacao.getText().toString().isEmpty()) {
                        if (Integer.parseInt(edt_doacao.getText().toString()) >= 10) {
                            if (!edt_cartao_nome.getText().toString().isEmpty()
                                    && !edt_cartao_numero.getText().toString().isEmpty()
                                    && !edt_cartao_codigo.getText().toString().isEmpty()
                                    && !edt_data_cartao.getText().toString().isEmpty()
                                    && edt_cartao_numero.getText().toString().length()-3 == 16
                                    && edt_cartao_codigo.getText().toString().length() == 3
                                    && edt_data_cartao.getText().toString().length()-1 == 6
                            ) {
                                paymentDebit(edt_cartao_numero.getText().toString(), edt_cartao_codigo.getText().toString(), edt_cartao_nome.getText().toString(), edt_data_cartao.getText().toString(), spn_bandeira_cartao.getSelectedItem().toString(), Integer.parseInt(edt_doacao.getText().toString()));
                            } else {
                                Log.e("teste", String.valueOf(edt_cartao_numero.getText().toString().length()));
                                Toast.makeText(getContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getContext(), "Contribuição não deve ser menor que 10 Reais", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                    }
                }else {

                    if (!edt_doacao.getText().toString().isEmpty()) {
                        if (Integer.parseInt(edt_doacao.getText().toString()) >= 10) {
                            if (!edt_cartao_nome.getText().toString().isEmpty()
                                    && !edt_cartao_numero.getText().toString().isEmpty()
                                    && !edt_cartao_codigo.getText().toString().isEmpty()
                                    && !edt_data_cartao.getText().toString().isEmpty()
                            ) {
                                paymentCredit(edt_cartao_numero.getText().toString(), edt_cartao_codigo.getText().toString(), edt_cartao_nome.getText().toString(), edt_data_cartao.getText().toString(), spn_bandeira_cartao.getSelectedItem().toString(), Integer.parseInt(edt_doacao.getText().toString()));
                            } else {
                                Toast.makeText(getContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getContext(), "Contribuição não deve ser menor que 10 Reais", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btn_historico.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("contribuir")
                        .replace(R.id.container, new ContribuicoesHistoricoFragment())
                        .commit();
            }
        });

        return view;
    }

    public void paymentCredit(String nCartao, String cCartao, String nomeCartao,String dCartao, String brandCartao, int Valor) {

        Merchant merchant = new Merchant(MerchantId, MerchantKey);

        Sale sale = new Sale(nCartao+"iDizimo"+dCartao);
        Customer customer = new Customer(nomeCartao);
        Payment payment = sale.payment(Valor);

        payment.setAuthenticate(false);
        payment.creditCard(cCartao, brandCartao).setExpirationDate(dCartao)
                .setCardNumber(nCartao)
                .setHolder(nomeCartao);


        try {
            sale = new CieloEcommerce(merchant, Environment.SANDBOX).createSale(sale).setCustomer(customer);

            String paymentId = sale.getPayment().getPaymentId();

            UpdateSaleResponse resul = new CieloEcommerce(merchant, Environment.SANDBOX).captureSale(paymentId, Valor);

            String resultado = resul.getReturnMessage();
            if(resultado.equals("Operation Successful")){
                Util.showMessage(getContext(), "Parabéns", "Sua contribuição no valor de R$" + Valor +",00 foi efetuada com sucesso!!");


                Calendar data = Calendar.getInstance();


                writeNewContribuicoes(firebase.getUid(), Valor, nCartao.split(".")[3], "Credito", data.toString(),brandCartao);

                ContribuicoesFragment debito = new ContribuicoesFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, debito )
                        .commit();
            }else {
                Util.showMessage(getContext(),"Aviso", resultado);
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (CieloRequestException e) {
            CieloError error = e.getError();

            Log.v("Cielo", error.getCode().toString());
            Log.v("Cielo", error.getMessage());

            if (error.getCode() != 404) {
                Log.e("Cielo", null, e);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void paymentDebit(String nCartao, String cCartao, String nomeCartao,String dCartao, String brandCartao, int Valor) {

        Merchant merchant = new Merchant(MerchantId, MerchantKey);

        Sale sale = new Sale(nCartao+"iDizimo"+dCartao);
        Customer customer = new Customer(nomeCartao);

        Payment payment = sale.payment(Valor);
        payment.setAuthenticate(true);
        payment.debitCard(cCartao, brandCartao).setExpirationDate(dCartao)
                .setCardNumber(nCartao)
                .setHolder(nomeCartao);
        try {

            sale = new CieloEcommerce(merchant, Environment.SANDBOX).createSale(sale).setCustomer(customer);


            String paymentId = sale.getPayment().getPaymentId();

            String message = sale.getPayment().getReturnMessage();

            if(sale.getPayment().getAuthenticationUrl() != null){

                DebitoFragment debito = new DebitoFragment();
                Bundle bundle = new Bundle();

                bundle.putString("urlAul", sale.getPayment().getAuthenticationUrl());
                bundle.putString("paymentId", paymentId);
                bundle.putInt("valor", Valor);
                debito.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("contribuir")
                        .replace(R.id.container, debito )
                        .commit();

            }else{
                Util.showMessage(getContext(), "Aviso", message);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (CieloRequestException e) {
            CieloError error = e.getError();

            Log.v("Cielo", error.getCode().toString());
            Log.v("Cielo", error.getMessage());

            if (error.getCode() != 404) {
                Log.e("Cielo", null, e);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void Spinner(){

          final String[] bandeiras = new String[] {
                  "Visa", "Master", "Amex", " Elo","Aura", "JCB", "Diners", "Discover",
                  "Hipercard ", "Hiper"
          };


        ArrayAdapter<String> a =new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, bandeiras);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_bandeira_cartao.setAdapter(a);

    }
    public void SpinnerFuncao(){

        final String[] funcao = new String[] {
                 "Debito", "Credito" };


        ArrayAdapter<String> a =new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, funcao);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_funcao.setAdapter(a);

    }


    private void writeNewContribuicoes(String userId, int valor, String finalcartao, String forma, String data, String banderiacartao) {
         Contribuicoes contribuicoes = new Contribuicoes(userId, valor, finalcartao,  forma, data, banderiacartao);

        // Write a message to the database
        mDatabase = FirebaseDatabase.getInstance().getReference("Contribuicoes");

        mDatabase.child(userId).child(valor+finalcartao+data).setValue(contribuicoes).addOnSuccessListener(
                getActivity(), new OnSuccessListener(){
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(getActivity(), "Salvos com sucesso", Toast.LENGTH_SHORT).show();

                    }
                }
        ).addOnFailureListener(
                getActivity(), new OnFailureListener() {

                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
        );
    }
}
