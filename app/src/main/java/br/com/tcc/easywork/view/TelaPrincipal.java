package br.com.tcc.easywork.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import br.com.tcc.easywork.R;
import br.com.tcc.easywork.fragments.BottomHome;
import br.com.tcc.easywork.fragments.BottomMenu;
import br.com.tcc.easywork.fragments.BottomPerfil;
import br.com.tcc.easywork.fragments.BottomTrabalhos;
import br.com.tcc.easywork.util.Auxiliadores;

public class TelaPrincipal extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.bottomNav);

        navigationView.setOnNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.menu_menu:
                Toast.makeText(this, "Menu 1", Toast.LENGTH_SHORT).show();
                /*Fragment menuFragment = BottomMenu.newInstance();
                abrirFragment(menuFragment);*/
                fragment = new BottomMenu();
                break;

            case R.id.menu_home:
                Toast.makeText(this, "Menu 2", Toast.LENGTH_SHORT).show();
                /*Fragment homeFragment = BottomHome.newInstance();
                abrirFragment(homeFragment);*/
                fragment = new BottomHome();
                break;

            case R.id.menu_trabalho:
                Toast.makeText(this, "Menu 3", Toast.LENGTH_SHORT).show();
                /*Fragment trabalhoFragment = BottomTrabalhos.newInstance();
                abrirFragment(trabalhoFragment);*/
                fragment = new BottomTrabalhos();
                break;

            case R.id.menu_perfil:
                Toast.makeText(this, "Menu 4", Toast.LENGTH_SHORT).show();
                /*Fragment perfilFragment = BottomPerfil.newInstance();
                abrirFragment(perfilFragment);*/
                fragment = new BottomPerfil();
                break;

        }

        return loadFragment(fragment);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        }
        return false;
    }
}
