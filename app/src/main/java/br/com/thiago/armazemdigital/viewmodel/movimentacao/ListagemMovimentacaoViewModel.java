package br.com.thiago.armazemdigital.viewmodel.movimentacao;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import br.com.thiago.armazemdigital.database.repository.view.MovimentacaoCadastroRepository;
import br.com.thiago.armazemdigital.model.enums.StatusMovimentacao;
import br.com.thiago.armazemdigital.model.view.MovimentacaoCadastro;
import br.com.thiago.armazemdigital.viewmodel.BaseListagemViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ListagemMovimentacaoViewModel extends BaseListagemViewModel<MovimentacaoCadastro> {
    private final MovimentacaoCadastroRepository movimentacaoCadastroRepository;

    @Inject
    public ListagemMovimentacaoViewModel(MovimentacaoCadastroRepository movimentacaoCadastroRepository) {
        this.movimentacaoCadastroRepository = movimentacaoCadastroRepository;
        observeItens();
    }

    @Override
    protected LiveData<List<MovimentacaoCadastro>> getItensFromRepo() {
        return movimentacaoCadastroRepository.getMovimentacoesCadastroWithStatusLiveData(StatusMovimentacao.PENDENTE);
    }
}
