package br.com.thiago.armazemdigital.fragments.cadastros.fornecedor;

import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import br.com.thiago.armazemdigital.R;
import br.com.thiago.armazemdigital.databinding.FragmentCadastroFornecedorBinding;
import br.com.thiago.armazemdigital.fragments.cadastros.BaseCadastroFragment;
import br.com.thiago.armazemdigital.utils.FormUtils;
import br.com.thiago.armazemdigital.utils.StringUtils;
import br.com.thiago.armazemdigital.viewmodel.cadastros.fornecedor.CadastroFornecedorViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CadastroFornecedorFragment extends BaseCadastroFragment<FragmentCadastroFornecedorBinding> {
    private CadastroFornecedorViewModel mViewModel;

    public CadastroFornecedorFragment() {
        // Required empty public constructor
    }

    @Override
    protected FragmentCadastroFornecedorBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentCadastroFornecedorBinding.inflate(inflater, container, false);
    }

    @Override
    protected void setupViewModel() {
        mViewModel = new ViewModelProvider(this).get(CadastroFornecedorViewModel.class);

        // Adiciona observáveis
        mViewModel.getNome().observe(getViewLifecycleOwner(), nome -> validarCampoTextoObrigatorio(mBinding.etNomeFornecedor, nome));
        mViewModel.getTelefone().observe(getViewLifecycleOwner(), telefone -> validarCampoTextoObrigatorio(mBinding.etTelefoneFornecedor, telefone));
        mViewModel.getEmail().observe(getViewLifecycleOwner(), email -> validarCampoTextoObrigatorio(mBinding.etEmailFornecedor, email));
        mViewModel.getCep().observe(getViewLifecycleOwner(), cep -> validarCampoTextoObrigatorio(mBinding.etCepFornecedor, cep));
        mViewModel.getEstado().observe(getViewLifecycleOwner(), estado -> validarCampoTextoObrigatorio(mBinding.etEstadoFornecedor, estado));
        mViewModel.getCidade().observe(getViewLifecycleOwner(), cidade -> validarCampoTextoObrigatorio(mBinding.etCidadeFornecedor, cidade));
        mViewModel.getBairro().observe(getViewLifecycleOwner(), bairro -> validarCampoTextoObrigatorio(mBinding.etBairroFornecedor, bairro));
        mViewModel.getLogradouro().observe(getViewLifecycleOwner(), logradouro -> validarCampoTextoObrigatorio(mBinding.etLogradouroFornecedor, logradouro));

        // Campos não obrigatórios
        mViewModel.getNumero().observe(getViewLifecycleOwner(), numero -> mBinding.etNumeroFornecedor.setText(numero));
        mViewModel.getComplemento().observe(getViewLifecycleOwner(), complemento -> mBinding.etComplementoFornecedor.setText(complemento));

        // Sucesso
        mViewModel.getSuccess().observe(getViewLifecycleOwner(), this::onSaveFinished);
    }

    @Override
    protected void setupViews() {
        mBinding.btnCancelarCadastroCategoria.setOnClickListener(v -> cancelaCadastro());
        mBinding.btnSalvarCadastroCategoria.setOnClickListener(v -> salvarCadastro());

        // Adiciona filtros de campos
        mBinding.etNomeFornecedor.setFilters(new InputFilter[]{FormUtils.getInputFilterForFields()});
        mBinding.etTelefoneFornecedor.setFilters(new InputFilter[]{FormUtils.getInputFilterForFields()});
        mBinding.etEmailFornecedor.setFilters(new InputFilter[]{FormUtils.getInputFilterForFields()});
        mBinding.etCepFornecedor.setFilters(new InputFilter[]{FormUtils.getInputFilterForFields()});
        mBinding.etEstadoFornecedor.setFilters(new InputFilter[]{FormUtils.getInputFilterForFields()});
        mBinding.etCidadeFornecedor.setFilters(new InputFilter[]{FormUtils.getInputFilterForFields()});
        mBinding.etBairroFornecedor.setFilters(new InputFilter[]{FormUtils.getInputFilterForFields()});
        mBinding.etLogradouroFornecedor.setFilters(new InputFilter[]{FormUtils.getInputFilterForFields()});
        mBinding.etNumeroFornecedor.setFilters(new InputFilter[]{FormUtils.getInputFilterForFields()});
        mBinding.etComplementoFornecedor.setFilters(new InputFilter[]{FormUtils.getInputFilterForFields()});
    }

    @Override
    protected void reset() {
        mViewModel.reset();
    }

    @Override
    protected String getRegistryName() {
        return getString(R.string.registry_name_supplier);
    }

    @Override
    protected void atualizarCampos() {
        mViewModel.setNome(StringUtils.getSafeStringFromEditable(mBinding.etNomeFornecedor.getText()));
        mViewModel.setTelefone(StringUtils.getSafeStringFromEditable(mBinding.etTelefoneFornecedor.getText()));
        mViewModel.setEmail(StringUtils.getSafeStringFromEditable(mBinding.etEmailFornecedor.getText()));
        mViewModel.setCep(StringUtils.getSafeStringFromEditable(mBinding.etCepFornecedor.getText()));
        mViewModel.setEstado(StringUtils.getSafeStringFromEditable(mBinding.etEstadoFornecedor.getText()));
        mViewModel.setCidade(StringUtils.getSafeStringFromEditable(mBinding.etCidadeFornecedor.getText()));
        mViewModel.setBairro(StringUtils.getSafeStringFromEditable(mBinding.etBairroFornecedor.getText()));
        mViewModel.setLogradouro(StringUtils.getSafeStringFromEditable(mBinding.etLogradouroFornecedor.getText()));
        mViewModel.setNumero(StringUtils.getSafeStringFromEditable(mBinding.etNumeroFornecedor.getText()));
        mViewModel.setComplemento(StringUtils.getSafeStringFromEditable(mBinding.etComplementoFornecedor.getText()));
    }

    @Override
    protected void salvarDados(long id) {
        if (id > 0) {
            mViewModel.updateFornecedor(id);
            return;
        }

        mViewModel.salvarFornecedor();
    }

    @Override
    protected boolean validarDados() {
        return StringUtils.isNullOrEmpty(mBinding.etNomeFornecedor.getError()) &&
                StringUtils.isNullOrEmpty(mBinding.etTelefoneFornecedor.getError()) &&
                StringUtils.isNullOrEmpty(mBinding.etEmailFornecedor.getError()) &&
                StringUtils.isNullOrEmpty(mBinding.etCepFornecedor.getError()) &&
                StringUtils.isNullOrEmpty(mBinding.etEstadoFornecedor.getError()) &&
                StringUtils.isNullOrEmpty(mBinding.etCidadeFornecedor.getError()) &&
                StringUtils.isNullOrEmpty(mBinding.etBairroFornecedor.getError()) &&
                StringUtils.isNullOrEmpty(mBinding.etLogradouroFornecedor.getError()) &&
                StringUtils.isNullOrEmpty(mBinding.etNumeroFornecedor.getError()) &&
                StringUtils.isNullOrEmpty(mBinding.etComplementoFornecedor.getError());
    }

    @Override
    protected void carregarDados(long id) {
        mViewModel.carregarFornecedor(id);
    }
}