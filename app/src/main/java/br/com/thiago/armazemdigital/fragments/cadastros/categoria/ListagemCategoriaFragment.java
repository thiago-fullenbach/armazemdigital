package br.com.thiago.armazemdigital.fragments.cadastros.categoria;

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
import br.com.thiago.armazemdigital.adapters.cadastro.categoria.ListagemCategoriaAdapter;
import br.com.thiago.armazemdigital.databinding.FragmentListagemCategoriaBinding;
import br.com.thiago.armazemdigital.fragments.cadastros.BaseListagemFragment;
import br.com.thiago.armazemdigital.model.view.CategoriaCadastro;
import br.com.thiago.armazemdigital.utils.ListUtils;
import br.com.thiago.armazemdigital.utils.wrapper.LinearLayoutManagerWrapper;
import br.com.thiago.armazemdigital.viewmodel.cadastros.categoria.ListagemCategoriasViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ListagemCategoriaFragment extends BaseListagemFragment<FragmentListagemCategoriaBinding> {
    private ListagemCategoriaAdapter mAdapter;

    public ListagemCategoriaFragment() {
        // Required empty public constructor
    }

    @Override
    protected FragmentListagemCategoriaBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentListagemCategoriaBinding.inflate(inflater, container, false);
    }

    @Override
    protected void setupViewModel() {
        // Inicializa ViewModel e suas dependências
        ListagemCategoriasViewModel mViewModel = new ViewModelProvider(this).get(ListagemCategoriasViewModel.class);

        // Adiciona observáveis
        mViewModel.getItens().observe(getViewLifecycleOwner(), categorias -> {
            // Atualiza lista
            mAdapter.setListData(categorias);
            showProductList(categorias);
        });
    }

    @Override
    protected void setupViews() {
        mAdapter = new ListagemCategoriaAdapter(new ArrayList<>(), categoria -> editCadastro(R.id.action_listagem_categoria_fragment_to_cadastro_categoria_fragment, categoria.getCategoryId()));
        mBinding.rvListaCadastroCategoria.setLayoutManager(new LinearLayoutManagerWrapper(requireActivity()));
        mBinding.rvListaCadastroCategoria.setAdapter(mAdapter);
        mBinding.btnCadastrarCategoria.setOnClickListener(v -> navigateToFragment(R.id.action_listagem_categoria_fragment_to_cadastro_categoria_fragment));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showLoadingList();
    }

    private void showLoadingList() {
        // Inicializa tela com carregamento
        mBinding.pbLoadingListCategorias.setVisibility(View.VISIBLE);
        mBinding.rvListaCadastroCategoria.setVisibility(View.GONE);
        mBinding.tvAvisoSemCategoria.setVisibility(View.GONE);
    }

    private void showProductList(List<CategoriaCadastro> categoriasCadastradas) {
        mBinding.pbLoadingListCategorias.setVisibility(View.GONE);
        mBinding.rvListaCadastroCategoria.setVisibility(ListUtils.isNullOrEmpty(categoriasCadastradas) ? View.GONE : View.VISIBLE);
        mBinding.tvAvisoSemCategoria.setVisibility(ListUtils.isNullOrEmpty(categoriasCadastradas) ? View.VISIBLE : View.GONE);
    }
}