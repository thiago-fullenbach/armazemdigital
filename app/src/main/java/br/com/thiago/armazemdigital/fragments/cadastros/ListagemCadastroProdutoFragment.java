package br.com.thiago.armazemdigital.fragments.cadastros;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import br.com.thiago.armazemdigital.databinding.FragmentListagemCadastroProdutoBinding;

public class ListagemCadastroProdutoFragment extends Fragment {

    public ListagemCadastroProdutoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentListagemCadastroProdutoBinding binding = FragmentListagemCadastroProdutoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}