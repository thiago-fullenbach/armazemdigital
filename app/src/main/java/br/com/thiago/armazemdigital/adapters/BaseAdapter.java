package br.com.thiago.armazemdigital.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import java.util.List;

public abstract class BaseAdapter<B extends ViewBinding, V extends RecyclerView.ViewHolder, I> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected B mBindingItem;

    @NonNull
    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mBindingItem = inflateBinding(LayoutInflater.from(parent.getContext()), parent);
        return getItemViewInstance(mBindingItem);
    }

    @Override
    public final int getItemCount() {
        return getListItem().size();
    }

    public final void setListData(@Nullable List<I> itens) {
        if(itens != null) {
            getListItem().clear();
            getListItem().addAll(itens);
            notifyItemRangeInserted(0, itens.size());
        }
    }

    protected abstract B inflateBinding(LayoutInflater inflater, ViewGroup container);

    protected abstract V getItemViewInstance(B binding);

    protected abstract List<I> getListItem();
}
