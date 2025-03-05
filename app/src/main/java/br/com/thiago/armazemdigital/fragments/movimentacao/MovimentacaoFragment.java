package br.com.thiago.armazemdigital.fragments.movimentacao;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import br.com.thiago.armazemdigital.databinding.FragmentMovimentacaoBinding;
import br.com.thiago.armazemdigital.fragments.BaseFragment;

public class MovimentacaoFragment extends BaseFragment<FragmentMovimentacaoBinding> {

    public MovimentacaoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected FragmentMovimentacaoBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentMovimentacaoBinding.inflate(inflater, container, false);
    }

    @Override
    protected void setupViewModel() {

    }

    @Override
    protected void setupViews() {

    }
}