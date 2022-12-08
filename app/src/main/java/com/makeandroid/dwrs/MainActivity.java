package com.makeandroid.dwrs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    Fraghome fraghome;
    Fragchart fragchart;
    Fraginfo fraginfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fraghome = new Fraghome();
        fragchart = new Fragchart();
        fraginfo = new Fraginfo();

        getSupportFragmentManager().beginTransaction().replace(R.id.containers, fraghome).commit();

        NavigationBarView navigationBarView = findViewById(R.id.bottomNavi);
        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, fraghome).commit();
                        return true;
                    case R.id.action_chart:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, fragchart).commit();
                        return true;
                    case R.id.action_mypage:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, fraginfo).commit();
                        return true;

                }

                return false;
            }
        });

    }
}