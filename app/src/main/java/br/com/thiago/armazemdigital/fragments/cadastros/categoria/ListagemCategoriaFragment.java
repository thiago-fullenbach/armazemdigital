package br.com.thiago.armazemdigital.fragments.cadastros.categoria;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import br.com.thiago.armazemdigital.databinding.FragmentListagemCategoriaBinding;
import br.com.thiago.armazemdigital.fragments.BaseFragment;

public class ListagemCategoriaFragment extends BaseFragment<FragmentListagemCategoriaBinding> {

    public ListagemCategoriaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected FragmentListagemCategoriaBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentListagemCategoriaBinding.inflate(inflater, container, false);
    }
}