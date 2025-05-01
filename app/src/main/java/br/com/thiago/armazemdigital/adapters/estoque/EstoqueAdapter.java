package br.com.thiago.armazemdigital.adapters.estoque;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.thiago.armazemdigital.R;
import br.com.thiago.armazemdigital.adapters.BaseAdapter;
import br.com.thiago.armazemdigital.databinding.ListItemEstoqueBinding;
import br.com.thiago.armazemdigital.model.view.ProdutoEstoque;
import br.com.thiago.armazemdigital.utils.WeightFormatterUtils;

public class EstoqueAdapter extends BaseAdapter<ListItemEstoqueBinding, EstoqueAdapter.ProdutoEstoqueViewHolder, ProdutoEstoque> {
    private final List<ProdutoEstoque> mProdutosEstoque;

    public EstoqueAdapter(List<ProdutoEstoque> mProdutosEstoque) {
        this.mProdutosEstoque = mProdutosEstoque;
    }

    @Override
    protected ListItemEstoqueBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return ListItemEstoqueBinding.inflate(inflater, container, false);
    }

    @Override
    protected List<ProdutoEstoque> getItemList() {
        return mProdutosEstoque;
    }

    @Override
    protected ProdutoEstoqueViewHolder getItemViewInstance(ListItemEstoqueBinding binding) {
        return new ProdutoEstoqueViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        if(holder instanceof ProdutoEstoqueViewHolder produtoViewHolder) {
            ProdutoEstoque produtoEstoque = mProdutosEstoque.get(i);
            // TODO: Adicionar lógica para carregamento da imagem
            produtoViewHolder.tvNomeProduto.setText(produtoEstoque.getNameProduct());
            produtoViewHolder.tvDescProduto.setText(produtoEstoque.getDescProduct());
            produtoViewHolder.tvQuantidadeAtual.setText(getContext().getString(
                    R.string.placeholder_stock_default, WeightFormatterUtils.getFormattedWeight(produtoEstoque.getQtt()), produtoEstoque.getUnidade().getAcronym()));
        }
    }

    public static class ProdutoEstoqueViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImagemCadastro;
        TextView tvNomeProduto;
        TextView tvDescProduto;
        TextView tvQuantidadeAtual;

        public ProdutoEstoqueViewHolder(@NonNull ListItemEstoqueBinding binding) {
            super(binding.getRoot());
            ivImagemCadastro = binding.ivImagemCadastro;
            tvNomeProduto = binding.tvNomeProduto;
            tvDescProduto = binding.tvDescProduto;
            tvQuantidadeAtual = binding.tvQuantidadeAtual;
        }
    }
}
