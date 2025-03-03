package br.com.thiago.armazemdigital.fragments.cadastros;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.thiago.armazemdigital.ArmazemDigitalApp;
import br.com.thiago.armazemdigital.adapters.ListagemCadastroProdutosAdapter;
import br.com.thiago.armazemdigital.database.repository.view.ProdutoCadastroRepository;
import br.com.thiago.armazemdigital.databinding.FragmentListagemCadastroProdutoBinding;
import br.com.thiago.armazemdigital.model.view.ProdutoCadastro;
import br.com.thiago.armazemdigital.utils.ListUtil;
import br.com.thiago.armazemdigital.viewmodel.ListagemCadastradoProdutosViewModel;
import br.com.thiago.armazemdigital.viewmodel.factory.ListagemCadastradoProdutosViewModelFactory;

public class ListagemCadastroProdutoFragment extends Fragment {
    private FragmentListagemCadastroProdutoBinding mBinding;
    private ListagemCadastradoProdutosViewModel mViewModel;
    private ListagemCadastroProdutosAdapter mAdapter;
    private boolean isLoading = false;

    public ListagemCadastroProdutoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ProdutoCadastroRepository produtoCadastroRepository = new ProdutoCadastroRepository(ArmazemDigitalApp.getDbInstance(requireActivity().getApplicationContext()).produtoCadastroDao());
        ListagemCadastradoProdutosViewModelFactory factory = new ListagemCadastradoProdutosViewModelFactory(produtoCadastroRepository);

        mAdapter = new ListagemCadastroProdutosAdapter(new ArrayList<>());

        mViewModel = new ViewModelProvider(this, factory).get(ListagemCadastradoProdutosViewModel.class);

        mViewModel.isLoading.observe(this, isLoading -> {
            mAdapter.showIsLoading(isLoading);
            this.isLoading = isLoading;
        });

        mViewModel.produtosCadastrados.observe(this, produtos -> {
            mAdapter.setListData(produtos);
            showProductList(produtos);
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mBinding.rvListaCadastroProduto.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentListagemCadastroProdutoBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    private void showProductList(List<ProdutoCadastro> produtoCadastros) {
        mBinding.rvListaCadastroProduto.setVisibility(ListUtil.isNullOrEmpty(produtoCadastros) ? View.GONE : View.VISIBLE);
        mBinding.tvAvisoSemProduto.setVisibility(ListUtil.isNullOrEmpty(produtoCadastros) ? View.VISIBLE : View.GONE);
    }
}