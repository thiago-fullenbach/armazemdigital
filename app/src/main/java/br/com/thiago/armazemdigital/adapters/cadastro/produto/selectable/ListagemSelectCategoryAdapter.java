package br.com.thiago.armazemdigital.adapters.cadastro.produto.selectable;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

import br.com.thiago.armazemdigital.adapters.BaseAdapter;
import br.com.thiago.armazemdigital.databinding.ListItemSelectableCadastroCategoriaBinding;
import br.com.thiago.armazemdigital.model.view.CategoriaCadastro;
import lombok.Getter;

public class ListagemSelectCategoryAdapter extends BaseAdapter<ListItemSelectableCadastroCategoriaBinding, ListagemSelectCategoryAdapter.SelectableCadastroCategoriaViewHolder, CategoriaCadastro> {
    private final List<CategoriaCadastro> mCategorias;

    @Getter
    private Long categoriaSelecionadaId = null;

    public ListagemSelectCategoryAdapter(List<CategoriaCadastro> mCategorias) {
        this.mCategorias = mCategorias;
    }

    @Override
    protected ListItemSelectableCadastroCategoriaBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return ListItemSelectableCadastroCategoriaBinding.inflate(inflater, container, false);
    }

    @Override
    protected SelectableCadastroCategoriaViewHolder getItemViewInstance(ListItemSelectableCadastroCategoriaBinding binding) {
        return new ListagemSelectCategoryAdapter.SelectableCadastroCategoriaViewHolder(binding);
    }

    @Override
    protected List<CategoriaCadastro> getItemList() {
        return mCategorias;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SelectableCadastroCategoriaViewHolder selectableCadastroCategoriaViewHolder) {
            CategoriaCadastro categoria = mCategorias.get(position);

            selectableCadastroCategoriaViewHolder.rbCategoria.setChecked(Objects.equals(categoriaSelecionadaId, categoria.getCategoryId()));
            selectableCadastroCategoriaViewHolder.tvNomeCategoria.setText(categoria.getCategoryName());
            selectableCadastroCategoriaViewHolder.tvDescCategoria.setText(categoria.getCategoryDescription());
            selectableCadastroCategoriaViewHolder.rbCategoria.setOnClickListener(v -> setCategoriaSelecionadaId(categoria.getCategoryId()));
            selectableCadastroCategoriaViewHolder.itemView.setOnClickListener(v -> setCategoriaSelecionadaId(categoria.getCategoryId()));
        }
    }

    public void setCategoriaSelecionadaId(Long categoriaSelecionadaId) {
        this.categoriaSelecionadaId = categoriaSelecionadaId;
        notifyItemRangeChanged(0, mCategorias.size());
    }

    public static class SelectableCadastroCategoriaViewHolder extends RecyclerView.ViewHolder {
        RadioButton rbCategoria;
        TextView tvNomeCategoria;
        TextView tvDescCategoria;

        public SelectableCadastroCategoriaViewHolder(@NonNull ListItemSelectableCadastroCategoriaBinding binding) {
            super(binding.getRoot());
            rbCategoria = binding.rbCategoria;
            tvNomeCategoria = binding.tvNomeCategoria;
            tvDescCategoria = binding.tvDescCategoria;
        }
    }
}
