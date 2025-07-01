package br.com.thiago.armazemdigital.utils;

import android.content.Context;
import android.net.Uri;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Classe utilitária para manipulação de arquivos.
 */
public final class ZipFileUtils {
    private static final int DEFAULT_BUFFER_SIZE = 1024;

    private ZipFileUtils() {}

    /**
     * Função para compactação de arquivos em um zip.
     *
     * @param context Contexto da aplicação.
     * @param srcPath Caminho do arquivo a ser compactado.
     * @param destUri URI do arquivo compactado (obtido pelo SAF).
     * @throws IOException Erro de IO durante compactação.
     */
    public static void zipFolder(Context context, String srcPath, Uri destUri) throws IOException {
        File srcFile = new File(srcPath);
        if (!srcFile.exists()) throw new FileNotFoundException("Arquivo não encontrado.");

        try (OutputStream os = context.getContentResolver().openOutputStream(destUri);
             ZipOutputStream zos = new ZipOutputStream(os)) {
            zipFile(srcFile, srcFile.getName(), zos);
        }
    }

    /**
     * Função para compactação de arquivos individuais.
     *
     * @param srcFile     Arquivo a ser compactado.
     * @param srcFileName Nome do arquivo a ser compactado.
     * @param zos         ZipOutputStream para escrita.
     * @throws IOException Erro de IO durante compactação.
     */
    private static void zipFile(File srcFile, String srcFileName, ZipOutputStream zos) throws IOException {
        if (srcFile.isHidden()) return;

        if (srcFile.isDirectory()) {
            if (!srcFileName.endsWith("/")) srcFileName += "/";

            zos.putNextEntry(new ZipEntry(srcFileName));
            zos.closeEntry();

            File[] fileList = srcFile.listFiles();
            if (fileList == null) return;
            for (File file : fileList) {
                zipFile(file, srcFileName + file.getName(), zos);
            }

            return;
        }

        try (FileInputStream fis = new FileInputStream(srcFile)) {
            zos.putNextEntry(new ZipEntry(srcFileName));
            byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zos.write(bytes, 0, length);
            }
        }
    }

    /**
     * Classe para representar o resultado da operação de compactação.
     */
    public record ZipResult(boolean success, String error) {}
}
