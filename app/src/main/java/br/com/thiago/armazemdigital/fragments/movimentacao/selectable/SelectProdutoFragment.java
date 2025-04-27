package br.com.thiago.armazemdigital.fragments.movimentacao.selectable;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.hilt.navigation.HiltViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavBackStackEntry;

import java.util.ArrayList;
import java.util.List;

import br.com.thiago.armazemdigital.R;
import br.com.thiago.armazemdigital.adapters.movimentacao.selectable.ListagemSelectProductAdapter;
import br.com.thiago.armazemdigital.databinding.FragmentSelectProdutoBinding;
import br.com.thiago.armazemdigital.fragments.BaseFragment;
import br.com.thiago.armazemdigital.model.view.ProdutoCadastro;
import br.com.thiago.armazemdigital.utils.ListValidatorUtils;
import br.com.thiago.armazemdigital.utils.wrapper.LinearLayoutManagerWrapper;
import br.com.thiago.armazemdigital.viewmodel.movimentacao.CadastroMovimentacaoViewModel;
import br.com.thiago.armazemdigital.viewmodel.movimentacao.selectable.ListagemSelectProductViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SelectProdutoFragment extends BaseFragment<FragmentSelectProdutoBinding> {
    private ListagemSelectProductAdapter mAdapter;
    private CadastroMovimentacaoViewModel mCadastroViewModel;

    public SelectProdutoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showLoadingList();
    }

    @Override
    protected FragmentSelectProdutoBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentSelectProdutoBinding.inflate(inflater, container, false);
    }

    @Override
    protected void setupViewModel() {
        NavBackStackEntry entry = getNavController().getBackStackEntry(R.id.nav_graph_cadastro_movimentacao);
        mCadastroViewModel = new ViewModelProvider(entry, HiltViewModelFactory.create(requireContext(), entry)).get(CadastroMovimentacaoViewModel.class);
        ListagemSelectProductViewModel listagemViewModel = new ViewModelProvider(this).get(ListagemSelectProductViewModel.class);

        mCadastroViewModel.getSelectedProductId().observe(getViewLifecycleOwner(), produtoSelecionadoId -> mAdapter.setProdutoSelecionadoId(produtoSelecionadoId));
        listagemViewModel.getItens().observe(getViewLifecycleOwner(), produtos -> {
            mAdapter.setListData(produtos);
            showProductList(produtos);
        });
    }

    @Override
    protected void setupViews() {
        mAdapter = new ListagemSelectProductAdapter(new ArrayList<>());
        mBinding.rvListaCadastroProdutos.setLayoutManager(new LinearLayoutManagerWrapper(requireActivity()));
        mBinding.rvListaCadastroProdutos.setAdapter(mAdapter);
        mBinding.btnSelecionarProduto.setOnClickListener(v -> selectProduct(mAdapter.getProdutoSelecionadoId()));
        mBinding.btnCadastrarProduto.setOnClickListener(v -> navigateToFragment(R.id.nav_graph_cadastro_produto));
    }

    private void selectProduct(Long productId) {
        mCadastroViewModel.setSelectedProduct(productId);
        navigateBack();
    }

    private void showLoadingList() {
        // Inicializa tela com carregamento
        mBinding.pbLoadingListProdutos.setVisibility(View.VISIBLE);
        mBinding.rvListaCadastroProdutos.setVisibility(View.GONE);
        mBinding.tvAvisoSemProduto.setVisibility(View.GONE);
    }

    private void showProductList(List<ProdutoCadastro> produtoCadastros) {
        mBinding.pbLoadingListProdutos.setVisibility(View.GONE);
        mBinding.rvListaCadastroProdutos.setVisibility(ListValidatorUtils.isNullOrEmpty(produtoCadastros) ? View.GONE : View.VISIBLE);
        mBinding.tvAvisoSemProduto.setVisibility(ListValidatorUtils.isNullOrEmpty(produtoCadastros) ? View.VISIBLE : View.GONE);
    }
}