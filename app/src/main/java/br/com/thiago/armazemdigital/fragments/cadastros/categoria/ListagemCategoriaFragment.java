package br.com.thiago.armazemdigital.fragments.cadastros.categoria;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import br.com.thiago.armazemdigital.viewmodel.cadastros.categoria.ListagemCategoriasViewModel;
import br.com.thiago.armazemdigital.viewmodel.factory.cadastros.categoria.ListagemCategoriasViewModelFactory;

public class ListagemCategoriaFragment extends BaseFragment<FragmentListagemCategoriaBinding> {
    private ListagemCategoriaAdapter mAdapter;
    private ListagemCategoriasViewModel mViewModel;
    private boolean isLoading = false;

    public ListagemCategoriaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CategoriaDao categoriaDao = ArmazemDigitalApp.getDbInstance(requireActivity().getApplicationContext()).categoriaDao();
        CategoriaRepository categoriaRepository = new CategoriaRepository(categoriaDao);
        ListagemCategoriasViewModelFactory factory = new ListagemCategoriasViewModelFactory(categoriaRepository);

        mAdapter = new ListagemCategoriaAdapter(new ArrayList<>());
        mViewModel = new ViewModelProvider(this, factory).get(ListagemCategoriasViewModel.class);

        mViewModel.isLoading.observe(this, isLoading -> {
            mAdapter.showIsLoading(isLoading);
            this.isLoading = isLoading;
        });

        mViewModel.itens.observe(this, categoria -> {
            mAdapter.setListData(categoria);
            showProductList(categoria);
        });
    }

    @Override
    protected FragmentListagemCategoriaBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentListagemCategoriaBinding.inflate(inflater, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.btnCadastrarCategoria.setOnClickListener(v -> navigateToFragment(R.id.action_listagem_categoria_fragment_to_cadastro_categoria_fragment));

        mBinding.rvListaCadastroCategoria.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (layoutManager == null) return;

                int totalItemCount = layoutManager.getItemCount();
                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();

                if (!isLoading && totalItemCount <= (lastVisibleItem + 3)) {
                    mViewModel.loadMoreItens();
                }
            }
        });
    }

    private void showProductList(List<Categoria> categoriasCadastradas) {
        mBinding.rvListaCadastroCategoria.setVisibility(ListUtil.isNullOrEmpty(categoriasCadastradas) ? View.GONE : View.VISIBLE);
        mBinding.tvAvisoSemCategoria.setVisibility(ListUtil.isNullOrEmpty(categoriasCadastradas) ? View.VISIBLE : View.GONE);
    }
}