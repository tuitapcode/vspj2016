package com.t4pj.mvp_practices.Main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.t4pj.mvp_practices.RecyclerView01.Rv01Activity;
import com.t4pj.mvp_practices.RecyclerView02.Rv02Activity;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;

import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by Akechi on 6/22/2016.
 */
public class MainPresenter implements MainConstract.Presenter {

    private final MainConstract.View mView;

    public MainPresenter(MainConstract.View view){
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        //
    }

    @Override
    public void onClick_btnRecyclerView01() {
        Intent intent = new Intent(mView.getCurContext(), Rv02Activity.class);
        startActivity(mView.getCurActivity(), intent, Bundle.EMPTY);
    }
}
