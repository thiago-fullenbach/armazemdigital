package br.com.thiago.armazemdigital.fragments.cadastros.produto;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.hilt.navigation.HiltViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavBackStackEntry;

import java.util.List;
import java.util.Objects;

import br.com.thiago.armazemdigital.R;
import br.com.thiago.armazemdigital.databinding.FragmentCadastroProdutoBinding;
import br.com.thiago.armazemdigital.fragments.BaseCadastroFragment;
import br.com.thiago.armazemdigital.model.enums.TipoUnidade;
import br.com.thiago.armazemdigital.utils.FormManagerUtils;
import br.com.thiago.armazemdigital.utils.MoneyFormatterUtils;
import br.com.thiago.armazemdigital.utils.StringValidatorUtils;
import br.com.thiago.armazemdigital.viewmodel.cadastros.produto.CadastroProdutoViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CadastroProdutoFragment extends BaseCadastroFragment<FragmentCadastroProdutoBinding> {
    private CadastroProdutoViewModel mViewModel;

    public CadastroProdutoFragment() {
        // Required empty public constructor
    }

    @Override
    protected FragmentCadastroProdutoBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentCadastroProdutoBinding.inflate(inflater, container, false);
    }

    @Override
    protected void setupViewModel() {
        NavBackStackEntry entry = getNavController().getBackStackEntry(R.id.nav_graph_cadastro_produto);
        mViewModel = new ViewModelProvider(entry, HiltViewModelFactory.create(requireContext(), entry)).get(CadastroProdutoViewModel.class);
        mViewModel.getNome().observe(getViewLifecycleOwner(), nome -> validarCampoTextoObrigatorio(mBinding.etNomeProduto, nome));
        mViewModel.getDescricao().observe(getViewLifecycleOwner(), descricao -> validarCampoTextoObrigatorio(mBinding.etDescricaoProduto, descricao));
        mViewModel.getPreco().observe(getViewLifecycleOwner(), preco -> validarCampoTextoObrigatorio(mBinding.etPrecoProduto, MoneyFormatterUtils.moneyLongToString(preco)));
        mViewModel.getUnidadeMedidaSelecionada().observe(getViewLifecycleOwner(), tipoUnidade ->
                validarCampoTextoObrigatorio(
                        mBinding.actvUnidadeMedidaProduto,
                        tipoUnidade == null ? "" : tipoUnidade.getName(),
                        List.of(() -> tipoUnidade == null ? getString(R.string.field_error_invalid_unit) : null)));
        mViewModel.getSuccess().observe(getViewLifecycleOwner(), this::onSaveFinished);
    }

    @Override
    protected void setupViews() {
        mBinding.btnCancelarCadastroProduto.setOnClickListener(v -> navigateBack());
        mBinding.btnSalvarCadastroProduto.setOnClickListener(v -> salvarCadastro());

        mBinding.actvUnidadeMedidaProduto.setAdapter(criarAdapter(List.of(TipoUnidade.values()), tipoUnidade -> Objects.requireNonNullElse(tipoUnidade, TipoUnidade.UNIDADE).getName()));
        mBinding.actvUnidadeMedidaProduto.setOnItemClickListener((adapterView, view, position, id) -> {
            TipoUnidade unidade = (TipoUnidade) adapterView.getItemAtPosition(position);
            mBinding.actvUnidadeMedidaProduto.setText(unidade.getName(), false);
        });
        mBinding.actvUnidadeMedidaProduto.setThreshold(TipoUnidade.values().length);

        mBinding.etNomeProduto.setFilters(new InputFilter[]{FormManagerUtils.getInputFilterForFields()});
        mBinding.etDescricaoProduto.setFilters(new InputFilter[]{FormManagerUtils.getInputFilterForFields()});
        mBinding.etPrecoProduto.setFilters(new InputFilter[]{FormManagerUtils.getInputFilterForFields()});
        mBinding.actvUnidadeMedidaProduto.setFilters(new InputFilter[]{FormManagerUtils.getInputFilterForFields()});
        mBinding.btnSelecionarCategoriaProduto.setOnClickListener(v -> {
            atualizarCampos();  // Atualiza campos, evitando perda de dados na transação entre fragments
            reset();            // Reseta flag de sucesso
            navigateToFragment(R.id.action_cadastro_produto_fragment_to_select_category_fragment);
        });
        mBinding.btnSelecionarFornecedorProduto.setOnClickListener(v -> {
            atualizarCampos();  // Atualiza campos, evitando perda de dados na transação entre fragments
            reset();            // Reseta flag de sucesso
            navigateToFragment(R.id.action_cadastro_produto_fragment_to_select_fornecedor_fragment);
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void reset() {
        mViewModel.reset();
    }

    @Override
    protected String getRegistryName() {
        return getString(R.string.registry_name_product);
    }

    @Override
    protected void atualizarCampos() {
        mViewModel.setNome(StringValidatorUtils.getSafeStringFromEditable(mBinding.etNomeProduto.getText()));
        mViewModel.setDescricao(StringValidatorUtils.getSafeStringFromEditable(mBinding.etDescricaoProduto.getText()));
        mViewModel.setPreco(MoneyFormatterUtils.moneyStringToLong(StringValidatorUtils.getSafeStringFromEditable(mBinding.etPrecoProduto.getText())));
        mViewModel.setUnidadeMedidaSelecionada(TipoUnidade.fromName(StringValidatorUtils.getSafeStringFromEditable(mBinding.actvUnidadeMedidaProduto.getText())));
    }

    @Override
    protected void salvarDados(long id) {
        if(id > 0) {
            mViewModel.updateProduto(id);
            return;
        }

        mViewModel.salvarProduto();
    }

    @Override
    protected boolean validarDados() {
        return StringValidatorUtils.isNullOrEmpty(mBinding.etNomeProduto.getError()) &&
                StringValidatorUtils.isNullOrEmpty(mBinding.etDescricaoProduto.getError()) &&
                StringValidatorUtils.isNullOrEmpty(mBinding.etPrecoProduto.getError()) &&
                StringValidatorUtils.isNullOrEmpty(mBinding.actvUnidadeMedidaProduto.getError());
    }

    @Override
    protected void carregarDados(long id) {
        mViewModel.carregarProduto(id);
    }
}