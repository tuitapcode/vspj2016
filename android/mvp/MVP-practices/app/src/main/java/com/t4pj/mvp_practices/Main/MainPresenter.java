package com.t4pj.mvp_practices.Main;

import android.widget.Toast;

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
        Toast.makeText(mView.getCurContext(), "onClick btnRecyclerView01", Toast.LENGTH_SHORT).show();
    }
}
