package br.com.thiago.armazemdigital.fragments.cadastros;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.btnCadastroProduto.setOnClickListener(v -> {
            NavController navController = NavHostFragment.findNavController(this);
            navController.navigate(R.id.action_item_menu_cadastro_to_listagem_cadastro_produto_fragment);
        });
        mBinding.btnCadastroFornecedor.setOnClickListener(v -> goToCadastroFornecedor());
        mBinding.btnCadastroCategoria.setOnClickListener(v -> goToCadastroCategoria());
    }

    private void goToCadastroFornecedor() {
        Toast toast = new Toast(getContext());
        toast.setText("Acessou tela de cadastro de fornecedores!");
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    private void goToCadastroCategoria() {
        Toast toast = new Toast(getContext());
        toast.setText("Acessou tela de cadastro de categorias!");
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
}