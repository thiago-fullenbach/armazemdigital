package br.com.thiago.armazemdigital.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewbinding.ViewBinding;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseFragment<B extends ViewBinding> extends Fragment {
    private final Logger mLogger = LoggerFactory.getLogger(BaseFragment.class);
    protected B mBinding;

    protected abstract B inflateBinding(LayoutInflater inflater, ViewGroup container);

    protected abstract void setupViewModel();

    protected abstract void setupViews();

    @NonNull
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mLogger.info("@onCreateView() chamado");
        mBinding = inflateBinding(inflater, container);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLogger.info("Iniciada tela de cadastro de {}", getClass().getSimpleName());
        mLogger.info("@onViewCreated() chamado");

        mLogger.info("Inicializando ViewModels...");
        setupViewModel();

        mLogger.info("Inicializando Views");
        setupViews();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mLogger.info("@onDestroyView() chamado");
        mLogger.info("Finalizada tela de cadastro de {}", getClass().getSimpleName());
    }

    protected void navigateBack() {
        mLogger.info("@navigateBack() chamado");
        if(!isAdded()) {
            mLogger.info("Fragment not added to activity");
            return;
        }
        getNavController().popBackStack();
    }

    protected void navigateToFragment(int resourceId) {
        navigateToFragment(resourceId, null);
    }

    protected void navigateToFragment(int resourceId, @Nullable Bundle bundle) {
        mLogger.info("@navigateToFragment() chamado");
        if(!isAdded()) {
            mLogger.info("Fragment not added to activity");
            return;
        }
        getNavController().navigate(resourceId, bundle);
    }

    protected NavController getNavController() {
        mLogger.info("@getNavController() chamado");
        if(!isAdded()) {
            mLogger.info("Fragment not added to activity");
            return null;
        }
        return NavHostFragment.findNavController(this);
    }
}
