package br.com.thiago.armazemdigital.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import br.com.thiago.armazemdigital.R;
import br.com.thiago.armazemdigital.databinding.FragmentCadastroBinding;

public class CadastroFragment extends Fragment {
    private FragmentCadastroBinding binding;

    public CadastroFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCadastroBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        binding.btnCadastroProduto.setOnClickListener(view -> {
            NavController navController = NavHostFragment.findNavController(this);
            navController.navigate(R.id.action_item_menu_cadastro_to_listagem_cadastro_produto_fragment);
        });
        binding.btnCadastroFornecedor.setOnClickListener(view -> goToCadastroFornecedor());
        binding.btnCadastroCategoria.setOnClickListener(view -> goToCadastroCategoria());
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