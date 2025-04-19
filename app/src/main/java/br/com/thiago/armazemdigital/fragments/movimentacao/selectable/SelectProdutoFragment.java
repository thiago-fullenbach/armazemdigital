package br.com.thiago.armazemdigital.fragments.movimentacao.selectable;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import br.com.thiago.armazemdigital.databinding.FragmentSelectProdutoBinding;
import br.com.thiago.armazemdigital.fragments.BaseFragment;

public class SelectProdutoFragment extends BaseFragment<FragmentSelectProdutoBinding> {

    public SelectProdutoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected FragmentSelectProdutoBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentSelectProdutoBinding.inflate(inflater, container, false);
    }

    @Override
    protected void setupViewModel() {

    }

    @Override
    protected void setupViews() {

    }
}