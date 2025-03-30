package br.com.thiago.armazemdigital.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.thiago.armazemdigital.ArmazemDigitalApp;
import br.com.thiago.armazemdigital.R;
import br.com.thiago.armazemdigital.databinding.ActivityMainBinding;
import br.com.thiago.armazemdigital.utils.DialogUtils;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private final Logger mLogger = LoggerFactory.getLogger(MainActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLogger.info("@onCreate() chamado");

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        setupNavigation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLogger.info("@onDestroy() chamado");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_info_system) {
            showInfoDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showInfoDialog() {
        mLogger.info("@showInfoDialog() chamado");
        String versionName = ((ArmazemDigitalApp) getApplication()).getVersionName();
        AlertDialog dialog = DialogUtils.createInfoDialog(this, versionName);
        dialog.show();
    }

    private void setupNavigation() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(binding.contentMain.navHostFragmentContentMain.getId());

        if(navHostFragment != null) {
            NavController navController = navHostFragment.getNavController();
            NavigationUI.setupWithNavController(binding.contentMain.navBottomContentMain, navController);
        }
    }
}