package br.com.thiago.armazemdigital.fragments.configuracao;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import br.com.thiago.armazemdigital.databinding.FragmentConfiguracaoBinding;
import br.com.thiago.armazemdigital.fragments.BaseFragment;

public class ConfiguracaoFragment extends BaseFragment<FragmentConfiguracaoBinding> {

    public ConfiguracaoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected FragmentConfiguracaoBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentConfiguracaoBinding.inflate(inflater, container, false);
    }

    @Override
    protected void setupViewModel() {

    }

    @Override
    protected void setupViews() {

    }
}