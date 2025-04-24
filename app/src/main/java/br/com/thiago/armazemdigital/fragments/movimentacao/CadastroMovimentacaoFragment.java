package br.com.thiago.armazemdigital.fragments.movimentacao;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.hilt.navigation.HiltViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavBackStackEntry;

import br.com.thiago.armazemdigital.R;
import br.com.thiago.armazemdigital.databinding.FragmentCadastroMovimentacaoBinding;
import br.com.thiago.armazemdigital.fragments.BaseCadastroFragment;
import br.com.thiago.armazemdigital.model.enums.MotivoMovimentacao;
import br.com.thiago.armazemdigital.model.enums.TipoMovimentacao;
import br.com.thiago.armazemdigital.utils.FormManagerUtils;
import br.com.thiago.armazemdigital.utils.StringValidatorUtils;
import br.com.thiago.armazemdigital.utils.WeightFormatterUtils;
import br.com.thiago.armazemdigital.viewmodel.movimentacao.CadastroMovimentacaoViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CadastroMovimentacaoFragment extends BaseCadastroFragment<FragmentCadastroMovimentacaoBinding> {
    private CadastroMovimentacaoViewModel mViewModel;

    public CadastroMovimentacaoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewModel.reset();
    }

    @Override
    protected FragmentCadastroMovimentacaoBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentCadastroMovimentacaoBinding.inflate(inflater, container, false);
    }

    @Override
    protected void setupViewModel() {
        NavBackStackEntry entry = getNavController().getBackStackEntry(R.id.nav_graph_movimentacao);
        mViewModel = new ViewModelProvider(entry, HiltViewModelFactory.create(requireContext(), entry)).get(CadastroMovimentacaoViewModel.class);
        mViewModel.getNameOperator().observe(getViewLifecycleOwner(), name -> validarCampoTextoObrigatorio(mBinding.etNomeOperador, name));
        mViewModel.getQtt().observe(getViewLifecycleOwner(), qtt -> validarCampoTextoObrigatorio(mBinding.etQuantidadeMovimentada, WeightFormatterUtils.weightLongToString(qtt)));
        mViewModel.getType().observe(getViewLifecycleOwner(), type -> {
            updateMotivesAdapter(type);
            validarCampoTextoObrigatorio(mBinding.actvTipoMovimentacao, type == null ? null : type.getDisplayDesc());
        });
        mViewModel.getMotive().observe(getViewLifecycleOwner(), motive -> validarCampoTextoObrigatorio(mBinding.actvMotivo, motive == null ? null : motive.getDisplayDesc()));
        mViewModel.getObservation().observe(getViewLifecycleOwner(), observation -> mBinding.etObservacao.setText(observation));
        mViewModel.getSuccess().observe(getViewLifecycleOwner(), this::onSaveFinished);
    }

    @Override
    protected void setupViews() {
        mBinding.btnCancelarCadastroMovimentacao.setOnClickListener(v -> navigateBack());
        mBinding.btnSalvarCadastroMovimentacao.setOnClickListener(v -> salvarCadastro());

        mBinding.actvTipoMovimentacao.setAdapter(criarAdapter(TipoMovimentacao.values(), TipoMovimentacao::getDisplayDesc));
        mBinding.actvTipoMovimentacao.setOnItemClickListener((adapterView, view, position, id) -> {
            TipoMovimentacao tipoMovimentacao = (TipoMovimentacao) adapterView.getItemAtPosition(position);
            mBinding.actvTipoMovimentacao.setText(tipoMovimentacao.getDisplayDesc(), false);
            mBinding.actvMotivo.setText(null, false);
            updateMotivesAdapter(tipoMovimentacao);
        });
        mBinding.actvTipoMovimentacao.setThreshold(TipoMovimentacao.values().length);

        // Inicia desabilitado até que o tipo de movimentação seja selecionado
        mBinding.actvMotivo.setEnabled(false);
        mBinding.actvMotivo.setOnItemClickListener((adapterView, view, i, l) -> {
            MotivoMovimentacao motivoMovimentacao = (MotivoMovimentacao) adapterView.getItemAtPosition(i);
            mBinding.actvMotivo.setText(motivoMovimentacao.getDisplayDesc(), false);
        });

        mBinding.etNomeOperador.setFilters(new InputFilter[]{FormManagerUtils.getInputFilterForFields()});
        mBinding.etQuantidadeMovimentada.setFilters(new InputFilter[]{FormManagerUtils.getInputFilterForFields()});
        mBinding.etObservacao.setFilters(new InputFilter[]{FormManagerUtils.getInputFilterForFields()});
    }

    private void updateMotivesAdapter(TipoMovimentacao tipoMovimentacao) {
        if(tipoMovimentacao == null) return;
        mBinding.actvMotivo.setEnabled(true);
        MotivoMovimentacao[] motivos = MotivoMovimentacao.getMotivesForType(tipoMovimentacao);
        mBinding.actvMotivo.setAdapter(criarAdapter(motivos, MotivoMovimentacao::getDisplayDesc));
        mBinding.actvMotivo.setThreshold(motivos.length);
    }

    @Override
    protected void reset() {
        mViewModel.reset();
    }

    @Override
    protected String getRegistryName() {
        return getString(R.string.registry_name_movement);
    }

    @Override
    protected void atualizarCampos() {
        mViewModel.setNameOperator(StringValidatorUtils.getSafeStringFromEditable(mBinding.etNomeOperador.getText()));
        mViewModel.setQtt(WeightFormatterUtils.weightStringToLong(StringValidatorUtils.getSafeStringFromEditable(mBinding.etQuantidadeMovimentada.getText())));
        mViewModel.setType(TipoMovimentacao.fromDisplayDesc(StringValidatorUtils.getSafeStringFromEditable(mBinding.actvTipoMovimentacao.getText())));
        mViewModel.setMotive(MotivoMovimentacao.fromDisplayDesc(StringValidatorUtils.getSafeStringFromEditable(mBinding.actvMotivo.getText())));
        mViewModel.setObservation(StringValidatorUtils.getSafeStringFromEditable(mBinding.etObservacao.getText()));
    }

    @Override
    protected void salvarDados(long id) {
        if(id > 0) {
            mViewModel.updateMovimentacao(id);
            return;
        }

        mViewModel.salvarMovimentacao();
    }

    @Override
    protected boolean validarDados() {
        return StringValidatorUtils.isNullOrEmpty(mBinding.etNomeOperador.getError()) &&
                StringValidatorUtils.isNullOrEmpty(mBinding.etQuantidadeMovimentada.getError()) &&
                StringValidatorUtils.isNullOrEmpty(mBinding.actvTipoMovimentacao.getError()) &&
                StringValidatorUtils.isNullOrEmpty(mBinding.actvMotivo.getError());
    }

    @Override
    protected void carregarDados(long id) {
        mViewModel.carregarMovimentacao(id);
    }
}