package br.com.thiago.armazemdigital.viewmodel.cadastros.fornecedor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Date;

import javax.inject.Inject;

import br.com.thiago.armazemdigital.database.repository.FornecedorRepository;
import br.com.thiago.armazemdigital.model.Fornecedor;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CadastroFornecedorViewModel extends ViewModel {
    private final FornecedorRepository fornecedorRepository;
    private final MutableLiveData<String> nome = new MutableLiveData<>();
    private final MutableLiveData<String> telefone = new MutableLiveData<>();
    private final MutableLiveData<String> email = new MutableLiveData<>();
    private final MutableLiveData<String> cep = new MutableLiveData<>();
    private final MutableLiveData<String> estado = new MutableLiveData<>();
    private final MutableLiveData<String> cidade = new MutableLiveData<>();
    private final MutableLiveData<String> bairro = new MutableLiveData<>();
    private final MutableLiveData<String> logradouro = new MutableLiveData<>();
    private final MutableLiveData<String> complemento = new MutableLiveData<>();
    private final MutableLiveData<String> numero = new MutableLiveData<>();
    private final MutableLiveData<Boolean> success = new MutableLiveData<>();

    @Inject
    public CadastroFornecedorViewModel(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    public LiveData<String> getNome() {
        return nome;
    }

    public LiveData<String> getTelefone() {
        return telefone;
    }

    public LiveData<String> getEmail() {
        return email;
    }

    public LiveData<String> getCep() {
        return cep;
    }

    public LiveData<String> getEstado() {
        return estado;
    }

    public LiveData<String> getCidade() {
        return cidade;
    }

    public LiveData<String> getBairro() {
        return bairro;
    }

    public LiveData<String> getLogradouro() {
        return logradouro;
    }

    public LiveData<String> getComplemento() {
        return complemento;
    }

    public LiveData<String> getNumero() {
        return numero;
    }

    public LiveData<Boolean> getSuccess() {
        return success;
    }

    public void setNome(String nomeFornecedor) {
        nome.setValue(nomeFornecedor);
    }

    public void setTelefone(String telefoneFornecedor) {
        telefone.setValue(telefoneFornecedor);
    }

    public void setEmail(String emailFornecedor) {
        email.setValue(emailFornecedor);
    }

    public void setCep(String cepFornecedor) {
        cep.setValue(cepFornecedor);
    }

    public void setEstado(String estadoFornecedor) {
        estado.setValue(estadoFornecedor);
    }

    public void setCidade(String cidadeFornecedor) {
        cidade.setValue(cidadeFornecedor);
    }

    public void setBairro(String bairroFornecedor) {
        bairro.setValue(bairroFornecedor);
    }

    public void setLogradouro(String logradouroFornecedor) {
        logradouro.setValue(logradouroFornecedor);
    }

    public void setComplemento(String complementoFornecedor) {
        complemento.setValue(complementoFornecedor);
    }

    public void setNumero(String numeroFornecedor) {
        numero.setValue(numeroFornecedor);
    }

    public void salvarFornecedor() {
        Thread saveThread = new Thread(() -> {
            Fornecedor fornecedor = new Fornecedor(
                    nome.getValue(),
                    telefone.getValue(),
                    email.getValue(),
                    cep.getValue(),
                    estado.getValue(),
                    cidade.getValue(),
                    bairro.getValue(),
                    logradouro.getValue(),
                    complemento.getValue(),
                    numero.getValue(),
                    new Date()
            );

            long id = fornecedorRepository.insertFornecedor(fornecedor);
            success.postValue(id > 0);
        });

        saveThread.start();
    }

    public void updateFornecedor(long id) {
        Thread updateThread = new Thread(() -> {
            Fornecedor fornecedor = fornecedorRepository.getFornecedor(id);

            fornecedor.setName(nome.getValue());
            fornecedor.setPhone(telefone.getValue());
            fornecedor.setEmail(email.getValue());
            fornecedor.setZipCode(cep.getValue());
            fornecedor.setState(estado.getValue());
            fornecedor.setCity(cidade.getValue());
            fornecedor.setNeighborhood(bairro.getValue());
            fornecedor.setAddressLine1(logradouro.getValue());
            fornecedor.setAddressLine2(complemento.getValue());
            fornecedor.setNumber(numero.getValue());

            success.postValue(fornecedorRepository.updateFornecedor(fornecedor) > 0);
        });

        updateThread.start();
    }

    public void carregarFornecedor(long id) {
        Thread loadThread = new Thread(() -> {
            Fornecedor fornecedor = fornecedorRepository.getFornecedor(id);

            nome.postValue(fornecedor.getName());
            telefone.postValue(fornecedor.getPhone());
            email.postValue(fornecedor.getEmail());
            cep.postValue(fornecedor.getZipCode());
            estado.postValue(fornecedor.getState());
            cidade.postValue(fornecedor.getCity());
            bairro.postValue(fornecedor.getNeighborhood());
            logradouro.postValue(fornecedor.getAddressLine1());
            complemento.postValue(fornecedor.getAddressLine2());
            numero.postValue(fornecedor.getNumber());
        });

        loadThread.start();
    }

    public void reset() {
        success.setValue(null);
    }
}
