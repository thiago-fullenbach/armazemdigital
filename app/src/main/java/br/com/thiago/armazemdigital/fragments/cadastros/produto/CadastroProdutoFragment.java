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
import br.com.thiago.armazemdigital.fragments.cadastros.BaseCadastroFragment;
import br.com.thiago.armazemdigital.model.enums.TipoUnidade;
import br.com.thiago.armazemdigital.utils.FormUtils;
import br.com.thiago.armazemdigital.utils.MoneyUtil;
import br.com.thiago.armazemdigital.utils.StringUtil;
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
        mViewModel.getPreco().observe(getViewLifecycleOwner(), preco -> validarCampoTextoObrigatorio(mBinding.etPrecoProduto, MoneyUtil.moneyLongToString(preco)));
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
            mBinding.actvUnidadeMedidaProduto.setText(unidade.getName());
        });

        mBinding.etNomeProduto.setFilters(new InputFilter[]{FormUtils.getInputFilterForFields()});
        mBinding.etDescricaoProduto.setFilters(new InputFilter[]{FormUtils.getInputFilterForFields()});
        mBinding.etPrecoProduto.setFilters(new InputFilter[]{FormUtils.getInputFilterForFields()});
        mBinding.actvUnidadeMedidaProduto.setFilters(new InputFilter[]{FormUtils.getInputFilterForFields()});
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
        mViewModel.setNome(StringUtil.getSafeStringFromEditable(mBinding.etNomeProduto.getText()));
        mViewModel.setDescricao(StringUtil.getSafeStringFromEditable(mBinding.etDescricaoProduto.getText()));
        mViewModel.setPreco(MoneyUtil.moneyStringToLong(StringUtil.getSafeStringFromEditable(mBinding.etPrecoProduto.getText())));
        mViewModel.setUnidadeMedidaSelecionada(TipoUnidade.fromName(StringUtil.getSafeStringFromEditable(mBinding.actvUnidadeMedidaProduto.getText())));
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
        return StringUtil.isNullOrEmpty(mBinding.etNomeProduto.getError()) &&
                StringUtil.isNullOrEmpty(mBinding.etDescricaoProduto.getError()) &&
                StringUtil.isNullOrEmpty(mBinding.etPrecoProduto.getError()) &&
                StringUtil.isNullOrEmpty(mBinding.actvUnidadeMedidaProduto.getError());
    }

    @Override
    protected void carregarDados(long id) {
        mViewModel.carregarProduto(id);
    }
}