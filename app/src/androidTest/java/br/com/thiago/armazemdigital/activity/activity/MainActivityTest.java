package br.com.thiago.armazemdigital.activity.activity;

import android.content.Context;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.thiago.armazemdigital.R;
import br.com.thiago.armazemdigital.activity.MainActivity;

/**
 * Testes de interface da MainActivity
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public final ActivityScenarioRule<MainActivity> rule =
            new ActivityScenarioRule<>(MainActivity.class);

    /**
     * Testa navegação do BottomNavigationView e verifica fragment exibido.
     * Realiza teste em todos os itens do menu de navegação.
     */
    @Test
    public void testNavigation() {
        Espresso.onView(ViewMatchers.withId(R.id.item_menu_cadastro))
                .perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.fragment_cadastros))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.item_menu_movimentacao))
                .perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.tv_texto_movimentacao))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.item_menu_estoque))
                .perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.tv_texto_estoque))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.item_menu_configuracao))
                .perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.tv_texto_configuracao))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    /**
     * Testa se dialog é exibido ao clicar no botão de informações.
     * Testa se o diálogo é cancelável.
     */
    @Test
    public void testCheckSystemInfo() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        Espresso.onView(ViewMatchers.withId(R.id.action_info_system))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withText(appContext.getString(R.string.info_title)))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.pressBack();

        Espresso.onView(ViewMatchers.withText(appContext.getString(R.string.info_title)))
                .check(ViewAssertions.doesNotExist());
    }
}
