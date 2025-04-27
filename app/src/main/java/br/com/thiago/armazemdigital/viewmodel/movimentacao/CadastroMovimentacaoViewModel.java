package br.com.thiago.armazemdigital.viewmodel.movimentacao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Date;

import javax.inject.Inject;

import br.com.thiago.armazemdigital.database.repository.MovimentacaoRepository;
import br.com.thiago.armazemdigital.database.repository.view.ProdutoCadastroRepository;
import br.com.thiago.armazemdigital.model.Movimentacao;
import br.com.thiago.armazemdigital.model.enums.MotivoMovimentacao;
import br.com.thiago.armazemdigital.model.enums.StatusMovimentacao;
import br.com.thiago.armazemdigital.model.enums.TipoMovimentacao;
import br.com.thiago.armazemdigital.model.view.ProdutoCadastro;
import br.com.thiago.armazemdigital.utils.LongValidatorUtils;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CadastroMovimentacaoViewModel extends ViewModel {
    private final MovimentacaoRepository mMovimentacaoRepository;
    private final ProdutoCadastroRepository mProdutoCadastroRepository;
    private final MutableLiveData<Long> mSelectedProductId = new MutableLiveData<>();
    private final MutableLiveData<ProdutoCadastro> mSelectedProduct = new MutableLiveData<>();
    private final MutableLiveData<String> mNameOperator = new MutableLiveData<>();
    private final MutableLiveData<Long> mQtt = new MutableLiveData<>();
    private final MutableLiveData<TipoMovimentacao> mType = new MutableLiveData<>();
    private final MutableLiveData<MotivoMovimentacao> mMotive = new MutableLiveData<>();
    private final MutableLiveData<String> mObservation = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mSuccess = new MutableLiveData<>();

    @Inject
    public CadastroMovimentacaoViewModel(MovimentacaoRepository mMovimentacaoRepository, ProdutoCadastroRepository mProdutoCadastroRepository) {
        this.mMovimentacaoRepository = mMovimentacaoRepository;
        this.mProdutoCadastroRepository = mProdutoCadastroRepository;
    }

    public LiveData<Long> getSelectedProductId() {
        return mSelectedProductId;
    }

    public LiveData<ProdutoCadastro> getSelectedProduct() {
        return mSelectedProduct;
    }

    public LiveData<String> getNameOperator() {
        return mNameOperator;
    }

    public LiveData<Long> getQtt() {
        return mQtt;
    }

    public LiveData<TipoMovimentacao> getType() {
        return mType;
    }

    public LiveData<MotivoMovimentacao> getMotive() {
        return mMotive;
    }

    public LiveData<String> getObservation() {
        return mObservation;
    }

    public LiveData<Boolean> getSuccess() {
        return mSuccess;
    }

    public void setSelectedProduct(Long selectedProductId) {
        mSelectedProductId.setValue(selectedProductId);
    }

    public void setNameOperator(String nameOperator) {
        mNameOperator.setValue(nameOperator);
    }

    public void setQtt(Long qtt) {
        mQtt.setValue(qtt);
    }

    public void setType(TipoMovimentacao type) {
        mType.setValue(type);
    }

    public void setMotive(MotivoMovimentacao motive) {
        mMotive.setValue(motive);
    }

    public void setObservation(String observation) {
        mObservation.setValue(observation);
    }

    public void reset() {
        mSuccess.setValue(null);
    }

    public void carregarProduto() {
        Thread loadThread = new Thread(() -> {
            ProdutoCadastro produtoCadastro = mProdutoCadastroRepository.getProdutoCadastro(LongValidatorUtils.unbox(mSelectedProductId.getValue()));
            mSelectedProduct.postValue(produtoCadastro);
        });

        loadThread.start();
    }

    public void salvarMovimentacao() {
        Thread saveThread = new Thread(() -> {
            long productId = LongValidatorUtils.unbox(mSelectedProductId.getValue());
            if(productId <= 0) {
                mSuccess.postValue(false);
                return;
            }

            Movimentacao movimentacao = new Movimentacao(
                    LongValidatorUtils.unbox(mSelectedProductId.getValue()),
                    mNameOperator.getValue(),
                    mQtt.getValue(),
                    mType.getValue(),
                    mMotive.getValue(),
                    mObservation.getValue(),
                    StatusMovimentacao.PENDENTE,
                    new Date()
            );

            mSuccess.postValue(mMovimentacaoRepository.insertMovimentacao(movimentacao) > 0);
        });

        saveThread.start();
    }

    public void updateMovimentacao(long id) {
        Thread updateThread = new Thread(() -> {
            Movimentacao movimentacao = mMovimentacaoRepository.getMovimentacao(id);
            long productId = LongValidatorUtils.unbox(mSelectedProductId.getValue());
            if(productId <= 0) {
                mSuccess.postValue(false);
                return;
            }

            movimentacao.setProductId(LongValidatorUtils.unbox(mSelectedProductId.getValue()));
            movimentacao.setOperatorName(mNameOperator.getValue());
            movimentacao.setQtt(mQtt.getValue());
            movimentacao.setTypeMovement(mType.getValue());
            movimentacao.setMotive(mMotive.getValue());
            movimentacao.setObservation(mObservation.getValue());

            mSuccess.postValue(mMovimentacaoRepository.updateMovimentacao(movimentacao) > 0);
        });

        updateThread.start();
    }

    public void carregarMovimentacao(long id) {
        Thread loadThread = new Thread(() -> {
            Movimentacao movimentacao = mMovimentacaoRepository.getMovimentacao(id);

            mSelectedProductId.postValue(movimentacao.getProductId());
            mNameOperator.postValue(movimentacao.getOperatorName());
            mQtt.postValue(movimentacao.getQtt());
            mType.postValue(movimentacao.getTypeMovement());
            mMotive.postValue(movimentacao.getMotive());
            mObservation.postValue(movimentacao.getObservation());
        });

        loadThread.start();
    }
}
