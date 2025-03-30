package br.com.thiago.armazemdigital.fragments.cadastros;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import br.com.thiago.armazemdigital.R;
import br.com.thiago.armazemdigital.fragments.BaseFragment;
import br.com.thiago.armazemdigital.utils.DialogUtils;
import br.com.thiago.armazemdigital.utils.StringUtils;
import br.com.thiago.armazemdigital.utils.interfaces.BundleKeys;
import br.com.thiago.armazemdigital.utils.wrapper.ArrayAdapterWrapper;
import br.com.thiago.armazemdigital.viewmodel.cadastros.BaseCadastroViewModel;

/**
 * Fragment base para telas de cadastro.
 * @param <B> binding relacionado a tela de cadastro.
 */
public abstract class BaseCadastroFragment<B extends ViewBinding> extends BaseFragment<B> {
    private final BaseCadastroViewModel mViewModel = new BaseCadastroViewModel();
    private final Logger mLogger = LoggerFactory.getLogger(BaseCadastroFragment.class);
    private AlertDialog mLoadingDialog;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLogger.info("@onViewCreated() chamado");
        mViewModel.getCarregado().observe(getViewLifecycleOwner(), carregado -> {
            mLogger.info("Observer carregado() chamado: {}", carregado);
            if (carregado != null && carregado) return;

            Bundle bundle = getArguments();
            if (bundle != null) {
                // Caso esteja carregando um item da lista
                long id = bundle.getLong(BundleKeys.ARG_CADASTRO_ID);
                mLogger.debug("Carregando item de id: {}", id);
                carregarDados(id);
                mViewModel.setCarregado(true);
            }
        });

        mLoadingDialog = DialogUtils.createLoadingDialog(requireContext(), getLayoutInflater(), getRegistryName());
    }

    /**
     * Atualiza campo obrigatório e adiciona erro caso esteja vazio.
     *
     * @param fieldView  Campo (tipo EditText) do formulário.
     * @param fieldValue String representando o valor do campo.
     * @param <T>        Tipo da View, deve estender EditText.
     */
    protected <T extends EditText> void validarCampoTextoObrigatorio(T fieldView, String fieldValue) {
        validarCampoTextoObrigatorio(fieldView, fieldValue, new ArrayList<>());
    }

    /**
     * Atualiza campo obrigatório e adiciona erro caso esteja vazio.
     *
     * @param fieldView  Campo (tipo EditText) do formulário.
     * @param fieldValue String representando o valor do campo.
     * @param validators Lista de funções de validação do campo.
     * @param <T>        Tipo da View, deve estender EditText.
     */
    protected <T extends EditText> void validarCampoTextoObrigatorio(T fieldView, String fieldValue, List<Supplier<String>> validators) {
        mLogger.info("@validarCampoTextoObrigatorio() chamado");
        List<Supplier<String>> validatorFinal = new ArrayList<>(validators);
        validatorFinal.add(() -> {
            if (StringUtils.isNullOrEmpty(fieldValue)) {
                String hint = StringUtils.getSafeStringFromCharSequence(fieldView.getHint());
                return StringUtils.isNullOrEmpty(hint) ? getString(R.string.field_error_generic)
                        : getString(R.string.field_error, fieldView.getHint());
            }

            return null;
        });

        validarCampoTexto(fieldView, fieldValue, validatorFinal);
    }

    /**
     * Atualiza campo obrigatório e adiciona erro caso esteja vazio.
     *
     * @param fieldView  Campo (tipo EditText) do formulário.
     * @param fieldValue String representando o valor do campo.
     * @param validators Lista de funções de validação do campo.
     * @param <T>        Tipo da View, deve estender EditText.
     */
    protected <T extends EditText> void validarCampoTexto(T fieldView, String fieldValue, List<Supplier<String>> validators) {
        mLogger.info("@validarCampoTexto() chamado");
        fieldView.setText(fieldValue);
        for (Supplier<String> validator : validators) {
            String msg = validator.get();
            // Se houver erro, sai do loop
            mLogger.debug("Validando campo: {}", fieldView.getHint());
            mLogger.debug("Valor do campo: {}", fieldValue);
            if (!StringUtils.isNullOrEmpty(msg)) {
                mLogger.warn("Erro ao validar campo: {}", msg);
                fieldView.setError(msg);
                return;
            }
        }

        // Limpa erros do campo
        fieldView.setError(null);
    }

    /**
     * Função genérica para criação de ArrayAdapters de views do tipo Spinner ou AutoCompleteTextView.
     *
     * @param listObj                Lista de objetos que compõe o Adapter.
     * @param functionGetDisplayName Função que retorna o nome a ser exibido no adapter.
     * @param <T>                    Tipo do objeto que compõe a lista.
     * @return ArrayAdapterWrapper<T> do AutoCompleteTextView
     * @noinspection unchecked
     * @see BaseCadastroFragment#criarAdapter(Object[], Function)
     */
    protected <T> ArrayAdapterWrapper<T> criarAdapter(List<T> listObj, @NonNull Function<T, String> functionGetDisplayName) {
        return criarAdapter((T[]) listObj.toArray(), functionGetDisplayName);
    }

    /**
     * Função genérica para criação de ArrayAdapters de views do tipo Spinner ou AutoCompleteTextView.
     *
     * @param objs                   Array de objetos que compõe o Adapter.
     * @param functionGetDisplayName Função que retorna o nome a ser exibido no adapter.
     * @param <T>                    Tipo do objeto que compõe a lista.
     * @return ArrayAdapterWrapper<T> do View.
     * @see BaseCadastroFragment#criarAdapter(List, Function)
     */
    protected <T> ArrayAdapterWrapper<T> criarAdapter(T[] objs, @NonNull Function<T, String> functionGetDisplayName) {
        mLogger.info("@criarAdapter() chamado");
        return new ArrayAdapterWrapper<>(requireContext(), android.R.layout.simple_list_item_1, objs) {
            @Override
            protected String getDisplayName(T object) {
                return functionGetDisplayName.apply(object);
            }
        };
    }

    /**
     * Salva dados e retorna ao fragment anterior.
     *
     * @param success Booleano indicando sucesso ou não da operação de salvar dados.
     */
    protected void onSaveFinished(Boolean success) {
        mLogger.info("@onSaveFinished() chamado");
        dismissLoadingDialog();
        // Se retornou nulo, não tentou realizar a operação de salvar dados ou resetou a tela de cadastro.
        if (success == null) return;
        if (!success) {
            mLogger.warn("Erro ao salvar dados");
            // Operação de salvar dados mal sucedida, apresenta dialog de erro
            AlertDialog dialog = DialogUtils.createSaveErrorDialog(requireContext());
            dialog.show();
            return;
        }

        // Inserção bem sucedida, retorna para fragment anterior
        mLogger.info("Dados salvos com sucesso, retornando ao fragment anterior...");
        navigateBack();
    }

    /**
     * Atualiza campos, valida e salva dados.
     */
    protected void salvarCadastro() {
        mLogger.info("@salvarCadastro() chamado");
        showLoadingDialog();

        // Atualiza todos os campos da tela de cadastro
        mLogger.info("Atualizando campos...");
        atualizarCampos();

        // Valida dados e mostra dialog de erro, caso necessário
        mLogger.info("Validando dados...");
        if (!validarDados()) {
            mLogger.warn("Dados inválidos");
            dismissLoadingDialog();
            AlertDialog dialog = DialogUtils.createErrorDialog(requireContext());
            dialog.show();
            return;
        }

        // Salva dados e retorna ao fragment anterior
        mLogger.info("Dados válidos, salvando...");
        Bundle bundle = getArguments();
        salvarDados(bundle != null ? bundle.getLong(BundleKeys.ARG_CADASTRO_ID) : 0);
    }

    /**
     * Cancela cadastro e retorna ao fragment anterior.
     */
    protected void cancelaCadastro() {
        mLogger.info("@cancelaCadastro() chamado");
        navigateBack();
    }

    /**
     * Mostra o dialog de carregamento da operação de salvar cadastro.
     */
    private void showLoadingDialog() {
        mLogger.info("@showLoadingDialog() chamado");
        // Verifica se fragment está carregado antes de exibir dialog
        if (mLoadingDialog != null && isAdded() && !isStateSaved()) {
            // Abre dialog de carregamento
            mLoadingDialog.show();
        }
    }

    /**
     * Esconde o dialog de carregamento da operação de salvar cadastro.
     */
    private void dismissLoadingDialog() {
        mLogger.info("@dismissLoadingDialog() chamado");
        // Verifica se fragment está carregado antes de fechar dialog
        if (mLoadingDialog != null && isAdded() && !isStateSaved()) {
            // Fecha dialog de carregamento
            mLoadingDialog.dismiss();
        }
    }

    /**
     * Função para reinicializar processo de cadastro.
     */
    protected abstract void reset();

    /** Retorna o nome do cadastro referente a tela.
     *
     * @return String representando o nome do cadastro.
     */
    protected abstract String getRegistryName();

    /**
     * Atualiza campos da tela de cadastro.
     */
    protected abstract void atualizarCampos();

    /**
     * Salva dados no banco de dados.
     *
     * @param id Long repesentando o id do item a ser atualizado.
     */
    protected abstract void salvarDados(long id);

    /**
     * Valida dados fornecidos na tela de cadastro.
     *
     * @return Booleano indicando se os dados fornecidos são válidos.
     */
    protected abstract boolean validarDados();

    /**
     * Carrega dados do banco de dados.
     *
     * @param id Long representando o id do item a ser carregado.
     */
    protected abstract void carregarDados(long id);
}
