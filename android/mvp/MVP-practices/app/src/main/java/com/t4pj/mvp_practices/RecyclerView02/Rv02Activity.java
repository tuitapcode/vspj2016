package com.t4pj.mvp_practices.RecyclerView02;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.t4pj.mvp_practices.R;

/**
 * Created by Akechi on 6/22/2016.
 */
public class Rv02Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rv02_act);

        Rv02Fragment mRv02Fragment = (Rv02Fragment)getSupportFragmentManager()
                .findFragmentById(R.id.rv02_content_frame);

        if (mRv02Fragment == null) {
            mRv02Fragment = Rv02Fragment.newInstance();

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.rv02_content_frame, mRv02Fragment);
            transaction.commit();
        }

        new Rv02Presenter(mRv02Fragment);
    }
}
