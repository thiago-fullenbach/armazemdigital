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
import br.com.thiago.armazemdigital.adapters.cadastro.produto.ListagemCadastroProdutosAdapter;
import br.com.thiago.armazemdigital.database.repository.view.ProdutoCadastroRepository;
import br.com.thiago.armazemdigital.databinding.FragmentListagemCadastroProdutoBinding;
import br.com.thiago.armazemdigital.fragments.BaseFragment;
import br.com.thiago.armazemdigital.model.view.ProdutoCadastro;
import br.com.thiago.armazemdigital.utils.ListUtil;
import br.com.thiago.armazemdigital.viewmodel.ListagemCadastradoProdutosViewModel;
import br.com.thiago.armazemdigital.viewmodel.factory.ListagemCadastradoProdutosViewModelFactory;

public class ListagemCadastroProdutoFragment extends BaseFragment<FragmentListagemCadastroProdutoBinding> {
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
    protected FragmentListagemCadastroProdutoBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentListagemCadastroProdutoBinding.inflate(inflater, container, false);
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