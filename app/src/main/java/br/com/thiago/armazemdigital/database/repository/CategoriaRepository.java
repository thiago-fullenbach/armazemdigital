package br.com.thiago.armazemdigital.database.repository;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.thiago.armazemdigital.database.dao.CategoriaDao;
import br.com.thiago.armazemdigital.model.Categoria;

public class CategoriaRepository {
    private final CategoriaDao categoriaDao;

    public CategoriaRepository(CategoriaDao categoriaDao) {
        this.categoriaDao = categoriaDao;
    }

    public long insertCategoria(Categoria categoria) {
        return categoriaDao.insert(categoria);
    }

    public int updateCategoria(Categoria categoria) {
        return categoriaDao.update(categoria);
    }

    public int deleteCategoria(Categoria categoria) {
        return categoriaDao.delete(categoria);
    }

    public LiveData<List<Categoria>> getCategoriasLiveData() {
        return categoriaDao.getCategoriasLiveData();
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    public List<Categoria> getCategorias() {
        return categoriaDao.getCategorias();
    }

    public Categoria getCategoria(long id) {
        return categoriaDao.getCategoria(id);
    }
}
