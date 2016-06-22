package com.t4pj.mvp_practices.Main;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.t4pj.mvp_practices.R;

// TODO: Load fragment -> hiện 1 list các Button -> mở các activity cho từng phần BT
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);

        MainFragment mainFragment = (MainFragment) getSupportFragmentManager()
                .findFragmentById(R.id.content_frame);

        if (mainFragment == null) {
            mainFragment = MainFragment.newInstance();

            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction();

            transaction.add(R.id.content_frame, mainFragment);
            transaction.commit();
        }

        new MainPresenter(mainFragment);

    }
}
