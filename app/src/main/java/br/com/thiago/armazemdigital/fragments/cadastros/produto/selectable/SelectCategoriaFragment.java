package br.com.thiago.armazemdigital.fragments.cadastros.produto.selectable;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.hilt.navigation.HiltViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavBackStackEntry;

import java.util.ArrayList;
import java.util.List;

import br.com.thiago.armazemdigital.R;
import br.com.thiago.armazemdigital.adapters.cadastro.produto.selectable.ListagemSelectCategoryAdapter;
import br.com.thiago.armazemdigital.databinding.FragmentSelectCategoriaBinding;
import br.com.thiago.armazemdigital.fragments.BaseListagemFragment;
import br.com.thiago.armazemdigital.model.view.CategoriaCadastro;
import br.com.thiago.armazemdigital.utils.DialogCreatorUtils;
import br.com.thiago.armazemdigital.utils.ListValidatorUtils;
import br.com.thiago.armazemdigital.utils.wrapper.LinearLayoutManagerWrapper;
import br.com.thiago.armazemdigital.viewmodel.cadastros.produto.CadastroProdutoViewModel;
import br.com.thiago.armazemdigital.viewmodel.cadastros.produto.selectable.ListagemSelectCategoryViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SelectCategoriaFragment extends BaseListagemFragment<FragmentSelectCategoriaBinding> {
    private ListagemSelectCategoryAdapter mAdapter;
    private CadastroProdutoViewModel mCadastroViewModel;

    public SelectCategoriaFragment() {
        // Required empty public constructor
    }

    @Override
    protected FragmentSelectCategoriaBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentSelectCategoriaBinding.inflate(inflater, container, false);
    }

    @Override
    protected void setupViewModel() {
        NavBackStackEntry entry = getNavController().getBackStackEntry(R.id.nav_graph_cadastro_produto);
        mCadastroViewModel = new ViewModelProvider(entry, HiltViewModelFactory.create(requireContext(), entry)).get(CadastroProdutoViewModel.class);
        ListagemSelectCategoryViewModel mListagemViewModel = new ViewModelProvider(this).get(ListagemSelectCategoryViewModel.class);

        mCadastroViewModel.getCategoriaSelecionadaId().observe(getViewLifecycleOwner(), categoriaSelecionadaId -> mAdapter.setCategoriaSelecionadaId(categoriaSelecionadaId));
        mListagemViewModel.getItens().observe(getViewLifecycleOwner(), categorias -> {
            mAdapter.setListData(categorias);
            showProductList(categorias);
        });
    }

    @Override
    protected void setupViews() {
        mAdapter = new ListagemSelectCategoryAdapter(new ArrayList<>());
        mBinding.rvListaCadastroCategoria.setLayoutManager(new LinearLayoutManagerWrapper(requireActivity()));
        mBinding.rvListaCadastroCategoria.setAdapter(mAdapter);
        mBinding.btnSelecionarCategoria.setOnClickListener(v -> selectCategory(mAdapter.getCategoriaSelecionadaId()));
        mBinding.btnCadastrarCategoria.setOnClickListener(v -> navigateToFragment(R.id.action_select_category_fragment_to_cadastro_categoria_fragment));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requireActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                selectCategory(mAdapter.getCategoriaSelecionadaId());
            }
        });

        showLoadingList();
    }

    private void selectCategory(Long categoryId) {
        if (categoryId == null || categoryId <= 0) {
            AlertDialog dialog = DialogCreatorUtils.createSelectProductCategoryError(requireContext());
            dialog.show();
            return;
        }

        mCadastroViewModel.setCategoriaSelecionadaId(categoryId);
        navigateBack();
    }

    private void showLoadingList() {
        // Inicializa tela com carregamento
        mBinding.pbLoadingListCategorias.setVisibility(View.VISIBLE);
        mBinding.rvListaCadastroCategoria.setVisibility(View.GONE);
        mBinding.tvAvisoSemCategoria.setVisibility(View.GONE);
    }

    private void showProductList(List<CategoriaCadastro> categoriasCadastradas) {
        mBinding.pbLoadingListCategorias.setVisibility(View.GONE);
        mBinding.rvListaCadastroCategoria.setVisibility(ListValidatorUtils.isNullOrEmpty(categoriasCadastradas) ? View.GONE : View.VISIBLE);
        mBinding.tvAvisoSemCategoria.setVisibility(ListValidatorUtils.isNullOrEmpty(categoriasCadastradas) ? View.VISIBLE : View.GONE);
    }
}