package com.example.ricardo.rougikebottombar;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ricardo.rougikebottombar.fragments.DeporteFragment;
import com.example.ricardo.rougikebottombar.fragments.SaludFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setDefaultTab(R.id.tab_deporte);


        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_deporte) {
                    DeporteFragment deporteFragment = new DeporteFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                    fragmentTransaction.replace(R.id.contentContainer,deporteFragment);
                    fragmentTransaction.addToBackStack(null).commit();
                }
                if (tabId == R.id.tab_salud) {
                    SaludFragment saludFragment = new SaludFragment();
                    FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction1.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
                    fragmentTransaction1.replace(R.id.contentContainer,saludFragment);
                    fragmentTransaction1.addToBackStack(null).commit();
                }

            }
        });
    }
}
