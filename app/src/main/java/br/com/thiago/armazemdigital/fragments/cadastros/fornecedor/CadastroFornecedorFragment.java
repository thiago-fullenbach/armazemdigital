package br.com.thiago.armazemdigital.fragments.cadastros.fornecedor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import br.com.thiago.armazemdigital.databinding.FragmentCadastroFornecedorBinding;
import br.com.thiago.armazemdigital.fragments.cadastros.BaseCadastroFragment;

public class CadastroFornecedorFragment extends BaseCadastroFragment<FragmentCadastroFornecedorBinding> {

    public CadastroFornecedorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected FragmentCadastroFornecedorBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentCadastroFornecedorBinding.inflate(inflater, container, false);
    }

    @Override
    protected void salvarDados(long id) {

    }

    @Override
    protected boolean validarDados() {
        return false;
    }

    @Override
    protected void carregarDados(long id) {

    }
}