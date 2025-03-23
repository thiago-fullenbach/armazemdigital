package br.com.thiago.armazemdigital.utils.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;

public class InstantAutoCompleteView extends AppCompatAutoCompleteTextView {
    public InstantAutoCompleteView(@NonNull Context context) {
        super(context);
    }

    public InstantAutoCompleteView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public InstantAutoCompleteView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean enoughToFilter() {
        return true;
    }

    @Override
    protected void performFiltering(CharSequence text, int keyCode) {
        super.performFiltering("", keyCode);
    }
}
