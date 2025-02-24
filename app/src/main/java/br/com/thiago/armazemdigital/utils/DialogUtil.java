package br.com.thiago.armazemdigital.utils;

import android.app.AlertDialog;
import android.content.Context;

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
