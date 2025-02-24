package br.com.thiago.armazemdigital.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import br.com.thiago.armazemdigital.databinding.FragmentEstoqueBinding;

public class EstoqueFragment extends Fragment {

    public EstoqueFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentEstoqueBinding binding = FragmentEstoqueBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}