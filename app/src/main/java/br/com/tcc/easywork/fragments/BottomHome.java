package br.com.tcc.easywork.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import br.com.tcc.easywork.R;

public class BottomHome extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Toast.makeText(getActivity(), "ABRIU HOME", Toast.LENGTH_SHORT).show();


        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public static BottomHome newInstance() {
        return new BottomHome();
    }

}
