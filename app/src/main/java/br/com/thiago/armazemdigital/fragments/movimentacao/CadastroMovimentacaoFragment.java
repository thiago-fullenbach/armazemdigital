package br.com.thiago.armazemdigital.fragments.movimentacao;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import br.com.thiago.armazemdigital.databinding.FragmentCadastroMovimentacaoBinding;
import br.com.thiago.armazemdigital.fragments.BaseFragment;

public class CadastroMovimentacaoFragment extends BaseFragment<FragmentCadastroMovimentacaoBinding> {

    public CadastroMovimentacaoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected FragmentCadastroMovimentacaoBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentCadastroMovimentacaoBinding.inflate(inflater, container, false);
    }

    @Override
    protected void setupViewModel() {

    }

    @Override
    protected void setupViews() {

    }
}