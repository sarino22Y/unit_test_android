package com.example.unittestbd.vue;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import com.example.unittestbd.R;
import com.google.android.material.navigation.NavigationView;
import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    public void init() {
        clicLinearNewClient();
        menuToogle();
        fragmentOfImgView2();
    }

    public void clicLinearNewClient() {
        findViewById(R.id.btnImgCreateClient).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Mandeha ve", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, ClientFiche.class);
                startActivity(intent);
            }
        });
    }

    public void fragmentOfImgView2() {
        findViewById(R.id.btnImageView2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Fragment 1", Toast.LENGTH_LONG).show();
                
                replaceFragment(new Fragment1());
            }

        });
    }

    private void replaceFragment(Fragment1 fragment1) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frameLayout1, fragment1).commit();
    }

    private void menuToogle() {
        /*--------------------- Hooks --------------------*/
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_View);
        toolbar = findViewById(R.id.toolbar);
        /*--------------------- Toolbar --------------------*/
        this.setSupportActionBar(toolbar);

        // Hide or show items
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_list_client).setVisible(false);

        /*------------------ Navigation Drawer --------------------*/
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_create_client);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_create_client:
                showToast("Créer client");
                Intent intent = new Intent(MainActivity.this, ClientFiche.class);
                startActivity(intent);
                break;
            case R.id.nav_modify_client:
                showToast("Modifier client");
                break;
            case R.id.nav_list_client:
                showToast("Lister client");
                break;
            case R.id.nav_create_marchand:
                showToast("Créer marchand");
                break;
            case R.id.nav_modify_marchand:
                showToast("Modifier marchand");
                break;
            case R.id.nav_list_marchand:
                showToast("Lister marchand");
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}