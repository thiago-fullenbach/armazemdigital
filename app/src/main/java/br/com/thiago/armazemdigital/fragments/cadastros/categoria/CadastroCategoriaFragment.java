package br.com.thiago.armazemdigital.fragments.cadastros.categoria;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import br.com.thiago.armazemdigital.databinding.FragmentCadastroCategoriaBinding;
import br.com.thiago.armazemdigital.fragments.cadastros.BaseCadastroFragment;

public class CadastroCategoriaFragment extends BaseCadastroFragment<FragmentCadastroCategoriaBinding> {

    public CadastroCategoriaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected FragmentCadastroCategoriaBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentCadastroCategoriaBinding.inflate(inflater, container, false);
    }

    @Override
    protected void salvarDados() {

    }

    @Override
    protected boolean validarDados() {
        return false;
    }

    @Override
    protected void carregarDados() {

    }
}