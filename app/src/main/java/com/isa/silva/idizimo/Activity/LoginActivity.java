package com.isa.silva.idizimo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.isa.silva.idizimo.Fragment.BibliaFragment;
import com.isa.silva.idizimo.Fragment.ContribuicoesFragment;
import com.isa.silva.idizimo.Fragment.HomeFragment;
import com.isa.silva.idizimo.Fragment.IgrejasFragment;
import com.isa.silva.idizimo.Fragment.OracoesFragment;
import com.isa.silva.idizimo.Fragment.PerfilFragment;
import com.isa.silva.idizimo.R;
import com.isa.silva.idizimo.Utils.Util;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

import java.lang.reflect.Array;


public class LoginActivity extends AppCompatActivity {

    private Button btn_login;
    private Button btn_cadastrar;
    private Button btn_esqueci;
    private EditText edt_login;
    private EditText edt_senha;
    private FirebaseAuth firebase = new FirebaseAuth(FirebaseApp.getInstance());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = findViewById(R.id.btn_entrar);
        btn_cadastrar = findViewById(R.id.btn_cadastro);
        btn_esqueci = findViewById(R.id.btn_Esqueci);
        edt_login = findViewById(R.id.edt_user);
        edt_senha = findViewById(R.id.edt_password);


        btn_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (Util.isValidEmailAddressRegex(edt_login.getText().toString())) {
                    if (!edt_login.getText().toString().isEmpty() && !edt_senha.getText().toString().isEmpty()) {
                        Task<AuthResult> fire = firebase.signInWithEmailAndPassword(
                                edt_login.getText().toString(),
                                edt_senha.getText().toString()
                        );


                        fire.addOnSuccessListener(LoginActivity.this, new OnSuccessListener<AuthResult>() {

                            @Override
                            public void onSuccess(AuthResult it) {

                                if (it != null) {
                                    // User is signed in.
                                    FirebaseUser user = it.getUser();
                                            Toast.makeText(LoginActivity.this, "Bem-vindo " + user.getDisplayName(), Toast.LENGTH_SHORT).show();

                                    finish();
                                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                    startActivity(intent);

                                } else {

                                    Toast.makeText(LoginActivity.this, "Tente novamente!", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                            fire.addOnFailureListener(LoginActivity.this, new OnFailureListener() {


                                @Override
                                public void onFailure(@NonNull Exception it) {
                                    String[] error = it.toString().split(":");


                                    Toast.makeText(LoginActivity.this, error[1] + "  Tente novamente!", Toast.LENGTH_LONG).show();

                                }
                            });



                    } else {
                        Toast.makeText(getApplicationContext(), getString(R.string.validacaoCampos), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), getString(R.string.validacaoEmail), Toast.LENGTH_SHORT).show();
                }

            }
        });
        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(intent);

            }
        });

        btn_esqueci.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                finish();
                Intent intent = new Intent(LoginActivity.this, EsqueciActivity.class);
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
