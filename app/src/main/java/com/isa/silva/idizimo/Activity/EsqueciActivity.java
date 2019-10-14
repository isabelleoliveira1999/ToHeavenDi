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


public class EsqueciActivity extends AppCompatActivity {

    private Button btn_login;
    private Button btn_voltar;
    private EditText edt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueci);

        btn_login = findViewById(R.id.btn_entrar);
        btn_voltar = findViewById(R.id.btn_voltar);
        edt_login = findViewById(R.id.edt_user);


        btn_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (Util.isValidEmailAddressRegex(edt_login.getText().toString())) {
                    if (!edt_login.getText().toString().isEmpty()) {
                        finish();
                        Intent intent = new Intent(EsqueciActivity.this, LoginActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "Email invalido", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_voltar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(EsqueciActivity.this, LoginActivity.class);
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
