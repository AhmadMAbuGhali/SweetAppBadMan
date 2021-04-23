package com.example.sweetapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.sweetapp.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChaletOwnerMainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    private Toolbar mToolbar;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chalet_owner_main);
        mToolbar = findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        drawerLayout = findViewById(R.id.drowerlayout);
        navigationView = findViewById(R.id.navigationview);
        mAuth = FirebaseAuth.getInstance();
        navigationView.bringToFront();
        ActionBarDrawerToggle dToggle = new ActionBarDrawerToggle(this,drawerLayout,mToolbar,R.string.navigation_open,R.string.navigation_close);
        drawerLayout.addDrawerListener(dToggle);
        dToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            sendUserToChooseTypeActivity();
        }
    }
    private void sendUserToChooseTypeActivity() {
        Intent regIntent = new Intent(ChaletOwnerMainActivity.this, ChooseTypeActivity.class);
        startActivity(regIntent);
    }
    private void sendUserToChaletList() {
        Intent regIntent = new Intent(ChaletOwnerMainActivity.this, ChaletListActivity.class);
        startActivity(regIntent);
    }
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }


    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.main_account_mangment:
                break;
            case R.id.main_chalet_list:
                sendUserToChaletList();
                break;
            case R.id.main_order_list:

                break;
            case R.id.main_logout_option:
                mAuth.signOut();
                sendUserToChooseTypeActivity();

        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;

    }
}