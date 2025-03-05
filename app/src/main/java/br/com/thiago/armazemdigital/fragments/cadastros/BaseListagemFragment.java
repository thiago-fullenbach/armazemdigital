package br.com.thiago.armazemdigital.fragments.cadastros;

import android.os.Bundle;

import androidx.viewbinding.ViewBinding;

import br.com.thiago.armazemdigital.fragments.BaseFragment;
import br.com.thiago.armazemdigital.utils.interfaces.BundleKeys;

public abstract class BaseListagemFragment<B extends ViewBinding> extends BaseFragment<B> {
    protected void editCadastro(int actionResId, long id) {
        Bundle bundle = new Bundle();
        bundle.putLong(BundleKeys.ARG_CADASTRO_ID, id);
        navigateToFragment(actionResId, bundle);
    }
}
