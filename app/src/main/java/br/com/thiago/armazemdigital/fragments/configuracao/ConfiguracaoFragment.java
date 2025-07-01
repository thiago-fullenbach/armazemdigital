package br.com.thiago.armazemdigital.fragments.configuracao;

import static android.app.Activity.RESULT_OK;
import static br.com.thiago.armazemdigital.utils.interfaces.Constants.PROPERTY_LOG_DIR_KEY;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.ViewModelProvider;

import br.com.thiago.armazemdigital.R;
import br.com.thiago.armazemdigital.databinding.FragmentConfiguracaoBinding;
import br.com.thiago.armazemdigital.fragments.BaseFragment;
import br.com.thiago.armazemdigital.utils.ToastCreatorUtils;
import br.com.thiago.armazemdigital.viewmodel.configuracao.ConfiguracaoViewModel;
import ch.qos.logback.classic.Level;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ConfiguracaoFragment extends BaseFragment<FragmentConfiguracaoBinding> {
    private static final Level[] mLevels = new Level[]{Level.ALL, Level.TRACE, Level.DEBUG, Level.INFO, Level.WARN, Level.ERROR, Level.OFF};
    private ConfiguracaoViewModel mViewModel;
    private ActivityResultLauncher<Intent> mZipResultLauncher;

    public ConfiguracaoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mZipResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                Uri uri = result.getData().getData();
                if (uri == null) return;

                // Inicia processo de geração do zip
                mViewModel.zipLogFile(
                        requireContext(),
                        System.getProperty(PROPERTY_LOG_DIR_KEY),
                        uri);
            }
        });
    }

    @Override
    protected FragmentConfiguracaoBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentConfiguracaoBinding.inflate(inflater, container, false);
    }

    @Override
    protected void setupViewModel() {
        mViewModel = new ViewModelProvider(requireActivity()).get(ConfiguracaoViewModel.class);
        mViewModel.getLevel().observe(getViewLifecycleOwner(), level -> mBinding.actvNivelLog.setText(level.toString()));
        mViewModel.getLoading().observe(getViewLifecycleOwner(), loading -> mBinding.pbLoadingExtrairLog.setVisibility(loading ? View.VISIBLE : View.GONE));
        mViewModel.getResult().observe(getViewLifecycleOwner(), result -> {
            mViewModel.setLoading(false);
            if (result == null) return;
            if (!result.success()) {
                ToastCreatorUtils.createAndShowToastMessage(
                        requireContext(),
                        result.error(),
                        Toast.LENGTH_LONG);
                mViewModel.reset();
                return;
            }

            ToastCreatorUtils.createAndShowToastMessage(
                    requireContext(),
                    getString(R.string.msg_file_generation_success),
                    Toast.LENGTH_LONG);
            mViewModel.reset();
        });
    }

    @Override
    protected void setupViews() {
        mBinding.actvNivelLog.setAdapter(criarAdapter(mLevels, Level::toString));
        mBinding.actvNivelLog.setOnItemClickListener((adapterView, view, position, id) -> {
            Level level = mLevels[position];
            mViewModel.setLevel(level);
        });
        mBinding.actvNivelLog.setThreshold(mLevels.length);
        mBinding.btnExtrairLog.setOnClickListener(v -> {
            boolean isLoading = Boolean.TRUE.equals(mViewModel.getLoading().getValue());
            if (isLoading) {
                ToastCreatorUtils.createAndShowToastMessage(
                        requireContext(),
                        getString(R.string.error_already_generating_file),
                        Toast.LENGTH_SHORT);
                return;
            }

            mViewModel.setLoading(true);

            // Inicia atividade para selecionar o caminho do arquivo ZIP resultante.
            Intent createDoc = new Intent(Intent.ACTION_CREATE_DOCUMENT);
            createDoc.setType("application/zip");
            createDoc.addCategory(Intent.CATEGORY_OPENABLE);
            createDoc.putExtra(Intent.EXTRA_TITLE, "log.zip");
            mZipResultLauncher.launch(createDoc);
        });
    }
}