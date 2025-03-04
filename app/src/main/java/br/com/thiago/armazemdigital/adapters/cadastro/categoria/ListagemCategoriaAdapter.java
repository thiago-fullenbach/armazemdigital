package br.com.thiago.armazemdigital.adapters.cadastro.categoria;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.thiago.armazemdigital.adapters.BaseAdapter;
import br.com.thiago.armazemdigital.model.Categoria;

public class ListagemCategoriaAdapter extends BaseAdapter<ListagemCategoriaAdapter.CadastroCategoriaViewHolder, Categoria> {
    private final List<Categoria> mCategorias;

    public ListagemCategoriaAdapter(List<Categoria> mCategorias) {
        this.mCategorias = mCategorias;
    }

    @Override
    protected int getLayoutId() {
        return mCategorias.size();
    }

    @Override
    protected CadastroCategoriaViewHolder getItemViewInstance(View v) {
        return new CadastroCategoriaViewHolder(v);
    }

    @Override
    protected List<Categoria> getListItem() {
        return mCategorias;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CadastroCategoriaViewHolder cadastroCategoriaViewHolder) {
            Categoria categoria = mCategorias.get(position);
            cadastroCategoriaViewHolder.tvNomeCategoria.setText(categoria.getName());
            cadastroCategoriaViewHolder.tvDescCategoria.setText(categoria.getDescription());
        }
    }

    public static class CadastroCategoriaViewHolder extends RecyclerView.ViewHolder {
        TextView tvNomeCategoria;
        TextView tvDescCategoria;

        public CadastroCategoriaViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
