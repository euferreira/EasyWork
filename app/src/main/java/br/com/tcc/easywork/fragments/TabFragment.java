package br.com.tcc.easywork.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import br.com.tcc.easywork.R;

public class TabFragment extends Fragment {

    public static String ARG_POSITION = "arg_position";

    public TabFragment() {
    }

    public static TabFragment novaInstancia(int posicao) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_POSITION, posicao);

        TabFragment fragment = new TabFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceBundle) {
        View fragView = inflater.inflate(R.layout.fragment_tab, container, false);



        return fragView;
    }

}
