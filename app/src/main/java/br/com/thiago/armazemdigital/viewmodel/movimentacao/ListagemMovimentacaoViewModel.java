package br.com.thiago.armazemdigital.viewmodel.movimentacao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import br.com.thiago.armazemdigital.database.repository.MovimentacaoRepository;
import br.com.thiago.armazemdigital.database.repository.view.MovimentacaoCadastroRepository;
import br.com.thiago.armazemdigital.model.enums.StatusMovimentacao;
import br.com.thiago.armazemdigital.model.view.MovimentacaoCadastro;
import br.com.thiago.armazemdigital.viewmodel.BaseListagemViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ListagemMovimentacaoViewModel extends BaseListagemViewModel<MovimentacaoCadastro> {
    private final MovimentacaoRepository movimentacaoRepository;
    private final MovimentacaoCadastroRepository movimentacaoCadastroRepository;
    private final MutableLiveData<Boolean> success = new MutableLiveData<>();

    @Inject
    public ListagemMovimentacaoViewModel(MovimentacaoRepository movimentacaoRepository, MovimentacaoCadastroRepository movimentacaoCadastroRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.movimentacaoCadastroRepository = movimentacaoCadastroRepository;
        observeItens();
    }

    @Override
    protected LiveData<List<MovimentacaoCadastro>> getItensFromRepo() {
        return movimentacaoCadastroRepository.getMovimentacoesCadastroWithStatusLiveData(StatusMovimentacao.PENDENTE);
    }

    public LiveData<Boolean> getSuccess() {
        return success;
    }

    public void reset() {
        success.postValue(null);
    }

    public void saveMovimentacoes() {
        updateMovimentacao(StatusMovimentacao.CONCLUIDO);
    }

    public void cancelMovimentacoes() {
        updateMovimentacao(StatusMovimentacao.CANCELADO);
    }

    private void updateMovimentacao(StatusMovimentacao newStatus) {
        Thread updateThread = new Thread(() -> {
            int numAtualizados = movimentacaoRepository.updateStatus(
                    StatusMovimentacao.PENDENTE, newStatus);

            success.postValue(numAtualizados > 0);
        });

        updateThread.start();
    }
}
