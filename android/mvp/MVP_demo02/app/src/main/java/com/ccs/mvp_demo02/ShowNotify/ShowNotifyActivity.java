package com.ccs.mvp_demo02.ShowNotify;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.ccs.mvp_demo02.R;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Akechi on 6/21/2016.
 */
public class ShowNotifyActivity extends AppCompatActivity {




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_notify_container);

        ShowNotifyFragment showNotifyFragment = (ShowNotifyFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if (showNotifyFragment == null) {
            showNotifyFragment = ShowNotifyFragment.newInstance();
            themFragmentVaoActivity(getSupportFragmentManager(), showNotifyFragment, R.id.contentFrame);
        }

        new ShowNotifyPresenter(showNotifyFragment);
    }


    public static void themFragmentVaoActivity(FragmentManager fragmentManager,
                                               Fragment fragment, int frameId) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }
}
