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

import br.com.thiago.armazemdigital.ArmazemDigitalApp;
import br.com.thiago.armazemdigital.R;
import br.com.thiago.armazemdigital.adapters.cadastro.produto.ListagemProdutosAdapter;
import br.com.thiago.armazemdigital.database.dao.view.ProdutoCadastroDao;
import br.com.thiago.armazemdigital.database.repository.view.ProdutoCadastroRepository;
import br.com.thiago.armazemdigital.databinding.FragmentListagemProdutoBinding;
import br.com.thiago.armazemdigital.fragments.BaseFragment;
import br.com.thiago.armazemdigital.model.view.ProdutoCadastro;
import br.com.thiago.armazemdigital.utils.ListUtil;
import br.com.thiago.armazemdigital.utils.wrapper.LinearLayoutManagerWrapper;
import br.com.thiago.armazemdigital.viewmodel.cadastros.produto.ListagemProdutosViewModel;
import br.com.thiago.armazemdigital.viewmodel.factory.cadastros.produto.ListagemProdutosViewModelFactory;

public class ListagemProdutoFragment extends BaseFragment<FragmentListagemProdutoBinding> {
    private ListagemProdutosViewModel mViewModel;
    private ListagemProdutosAdapter mAdapter;

    public ListagemProdutoFragment() {
        // Required empty public constructor
    }

    @Override
    protected FragmentListagemProdutoBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentListagemProdutoBinding.inflate(inflater, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Inicializa tela com carregamento
        mBinding.pbLoadingListProdutos.setVisibility(View.VISIBLE);
        mBinding.rvListaCadastroProduto.setVisibility(View.GONE);
        mBinding.tvAvisoSemProduto.setVisibility(View.GONE);

        ProdutoCadastroDao produtoCadastroDao = ArmazemDigitalApp.getDbInstance(requireActivity().getApplicationContext()).produtoCadastroDao();
        ProdutoCadastroRepository produtoCadastroRepository = new ProdutoCadastroRepository(produtoCadastroDao);
        ListagemProdutosViewModelFactory factory = new ListagemProdutosViewModelFactory(produtoCadastroRepository);

        mAdapter = new ListagemProdutosAdapter(new ArrayList<>());
        mBinding.rvListaCadastroProduto.setLayoutManager(new LinearLayoutManagerWrapper(requireActivity()));
        mBinding.rvListaCadastroProduto.setAdapter(mAdapter);

        mViewModel = new ViewModelProvider(this, factory).get(ListagemProdutosViewModel.class);

        mViewModel.getItens().observe(getViewLifecycleOwner(), produtos -> {
            mAdapter.setListData(produtos);
            showProductList(produtos);
        });

        mBinding.btnCadastrarProduto.setOnClickListener(v -> navigateToFragment(R.id.action_item_to_cadastro_produto_fragment));
    }

    private void showProductList(List<ProdutoCadastro> produtoCadastros) {
        mBinding.pbLoadingListProdutos.setVisibility(View.GONE);
        mBinding.rvListaCadastroProduto.setVisibility(ListUtil.isNullOrEmpty(produtoCadastros) ? View.GONE : View.VISIBLE);
        mBinding.tvAvisoSemProduto.setVisibility(ListUtil.isNullOrEmpty(produtoCadastros) ? View.VISIBLE : View.GONE);
    }
}