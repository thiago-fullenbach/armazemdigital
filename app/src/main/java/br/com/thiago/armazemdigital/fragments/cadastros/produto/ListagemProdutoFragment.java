package br.com.thiago.armazemdigital.fragments.cadastros.produto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.thiago.armazemdigital.ArmazemDigitalApp;
import br.com.thiago.armazemdigital.R;
import br.com.thiago.armazemdigital.adapters.cadastro.produto.ListagemProdutosAdapter;
import br.com.thiago.armazemdigital.database.repository.view.ProdutoCadastroRepository;
import br.com.thiago.armazemdigital.databinding.FragmentListagemProdutoBinding;
import br.com.thiago.armazemdigital.fragments.BaseFragment;
import br.com.thiago.armazemdigital.model.view.ProdutoCadastro;
import br.com.thiago.armazemdigital.utils.ListUtil;
import br.com.thiago.armazemdigital.viewmodel.ListagemProdutosViewModel;
import br.com.thiago.armazemdigital.viewmodel.factory.ListagemCadastradoProdutosViewModelFactory;

public class ListagemProdutoFragment extends BaseFragment<FragmentListagemProdutoBinding> {
    private ListagemProdutosViewModel mViewModel;
    private ListagemProdutosAdapter mAdapter;
    private boolean isLoading = false;

    public ListagemProdutoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ProdutoCadastroRepository produtoCadastroRepository = new ProdutoCadastroRepository(ArmazemDigitalApp.getDbInstance(requireActivity().getApplicationContext()).produtoCadastroDao());
        ListagemCadastradoProdutosViewModelFactory factory = new ListagemCadastradoProdutosViewModelFactory(produtoCadastroRepository);

        mAdapter = new ListagemProdutosAdapter(new ArrayList<>());

        mViewModel = new ViewModelProvider(this, factory).get(ListagemProdutosViewModel.class);

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
    protected FragmentListagemProdutoBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentListagemProdutoBinding.inflate(inflater, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.btnSalvarCadastroProduto.setOnClickListener(v -> {
            NavController navController = NavHostFragment.findNavController(this);
            navController.navigate(R.id.action_item_to_cadastro_produto_fragment);
        });

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

    private void showProductList(List<ProdutoCadastro> produtoCadastros) {
        mBinding.rvListaCadastroProduto.setVisibility(ListUtil.isNullOrEmpty(produtoCadastros) ? View.GONE : View.VISIBLE);
        mBinding.tvAvisoSemProduto.setVisibility(ListUtil.isNullOrEmpty(produtoCadastros) ? View.VISIBLE : View.GONE);
    }
}