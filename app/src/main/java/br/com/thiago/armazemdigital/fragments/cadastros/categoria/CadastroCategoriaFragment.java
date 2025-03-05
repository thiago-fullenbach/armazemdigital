package br.com.thiago.armazemdigital.fragments.cadastros.categoria;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import br.com.thiago.armazemdigital.ArmazemDigitalApp;
import br.com.thiago.armazemdigital.database.dao.CategoriaDao;
import br.com.thiago.armazemdigital.database.repository.CategoriaRepository;
import br.com.thiago.armazemdigital.databinding.FragmentCadastroCategoriaBinding;
import br.com.thiago.armazemdigital.fragments.cadastros.BaseCadastroFragment;
import br.com.thiago.armazemdigital.utils.DialogUtil;
import br.com.thiago.armazemdigital.utils.FormUtils;
import br.com.thiago.armazemdigital.utils.StringUtil;
import br.com.thiago.armazemdigital.viewmodel.cadastros.categoria.CadastroCategoriaViewModel;
import br.com.thiago.armazemdigital.viewmodel.factory.cadastros.categoria.CadastroCategoriaViewModelFactory;

public class CadastroCategoriaFragment extends BaseCadastroFragment<FragmentCadastroCategoriaBinding> {
    private CadastroCategoriaViewModel mViewModel;

    public CadastroCategoriaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CategoriaDao categoriaDao = ArmazemDigitalApp.getDbInstance(requireActivity().getApplicationContext()).categoriaDao();
        CategoriaRepository categoriaRepository = new CategoriaRepository(categoriaDao);
        CadastroCategoriaViewModelFactory factory = new CadastroCategoriaViewModelFactory(categoriaRepository);
        mViewModel = new ViewModelProvider(this, factory).get(CadastroCategoriaViewModel.class);
        mViewModel.getNome().observe(this, nome -> {
            if(StringUtil.isNullOrEmpty(nome)) {
                mBinding.etNomeCategoria.setError("O Nome da categoria é obrigatório.");
                return;
            }

            mBinding.etNomeCategoria.setError(null);
        });

        mViewModel.getDescricao().observe(this, descricao -> {
            if(StringUtil.isNullOrEmpty(descricao)) {
                mBinding.etDescricaoCategoria.setError("A descrição da categoria é obrigatória.");
                return;
            }

            mBinding.etDescricaoCategoria.setError(null);
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.btnCancelarCadastroCategoria.setOnClickListener(v -> navigateBack());
        mBinding.btnSalvarCadastroCategoria.setOnClickListener(v -> {
            // Seta os valores dos campos no ViewModel
            mViewModel.setNome(mBinding.etNomeCategoria.getText() != null ? mBinding.etNomeCategoria.getText().toString() : "");
            mViewModel.setDescricao(mBinding.etDescricaoCategoria.getText() != null ? mBinding.etDescricaoCategoria.getText().toString() : "");

            // Valida dados e mostra dialog de erro, caso necessário
            if(!validarDados()) {
                AlertDialog dialog = DialogUtil.createErrorDialog(requireContext());
                dialog.show();
                return;
            }

            // Salva dados e retorna ao fragment anterior
            salvarDados();
            navigateBack();
        });

        mBinding.etNomeCategoria.setFilters(new InputFilter[]{ FormUtils.getInputFilterForFields() });
        mBinding.etDescricaoCategoria.setFilters(new InputFilter[]{ FormUtils.getInputFilterForFields() });
    }

    @Override
    protected FragmentCadastroCategoriaBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentCadastroCategoriaBinding.inflate(inflater, container, false);
    }

    @Override
    protected void salvarDados() {
        mViewModel.salvarCategoria();
    }

    @Override
    protected boolean validarDados() {
        return StringUtil.isNullOrEmpty(mBinding.etNomeCategoria.getError()) &&
                StringUtil.isNullOrEmpty(mBinding.etDescricaoCategoria.getError());
    }

    @Override
    protected void carregarDados() {

    }
}