package br.com.thiago.armazemdigital.database.repository.view;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.thiago.armazemdigital.database.dao.view.CategoriaCadastroDao;
import br.com.thiago.armazemdigital.model.view.CategoriaCadastro;

public class CategoriaCadastroRepository {
    private final CategoriaCadastroDao categoriaCadastroDao;

    public CategoriaCadastroRepository(CategoriaCadastroDao categoriaCadastroDao) {
        this.categoriaCadastroDao = categoriaCadastroDao;
    }

    public LiveData<List<CategoriaCadastro>> getCategoriasCadastroLiveData() {
        return categoriaCadastroDao.getCategoriasCadastroLiveData();
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    public List<CategoriaCadastro> getCategoriasCadastro() {
        return categoriaCadastroDao.getCategoriasCadastro();
    }
}
