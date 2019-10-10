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

import androidx.fragment.app.Fragment;

import com.isa.silva.idizimo.Activity.CadastroActivity;
import com.isa.silva.idizimo.Activity.HomeActivity;
import com.isa.silva.idizimo.R;
import com.isa.silva.idizimo.Utils.Util;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import cieloecommerce.sdk.Merchant;
import cieloecommerce.sdk.ecommerce.CieloEcommerce;
import cieloecommerce.sdk.ecommerce.Customer;
import cieloecommerce.sdk.ecommerce.Environment;
import cieloecommerce.sdk.ecommerce.Payment;
import cieloecommerce.sdk.ecommerce.Sale;
import cieloecommerce.sdk.ecommerce.request.CieloError;
import cieloecommerce.sdk.ecommerce.request.CieloRequestException;

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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contribuicoes, container, false);

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
                if(spn_funcao.getSelectedItem() == "Debito") {
                    if (!edt_doacao.getText().toString().isEmpty()) {
                        if (Integer.parseInt(edt_doacao.getText().toString()) >= 10) {
                            if (!edt_cartao_nome.getText().toString().isEmpty()
                                    && !edt_cartao_numero.getText().toString().isEmpty()
                                    && !edt_cartao_codigo.getText().toString().isEmpty()
                                    && !edt_data_cartao.getText().toString().isEmpty()
                            ) {
                                paymentDebit(edt_cartao_numero.getText().toString(), edt_cartao_codigo.getText().toString(), edt_cartao_nome.getText().toString(), edt_data_cartao.getText().toString(), spn_bandeira_cartao.toString(), Integer.parseInt(edt_doacao.getText().toString()));
                            } else {
                                Toast.makeText(getContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getContext(), "Contribuição não deve ser menor que 20 Reais", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                    }
                }else {

                    if (!edt_doacao.getText().toString().isEmpty()) {
                        if (Integer.parseInt(edt_doacao.getText().toString()) > 20) {
                            if (!edt_cartao_nome.getText().toString().isEmpty()
                                    && !edt_cartao_numero.getText().toString().isEmpty()
                                    && !edt_cartao_codigo.getText().toString().isEmpty()
                                    && !edt_data_cartao.getText().toString().isEmpty()
                            ) {
                                paymentCredit(edt_cartao_numero.getText().toString(), edt_cartao_codigo.getText().toString(), edt_cartao_nome.getText().toString(), edt_data_cartao.getText().toString(), spn_bandeira_cartao.toString(), Integer.parseInt(edt_doacao.getText().toString()));
                            } else {
                                Toast.makeText(getContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getContext(), "Contribuição não deve ser menor que 20 Reais", Toast.LENGTH_SHORT).show();
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

// Crie uma instância de Sale informando o ID do pagamento
        Sale sale = new Sale("ID do pagamento");

// Crie uma instância de Customer informando o nome do cliente
        Customer customer = sale.customer("Comprador Teste");

// Crie uma instância de Payment informando o valor do pagamento
        Payment payment = sale.payment(Valor);

// Crie  uma instância de Credit Card utilizando os dados de teste
// esses dados estão disponíveis no manual de integração
        payment.creditCard(cCartao, brandCartao).setExpirationDate(dCartao)
                .setCardNumber(nCartao)
                .setHolder(nomeCartao);

// Crie o pagamento na Cielo
        try {
            // Configure o SDK com seu merchant e o ambiente apropriado para criar a venda
            sale = new CieloEcommerce(merchant, Environment.SANDBOX).createSale(sale);

            // Com a venda criada na Cielo, já temos o ID do pagamento, TID e demais
            // dados retornados pela Cielo
            String paymentId = sale.getPayment().getPaymentId();

            // Com o ID do pagamento, podemos fazer sua captura, se ela não tiver sido capturada ainda
            new CieloEcommerce(merchant, Environment.SANDBOX).captureSale(paymentId, 15700, 0);

            // E também podemos fazer seu cancelamento, se for o caso
            //sale = new CieloEcommerce(merchant, Environment.SANDBOX).cancelSale(paymentId, 15700);
        } catch (InterruptedException e) {
            // Como se trata de uma AsyncTask, será preciso tratar ExecutionException e InterruptedException
            e.printStackTrace();
        } catch (CieloRequestException e) {
            // Em caso de erros de integração, podemos tratar o erro aqui.
            // os códigos de erro estão todos disponíveis no manual de integração.
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

// Crie uma instância de Sale informando o ID do pagamento
        Sale sale = new Sale("ID do pagamento");

// Crie uma instância de Customer informando o nome do cliente
        Customer customer = sale.customer("Comprador Teste");

// Crie uma instância de Payment informando o valor do pagamento
        Payment payment = sale.payment(Valor);

// Crie  uma instância de Credit Card utilizando os dados de teste
// esses dados estão disponíveis no manual de integração
        payment.debitCard(cCartao, brandCartao).setExpirationDate(dCartao)
                .setCardNumber(nCartao)
                .setHolder(nomeCartao);

// Crie o pagamento na Cielo
        try {
            // Configure o SDK com seu merchant e o ambiente apropriado para criar a venda
            sale = new CieloEcommerce(merchant, Environment.SANDBOX).createSale(sale);

            // Com a venda criada na Cielo, já temos o ID do pagamento, TID e demais
            // dados retornados pela Cielo
            String paymentId = sale.getPayment().getPaymentId();

            // Com o ID do pagamento, podemos fazer sua captura, se ela não tiver sido capturada ainda
            new CieloEcommerce(merchant, Environment.SANDBOX).captureSale(paymentId, 15700, 0);

            // E também podemos fazer seu cancelamento, se for o caso
            //sale = new CieloEcommerce(merchant, Environment.SANDBOX).cancelSale(paymentId, 15700);
        } catch (InterruptedException e) {
            // Como se trata de uma AsyncTask, será preciso tratar ExecutionException e InterruptedException
            e.printStackTrace();
        } catch (CieloRequestException e) {
            // Em caso de erros de integração, podemos tratar o erro aqui.
            // os códigos de erro estão todos disponíveis no manual de integração.
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

          final String[] bandeiras = new String[] { "Visa",
                "Master Card", "American Express", "Elo", "Diners Club", "Discover", "JCB", "Aura", "Hipercard", "Hiper" };


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
}
