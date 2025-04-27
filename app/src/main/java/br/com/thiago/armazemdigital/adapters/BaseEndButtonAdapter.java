package br.com.thiago.armazemdigital.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import br.com.thiago.armazemdigital.databinding.ListItemButtonNewCadastroBinding;

public abstract class BaseEndButtonAdapter<B extends ViewBinding, I> extends BaseAdapter<B, RecyclerView.ViewHolder, I> {
    private static final int VIEW_TYPE_BUTTON_NEW_CADASTRO_ITEM = 1;
    private static final int VIEW_TYPE_DEFAULT_ITEM = 2;
    private final View.OnClickListener onEndButtonClick;

    public BaseEndButtonAdapter(View.OnClickListener onEndButtonClick) {
        this.onEndButtonClick = onEndButtonClick;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == getItemCount() - 1)
            return VIEW_TYPE_BUTTON_NEW_CADASTRO_ITEM;
        return VIEW_TYPE_DEFAULT_ITEM;
    }

    @NonNull
    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        if(viewType == VIEW_TYPE_BUTTON_NEW_CADASTRO_ITEM)
            return new ButtonNewCadastroViewHolder(ListItemButtonNewCadastroBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        return getItemViewInstance(inflateBinding(LayoutInflater.from(parent.getContext()), parent));
    }

    @Override
    public final void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if(viewHolder instanceof ButtonNewCadastroViewHolder buttonNewCadastroViewHolder) {
            // Botão de novo cadastro, localizado sempre no final da lista.
            buttonNewCadastroViewHolder.btnNovoCadastro.setOnClickListener(onEndButtonClick);
            return;
        }

        // Item default da lista.
        onBindDefaultViewHolder(viewHolder, i);
    }

    @Override
    public final int getItemCount() {
        return super.getItemCount() + 1;
    }

    public abstract void onBindDefaultViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i);

    public static class ButtonNewCadastroViewHolder extends RecyclerView.ViewHolder {
        Button btnNovoCadastro;

        public ButtonNewCadastroViewHolder(@NonNull ListItemButtonNewCadastroBinding binding) {
            super(binding.getRoot());
            btnNovoCadastro = binding.btnCadastrarNovo;
        }
    }
}
