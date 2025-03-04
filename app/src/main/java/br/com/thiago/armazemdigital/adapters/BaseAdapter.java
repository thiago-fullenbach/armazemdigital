package br.com.thiago.armazemdigital.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.thiago.armazemdigital.R;

public abstract class BaseAdapter<V extends RecyclerView.ViewHolder, I> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ITEM_VIEW = 1;
    private static final int LOADING_VIEW = 2;
    protected boolean isLoading = false;

    @NonNull
    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_VIEW) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(getLayoutId(), parent, false);
            return getItemViewInstance(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_loading, parent, false);
            return new LoadingView(view);
        }
    }

    @Override
    public final int getItemCount() {
        return getListItem().size();
    }

    @Override
    public final int getItemViewType(int position) {
        return (position == getListItem().size()) ? LOADING_VIEW : ITEM_VIEW;
    }

    @SuppressLint("NotifyDataSetChanged")
    public final void showIsLoading(boolean show) {
        isLoading = show;
        notifyDataSetChanged();
    }

    public final void setListData(List<I> itens) {
        getListItem().clear();
        getListItem().addAll(itens);
        notifyItemRangeInserted(0, itens.size());
    }

    protected abstract int getLayoutId();

    protected abstract V getItemViewInstance(View v);

    protected abstract List<I> getListItem();

    protected static class LoadingView extends RecyclerView.ViewHolder {
        public LoadingView(@NonNull View itemView) {
            super(itemView);
        }
    }
}
