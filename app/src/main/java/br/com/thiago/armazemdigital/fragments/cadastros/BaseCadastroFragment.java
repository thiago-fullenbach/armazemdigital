package br.com.thiago.armazemdigital.fragments.cadastros;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import java.util.List;
import java.util.function.Function;

import br.com.thiago.armazemdigital.R;
import br.com.thiago.armazemdigital.fragments.BaseFragment;
import br.com.thiago.armazemdigital.utils.DialogUtil;
import br.com.thiago.armazemdigital.utils.StringUtil;
import br.com.thiago.armazemdigital.utils.interfaces.BundleKeys;
import br.com.thiago.armazemdigital.utils.wrapper.ArrayAdapterWrapper;

public abstract class BaseCadastroFragment<B extends ViewBinding> extends BaseFragment<B> {
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null) {
            // Caso esteja carregando um item da lista
            long id = bundle.getLong(BundleKeys.ARG_CADASTRO_ID);
            carregarDados(id);
        }
    }

    /** Atualiza campo obrigatório e adiciona erro caso esteja vazio.
     *
     * @param fieldView Campo (tipo EditText) do formulário.
     * @param fieldValue String representando o valor do campo.
     */
    protected <T extends EditText> void validarCampoTextoObrigatorio(T fieldView, String fieldValue) {
        fieldView.setText(fieldValue);
        if(StringUtil.isNullOrEmpty(fieldValue)) {
            // Mostra erro no tooltip
            String hint = StringUtil.getSafeStringFromCharSequence(fieldView.getHint());
            String msgError = StringUtil.isNullOrEmpty(hint) ? getString(R.string.field_error_generic)
                    : getString(R.string.field_error, fieldView.getHint());
            fieldView.setError(msgError);
            return;
        }

        // Limpa erros do campo
        fieldView.setError(null);
    }

    /** Função genérica para criação de ArrayAdapters de views do tipo Spinner ou AutoCompleteTextView.
     * @see BaseCadastroFragment#criarAdapter(Object[], Function) 
     *
     * @param listObj Lista de objetos que compõe o Adapter.
     * @param functionGetDisplayName Função que retorna o nome a ser exibido no adapter.
     * @param <T> Tipo do objeto que compõe a lista.
     * @return ArrayAdapterWrapper<T> do AutoCompleteTextView
     * @noinspection unchecked
     */
    protected <T> ArrayAdapterWrapper<T> criarAdapter(List<T> listObj, @NonNull Function<T, String> functionGetDisplayName) {
        return criarAdapter(listObj.toArray((T[]) new Object[0]), functionGetDisplayName);
    }

    /** Função genérica para criação de ArrayAdapters de views do tipo Spinner ou AutoCompleteTextView.
     * @see BaseCadastroFragment#criarAdapter(List, Function) 
     * 
     * @param objs Array de objetos que compõe o Adapter.
     * @param functionGetDisplayName Função que retorna o nome a ser exibido no adapter.
     * @param <T> Tipo do objeto que compõe a lista.
     * @return ArrayAdapterWrapper<T> do View.
     */
    protected <T> ArrayAdapterWrapper<T> criarAdapter(T[] objs, @NonNull Function<T, String> functionGetDisplayName) {
        return new ArrayAdapterWrapper<>(requireContext(), android.R.layout.simple_list_item_1, objs) {
            @Override
            protected String getDisplayName(T object) {
                return functionGetDisplayName.apply(object);
            }
        };
    }

    /** Salva dados e retorna ao fragment anterior.
     *
     * @param success Booleano indicando sucesso ou não da operação de salvar dados.
     */
    protected void onSaveFinished(Boolean success) {
        if(success == null || !success) {
            // Operação de salvar dados mal sucedida, apresenta dialog de erro
            AlertDialog dialog = DialogUtil.createSaveErrorDialog(requireContext());
            dialog.show();
            return;
        }

        // Inserção bem sucedida, retorna para fragment anterior
        navigateBack();
    }

    /**
     * Atualiza campos, valida e salva dados.
     */
    protected void salvarCadastro() {
        // Atualiza todos os campos da tela de cadastro
        atualizarCampos();

        // Valida dados e mostra dialog de erro, caso necessário
        if(!validarDados()) {
            AlertDialog dialog = DialogUtil.createErrorDialog(requireContext());
            dialog.show();
            return;
        }

        // Salva dados e retorna ao fragment anterior
        Bundle bundle = getArguments();
        salvarDados(bundle != null ? bundle.getLong(BundleKeys.ARG_CADASTRO_ID) : 0);
    }

    /**
     * Cancela cadastro e retorna ao fragment anterior.
     */
    protected void cancelaCadastro() {
        navigateBack();
    }

    /**
     * Atualiza campos da tela de cadastro.
     */
    protected abstract void atualizarCampos();

    /** Salva dados no banco de dados.
     *
     * @param id Long repesentando o id do item a ser atualizado.
     */
    protected abstract void salvarDados(long id);

    /** Valida dados fornecidos na tela de cadastro.
     *
     * @return Booleano indicando se os dados fornecidos são válidos.
     */
    protected abstract boolean validarDados();

    /** Carrega dados do banco de dados.
     *
     * @param id Long representando o id do item a ser carregado.
     */
    protected abstract void carregarDados(long id);
}
