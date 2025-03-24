package br.com.thiago.armazemdigital.fragments.cadastros.categoria;

import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import br.com.thiago.armazemdigital.databinding.FragmentCadastroCategoriaBinding;
import br.com.thiago.armazemdigital.fragments.cadastros.BaseCadastroFragment;
import br.com.thiago.armazemdigital.utils.FormUtils;
import br.com.thiago.armazemdigital.utils.StringUtil;
import br.com.thiago.armazemdigital.viewmodel.cadastros.categoria.CadastroCategoriaViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CadastroCategoriaFragment extends BaseCadastroFragment<FragmentCadastroCategoriaBinding> {
    private CadastroCategoriaViewModel mViewModel;

    public CadastroCategoriaFragment() {
        // Required empty public constructor
    }

    @Override
    protected FragmentCadastroCategoriaBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentCadastroCategoriaBinding.inflate(inflater, container, false);
    }

    @Override
    protected void atualizarCampos() {
        mViewModel.setNome(StringUtil.getSafeStringFromEditable(mBinding.etNomeCategoria.getText()));
        mViewModel.setDescricao(StringUtil.getSafeStringFromEditable(mBinding.etDescricaoCategoria.getText()));
    }

    @Override
    protected void salvarDados(long id) {
        if (id > 0) {
            mViewModel.updateCategoria(id);
            return;
        }
        mViewModel.salvarCategoria();
    }

    @Override
    protected boolean validarDados() {
        return StringUtil.isNullOrEmpty(mBinding.etNomeCategoria.getError()) &&
                StringUtil.isNullOrEmpty(mBinding.etDescricaoCategoria.getError());
    }

    @Override
    protected void carregarDados(long id) {
        mViewModel.carregarCategoria(id);
    }

    @Override
    protected void setupViewModel() {
        // Instância o viewmodel
        mViewModel = new ViewModelProvider(this).get(CadastroCategoriaViewModel.class);

        // Adiciona observáveis
        mViewModel.getNome().observe(getViewLifecycleOwner(), nome -> validarCampoTextoObrigatorio(mBinding.etNomeCategoria, nome));
        mViewModel.getDescricao().observe(getViewLifecycleOwner(), descricao -> validarCampoTextoObrigatorio(mBinding.etDescricaoCategoria, descricao));
        mViewModel.getSuccess().observe(getViewLifecycleOwner(), this::onSaveFinished);
    }

    @Override
    protected void setupViews() {
        mBinding.btnCancelarCadastroCategoria.setOnClickListener(v -> cancelaCadastro());
        mBinding.btnSalvarCadastroCategoria.setOnClickListener(v -> salvarCadastro());

        mBinding.etNomeCategoria.setFilters(new InputFilter[]{FormUtils.getInputFilterForFields()});
        mBinding.etDescricaoCategoria.setFilters(new InputFilter[]{FormUtils.getInputFilterForFields()});
    }
}