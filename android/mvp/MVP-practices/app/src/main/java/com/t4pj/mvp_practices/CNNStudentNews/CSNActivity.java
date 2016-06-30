package com.t4pj.mvp_practices.CNNStudentNews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.t4pj.mvp_practices.R;

/**
 * Khởi tạo, load frag
 */
public class CSNActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.csn_act);

        CSNFragment csnFragment = (CSNFragment) getSupportFragmentManager()
                .findFragmentById(R.id.csn_frame_content);

        if (csnFragment == null) {
            csnFragment = CSNFragment.newInstance();

            FragmentTransaction transaction = (FragmentTransaction) getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.csn_frame_content, csnFragment);
            transaction.commit();
        }

        new CSNPresenter(csnFragment);

    }
}
