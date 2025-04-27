package br.com.thiago.armazemdigital.fragments;

import android.os.Bundle;

import androidx.viewbinding.ViewBinding;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.thiago.armazemdigital.utils.interfaces.BundleKeys;

public abstract class BaseListagemFragment<B extends ViewBinding> extends BaseFragment<B> {
    private final Logger mLogger = LoggerFactory.getLogger(BaseListagemFragment.class);

    protected void editCadastro(int actionResId, long id) {
        mLogger.info("@editCadastro() chamado");
        Bundle bundle = new Bundle();
        bundle.putLong(BundleKeys.ARG_CADASTRO_ID, id);
        mLogger.debug("Editando cadastro: {}", id);
        navigateToFragment(actionResId, bundle);
    }
}
