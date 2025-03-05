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

import br.com.thiago.armazemdigital.ArmazemDigitalApp;
import br.com.thiago.armazemdigital.R;
import br.com.thiago.armazemdigital.adapters.cadastro.categoria.ListagemCategoriaAdapter;
import br.com.thiago.armazemdigital.database.dao.CategoriaDao;
import br.com.thiago.armazemdigital.database.repository.CategoriaRepository;
import br.com.thiago.armazemdigital.databinding.FragmentListagemCategoriaBinding;
import br.com.thiago.armazemdigital.fragments.BaseFragment;
import br.com.thiago.armazemdigital.model.Categoria;
import br.com.thiago.armazemdigital.utils.ListUtil;
import br.com.thiago.armazemdigital.utils.interfaces.BundleKeys;
import br.com.thiago.armazemdigital.utils.wrapper.LinearLayoutManagerWrapper;
import br.com.thiago.armazemdigital.viewmodel.cadastros.categoria.ListagemCategoriasViewModel;
import br.com.thiago.armazemdigital.viewmodel.factory.cadastros.categoria.ListagemCategoriasViewModelFactory;

public class ListagemCategoriaFragment extends BaseFragment<FragmentListagemCategoriaBinding> {
    private ListagemCategoriaAdapter mAdapter;
    private ListagemCategoriasViewModel mViewModel;

    public ListagemCategoriaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected FragmentListagemCategoriaBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentListagemCategoriaBinding.inflate(inflater, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showLoadingList();
        initViewModel();

        mViewModel.getItens().observe(getViewLifecycleOwner(), categorias -> {
            mAdapter.setListData(categorias);
            showProductList(categorias);
        });

        mAdapter = new ListagemCategoriaAdapter(new ArrayList<>(), categoria -> {
            Bundle bundle = new Bundle();
            bundle.putLong(BundleKeys.ARG_CATEGORIA_ID, categoria.getId());
            navigateToFragment(
                    R.id.action_listagem_categoria_fragment_to_cadastro_categoria_fragment,
                    bundle
            );
        });
        mBinding.rvListaCadastroCategoria.setLayoutManager(new LinearLayoutManagerWrapper(requireActivity()));
        mBinding.rvListaCadastroCategoria.setAdapter(mAdapter);
        mBinding.btnCadastrarCategoria.setOnClickListener(v -> navigateToFragment(R.id.action_listagem_categoria_fragment_to_cadastro_categoria_fragment));
    }

    private void initViewModel() {
        // Inicializa ViewModel e suas dependências
        CategoriaDao categoriaDao = ArmazemDigitalApp.getDbInstance(requireActivity().getApplicationContext()).categoriaDao();
        CategoriaRepository categoriaRepository = new CategoriaRepository(categoriaDao);
        ListagemCategoriasViewModelFactory factory = new ListagemCategoriasViewModelFactory(categoriaRepository);
        mViewModel = new ViewModelProvider(this, factory).get(ListagemCategoriasViewModel.class);
    }

    private void showLoadingList() {
        // Inicializa tela com carregamento
        mBinding.pbLoadingListCategorias.setVisibility(View.VISIBLE);
        mBinding.rvListaCadastroCategoria.setVisibility(View.GONE);
        mBinding.tvAvisoSemCategoria.setVisibility(View.GONE);
    }

    private void showProductList(List<Categoria> categoriasCadastradas) {
        mBinding.pbLoadingListCategorias.setVisibility(View.GONE);
        mBinding.rvListaCadastroCategoria.setVisibility(ListUtil.isNullOrEmpty(categoriasCadastradas) ? View.GONE : View.VISIBLE);
        mBinding.tvAvisoSemCategoria.setVisibility(ListUtil.isNullOrEmpty(categoriasCadastradas) ? View.VISIBLE : View.GONE);
    }
}