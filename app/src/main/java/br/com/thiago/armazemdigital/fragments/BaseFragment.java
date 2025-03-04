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

    @NonNull
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = inflateBinding(inflater, container);
        return mBinding.getRoot();
    }

    protected void navigateBack() {
        NavController navController = NavHostFragment.findNavController(this);
        navController.popBackStack();
    }

    protected void navigateToFragment(int resourceId) {
        NavController navController = NavHostFragment.findNavController(this);
        navController.navigate(resourceId);
    }
}
