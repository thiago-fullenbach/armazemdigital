package br.com.thiago.armazemdigital.viewmodel.cadastros.categoria;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Date;

import javax.inject.Inject;

import br.com.thiago.armazemdigital.database.repository.CategoriaRepository;
import br.com.thiago.armazemdigital.model.Categoria;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CadastroCategoriaViewModel extends ViewModel {
    private final CategoriaRepository categoriaRepository;
    private final MutableLiveData<String> nome = new MutableLiveData<>();
    private final MutableLiveData<String> descricao = new MutableLiveData<>();
    private final MutableLiveData<Boolean> success = new MutableLiveData<>();

    @Inject
    public CadastroCategoriaViewModel(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public LiveData<String> getNome() {
        return nome;
    }

    public LiveData<String> getDescricao() {
        return descricao;
    }

    public LiveData<Boolean> getSuccess() {
        return success;
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

            success.postValue(categoriaRepository.insertCategoria(categoria) > 0);
        });

        saveThread.start();
    }

    public void updateCategoria(long id) {
        Thread updateThread = new Thread(() -> {
            Categoria categoria = categoriaRepository.getCategoria(id);

            categoria.setName(nome.getValue());
            categoria.setDescription(descricao.getValue());

            success.postValue(categoriaRepository.updateCategoria(categoria) > 0);
        });

        updateThread.start();
    }

    public void carregarCategoria(long id) {
        Thread loadThread = new Thread(() -> {
            Categoria categoria = categoriaRepository.getCategoria(id);

            nome.postValue(categoria.getName());
            descricao.postValue(categoria.getDescription());
        });

        loadThread.start();
    }
}
