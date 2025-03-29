package br.com.thiago.armazemdigital.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import br.com.thiago.armazemdigital.R;

/**
 * Utilitário de criação de diálogos da aplicação.
 * Dialogs utilizados no app devem ser criados apenas nesse utilitário
 */
public class DialogUtil {
    /**
     * Cria dialog de informações do sistema
     *
     * @param context     Contexto da atividade atual
     * @param versionName Versão atual do aplicativo (definido no Gradle do Módulo principal)
     * @return AlertDialog de informações do sistema
     */
    public static AlertDialog createInfoDialog(Context context, String versionName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(context.getString(R.string.info_title));
        builder.setMessage(buildInfoDialogMessage(context, versionName));
        builder.setPositiveButton(R.string.dialog_button_ok, (dialogInterface, i) -> dialogInterface.dismiss());

        return builder.create();
    }

    /** Cria dialog de erro de cadastro
     *
     * @param context Contexto da atividade atual
     * @return AlertDialog informando erro durante o cadastro
     */
    public static AlertDialog createErrorDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(R.string.error_register_title);
        builder.setMessage(R.string.error_register_message);
        builder.setPositiveButton(R.string.dialog_button_ok, (dialogInterface, i) -> dialogInterface.dismiss());
        builder.setCancelable(false);

        return builder.create();
    }

    /** Cria dialog de erro de salvamento de dados
     *
     * @param context Contexto da atividade atual
     * @return AlertDialog informando erro durante salvamento de dados
     */
    public static AlertDialog createSaveErrorDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(R.string.error_register_title);
        builder.setMessage(R.string.error_save_message);
        builder.setPositiveButton(R.string.dialog_button_ok, (dialogInterface, i) -> dialogInterface.dismiss());

        return builder.create();
    }

    /** Cria dialog de erro ao selecionar categoria
     *
     * @param context Contexto da atividade atual
     * @return AlertDialog informando erro ao selecionar categoria
     */
    public static AlertDialog createSelectProductCategoryError(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(R.string.error_select_title);
        builder.setMessage(R.string.error_select_category_message);
        builder.setPositiveButton(R.string.dialog_button_ok, (dialogInterface, i) -> dialogInterface.dismiss());

        return builder.create();
    }

    /** Cria dialog de carregamento.
     *
     * @param context Contexto da atividade atual
     * @param inflater Inflater para criação de views
     * @param name Nome do registro sendo carregado
     * @return AlertDialog informando carregamento
     */
    public static AlertDialog createLoadingDialog(Context context, LayoutInflater inflater, String name) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        View loadingView = inflater.inflate(R.layout.dialog_loading, null);

        builder.setTitle(context.getString(R.string.loading_saving_data_title, name));
        builder.setMessage(R.string.loading_saving_data_message);
        builder.setView(loadingView);
        builder.setCancelable(false);

        return builder.create();
    }

    /**
     * Cria mensagem do dialog de informações do sistema
     *
     * @param context Contexto da atividade atual
     * @param version Versão atual do aplicativo (definido no Gradle do Módulo principal)
     * @return String relacionado a mensagem do dialog de informações do sistema
     */
    private static String buildInfoDialogMessage(Context context, String version) {
        return context.getString(R.string.info_made_by) +
                "\n\n" +
                context.getString(R.string.info_version, version);
    }
}
