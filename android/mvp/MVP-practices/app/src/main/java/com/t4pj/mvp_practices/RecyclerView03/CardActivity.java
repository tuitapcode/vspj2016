package com.t4pj.mvp_practices.RecyclerView03;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.t4pj.mvp_practices.R;

/**
 * Created by Akechi on 6/22/2016.
 */
public class CardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rv03_act);

        CardFragment cardFragment = (CardFragment) getSupportFragmentManager()
                .findFragmentById(R.id.rv03_content_frame);

        if (cardFragment == null) {
            cardFragment = CardFragment.newInstance();

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.rv03_content_frame, cardFragment);
            transaction.commit();
        }

        new CardPresenter(cardFragment);
    }
}
