package br.com.thiago.armazemdigital.fragments.cadastros;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import br.com.thiago.armazemdigital.R;
import br.com.thiago.armazemdigital.databinding.FragmentCadastroBinding;
import br.com.thiago.armazemdigital.fragments.BaseFragment;

public class CadastroFragment extends BaseFragment<FragmentCadastroBinding> {

    public CadastroFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected FragmentCadastroBinding inflateBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentCadastroBinding.inflate(inflater, container, false);
    }

    @Override
    protected void setupViewModel() {
        // Não implementa
    }

    @Override
    protected void setupViews() {
        mBinding.btnCadastroProduto.setOnClickListener(v -> navigateToFragment(R.id.action_item_menu_cadastro_to_listagem_produto_fragment));
        mBinding.btnCadastroFornecedor.setOnClickListener(v -> navigateToFragment(R.id.action_item_menu_cadastro_to_listagem_fornecedor_fragment));
        mBinding.btnCadastroCategoria.setOnClickListener(v -> navigateToFragment(R.id.action_item_menu_cadastro_to_listagem_categoria_fragment));
    }
}