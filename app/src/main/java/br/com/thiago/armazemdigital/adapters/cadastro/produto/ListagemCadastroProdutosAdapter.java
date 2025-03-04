package br.com.thiago.armazemdigital.adapters.cadastro.produto;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.thiago.armazemdigital.R;
import br.com.thiago.armazemdigital.adapters.BaseAdapter;
import br.com.thiago.armazemdigital.model.view.ProdutoCadastro;
import br.com.thiago.armazemdigital.utils.MoneyUtil;

public class ListagemCadastroProdutosAdapter extends BaseAdapter<ListagemCadastroProdutosAdapter.CadastroProdutoViewHolder, ProdutoCadastro> {
    private final List<ProdutoCadastro> mProdutos;

    public ListagemCadastroProdutosAdapter(List<ProdutoCadastro> mProdutos) {
        this.mProdutos = mProdutos;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.list_item_cadastro_produto;
    }

    @Override
    protected CadastroProdutoViewHolder getItemViewInstance(View v) {
        return new CadastroProdutoViewHolder(v);
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

        public CadastroProdutoViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
