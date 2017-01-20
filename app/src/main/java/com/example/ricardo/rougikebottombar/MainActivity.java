package com.example.ricardo.rougikebottombar;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import com.example.ricardo.rougikebottombar.fragments.Fragment1;
import com.example.ricardo.rougikebottombar.fragments.Fragment2;
import com.example.ricardo.rougikebottombar.fragments.Fragment3;
import com.example.ricardo.rougikebottombar.fragments.Fragment4;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity {
    private float x1,x2;
    static final int MIN_DISTANCE = 150;
    int[] fragments = {R.id.tab_1, R.id.tab_2, R.id.tab_3, R.id.tab_4};
    int fragmentActual = 0;
    int fragmentAnterior = 0;
    Fragment fragment;
    BottomBar bottomBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setDefaultTab(fragments[fragmentActual]);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch(tabId){
                    case R.id.tab_1:
                        fragmentAnterior = fragmentActual;
                        fragmentActual = 0;
                        fragment = new Fragment1();
                        break;
                    case R.id.tab_2:
                        fragmentAnterior = fragmentActual;
                        fragmentActual = 1;
                        fragment = new Fragment2();
                        break;
                    case R.id.tab_3:
                        fragmentAnterior = fragmentActual;
                        fragmentActual = 2;
                        fragment = new Fragment3();
                        break;
                    case R.id.tab_4:
                        fragmentAnterior = fragmentActual;
                        fragmentActual = 3;
                        fragment = new Fragment4();
                        break;
                    default:break;
                }
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                if(fragmentActual > fragmentAnterior)
                    fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                else
                    fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
                fragmentTransaction.replace(R.id.contentContainer, fragment);
                fragmentTransaction.addToBackStack(null).commit();

//                if (tabId == fragments[1]) {
//                    fragmentActual = 1;
//                    Fragment3 fragment3 = new Fragment3();
//                    FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
//                    fragmentTransaction1.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
//                    fragmentTransaction1.replace(R.id.contentContainer, fragment3);
//                    fragmentTransaction1.addToBackStack(null).commit();
//                }

            }
        });
    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;

                if (Math.abs(deltaX) > MIN_DISTANCE)
                {
                    // Left to Right swipe action
                    if (x2 > x1)
                    {
                        if(fragmentActual > 0){
                            bottomBar.selectTabAtPosition(fragmentActual-1);
                        }
                        Toast.makeText(this,(fragmentActual-1)+"" , Toast.LENGTH_SHORT).show ();
                    }
                    // Right to left swipe action
                    else
                    {
                        if(fragmentActual < 3) {
                            bottomBar.selectTabAtPosition(fragmentActual+1);
                        }
                        Toast.makeText(this,(fragmentActual+1)+""  , Toast.LENGTH_SHORT).show ();
                    }
                }
                else
                {
                    // consider as something else - a screen tap for example
                }
                break;
        }
        return super.onTouchEvent(event);
    }
}
