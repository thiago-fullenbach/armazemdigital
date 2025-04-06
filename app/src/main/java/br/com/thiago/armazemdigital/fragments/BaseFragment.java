package br.com.thiago.armazemdigital.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewbinding.ViewBinding;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Function;

import br.com.thiago.armazemdigital.fragments.cadastros.BaseCadastroFragment;
import br.com.thiago.armazemdigital.utils.wrapper.ArrayAdapterWrapper;

public abstract class BaseFragment<B extends ViewBinding> extends Fragment {
    private final Logger mLogger = LoggerFactory.getLogger(BaseFragment.class);
    protected B mBinding;

    protected abstract B inflateBinding(LayoutInflater inflater, ViewGroup container);

    protected abstract void setupViewModel();

    protected abstract void setupViews();

    @NonNull
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mLogger.info("@onCreateView() chamado");
        mBinding = inflateBinding(inflater, container);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLogger.info("Iniciada tela de cadastro de {}", getClass().getSimpleName());
        mLogger.info("@onViewCreated() chamado");

        mLogger.info("Inicializando ViewModels...");
        setupViewModel();

        mLogger.info("Inicializando Views");
        setupViews();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mLogger.info("@onDestroyView() chamado");
        mLogger.info("Finalizada tela de cadastro de {}", getClass().getSimpleName());
    }

    /**
     * Função genérica para criação de ArrayAdapters de views do tipo Spinner ou AutoCompleteTextView.
     *
     * @param listObj                Lista de objetos que compõe o Adapter.
     * @param functionGetDisplayName Função que retorna o nome a ser exibido no adapter.
     * @param <T>                    Tipo do objeto que compõe a lista.
     * @return ArrayAdapterWrapper<T> do AutoCompleteTextView
     * @noinspection unchecked
     * @see BaseCadastroFragment#criarAdapter(Object[], Function)
     */
    protected <T> ArrayAdapterWrapper<T> criarAdapter(List<T> listObj, @NonNull Function<T, String> functionGetDisplayName) {
        return criarAdapter((T[]) listObj.toArray(), functionGetDisplayName);
    }

    /**
     * Função genérica para criação de ArrayAdapters de views do tipo Spinner ou AutoCompleteTextView.
     *
     * @param objs                   Array de objetos que compõe o Adapter.
     * @param functionGetDisplayName Função que retorna o nome a ser exibido no adapter.
     * @param <T>                    Tipo do objeto que compõe a lista.
     * @return ArrayAdapterWrapper<T> do View.
     * @see BaseCadastroFragment#criarAdapter(List, Function)
     */
    protected <T> ArrayAdapterWrapper<T> criarAdapter(T[] objs, @NonNull Function<T, String> functionGetDisplayName) {
        mLogger.info("@criarAdapter() chamado");
        return new ArrayAdapterWrapper<>(requireContext(), android.R.layout.simple_list_item_1, objs) {
            @Override
            protected String getDisplayName(T object) {
                return functionGetDisplayName.apply(object);
            }
        };
    }

    /**
     * Função utilizada para navegar para o fragment anterior.
     */
    protected void navigateBack() {
        mLogger.info("@navigateBack() chamado");
        if (!isAdded()) {
            mLogger.info("Fragment not added to activity");
            return;
        }
        getNavController().popBackStack();
    }

    /**
     * Função utilizada para navegar para um fragment informado.
     *
     * @param resourceId ID do action de transição para o fragment desejado.
     */
    protected void navigateToFragment(int resourceId) {
        navigateToFragment(resourceId, null);
    }

    /**
     * Função utilizada para navegar para um fragment informado.
     *
     * @param resourceId ID do action de transição para o fragment desejado.
     * @param bundle     Bundle com dados a serem passados para o fragment.
     */
    protected void navigateToFragment(int resourceId, @Nullable Bundle bundle) {
        mLogger.info("@navigateToFragment() chamado");
        if (!isAdded()) {
            mLogger.info("Fragment not added to activity");
            return;
        }
        getNavController().navigate(resourceId, bundle);
    }

    /**
     * Função utilizada para obter o NavController do fragment.
     *
     * @return NavController do fragment.
     */
    protected NavController getNavController() {
        mLogger.info("@getNavController() chamado");
        if (!isAdded()) {
            mLogger.info("Fragment not added to activity");
            return null;
        }
        return NavHostFragment.findNavController(this);
    }
}
