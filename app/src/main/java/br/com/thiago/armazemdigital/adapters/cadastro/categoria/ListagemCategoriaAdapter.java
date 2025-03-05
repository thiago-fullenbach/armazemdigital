package br.com.thiago.armazemdigital.adapters.cadastro.categoria;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.thiago.armazemdigital.adapters.BaseAdapter;
import br.com.thiago.armazemdigital.databinding.ListItemCadastroCategoriaBinding;
import br.com.thiago.armazemdigital.model.Categoria;

public class ListagemCategoriaAdapter extends BaseAdapter<ListItemCadastroCategoriaBinding, ListagemCategoriaAdapter.CadastroCategoriaViewHolder, Categoria> {
    private final List<Categoria> mCategorias;

    public ListagemCategoriaAdapter(List<Categoria> mCategorias) {
        this.mCategorias = mCategorias;
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

        public CadastroCategoriaViewHolder(@NonNull ListItemCadastroCategoriaBinding binding) {
            super(binding.getRoot());
            tvNomeCategoria = binding.tvNomeCategoria;
            tvDescCategoria = binding.tvDescCategoria;
        }
    }
}
