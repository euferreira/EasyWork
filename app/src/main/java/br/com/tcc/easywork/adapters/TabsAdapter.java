package br.com.tcc.easywork.adapters;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import br.com.tcc.easywork.R;
import br.com.tcc.easywork.fragments.TabFragment;
import br.com.tcc.easywork.fragments.TabFragmentEndereco;

public class TabsAdapter extends FragmentPagerAdapter {

    private List<TabFragment> listaFragmento = new ArrayList<>();
    private List<TabFragmentEndereco> listaFragEnd = new ArrayList<>();
    private List<String> listaFragmentoTitulo = new ArrayList<>();

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_dados_pessoais, R.string.tab_endereco};
    private final Context mContext;

    public TabsAdapter(Context context,FragmentManager fm){
        super(fm);
        mContext = context;
    }

    /**
     * Método responsável por adicionar novas tabs
     * Parametros, lista contendo as tabs
     * @param frag
     * @param fragEnd
     * */
    public void adicionaFragmento(TabFragment frag, TabFragmentEndereco fragEnd){
        this.listaFragmento.add(frag);
        this.listaFragEnd.add(fragEnd);
        //this.listaFragmentoTitulo.add(titulo);
    }

    public boolean inicializa(boolean flag, View view) {
        if (flag) {
            try{
                EditText edtCadNome = view.findViewById(R.id.edtCadNome);
            }catch (NullPointerException e){
                throw new NullPointerException();
            }
        }
        return false;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        //cada vez que adicionar uma nova tab, incluir aqui
        switch (position){
            case 0:
                fragment = new TabFragment();
                break;

            case 1:
                fragment = new TabFragmentEndereco();
                break;
        }

        return fragment;
        //return listaFragmento.get(fragment);
    }

    /**
     * Retorna o titulo da TAB, incluir no vetor TAB_TITLES, que tem como parametro position
     * @param position
     * @return TAB_TITLES
     * */
    @Override
    public CharSequence getPageTitle(int position){

        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    /**
     * Conta o número de TABS, sempre que incluir alguma nova, mudar aqui
     **/
    @Override
    public int getCount(){
        //NOTA: Pensar num jeito de incluir as tabs numa lista para retornar o tamanho
        //E não precisar vir aqui toda vez e mudar;
        return 2;
    }
}
