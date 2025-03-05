package br.com.thiago.armazemdigital.fragments.cadastros.produto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import br.com.thiago.armazemdigital.databinding.FragmentCadastroProdutoBinding;
import br.com.thiago.armazemdigital.fragments.cadastros.BaseCadastroFragment;

public class CadastroProdutoFragment extends BaseCadastroFragment<FragmentCadastroProdutoBinding> {
    public CadastroProdutoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected FragmentCadastroProdutoBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentCadastroProdutoBinding.inflate(inflater, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.btnCancelarCadastroProduto.setOnClickListener(v -> navigateBack());
    }

    @Override
    protected void salvarDados(long id) {
        // TODO: Implementar
    }

    @Override
    protected boolean validarDados() {
        // TODO: Implementar
        return false;
    }

    @Override
    protected void carregarDados(long id) {
        // TODO: Implementar
    }
}