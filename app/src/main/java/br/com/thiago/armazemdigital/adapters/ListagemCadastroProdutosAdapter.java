package br.com.thiago.armazemdigital.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.thiago.armazemdigital.R;
import br.com.thiago.armazemdigital.model.view.ProdutoCadastro;
import br.com.thiago.armazemdigital.utils.MoneyUtil;

public class ListagemCadastroProdutosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int CADASTRO_VIEW = 1;
    private static final int LOADING_VIEW = 2;
    private final List<ProdutoCadastro> mProdutos;
    private boolean isLoading = false;

    public ListagemCadastroProdutosAdapter(List<ProdutoCadastro> mProdutos) {
        this.mProdutos = mProdutos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == LOADING_VIEW) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_loading, parent, false);
            return new LoadingViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_cadastro_produto, parent, false);
            return new CadastroProdutoViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof CadastroProdutoViewHolder produtoViewHolder) {
            ProdutoCadastro produto = mProdutos.get(position);
            // TODO: Adicionar lógica para carregamento da imagem
            produtoViewHolder.tvNomeProduto.setText(produto.getNameProduct());
            produtoViewHolder.tvDescProduto.setText(produto.getDescProduct());
            produtoViewHolder.tvCategoriaProduto.setText(produto.getNameCategory());
            produtoViewHolder.tvPrecoProduto.setText(MoneyUtil.getFormattedMoney(produto.getPriceProduct()));
        }
    }

    @Override
    public int getItemCount() {
        return mProdutos.size() + (isLoading ? 1 : 0);
    }

    @Override
    public int getItemViewType(int position) {
        return (position == mProdutos.size()) ? LOADING_VIEW : CADASTRO_VIEW;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void showIsLoading(boolean show) {
        isLoading = show;
        notifyDataSetChanged();
    }

    public void setListData(List<ProdutoCadastro> produtos) {
        mProdutos.clear();
        mProdutos.addAll(produtos);
        notifyItemRangeInserted(0, produtos.size());
    }

    public static class CadastroProdutoViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImagemCadastro;
        TextView tvNomeProduto;
        TextView tvDescProduto;
        TextView tvCategoriaProduto;
        TextView tvPrecoProduto;

        public CadastroProdutoViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public static class LoadingViewHolder extends RecyclerView.ViewHolder {
        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
