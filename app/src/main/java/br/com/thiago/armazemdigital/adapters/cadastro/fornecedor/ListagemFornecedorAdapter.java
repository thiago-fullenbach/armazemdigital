package br.com.thiago.armazemdigital.adapters.cadastro.fornecedor;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.function.Consumer;

import br.com.thiago.armazemdigital.R;
import br.com.thiago.armazemdigital.adapters.BaseAdapter;
import br.com.thiago.armazemdigital.databinding.ListItemCadastroFornecedorBinding;
import br.com.thiago.armazemdigital.model.view.FornecedorCadastro;

public class ListagemFornecedorAdapter extends BaseAdapter<ListItemCadastroFornecedorBinding, ListagemFornecedorAdapter.CadastroFornecedorViewHolder, FornecedorCadastro> {
    private final List<FornecedorCadastro> mFornecedores;
    private final Consumer<FornecedorCadastro> mListener;

    public ListagemFornecedorAdapter(List<FornecedorCadastro> mFornecedores, Consumer<FornecedorCadastro> mListener) {
        this.mFornecedores = mFornecedores;
        this.mListener = mListener;
    }

    @Override
    protected ListItemCadastroFornecedorBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return ListItemCadastroFornecedorBinding.inflate(inflater, container, false);
    }

    @Override
    protected CadastroFornecedorViewHolder getItemViewInstance(ListItemCadastroFornecedorBinding binding) {
        return new CadastroFornecedorViewHolder(binding);
    }

    @Override
    protected List<FornecedorCadastro> getListItem() {
        return mFornecedores;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CadastroFornecedorViewHolder cadastroFornecedorViewHolder) {
            FornecedorCadastro fornecedor = mFornecedores.get(position);
            cadastroFornecedorViewHolder.tvNomeFornecedor.setText(fornecedor.getSupplierName());
            cadastroFornecedorViewHolder.tvQtdProdutos.setText(
                    cadastroFornecedorViewHolder.itemView.getContext().getString(R.string.txt_qtd_produto, fornecedor.getCountProduct())
            );
            cadastroFornecedorViewHolder.itemView.setOnClickListener(v -> {
                if (mListener != null) {
                    mListener.accept(fornecedor);
                }
            });
        }
    }

    public static class CadastroFornecedorViewHolder extends RecyclerView.ViewHolder {
        TextView tvNomeFornecedor;
        TextView tvQtdProdutos;

        public CadastroFornecedorViewHolder(@NonNull ListItemCadastroFornecedorBinding binding) {
            super(binding.getRoot());
            tvNomeFornecedor = binding.tvNomeFornecedor;
            tvQtdProdutos = binding.tvQtdProdutos;
        }
    }
}
