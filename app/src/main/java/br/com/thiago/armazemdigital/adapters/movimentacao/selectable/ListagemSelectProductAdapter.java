package br.com.thiago.armazemdigital.adapters.movimentacao.selectable;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

import br.com.thiago.armazemdigital.adapters.BaseAdapter;
import br.com.thiago.armazemdigital.databinding.ListItemSelectableCadastroProdutoBinding;
import br.com.thiago.armazemdigital.model.view.ProdutoCadastro;
import lombok.Getter;

public class ListagemSelectProductAdapter extends BaseAdapter<ListItemSelectableCadastroProdutoBinding, ListagemSelectProductAdapter.SelectableCadastroProdutoViewHolder, ProdutoCadastro> {
    private final List<ProdutoCadastro> mProdutos;

    @Getter
    private Long produtoSelecionadoId = null;

    public ListagemSelectProductAdapter(List<ProdutoCadastro> mProdutos) {
        this.mProdutos = mProdutos;
    }

    @Override
    protected ListItemSelectableCadastroProdutoBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return ListItemSelectableCadastroProdutoBinding.inflate(inflater, container, false);
    }

    @Override
    protected SelectableCadastroProdutoViewHolder getItemViewInstance(ListItemSelectableCadastroProdutoBinding binding) {
        return new SelectableCadastroProdutoViewHolder(binding);
    }

    @Override
    protected List<ProdutoCadastro> getItemList() {
        return mProdutos;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        if(holder instanceof SelectableCadastroProdutoViewHolder selectableCadastroProdutoViewHolder) {
            ProdutoCadastro produto = mProdutos.get(i);
            selectableCadastroProdutoViewHolder.rbProduto.setChecked(Objects.equals(produtoSelecionadoId, produto.getProductId()));
            // TODO: Carregar imagem do produto
            selectableCadastroProdutoViewHolder.tvNomeProduto.setText(produto.getNameProduct());
            selectableCadastroProdutoViewHolder.tvDescProduto.setText(produto.getDescProduct());
            selectableCadastroProdutoViewHolder.rbProduto.setOnClickListener(v -> setProdutoSelecionadoId(produto.getProductId()));
            selectableCadastroProdutoViewHolder.itemView.setOnClickListener(v -> setProdutoSelecionadoId(produto.getProductId()));
        }
    }

    public void setProdutoSelecionadoId(Long produtoSelecionadoId) {
        this.produtoSelecionadoId = produtoSelecionadoId;
        notifyItemRangeChanged(0, mProdutos.size());
    }

    public static class SelectableCadastroProdutoViewHolder extends RecyclerView.ViewHolder {
        RadioButton rbProduto;
        ImageView ivImagemCadastro;
        TextView tvNomeProduto;
        TextView tvDescProduto;


        public SelectableCadastroProdutoViewHolder(@NonNull ListItemSelectableCadastroProdutoBinding binding) {
            super(binding.getRoot());
            this.rbProduto = binding.rbProduto;
            this.ivImagemCadastro = binding.ivImagemCadastro;
            this.tvNomeProduto = binding.tvNomeProduto;
            this.tvDescProduto = binding.tvDescProduto;
        }
    }
}
