package com.isa.silva.idizimo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.isa.silva.idizimo.R;
import com.isa.silva.idizimo.Utils.Util;


public class CadastroActivity extends AppCompatActivity {

    private Button btn_login;
    private Button btn_voltar;
    private EditText edt_user;
    private EditText edt_mail;
    private EditText edt_tel;
    private EditText edt_password;
    private EditText edt_password_con;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        btn_login = findViewById(R.id.btn_contribuir);
        btn_voltar = findViewById(R.id.btn_voltar);
        edt_user = findViewById(R.id.edt_user);
        edt_mail = findViewById(R.id.edt_mail);
        edt_tel = findViewById(R.id.edt_tel);
        edt_password = findViewById(R.id.edt_password);
        edt_password_con = findViewById(R.id.edt_password_conf);

        edt_tel.addTextChangedListener(Util.mask(edt_tel, Util.FORMAT_FONE));
        btn_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!edt_user.getText().toString().isEmpty()
                        && !edt_mail.getText().toString().isEmpty()
                        && !edt_tel.getText().toString().isEmpty()
                        && !edt_password.getText().toString().isEmpty()
                        && !edt_password_con.getText().toString().isEmpty()) {
                    if(edt_password.getText().toString() == edt_password_con.getText().toString() ) {
                        finish();
                        Intent intent = new Intent(CadastroActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(), "A senha e a confirmação devem ser iguais", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}
