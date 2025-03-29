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

public abstract class BaseFragment<B extends ViewBinding> extends Fragment {
    protected B mBinding;

    protected abstract B inflateBinding(LayoutInflater inflater, ViewGroup container);

    protected abstract void setupViewModel();

    protected abstract void setupViews();

    @NonNull
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = inflateBinding(inflater, container);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViewModel();
        setupViews();
    }

    protected void navigateBack() {
        if(!isAdded()) return;
        getNavController().popBackStack();
    }

    protected void navigateToFragment(int resourceId) {
        if(!isAdded()) return;
        navigateToFragment(resourceId, null);
    }

    protected void navigateToFragment(int resourceId, @Nullable Bundle bundle) {
        if(!isAdded()) return;
        getNavController().navigate(resourceId, bundle);
    }

    protected NavController getNavController() {
        if(!isAdded()) return null;
        return NavHostFragment.findNavController(this);
    }
}
