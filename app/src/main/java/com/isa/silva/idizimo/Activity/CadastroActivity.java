package com.isa.silva.idizimo.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.isa.silva.idizimo.Fragment.DialogFragment;
import com.isa.silva.idizimo.R;
import com.isa.silva.idizimo.Utils.Util;


public class CadastroActivity extends AppCompatActivity {

    private Button btn_login;
    private Button btn_voltar;
    private EditText edt_cpf;
    private EditText edt_user;
    private EditText edt_mail;
    private EditText edt_tel;
    private EditText edt_password;
    private EditText edt_password_con;
    private CheckBox check_politicas;
    private TextView txt_politicas;
    private FirebaseAuth firebase = new FirebaseAuth(FirebaseApp.getInstance());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        btn_login = findViewById(R.id.btn_contribuir);
        btn_voltar = findViewById(R.id.btn_voltar);
        edt_cpf = findViewById(R.id.edt_cpf);
        edt_user = findViewById(R.id.edt_user);
        edt_mail = findViewById(R.id.edt_mail);
        edt_tel = findViewById(R.id.edt_tel);
        edt_password = findViewById(R.id.edt_password);
        edt_password_con = findViewById(R.id.edt_password_conf);
        check_politicas = findViewById(R.id.check_politicas);
        txt_politicas = findViewById(R.id.txt_politicas);

        edt_tel.addTextChangedListener(Util.mask(edt_tel, Util.FORMAT_FONE));
        edt_cpf.addTextChangedListener(Util.mask(edt_cpf, Util.FORMAT_CPF));

        SpannableString ss = new SpannableString("Eu aceito os termos e politicas de uso");

        ss.setSpan(new CustomClickableSpan(), 13, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        txt_politicas.setText(ss);
        txt_politicas.setMovementMethod(LinkMovementMethod.getInstance());
        btn_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!edt_cpf.getText().toString().isEmpty()
                        && !edt_user.getText().toString().isEmpty()
                        && !edt_mail.getText().toString().isEmpty()
                        && !edt_tel.getText().toString().isEmpty()
                        && !edt_password.getText().toString().isEmpty()
                        && !edt_password_con.getText().toString().isEmpty()) {
                    if (!check_politicas.isChecked()){
                        Toast.makeText(getApplicationContext(), getString(R.string.validacaoPoliticas), Toast.LENGTH_SHORT).show();
                    }else {
                        if (edt_password.getText().toString().equals(edt_password_con.getText().toString())) {

                            if(Util.isCPF(edt_cpf.getText().toString())) {
                                if (Util.validarTelefone(edt_tel.getText().toString())) {
                                    if (Util.isValidEmailAddressRegex(edt_mail.getText().toString())) {
                                        if (Util.isValidSenha(edt_password.getText().toString())) {

                                            Task<AuthResult> fire =  firebase.createUserWithEmailAndPassword(edt_mail.getText().toString(), edt_password.getText().toString());

                                            fire.addOnSuccessListener(CadastroActivity.this, new OnSuccessListener<AuthResult>(){

                                                @Override
                                                public void onSuccess(AuthResult authResult) {

                                                    FirebaseUser user = authResult.getUser();

                                                    UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                                                            .setDisplayName(edt_user.getText().toString())
                                                            .build();

                                                    user.updateProfile(profileUpdate)
                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(Task<Void> task) {
                                                                    if (task.isSuccessful()) { //success on updating user profile
                                                                        finish();
                                                                        Intent intent = new Intent(CadastroActivity.this, HomeActivity.class);
                                                                        startActivity(intent);
                                                                    } else { //failed on updating user profile
                                                                        Toast.makeText(CadastroActivity.this, "Tente novamente",
                                                                                Toast.LENGTH_SHORT).show();
                                                                    }
                                                                }
                                                            });
                                                }
                                            });


                                        } else {
                                            Toast.makeText(getApplicationContext(), getString(R.string.validacaoSenha), Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(getApplicationContext(), getString(R.string.validacaoEmail), Toast.LENGTH_SHORT).show();
                                    }
                                }else {
                                    Toast.makeText(getApplicationContext(), getString(R.string.validacaoTel), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(getApplicationContext(), getString(R.string.validacaoCpf), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), getString(R.string.validacaoSenhaCon), Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.validacaoCampos), Toast.LENGTH_SHORT).show();
                }

            }
        });
        btn_voltar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
    }

    class CustomClickableSpan extends ClickableSpan {
        @Override
        public void onClick(View textView) {
            DialogFragment pop = new DialogFragment();
            pop.show(getSupportFragmentManager(), "Cadastro");
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(Color.BLUE);//cor do texto
            ds.setUnderlineText(true); //remove sublinhado
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
