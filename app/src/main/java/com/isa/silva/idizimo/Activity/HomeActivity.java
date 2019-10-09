package com.isa.silva.idizimo.Activity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.isa.silva.idizimo.Fragment.*;
import com.isa.silva.idizimo.R;
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.push.Push;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;
import java.text.ParseException;


public class HomeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView textTitulo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        textTitulo = (TextView) findViewById(R.id.txt_title);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);
        AppCenter.start(getApplication(), "edc58bf2-3b64-41f8-bf8d-84c28728013a",
                Analytics.class, Crashes.class, Push.class);
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
        PrimaryDrawerItem item6 = new PrimaryDrawerItem().withIdentifier(6).withName("Meu Perfil")
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
                        item6.withIcon(R.drawable.ic_resume)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        //textView.setText("Você clicou em: " + ((Nameable) drawerItem).getName().getText(HomeActivity.this));
                        if(((Nameable) drawerItem).getName().getText(HomeActivity.this) == "Home"){
                            textTitulo.setText("Minha Home");
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.container, new HomeFragment())
                                    .commit();
                        }else if(((Nameable) drawerItem).getName().getText(HomeActivity.this) == "Orações"){
                            textTitulo.setText("Orações Do Dia");
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.container, new OracoesFragment())
                                    .commit();
                        }else if(((Nameable) drawerItem).getName().getText(HomeActivity.this) == "Bíblia"){
                            textTitulo.setText("Bíblia Digital");
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.container, new BibliaFragment())
                                    .commit();
                        }else if(((Nameable) drawerItem).getName().getText(HomeActivity.this) == "Igreja"){
                            textTitulo.setText("Sobre A Sua Igreja");
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.container, new IgrejasFragment())
                                    .commit();
                        }else if(((Nameable) drawerItem).getName().getText(HomeActivity.this) == "Contribuições"){
                            textTitulo.setText("Minhas Contribuições");
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.container, new ContribuicoesFragment())
                                    .commit();
                        }else if(((Nameable) drawerItem).getName().getText(HomeActivity.this) == "Meu Perfil"){
                            textTitulo.setText("Alterar Perfil");
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.container, new PerfilFragment())
                                    .commit();
                        }


                        return false;
                    }
                })
                .withSelectedItemByPosition(0).withSliderBackgroundColor(getResources().getColor(R.color.colorPrimaryDark))
                .build();
    }

}
