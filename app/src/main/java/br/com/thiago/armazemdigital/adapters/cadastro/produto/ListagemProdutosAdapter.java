package br.com.thiago.armazemdigital.adapters.cadastro.produto;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.thiago.armazemdigital.adapters.BaseAdapter;
import br.com.thiago.armazemdigital.databinding.ListItemCadastroProdutoBinding;
import br.com.thiago.armazemdigital.model.view.ProdutoCadastro;
import br.com.thiago.armazemdigital.utils.MoneyUtil;

public class ListagemProdutosAdapter extends BaseAdapter<ListItemCadastroProdutoBinding, ListagemProdutosAdapter.CadastroProdutoViewHolder, ProdutoCadastro> {
    private final List<ProdutoCadastro> mProdutos;

    public ListagemProdutosAdapter(List<ProdutoCadastro> mProdutos) {
        this.mProdutos = mProdutos;
    }

    @Override
    protected ListItemCadastroProdutoBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return ListItemCadastroProdutoBinding.inflate(inflater, container, false);
    }

    @Override
    protected CadastroProdutoViewHolder getItemViewInstance(ListItemCadastroProdutoBinding binding) {
        return new CadastroProdutoViewHolder(binding);
    }

    @Override
    protected List<ProdutoCadastro> getListItem() {
        return mProdutos;
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

    public static class CadastroProdutoViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImagemCadastro;
        TextView tvNomeProduto;
        TextView tvDescProduto;
        TextView tvCategoriaProduto;
        TextView tvPrecoProduto;

        public CadastroProdutoViewHolder(@NonNull ListItemCadastroProdutoBinding binding) {
            super(binding.getRoot());
            ivImagemCadastro = binding.ivImagemCadastro;
            tvNomeProduto = binding.tvNomeProduto;
            tvDescProduto = binding.tvDescProduto;
            tvCategoriaProduto = binding.tvCategoriaProd;
            tvPrecoProduto = binding.tvPrecoProd;
        }
    }
}
