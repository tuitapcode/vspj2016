package com.t4pj.mvp_practices.RecyclerView01;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.t4pj.mvp_practices.R;

/**
 * Created by Akechi on 6/22/2016.
 */
public class Rv01Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rv01_act);

        Rv01Fragment mRv01Fragment = (Rv01Fragment) getSupportFragmentManager()
                .findFragmentById(R.id.rv01_content_frame);

        if (mRv01Fragment == null) {
            mRv01Fragment = Rv01Fragment.newInstance();

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.rv01_content_frame, mRv01Fragment);
            transaction.commit();
        }

        new Rv01Presenter(mRv01Fragment);
    }
}
