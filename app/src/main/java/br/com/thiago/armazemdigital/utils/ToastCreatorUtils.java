package br.com.thiago.armazemdigital.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Classe utilitária para criação de mensagens Toasts.
 */
public final class ToastCreatorUtils {

    private ToastCreatorUtils() {
    }

    /**
     * Função para criar e exibir um Toast com a mensagem informada, que será exibido pela duração
     * informada.
     *
     * @param context  Contexto do aplicativo.
     * @param msg      Mensagem a ser exibida.
     * @param duration Duração do Toast.
     */
    public static void createAndShowToastMessage(Context context, String msg, int duration) {
        Toast toast = Toast.makeText(context, msg, duration);
        toast.show();
    }
}
