package br.com.thiago.armazemdigital.viewmodel.movimentacao;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.com.thiago.armazemdigital.database.repository.MovimentacaoRepository;
import br.com.thiago.armazemdigital.database.repository.view.MovimentacaoCadastroRepository;
import br.com.thiago.armazemdigital.model.Movimentacao;
import br.com.thiago.armazemdigital.model.enums.StatusMovimentacao;
import br.com.thiago.armazemdigital.model.view.MovimentacaoCadastro;
import br.com.thiago.armazemdigital.viewmodel.BaseListagemViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ListagemMovimentacaoViewModel extends BaseListagemViewModel<MovimentacaoCadastro> {
    private final MovimentacaoRepository mMovimentacaoRepository;
    private final MovimentacaoCadastroRepository mMovimentacaoCadastroRepository;
    private final MutableLiveData<Boolean> success = new MutableLiveData<>();

    @Inject
    public ListagemMovimentacaoViewModel(MovimentacaoRepository mMovimentacaoRepository, MovimentacaoCadastroRepository mMovimentacaoCadastroRepository) {
        this.mMovimentacaoRepository = mMovimentacaoRepository;
        this.mMovimentacaoCadastroRepository = mMovimentacaoCadastroRepository;
        observeItens();
    }

    @Override
    protected LiveData<List<MovimentacaoCadastro>> getItensFromRepo() {
        return mMovimentacaoCadastroRepository.getMovimentacoesCadastroWithStatusLiveData(StatusMovimentacao.PENDENTE);
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
            if(getItens().getValue() == null) {
                success.postValue(false);
                return;
            }

            List<Movimentacao> movimentacoes = new ArrayList<>();
            for (MovimentacaoCadastro movimentacaoCadastro : getItens().getValue()) {
                Movimentacao movimentacao = createMovimentacaoFromCadastro(newStatus, movimentacaoCadastro);
                movimentacoes.add(movimentacao);
            }

            success.postValue(mMovimentacaoRepository.updateMovimentacoes(movimentacoes) > 0);
        });

        updateThread.start();
    }

    @NonNull
    private Movimentacao createMovimentacaoFromCadastro(StatusMovimentacao newStatus, MovimentacaoCadastro movimentacaoCadastro) {
        Movimentacao movimentacao = new Movimentacao(
                movimentacaoCadastro.getProductId(),
                movimentacaoCadastro.getOperatorName(),
                movimentacaoCadastro.getQttMovement(),
                movimentacaoCadastro.getTypeMovement(),
                movimentacaoCadastro.getMotive(),
                movimentacaoCadastro.getObservation(),
                newStatus,
                new Date()
        );

        movimentacao.setId(movimentacaoCadastro.getMovementId());
        return movimentacao;
    }
}
