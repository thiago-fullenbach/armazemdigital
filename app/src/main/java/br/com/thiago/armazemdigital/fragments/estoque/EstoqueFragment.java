package br.com.thiago.armazemdigital.fragments.estoque;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import br.com.thiago.armazemdigital.databinding.FragmentEstoqueBinding;
import br.com.thiago.armazemdigital.fragments.BaseFragment;

public class EstoqueFragment extends BaseFragment<FragmentEstoqueBinding> {

    public EstoqueFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected FragmentEstoqueBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentEstoqueBinding.inflate(inflater, container, false);
    }

    @Override
    protected void setupViewModel() {

    }

    @Override
    protected void setupViews() {

    }
}