package com.isa.silva.idizimo.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.isa.silva.idizimo.Activity.CadastroActivity;
import com.isa.silva.idizimo.Model.Home;
import com.isa.silva.idizimo.Model.Igrejas;
import com.isa.silva.idizimo.Model.Oracoes;
import com.isa.silva.idizimo.Model.User;
import com.isa.silva.idizimo.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicMarkableReference;

public class AdmFragment extends Fragment {
    Spinner spn_tipo;
    String selected = "";
    Button btn_enviar;
    Button btn_imagem;
    EditText edt_autor;
    EditText edt_post;
    EditText edt_titulo;
    public static final int PICK_IMAGE = 1;
    private FirebaseAuth firebase = new FirebaseAuth(FirebaseApp.getInstance());
    private DatabaseReference mDatabase;
    String url = "";
    private int PERMISSAO_REQUEST = 100;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_adm, container, false);

        spn_tipo = view.findViewById(R.id.spn_tipo);
        btn_enviar = view.findViewById(R.id.btn_enviar);
        edt_autor = view.findViewById(R.id.edt_autor);
        edt_post = view.findViewById(R.id.edt_post);
        edt_titulo = view.findViewById(R.id.edt_titulo);
        btn_imagem = view.findViewById(R.id.btn_imagem);

        final String[] choices = {"Home", "Orações","Igreja"};
        ArrayAdapter<String> a =new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, choices);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_tipo.setAdapter(a);
        spn_tipo.setOnItemSelectedListener(new selectedListener());




        SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
        Date currentTime = Calendar.getInstance().getTime();

        final String data = curFormater.format(currentTime);

        btn_imagem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Abre Dialog perguntando se o app da ou não permissão de acesso as pastas
                if (ContextCompat.checkSelfPermission(getContext(),
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                            Manifest.permission.READ_EXTERNAL_STORAGE)){

                    }else{
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSAO_REQUEST);
                    }
                }else{
                    Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    pickIntent.setType("image/*");
                    startActivityForResult(Intent.createChooser(pickIntent, "Select Picture"), PICK_IMAGE);
                }
            }
        });
        btn_enviar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String post = edt_post.getText().toString();
                final String autor = edt_autor.getText().toString();
                final String titulo = edt_titulo.getText().toString();

                if(!selected.isEmpty()){
                    if(!url.isEmpty()){
                        imageUploa(url, selected, autor, post, titulo, data);
                    }else {
                        if (selected == "Orações") {
                            writeNewOracoes(firebase.getUid(), post, autor, titulo, data, url);
                        } else if (selected == "Home") {
                            writeNewHome(firebase.getUid(), post, autor, titulo, data, firebase.getCurrentUser().getEmail(), url);
                        } else if (selected == "Igreja") {
                            writeNewIgrejas(firebase.getUid(), post, autor, url, data);
                        }
                    }
                }

            }
        });

        return view;

    }

    public class selectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            selected = parent.getItemAtPosition(pos).toString();
        }

        public void onNothingSelected(AdapterView parent) {
            // Do nothing.
        }
    }

    private void writeNewOracoes(String userId, String post, String autor,  String titulo, String data, String url) {
        Oracoes oracoes = new Oracoes(userId, post, autor, titulo, data, url);

        // Write a message to the database
        mDatabase = FirebaseDatabase.getInstance().getReference("OracoesPosts");

        mDatabase.child(titulo+userId).setValue(oracoes).addOnSuccessListener(
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

    private void writeNewHome(String userId, String post, String autor,  String titulo, String data, String email, String url) {
        Home home = new Home(userId, post, autor, titulo, data, email, url);

        // Write a message to the database
        mDatabase = FirebaseDatabase.getInstance().getReference("Home");

        mDatabase.child(titulo+userId).setValue(home).addOnSuccessListener(
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

    private void writeNewIgrejas(String userId, String post, String autor,  String url, String data) {
        Igrejas igrejas = new Igrejas(userId, post, autor, data, url);

        // Write a message to the database
        mDatabase = FirebaseDatabase.getInstance().getReference("IgrejaPosts");

        mDatabase.child(autor+userId).setValue(igrejas).addOnSuccessListener(
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PICK_IMAGE) {

            url = getRealPathFromURI(getContext(), data.getData());
        }
    }

    private void imageUploa(final String data, final String selected, final String autor, final String post, final String titulo, final String dataz){
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference("/imagens_home");

        Uri file = Uri.fromFile(new File(data));
        StorageReference riversRef = storageRef.child(file.getLastPathSegment());
        UploadTask uploadTask = riversRef.putFile(file);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.e("erro", exception.getMessage());
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Log.e("Sucesso", taskSnapshot.getMetadata().getPath());

                if(selected == "Orações"){
                    writeNewOracoes(firebase.getUid(), post, autor, titulo, dataz, taskSnapshot.getMetadata().getPath());
                }else if(selected == "Home"){
                    writeNewHome(firebase.getUid(), post, autor, titulo, dataz, firebase.getCurrentUser().getEmail(), taskSnapshot.getMetadata().getPath() );
                }else if(selected == "Igreja"){
                    writeNewIgrejas(firebase.getUid(),post, autor, taskSnapshot.getMetadata().getPath(), dataz );
                }
            }
        });
    }
    private String getRealPathFromURI(Context context , Uri uri) {
        String result;
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);

        if (uri.toString().indexOf("file:///") > -1) {
            return uri.getPath();
        }

        if (cursor == null) {
            result = uri.getPath();
        } else {
            cursor.moveToFirst();

            int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);

            result = cursor.getString(index);
            cursor.close();
        }

        return result;
    }

}
