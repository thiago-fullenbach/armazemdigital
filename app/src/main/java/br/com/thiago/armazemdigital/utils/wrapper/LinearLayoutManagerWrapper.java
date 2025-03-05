package br.com.thiago.armazemdigital.utils.wrapper;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * Criado com o intuito de desativa animações preditivas, que podem causar inconsistências.
 */
public class LinearLayoutManagerWrapper extends LinearLayoutManager {
    public LinearLayoutManagerWrapper(Context context) {
        super(context);
    }

    @Override
    public boolean supportsPredictiveItemAnimations() {
        return false;
    }
}
