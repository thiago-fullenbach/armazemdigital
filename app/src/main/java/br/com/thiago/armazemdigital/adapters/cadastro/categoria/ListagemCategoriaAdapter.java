package br.com.thiago.armazemdigital.adapters.cadastro.categoria;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.function.Consumer;

import br.com.thiago.armazemdigital.R;
import br.com.thiago.armazemdigital.adapters.BaseAdapter;
import br.com.thiago.armazemdigital.databinding.ListItemCadastroCategoriaBinding;
import br.com.thiago.armazemdigital.model.view.CategoriaCadastro;

public class ListagemCategoriaAdapter extends BaseAdapter<ListItemCadastroCategoriaBinding, ListagemCategoriaAdapter.CadastroCategoriaViewHolder, CategoriaCadastro> {
    private final List<CategoriaCadastro> mCategorias;
    private final Consumer<CategoriaCadastro> mListener;
    public ListagemCategoriaAdapter(List<CategoriaCadastro> mCategorias, Consumer<CategoriaCadastro> mListener) {
        this.mCategorias = mCategorias;
        this.mListener = mListener;
    }

    @Override
    protected ListItemCadastroCategoriaBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return ListItemCadastroCategoriaBinding.inflate(inflater, container, false);
    }

    @Override
    protected CadastroCategoriaViewHolder getItemViewInstance(ListItemCadastroCategoriaBinding binding) {
        return new CadastroCategoriaViewHolder(binding);
    }

    @Override
    protected List<CategoriaCadastro> getListItem() {
        return mCategorias;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CadastroCategoriaViewHolder cadastroCategoriaViewHolder) {
            CategoriaCadastro categoria = mCategorias.get(position);
            cadastroCategoriaViewHolder.tvNomeCategoria.setText(categoria.getCategoryName());
            cadastroCategoriaViewHolder.tvDescCategoria.setText(categoria.getCategoryDescription());
            cadastroCategoriaViewHolder.tvQtdProdutos.setText(
                    cadastroCategoriaViewHolder.itemView.getContext().getString(R.string.txt_qtd_produto, categoria.getProductCount()));
            cadastroCategoriaViewHolder.itemView.setOnClickListener(v -> {
                if(mListener != null) {
                    mListener.accept(categoria);
                }
            });
        }
    }

    public static class CadastroCategoriaViewHolder extends RecyclerView.ViewHolder {
        TextView tvNomeCategoria;
        TextView tvDescCategoria;
        TextView tvQtdProdutos;

        public CadastroCategoriaViewHolder(@NonNull ListItemCadastroCategoriaBinding binding) {
            super(binding.getRoot());
            tvNomeCategoria = binding.tvNomeCategoria;
            tvDescCategoria = binding.tvDescCategoria;
            tvQtdProdutos = binding.tvQtdProdutos;
        }
    }
}
