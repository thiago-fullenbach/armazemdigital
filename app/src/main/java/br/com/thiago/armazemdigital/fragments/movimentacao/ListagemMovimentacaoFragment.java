package br.com.thiago.armazemdigital.fragments.movimentacao;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import br.com.thiago.armazemdigital.R;
import br.com.thiago.armazemdigital.adapters.movimentacao.ListagemMovimentacaoAdapter;
import br.com.thiago.armazemdigital.databinding.FragmentListagemMovimentacaoBinding;
import br.com.thiago.armazemdigital.fragments.BaseFragment;
import br.com.thiago.armazemdigital.utils.wrapper.LinearLayoutManagerWrapper;
import br.com.thiago.armazemdigital.viewmodel.movimentacao.ListagemMovimentacaoViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ListagemMovimentacaoFragment extends BaseFragment<FragmentListagemMovimentacaoBinding> {
    private ListagemMovimentacaoAdapter mAdapter;

    public ListagemMovimentacaoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showLoadingList();
    }

    @Override
    protected FragmentListagemMovimentacaoBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentListagemMovimentacaoBinding.inflate(inflater, container, false);
    }

    @Override
    protected void setupViewModel() {
        ListagemMovimentacaoViewModel mViewModel = new ViewModelProvider(this).get(ListagemMovimentacaoViewModel.class);
        mViewModel.getItens().observe(this, movimentacoes -> {
            mAdapter.setListData(movimentacoes);
            showMovementList();
        });
    }

    @Override
    protected void setupViews() {
        mAdapter = new ListagemMovimentacaoAdapter(new ArrayList<>(), v -> navigateToFragment(R.id.action_item_menu_movimentacao_to_cadastro_movimentacao_fragment));
        mBinding.rvListaMovimentacao.setLayoutManager(new LinearLayoutManagerWrapper(requireActivity()));
        mBinding.rvListaMovimentacao.setAdapter(mAdapter);
        mBinding.btnSalvarMovimentacoes.setOnClickListener(v -> navigateToFragment(R.id.action_item_menu_movimentacao_to_nav_graph_estoque));

    }

    private void showLoadingList() {
        // Inicializa tela com carregamento
        mBinding.pbLoadingListMovement.setVisibility(View.VISIBLE);
        mBinding.rvListaMovimentacao.setVisibility(View.GONE);
    }

    private void showMovementList() {
        mBinding.pbLoadingListMovement.setVisibility(View.GONE);
        mBinding.rvListaMovimentacao.setVisibility(View.VISIBLE);
    }
}