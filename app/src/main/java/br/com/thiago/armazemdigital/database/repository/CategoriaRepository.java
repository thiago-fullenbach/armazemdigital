package br.com.thiago.armazemdigital.database.repository;

import java.util.List;

import br.com.thiago.armazemdigital.database.dao.CategoriaDao;
import br.com.thiago.armazemdigital.model.Categoria;

public class CategoriaRepository {
    private final CategoriaDao categoriaDao;

    public CategoriaRepository(CategoriaDao categoriaDao) {
        this.categoriaDao = categoriaDao;
    }

    public long insetCategoria(Categoria categoria) {
        return categoriaDao.insert(categoria);
    }

    public void updateCategoria(Categoria categoria) {
        categoriaDao.update(categoria);
    }

    public void deleteCategoria(Categoria categoria) {
        categoriaDao.delete(categoria);
    }

    public List<Categoria> getCategorias(int limit, int offset) {
        return categoriaDao.getCategorias(limit, offset);
    }

    public Categoria getCategoria(long id) {
        return categoriaDao.getCategoria(id);
    }
}
