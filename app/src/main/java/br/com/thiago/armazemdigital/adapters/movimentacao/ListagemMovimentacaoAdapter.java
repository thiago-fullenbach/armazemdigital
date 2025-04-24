package br.com.thiago.armazemdigital.adapters.movimentacao;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.function.Consumer;

import br.com.thiago.armazemdigital.adapters.BaseEndButtonAdapter;
import br.com.thiago.armazemdigital.databinding.ListItemMovimentacaoBinding;
import br.com.thiago.armazemdigital.model.view.MovimentacaoCadastro;
import br.com.thiago.armazemdigital.utils.WeightFormatterUtils;

public class ListagemMovimentacaoAdapter extends BaseEndButtonAdapter<ListItemMovimentacaoBinding, MovimentacaoCadastro> {
    private final List<MovimentacaoCadastro> mMovimentacoes;
    private final Consumer<MovimentacaoCadastro> mListener;

    public ListagemMovimentacaoAdapter(List<MovimentacaoCadastro> mMovimentacoes, Consumer<MovimentacaoCadastro> mListener, View.OnClickListener onEndButtonClick) {
        super(onEndButtonClick);
        this.mMovimentacoes = mMovimentacoes;
        this.mListener = mListener;
    }

    @Override
    public void onBindDefaultViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        if(holder instanceof CadastroMovimentacaoViewHolder cadastroMovimentacaoViewHolder) {
            MovimentacaoCadastro movimentacaoCadastro = mMovimentacoes.get(i);
            // TODO: Adicionar lógica para carregamento da imagem
            cadastroMovimentacaoViewHolder.tvNomeProduto.setText(movimentacaoCadastro.getNameProduct());
            cadastroMovimentacaoViewHolder.tvDescProduto.setText(movimentacaoCadastro.getDescProduct());
            cadastroMovimentacaoViewHolder.tvQuantidadeMovimentacao.setText(WeightFormatterUtils.getFormattedWeight(movimentacaoCadastro.getQttMovement()));
            cadastroMovimentacaoViewHolder.itemView.setOnClickListener(v -> mListener.accept(movimentacaoCadastro));
        }
    }

    @Override
    protected ListItemMovimentacaoBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return ListItemMovimentacaoBinding.inflate(inflater, container, false);
    }

    @Override
    protected RecyclerView.ViewHolder getItemViewInstance(ListItemMovimentacaoBinding binding) {
        return new CadastroMovimentacaoViewHolder(binding);
    }

    @Override
    protected List<MovimentacaoCadastro> getListItem() {
        return mMovimentacoes;
    }

    public static class CadastroMovimentacaoViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImagemCadastro;
        TextView tvNomeProduto;
        TextView tvDescProduto;
        TextView tvQuantidadeMovimentacao;

        public CadastroMovimentacaoViewHolder(@NonNull ListItemMovimentacaoBinding binding) {
            super(binding.getRoot());
            ivImagemCadastro = binding.ivImagemCadastro;
            tvNomeProduto = binding.tvNomeProduto;
            tvDescProduto = binding.tvDescProduto;
            tvQuantidadeMovimentacao = binding.tvQuantidadeMovimentacao;
        }
    }
}
