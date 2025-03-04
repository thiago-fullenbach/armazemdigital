package br.com.thiago.armazemdigital.fragments.cadastros.fornecedor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import br.com.thiago.armazemdigital.databinding.FragmentListagemFornecedorBinding;
import br.com.thiago.armazemdigital.fragments.BaseFragment;

public class ListagemFornecedorFragment extends BaseFragment<FragmentListagemFornecedorBinding> {

    public ListagemFornecedorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected FragmentListagemFornecedorBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentListagemFornecedorBinding.inflate(inflater, container, false);
    }
}