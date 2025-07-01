package br.com.thiago.armazemdigital.fragments.cadastros.fornecedor;

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
import br.com.thiago.armazemdigital.adapters.cadastro.fornecedor.ListagemFornecedorAdapter;
import br.com.thiago.armazemdigital.databinding.FragmentListagemFornecedorBinding;
import br.com.thiago.armazemdigital.fragments.BaseListagemFragment;
import br.com.thiago.armazemdigital.model.view.FornecedorCadastro;
import br.com.thiago.armazemdigital.utils.ListValidatorUtils;
import br.com.thiago.armazemdigital.utils.wrapper.LinearLayoutManagerWrapper;
import br.com.thiago.armazemdigital.viewmodel.cadastros.fornecedor.ListagemFornecedorViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ListagemFornecedorFragment extends BaseListagemFragment<FragmentListagemFornecedorBinding> {
    private ListagemFornecedorAdapter mAdapter;

    public ListagemFornecedorFragment() {
        // Required empty public constructor
    }

    @Override
    protected FragmentListagemFornecedorBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentListagemFornecedorBinding.inflate(inflater, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showLoadingList();
    }

    @Override
    protected void setupViewModel() {
        ListagemFornecedorViewModel mViewModel = new ViewModelProvider(this).get(ListagemFornecedorViewModel.class);
        mViewModel.getItens().observe(getViewLifecycleOwner(), fornecedoresCadastro -> {
            mAdapter.setListData(fornecedoresCadastro);
            showSupplierList(fornecedoresCadastro);
        });
    }

    @Override
    protected void setupViews() {
        mAdapter = new ListagemFornecedorAdapter(new ArrayList<>(), fornecedor -> editCadastro(R.id.action_listagem_fornecedor_fragment_to_cadastro_fornecedor_fragment, fornecedor.getSupplierId()));
        mBinding.rvListaCadastroFornecedor.setLayoutManager(new LinearLayoutManagerWrapper(requireContext()));
        mBinding.rvListaCadastroFornecedor.setAdapter(mAdapter);
        mBinding.btnCadastrarFornecedor.setOnClickListener(v -> navigateToFragment(R.id.action_listagem_fornecedor_fragment_to_cadastro_fornecedor_fragment));
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