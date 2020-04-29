package br.com.tcc.easywork.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.tcc.easywork.R;
import br.com.tcc.easywork.adapters.AdapterRecycler;
import br.com.tcc.easywork.model.RecyclerItem;

public class BottomMenu extends Fragment {

    private RecyclerView recyclerView;
    private AdapterRecycler adapter;
    private List<RecyclerItem> listItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragView = inflater.inflate(R.layout.fragment_menu, container, false);
        inicializa(fragView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        listItems = new ArrayList<>();
        listItems.add(0, new RecyclerItem("Meu Perfil "));
        listItems.add(1, new RecyclerItem("Trabalhos Solicitados"));
        listItems.add(2, new RecyclerItem("Últimos trabalhos"));
        listItems.add(3, new RecyclerItem("Favoritos"));
        listItems.add(4, new RecyclerItem("Notificações "));
        listItems.add(5, new RecyclerItem("Seja nosso Parceiro"));
        listItems.add(6, new RecyclerItem("Sugerir novos usuários"));
        listItems.add(7, new RecyclerItem("Configurações"));
        listItems.add(8, new RecyclerItem("Ajuda"));

        adapter = new AdapterRecycler(listItems, getActivity());
        recyclerView.setAdapter(adapter);

        return fragView;
    }

    public static BottomMenu newInstance() {
        return new BottomMenu();
    }

    private void inicializa(View view){
        recyclerView = view.findViewById(R.id.recyclerView);
    }

}
