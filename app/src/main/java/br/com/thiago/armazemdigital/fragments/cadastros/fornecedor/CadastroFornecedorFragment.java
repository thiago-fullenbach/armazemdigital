package br.com.thiago.armazemdigital.fragments.cadastros.fornecedor;

import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import br.com.thiago.armazemdigital.R;
import br.com.thiago.armazemdigital.databinding.FragmentCadastroFornecedorBinding;
import br.com.thiago.armazemdigital.fragments.cadastros.BaseCadastroFragment;
import br.com.thiago.armazemdigital.utils.FormManagerUtils;
import br.com.thiago.armazemdigital.utils.StringValidatorUtils;
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
        mBinding.etNomeFornecedor.setFilters(new InputFilter[]{FormManagerUtils.getInputFilterForFields()});
        mBinding.etTelefoneFornecedor.setFilters(new InputFilter[]{FormManagerUtils.getInputFilterForFields()});
        mBinding.etEmailFornecedor.setFilters(new InputFilter[]{FormManagerUtils.getInputFilterForFields()});
        mBinding.etCepFornecedor.setFilters(new InputFilter[]{FormManagerUtils.getInputFilterForFields()});
        mBinding.etEstadoFornecedor.setFilters(new InputFilter[]{FormManagerUtils.getInputFilterForFields()});
        mBinding.etCidadeFornecedor.setFilters(new InputFilter[]{FormManagerUtils.getInputFilterForFields()});
        mBinding.etBairroFornecedor.setFilters(new InputFilter[]{FormManagerUtils.getInputFilterForFields()});
        mBinding.etLogradouroFornecedor.setFilters(new InputFilter[]{FormManagerUtils.getInputFilterForFields()});
        mBinding.etNumeroFornecedor.setFilters(new InputFilter[]{FormManagerUtils.getInputFilterForFields()});
        mBinding.etComplementoFornecedor.setFilters(new InputFilter[]{FormManagerUtils.getInputFilterForFields()});
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
        mViewModel.setNome(StringValidatorUtils.getSafeStringFromEditable(mBinding.etNomeFornecedor.getText()));
        mViewModel.setTelefone(StringValidatorUtils.getSafeStringFromEditable(mBinding.etTelefoneFornecedor.getText()));
        mViewModel.setEmail(StringValidatorUtils.getSafeStringFromEditable(mBinding.etEmailFornecedor.getText()));
        mViewModel.setCep(StringValidatorUtils.getSafeStringFromEditable(mBinding.etCepFornecedor.getText()));
        mViewModel.setEstado(StringValidatorUtils.getSafeStringFromEditable(mBinding.etEstadoFornecedor.getText()));
        mViewModel.setCidade(StringValidatorUtils.getSafeStringFromEditable(mBinding.etCidadeFornecedor.getText()));
        mViewModel.setBairro(StringValidatorUtils.getSafeStringFromEditable(mBinding.etBairroFornecedor.getText()));
        mViewModel.setLogradouro(StringValidatorUtils.getSafeStringFromEditable(mBinding.etLogradouroFornecedor.getText()));
        mViewModel.setNumero(StringValidatorUtils.getSafeStringFromEditable(mBinding.etNumeroFornecedor.getText()));
        mViewModel.setComplemento(StringValidatorUtils.getSafeStringFromEditable(mBinding.etComplementoFornecedor.getText()));
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
        return StringValidatorUtils.isNullOrEmpty(mBinding.etNomeFornecedor.getError()) &&
                StringValidatorUtils.isNullOrEmpty(mBinding.etTelefoneFornecedor.getError()) &&
                StringValidatorUtils.isNullOrEmpty(mBinding.etEmailFornecedor.getError()) &&
                StringValidatorUtils.isNullOrEmpty(mBinding.etCepFornecedor.getError()) &&
                StringValidatorUtils.isNullOrEmpty(mBinding.etEstadoFornecedor.getError()) &&
                StringValidatorUtils.isNullOrEmpty(mBinding.etCidadeFornecedor.getError()) &&
                StringValidatorUtils.isNullOrEmpty(mBinding.etBairroFornecedor.getError()) &&
                StringValidatorUtils.isNullOrEmpty(mBinding.etLogradouroFornecedor.getError()) &&
                StringValidatorUtils.isNullOrEmpty(mBinding.etNumeroFornecedor.getError()) &&
                StringValidatorUtils.isNullOrEmpty(mBinding.etComplementoFornecedor.getError());
    }

    @Override
    protected void carregarDados(long id) {
        mViewModel.carregarFornecedor(id);
    }
}