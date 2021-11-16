package com.vianna.entregasapp.ui.entregas;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vianna.entregasapp.R;

public class EntregasFragment extends Fragment {

//    TextView mensagem;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_entregas, container, false);//define qual fragment será carregada ao abrir
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onStart() {
        super.onStart();

//        mensagem = getActivity().findViewById(R.id.text_home);//binding

//        mensagem.setText(getArguments().getString("nome"));//pega o "nome" que foi passado via arguments da MainActivity e coloca no campo de id text_home
    }

}