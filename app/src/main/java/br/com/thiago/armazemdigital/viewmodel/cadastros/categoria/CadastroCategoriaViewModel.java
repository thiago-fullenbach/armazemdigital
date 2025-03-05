package br.com.thiago.armazemdigital.viewmodel.cadastros.categoria;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Date;

import br.com.thiago.armazemdigital.database.repository.CategoriaRepository;
import br.com.thiago.armazemdigital.model.Categoria;

public class CadastroCategoriaViewModel extends ViewModel {
    private final CategoriaRepository categoriaRepository;
    private final MutableLiveData<String> nome = new MutableLiveData<>();
    private final MutableLiveData<String> descricao = new MutableLiveData<>();

    public CadastroCategoriaViewModel(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public LiveData<String> getNome() {
        return nome;
    }

    public LiveData<String> getDescricao() {
        return descricao;
    }

    public void setNome(String nomeCategoria) {
        nome.setValue(nomeCategoria);
    }

    public void setDescricao(String descricaoCategoria) {
        descricao.setValue(descricaoCategoria);
    }

    public void salvarCategoria() {
        Thread saveThread = new Thread(() -> {
            Categoria categoria = new Categoria(
                    nome.getValue(),
                    descricao.getValue(),
                    new Date());

            categoriaRepository.insertCategoria(categoria);
        });

        saveThread.start();
    }
}
