package br.com.thiago.armazemdigital.utils.wrapper;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class ArrayAdapterWrapper<T> extends ArrayAdapter<T> {
    public ArrayAdapterWrapper(@NonNull Context context, int resource, @NonNull T[] objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView textView = (TextView) super.getView(position, convertView, parent);
        textView.setText(getDisplayName(getItem(position)));
        return textView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
        textView.setText(getDisplayName(getItem(position)));
        return textView;
    }

    protected abstract String getDisplayName(T object);
}
