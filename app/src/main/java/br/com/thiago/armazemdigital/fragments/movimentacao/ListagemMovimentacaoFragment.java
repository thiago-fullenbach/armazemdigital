package br.com.thiago.armazemdigital.fragments.movimentacao;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import br.com.thiago.armazemdigital.databinding.FragmentListagemMovimentacaoBinding;
import br.com.thiago.armazemdigital.fragments.BaseFragment;

public class ListagemMovimentacaoFragment extends BaseFragment<FragmentListagemMovimentacaoBinding> {

    public ListagemMovimentacaoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected FragmentListagemMovimentacaoBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentListagemMovimentacaoBinding.inflate(inflater, container, false);
    }

    @Override
    protected void setupViewModel() {

    }

    @Override
    protected void setupViews() {

    }
}