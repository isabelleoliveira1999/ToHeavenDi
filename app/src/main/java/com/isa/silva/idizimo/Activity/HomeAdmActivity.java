package com.isa.silva.idizimo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.isa.silva.idizimo.Fragment.AdmFragment;
import com.isa.silva.idizimo.Fragment.BibliaFragment;
import com.isa.silva.idizimo.Fragment.ContribuicoesFragment;
import com.isa.silva.idizimo.Fragment.HomeFragment;
import com.isa.silva.idizimo.Fragment.IgrejasFragment;
import com.isa.silva.idizimo.Fragment.OracoesFragment;
import com.isa.silva.idizimo.Fragment.PerfilFragment;
import com.isa.silva.idizimo.Fragment.SobreFragment;
import com.isa.silva.idizimo.R;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;


public class HomeAdmActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView textTitulo;
    private FirebaseAuth firebase = new FirebaseAuth(FirebaseApp.getInstance());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        textTitulo = (TextView) findViewById(R.id.txt_title);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new HomeFragment())
                .commit();
        createDrawer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void createDrawer(){
        //Itens do Drawer
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Home")
                .withTextColor(getResources().getColor(R.color.menuWhite))
                .withSelectedColor(getResources().getColor(R.color.menuRed))
                .withSelectedTextColor(getResources().getColor(R.color.menuWhite));
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("Orações")
                .withTextColor(getResources().getColor(R.color.menuWhite))
                .withSelectedColor(getResources().getColor(R.color.menuRed))
                .withSelectedTextColor(getResources().getColor(R.color.menuWhite));
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName("Bíblia")
                .withTextColor(getResources().getColor(R.color.menuWhite))
                .withSelectedColor(getResources().getColor(R.color.menuRed))
                .withSelectedTextColor(getResources().getColor(R.color.menuWhite));
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName("Igreja")
                .withTextColor(getResources().getColor(R.color.menuWhite))
                .withSelectedColor(getResources().getColor(R.color.menuRed))
                .withSelectedTextColor(getResources().getColor(R.color.menuWhite));
        PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName("Contribuições")
                .withTextColor(getResources().getColor(R.color.menuWhite))
                .withSelectedColor(getResources().getColor(R.color.menuRed))
                .withSelectedTextColor(getResources().getColor(R.color.menuWhite));
        PrimaryDrawerItem item6 = new PrimaryDrawerItem().withIdentifier(6).withName("Administrador")
                .withTextColor(getResources().getColor(R.color.menuWhite))
                .withSelectedColor(getResources().getColor(R.color.menuRed))
                .withSelectedTextColor(getResources().getColor(R.color.menuWhite));
        PrimaryDrawerItem item7 = new PrimaryDrawerItem().withIdentifier(7).withName("Sobre")
                .withTextColor(getResources().getColor(R.color.menuWhite))
                .withSelectedColor(getResources().getColor(R.color.menuRed))
                .withSelectedTextColor(getResources().getColor(R.color.menuWhite));
        PrimaryDrawerItem item8 = new PrimaryDrawerItem().withIdentifier(8).withName("Sair")
                .withTextColor(getResources().getColor(R.color.menuWhite))
                .withSelectedColor(getResources().getColor(R.color.menuRed))
                .withSelectedTextColor(getResources().getColor(R.color.menuWhite));


        //Definição do Drawer
        Drawer drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1.withIcon(R.drawable.ic_home),
                        item2.withIcon(R.drawable.ic_pray),
                      //  new SectionDrawerItem().withName("section"),//Seção
                        item3.withIcon(R.drawable.ic_bible),
                        item4.withIcon(R.drawable.ic_church),
                        item5.withIcon(R.drawable.ic_donate),
                        item6.withIcon(R.drawable.ic_resume),
                        item7.withIcon(R.drawable.ic_info),
                        item8.withIcon(R.drawable.ic_logout)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        //textView.setText("Você clicou em: " + ((Nameable) drawerItem).getName().getText(HomeActivity.this));
                        if(((Nameable) drawerItem).getName().getText(HomeAdmActivity.this) == "Home"){
                            textTitulo.setText("Minha Home");
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.container, new HomeFragment())
                                    .commit();
                        }else if(((Nameable) drawerItem).getName().getText(HomeAdmActivity.this) == "Orações"){
                            textTitulo.setText("Orações Do Dia");
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.container, new OracoesFragment())
                                    .commit();
                        }else if(((Nameable) drawerItem).getName().getText(HomeAdmActivity.this) == "Bíblia"){
                            textTitulo.setText("Bíblia Digital");
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.container, new BibliaFragment())
                                    .commit();
                        }else if(((Nameable) drawerItem).getName().getText(HomeAdmActivity.this) == "Igreja"){
                            textTitulo.setText("Sobre A Sua Igreja");
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.container, new IgrejasFragment())
                                    .commit();
                        }else if(((Nameable) drawerItem).getName().getText(HomeAdmActivity.this) == "Contribuições"){
                            textTitulo.setText("Contribuições");
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.container, new ContribuicoesFragment())
                                    .commit();
                        }else if(((Nameable) drawerItem).getName().getText(HomeAdmActivity.this) == "Administrador"){
                            textTitulo.setText("Novos Posts");
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.container, new AdmFragment())
                                    .commit();
                        }else if(((Nameable) drawerItem).getName().getText(HomeAdmActivity.this) == "Sobre"){
                            textTitulo.setText("Sobre");
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.container, new SobreFragment())
                                    .commit();
                        }
                        else if(((Nameable) drawerItem).getName().getText(HomeAdmActivity.this) == "Sair"){
                            firebase.signOut();
                                  finish();
                            Intent intent = new Intent(HomeAdmActivity.this, LoginActivity.class);
                            startActivity(intent); //Abre a segunda activity
                        }


                        return false;
                    }
                })
                .withSelectedItemByPosition(0).withSliderBackgroundColor(getResources().getColor(R.color.colorPrimaryDark))
                .build();
    }

}
