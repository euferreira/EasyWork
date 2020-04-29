package br.com.tcc.easywork.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import br.com.tcc.easywork.R;

public class BottomPerfil extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Toast.makeText(getActivity(), "ABRIU PERFIL", Toast.LENGTH_SHORT).show();


        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

    public static BottomPerfil newInstance() {
        return new BottomPerfil();
    }
}
