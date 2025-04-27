package br.com.thiago.armazemdigital.fragments.cadastros.produto.selectable;

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
import br.com.thiago.armazemdigital.adapters.cadastro.produto.selectable.ListagemSelectFornecedorAdapter;
import br.com.thiago.armazemdigital.databinding.FragmentSelectFornecedorBinding;
import br.com.thiago.armazemdigital.fragments.BaseListagemFragment;
import br.com.thiago.armazemdigital.model.view.FornecedorCadastro;
import br.com.thiago.armazemdigital.utils.ListValidatorUtils;
import br.com.thiago.armazemdigital.utils.wrapper.LinearLayoutManagerWrapper;
import br.com.thiago.armazemdigital.viewmodel.cadastros.produto.CadastroProdutoViewModel;
import br.com.thiago.armazemdigital.viewmodel.cadastros.produto.selectable.ListagemSelectFornecedorViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SelectFornecedorFragment extends BaseListagemFragment<FragmentSelectFornecedorBinding> {
    private ListagemSelectFornecedorAdapter mAdapter;
    private CadastroProdutoViewModel mCadastroViewModel;

    public SelectFornecedorFragment() {
        // Required empty public constructor
    }

    @Override
    protected FragmentSelectFornecedorBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentSelectFornecedorBinding.inflate(inflater, container, false);
    }

    @Override
    protected void setupViewModel() {
        NavBackStackEntry entry = getNavController().getBackStackEntry(R.id.nav_graph_cadastro_produto);
        mCadastroViewModel = new ViewModelProvider(entry, HiltViewModelFactory.create(requireContext(), entry)).get(CadastroProdutoViewModel.class);
        ListagemSelectFornecedorViewModel mListagemViewModel = new ViewModelProvider(this).get(ListagemSelectFornecedorViewModel.class);

        mCadastroViewModel.getFornecedoresSelecionadosId().observe(getViewLifecycleOwner(), fornecedoresSelecionadosId -> mAdapter.setFornecedoresSelecionados(fornecedoresSelecionadosId));
        mListagemViewModel.getItens().observe(getViewLifecycleOwner(), fornecedores -> {
            mAdapter.setListData(fornecedores);
            showSupplierList(fornecedores);
        });
    }

    @Override
    protected void setupViews() {
        mAdapter = new ListagemSelectFornecedorAdapter(new ArrayList<>());
        mBinding.rvListaCadastroFornecedor.setLayoutManager(new LinearLayoutManagerWrapper(requireActivity()));
        mBinding.rvListaCadastroFornecedor.setAdapter(mAdapter);
        mBinding.btnSelecionarFornecedores.setOnClickListener(v -> {
            mCadastroViewModel.setFornecedoresSelecionadosId(mAdapter.getFornecedoresSelecionados());
            navigateBack();
        });
        mBinding.btnCadastrarFornecedor.setOnClickListener(v -> navigateToFragment(R.id.action_select_fornecedor_fragment_to_cadastro_fornecedor_fragment));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showLoadingList();
    }

    private void showLoadingList() {
        // Inicializa tela com carregamento
        mBinding.pbLoadingListFornecedor.setVisibility(View.VISIBLE);
        mBinding.rvListaCadastroFornecedor.setVisibility(View.GONE);
        mBinding.tvAvisoSemFornecedor.setVisibility(View.GONE);
    }

    private void showSupplierList(List<FornecedorCadastro> fornecedorCadastros) {
        mBinding.pbLoadingListFornecedor.setVisibility(View.GONE);
        mBinding.rvListaCadastroFornecedor.setVisibility(ListValidatorUtils.isNullOrEmpty(fornecedorCadastros) ? View.GONE : View.VISIBLE);
        mBinding.tvAvisoSemFornecedor.setVisibility(ListValidatorUtils.isNullOrEmpty(fornecedorCadastros) ? View.VISIBLE : View.GONE);
    }
}