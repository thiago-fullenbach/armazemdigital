package br.com.thiago.armazemdigital.fragments.cadastros.produto;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;
import java.util.Objects;

import br.com.thiago.armazemdigital.databinding.FragmentCadastroProdutoBinding;
import br.com.thiago.armazemdigital.fragments.cadastros.BaseCadastroFragment;
import br.com.thiago.armazemdigital.model.Categoria;
import br.com.thiago.armazemdigital.model.enums.TipoUnidade;
import br.com.thiago.armazemdigital.utils.FormUtils;
import br.com.thiago.armazemdigital.viewmodel.cadastros.produto.CadastroProdutoViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CadastroProdutoFragment extends BaseCadastroFragment<FragmentCadastroProdutoBinding> {
    private CadastroProdutoViewModel mViewModel;

    public CadastroProdutoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected FragmentCadastroProdutoBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentCadastroProdutoBinding.inflate(inflater, container, false);
    }

    @Override
    protected void setupViewModel() {
        mViewModel = new ViewModelProvider(this).get(CadastroProdutoViewModel.class);

        mViewModel.getNome().observe(getViewLifecycleOwner(), nome -> validarCampoTextoObrigatorio(mBinding.etNomeProduto, nome));
        mViewModel.getDescricao().observe(getViewLifecycleOwner(), descricao -> validarCampoTextoObrigatorio(mBinding.etDescricaoProduto, descricao));
        mViewModel.getPreco().observe(getViewLifecycleOwner(), preco -> validarCampoTextoObrigatorio(mBinding.etPrecoProduto, preco));
        mViewModel.getUnidadeMedidaSelecionada().observe(getViewLifecycleOwner(), tipoUnidade -> validarCampoTextoObrigatorio(mBinding.actvUnidadeMedidaProduto, tipoUnidade.getName()));
        mViewModel.getCategoriasDisponiveis().observe(getViewLifecycleOwner(), categorias -> mBinding.actvCategoriaProduto.setAdapter(criarAdapter(categorias, Categoria::getName)));
        mViewModel.getCategoriaSelecionada().observe(getViewLifecycleOwner(), categoria -> validarCampoTextoObrigatorio(mBinding.actvCategoriaProduto, categoria.getName()));

        mViewModel.getSuccess().observe(getViewLifecycleOwner(), this::onSaveFinished);
    }

    @Override
    protected void setupViews() {
        mBinding.btnCancelarCadastroProduto.setOnClickListener(v -> navigateBack());
        mBinding.btnSalvarCadastroProduto.setOnClickListener(v -> salvarCadastro());

        mBinding.actvUnidadeMedidaProduto.setAdapter(criarAdapter(List.of(TipoUnidade.values()), tipoUnidade -> Objects.requireNonNullElse(tipoUnidade, TipoUnidade.UNIDADE).getName()));

        mBinding.actvUnidadeMedidaProduto.setOnItemClickListener((adapterView, view, position, id) -> {
            TipoUnidade unidade = (TipoUnidade) adapterView.getItemAtPosition(position);
            mViewModel.setUnidadeMedidaSelecionada(unidade);
            mBinding.actvUnidadeMedidaProduto.setText(unidade.getName());
        });

        mBinding.actvCategoriaProduto.setOnItemClickListener((adapterView, view, position, id) -> {
            Categoria categoria = (Categoria) adapterView.getItemAtPosition(position);
            mViewModel.setCategoriaSelecionada(categoria);
            mBinding.actvCategoriaProduto.setText(categoria.getName());
        });

        mBinding.etNomeProduto.setFilters(new InputFilter[]{FormUtils.getInputFilterForFields()});
        mBinding.etDescricaoProduto.setFilters(new InputFilter[]{FormUtils.getInputFilterForFields()});
        mBinding.etPrecoProduto.setFilters(new InputFilter[]{FormUtils.getInputFilterForFields()});
        mBinding.actvUnidadeMedidaProduto.setFilters(new InputFilter[]{FormUtils.getInputFilterForFields()});
        mBinding.actvCategoriaProduto.setFilters(new InputFilter[]{FormUtils.getInputFilterForFields()});
        mBinding.mactvFornecedorProduto.setFilters(new InputFilter[]{FormUtils.getInputFilterForFields()});
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void atualizarCampos() {
        // TODO: Implementar
    }

    @Override
    protected void salvarDados(long id) {
        // TODO: Implementar
    }

    @Override
    protected boolean validarDados() {
        // TODO: Implementar
        return false;
    }

    @Override
    protected void carregarDados(long id) {
        // TODO: Implementar
    }
}