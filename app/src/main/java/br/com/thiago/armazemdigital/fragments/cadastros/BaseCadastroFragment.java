package br.com.thiago.armazemdigital.fragments.cadastros;

import androidx.viewbinding.ViewBinding;

import br.com.thiago.armazemdigital.fragments.BaseFragment;

public abstract class BaseCadastroFragment<B extends ViewBinding> extends BaseFragment<B> {
    protected abstract void salvarDados();

    protected abstract boolean validarDados();

    protected abstract void carregarDados();
}
