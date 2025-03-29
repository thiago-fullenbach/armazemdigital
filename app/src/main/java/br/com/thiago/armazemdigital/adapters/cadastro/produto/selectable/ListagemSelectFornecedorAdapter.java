package br.com.thiago.armazemdigital.adapters.cadastro.produto.selectable;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.thiago.armazemdigital.adapters.BaseAdapter;
import br.com.thiago.armazemdigital.databinding.ListItemSelectableCadastroFornecedorBinding;
import br.com.thiago.armazemdigital.model.view.FornecedorCadastro;
import lombok.Getter;

public class ListagemSelectFornecedorAdapter extends BaseAdapter<ListItemSelectableCadastroFornecedorBinding, ListagemSelectFornecedorAdapter.SelectableCadastroFornecedorViewHolder, FornecedorCadastro> {
    private final List<FornecedorCadastro> mFornecedores;

    @Getter
    private final List<Long> fornecedoresSelecionados = new ArrayList<>();

    public ListagemSelectFornecedorAdapter(List<FornecedorCadastro> mFornecedores) {
        this.mFornecedores = mFornecedores;
    }

    @Override
    protected ListItemSelectableCadastroFornecedorBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return ListItemSelectableCadastroFornecedorBinding.inflate(inflater, container, false);
    }

    @Override
    protected SelectableCadastroFornecedorViewHolder getItemViewInstance(ListItemSelectableCadastroFornecedorBinding binding) {
        return new SelectableCadastroFornecedorViewHolder(binding);
    }

    @Override
    protected List<FornecedorCadastro> getListItem() {
        return mFornecedores;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SelectableCadastroFornecedorViewHolder selectableCadastroFornecedor) {
            FornecedorCadastro fornecedor = mFornecedores.get(position);
            selectableCadastroFornecedor.cbFornecedor.setChecked(fornecedoresSelecionados.contains(fornecedor.getSupplierId()));
            selectableCadastroFornecedor.tvNomeFornecedor.setText(fornecedor.getSupplierName());
            selectableCadastroFornecedor.cbFornecedor.setOnClickListener(v -> switchFornecedorSelected((CheckBox) v, fornecedor));
            selectableCadastroFornecedor.itemView.setOnClickListener(v -> switchFornecedorSelected(selectableCadastroFornecedor.cbFornecedor, fornecedor));
        }
    }

    private void switchFornecedorSelected(CheckBox v, FornecedorCadastro fornecedor) {
        if (fornecedoresSelecionados.contains(fornecedor.getSupplierId())) {
            fornecedoresSelecionados.remove(fornecedor.getSupplierId());
            v.setChecked(false);
        } else {
            fornecedoresSelecionados.add(fornecedor.getSupplierId());
            v.setChecked(true);
        }
    }

    public void setFornecedoresSelecionados(List<Long> fornecedoresSelecionados) {
        this.fornecedoresSelecionados.clear();
        this.fornecedoresSelecionados.addAll(fornecedoresSelecionados);
        notifyItemRangeChanged(0, mFornecedores.size());
    }

    public static class SelectableCadastroFornecedorViewHolder extends RecyclerView.ViewHolder {
        CheckBox cbFornecedor;
        TextView tvNomeFornecedor;

        public SelectableCadastroFornecedorViewHolder(@NonNull ListItemSelectableCadastroFornecedorBinding binding) {
            super(binding.getRoot());
            cbFornecedor = binding.cbFornecedor;
            tvNomeFornecedor = binding.tvNomeFornecedor;
        }
    }
}
