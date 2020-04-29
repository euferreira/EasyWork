package br.com.tcc.easywork.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import br.com.tcc.easywork.R;
import br.com.tcc.easywork.adapters.TabsAdapter;
import br.com.tcc.easywork.fragments.TabFragment;
import br.com.tcc.easywork.fragments.TabFragmentEndereco;

public class FormCadEdicao extends AppCompatActivity {

    private TabLayout tabLayout;
    private int tabIcones[] = {
            R.drawable.round_person_pin_white_18dp,
            R.drawable.round_house_white_18dp
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cad_edicao);

        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Cadastro de usuários");
        }

        Resources resources = getResources();
        TabsAdapter adapter = new TabsAdapter(this,getSupportFragmentManager());

        adapter.adicionaFragmento(
                TabFragment.novaInstancia(1),
                TabFragmentEndereco.novaInstancia(1));

        //adapter.adiciona(TabFragment.novaInstancia(2), "Endereço");

        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcones();

    }


    private void setupTabIcones(){
        tabLayout.getTabAt(0).setIcon(tabIcones[0]);
        tabLayout.getTabAt(1).setIcon(tabIcones[1]);
    }


}
