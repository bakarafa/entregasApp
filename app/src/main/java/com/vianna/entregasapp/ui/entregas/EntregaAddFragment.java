package com.vianna.entregasapp.ui.entregas;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputLayout;
import com.vianna.entregasapp.R;

import java.util.ArrayList;
import java.util.List;

public class EntregaAddFragment extends Fragment {

    TextInputLayout tilProduto, tilObs;
    Spinner spiOrigem, spiDestino;
    Button btnSolicitar;
    List<String>lista = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding();


        return null;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onStart() {
        super.onStart();

        preencheSpinner();
        
    }

    private void preencheSpinner() {
        lista.add("Centro");
        lista.add("São Mateus");
        lista.add("Grambery");
        lista.add("Bom Pastor");
        lista.add("Alto dos Passos");
        lista.add("Outros");

        ArrayAdapter<String>listaOrigem = new ArrayAdapter<>(getContext(), android.R.layout.simple_expandable_list_item_1, lista);
        ArrayAdapter<String>listaDestino = new ArrayAdapter<>(getContext(), android.R.layout.simple_expandable_list_item_1, lista);

        spiOrigem.setAdapter(listaOrigem);
        spiDestino.setAdapter(listaDestino);
    }

    private void binding() {
        tilProduto = getActivity().findViewById(R.id.tilAddEntregaProduto);
        tilObs = getActivity().findViewById(R.id.tilAddEntregaObs);
        spiDestino = getActivity().findViewById(R.id.spiAddEntregasDestino);
        spiOrigem = getActivity().findViewById(R.id.spiAddEntregasOrigem);
        btnSolicitar = getActivity().findViewById(R.id.btnAddEntregasFazerPedido);
    }

}