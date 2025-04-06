package br.com.thiago.armazemdigital.fragments.configuracao;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import br.com.thiago.armazemdigital.databinding.FragmentConfiguracaoBinding;
import br.com.thiago.armazemdigital.fragments.BaseFragment;
import br.com.thiago.armazemdigital.viewmodel.configuracao.ConfiguracaoViewModel;
import ch.qos.logback.classic.Level;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ConfiguracaoFragment extends BaseFragment<FragmentConfiguracaoBinding> {
    private ConfiguracaoViewModel mViewModel;
    private final Level[] mLevels = new Level[]{Level.ALL, Level.TRACE, Level.DEBUG, Level.INFO, Level.WARN, Level.ERROR, Level.OFF};

    public ConfiguracaoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected FragmentConfiguracaoBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentConfiguracaoBinding.inflate(inflater, container, false);
    }

    @Override
    protected void setupViewModel() {
        mViewModel = new ViewModelProvider(this).get(ConfiguracaoViewModel.class);
        mViewModel.getLevel().observe(this, level -> mBinding.actvNivelLog.setText(level.toString()));
    }

    @Override
    protected void setupViews() {
        mBinding.actvNivelLog.setAdapter(criarAdapter(mLevels, Level::toString));
        mBinding.actvNivelLog.setOnItemClickListener((adapterView, view, position, id) -> {
            Level level = mLevels[position];
            mViewModel.setLevel(level);
        });
        mBinding.actvNivelLog.setThreshold(mLevels.length);
    }
}