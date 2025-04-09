package br.com.thiago.armazemdigital.fragments.cadastros.produto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

import br.com.thiago.armazemdigital.R;
import br.com.thiago.armazemdigital.adapters.cadastro.produto.ListagemProdutosAdapter;
import br.com.thiago.armazemdigital.databinding.FragmentListagemProdutoBinding;
import br.com.thiago.armazemdigital.fragments.cadastros.BaseListagemFragment;
import br.com.thiago.armazemdigital.model.view.ProdutoCadastro;
import br.com.thiago.armazemdigital.utils.ListValidatorUtils;
import br.com.thiago.armazemdigital.utils.wrapper.LinearLayoutManagerWrapper;
import br.com.thiago.armazemdigital.viewmodel.cadastros.produto.ListagemProdutoViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ListagemProdutoFragment extends BaseListagemFragment<FragmentListagemProdutoBinding> {
    private ListagemProdutosAdapter mAdapter;

    public ListagemProdutoFragment() {
        // Required empty public constructor
    }

    @Override
    protected FragmentListagemProdutoBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentListagemProdutoBinding.inflate(inflater, container, false);
    }

    @Override
    protected void setupViewModel() {
        ListagemProdutoViewModel mViewModel = new ViewModelProvider(this).get(ListagemProdutoViewModel.class);
        mViewModel.getItens().observe(getViewLifecycleOwner(), produtos -> {
            mAdapter.setListData(produtos);
            showProductList(produtos);
        });
    }

    @Override
    protected void setupViews() {
        mAdapter = new ListagemProdutosAdapter(new ArrayList<>(), produto -> editCadastro(R.id.action_listagem_produto_fragment_to_nav_graph_cadastro_produto, produto.getProductId()));
        mBinding.rvListaCadastroProduto.setLayoutManager(new LinearLayoutManagerWrapper(requireActivity()));
        mBinding.rvListaCadastroProduto.setAdapter(mAdapter);
        mBinding.btnCadastrarProduto.setOnClickListener(v -> navigateToFragment(R.id.action_listagem_produto_fragment_to_nav_graph_cadastro_produto));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showLoadingList();
    }

    private void showLoadingList() {
        // Inicializa tela com carregamento
        mBinding.pbLoadingListProdutos.setVisibility(View.VISIBLE);
        mBinding.rvListaCadastroProduto.setVisibility(View.GONE);
        mBinding.tvAvisoSemProduto.setVisibility(View.GONE);
    }

    private void showProductList(List<ProdutoCadastro> produtoCadastros) {
        mBinding.pbLoadingListProdutos.setVisibility(View.GONE);
        mBinding.rvListaCadastroProduto.setVisibility(ListValidatorUtils.isNullOrEmpty(produtoCadastros) ? View.GONE : View.VISIBLE);
        mBinding.tvAvisoSemProduto.setVisibility(ListValidatorUtils.isNullOrEmpty(produtoCadastros) ? View.VISIBLE : View.GONE);
    }
}