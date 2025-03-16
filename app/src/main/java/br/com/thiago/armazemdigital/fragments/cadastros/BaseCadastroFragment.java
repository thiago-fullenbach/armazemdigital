package br.com.thiago.armazemdigital.fragments.cadastros;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.google.android.material.textfield.TextInputEditText;

import br.com.thiago.armazemdigital.R;
import br.com.thiago.armazemdigital.fragments.BaseFragment;
import br.com.thiago.armazemdigital.utils.DialogUtil;
import br.com.thiago.armazemdigital.utils.StringUtil;
import br.com.thiago.armazemdigital.utils.interfaces.BundleKeys;

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
     * @param fieldView TextInputEditText representando o campo na tela.
     * @param fieldValue String representando o valor do campo.
     */
    protected void validarCampoTextoObrigatorio(TextInputEditText fieldView, String fieldValue) {
        fieldView.setText(fieldValue);
        if(StringUtil.isNullOrEmpty(fieldValue)) {
            // Mostra erro no tooltip
            fieldView.setError(requireContext().getString(R.string.field_error, fieldView.getHint()));
            return;
        }

        // Limpa erros do campo
        fieldView.setError(null);
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
