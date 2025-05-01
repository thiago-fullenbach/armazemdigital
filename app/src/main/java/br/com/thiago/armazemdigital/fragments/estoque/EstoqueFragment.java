package br.com.thiago.armazemdigital.fragments.estoque;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

import br.com.thiago.armazemdigital.adapters.estoque.EstoqueAdapter;
import br.com.thiago.armazemdigital.databinding.FragmentEstoqueBinding;
import br.com.thiago.armazemdigital.fragments.BaseListagemFragment;
import br.com.thiago.armazemdigital.model.view.ProdutoEstoque;
import br.com.thiago.armazemdigital.utils.ListValidatorUtils;
import br.com.thiago.armazemdigital.utils.wrapper.LinearLayoutManagerWrapper;
import br.com.thiago.armazemdigital.viewmodel.estoque.EstoqueViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class EstoqueFragment extends BaseListagemFragment<FragmentEstoqueBinding> {
    private EstoqueAdapter mAdapter;

    public EstoqueFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showLoadingList();
    }

    @Override
    protected FragmentEstoqueBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentEstoqueBinding.inflate(inflater, container, false);
    }

    @Override
    protected void setupViewModel() {
        EstoqueViewModel estoqueViewModel = new ViewModelProvider(this).get(EstoqueViewModel.class);
        estoqueViewModel.getItens().observe(getViewLifecycleOwner(), produtoEstoque -> {
            mAdapter.setListData(produtoEstoque);
            showProductList(produtoEstoque);
        });
    }

    @Override
    protected void setupViews() {
        mAdapter = new EstoqueAdapter(new ArrayList<>());
        mBinding.rvListaEstoque.setLayoutManager(new LinearLayoutManagerWrapper(requireActivity()));
        mBinding.rvListaEstoque.setAdapter(mAdapter);
    }

    private void showLoadingList() {
        // Inicializa tela com carregamento
        mBinding.pbLoadingListEstoque.setVisibility(View.VISIBLE);
        mBinding.rvListaEstoque.setVisibility(View.GONE);
        mBinding.tvAvisoSemProduto.setVisibility(View.GONE);
    }

    private void showProductList(List<ProdutoEstoque> produtoEstoques) {
        mBinding.pbLoadingListEstoque.setVisibility(View.GONE);
        mBinding.rvListaEstoque.setVisibility(ListValidatorUtils.isNullOrEmpty(produtoEstoques) ? View.GONE : View.VISIBLE);
        mBinding.tvAvisoSemProduto.setVisibility(ListValidatorUtils.isNullOrEmpty(produtoEstoques) ? View.VISIBLE : View.GONE);
    }
}