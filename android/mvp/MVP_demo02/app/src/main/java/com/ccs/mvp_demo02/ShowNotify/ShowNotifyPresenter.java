package com.ccs.mvp_demo02.ShowNotify;


import android.location.Location;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Akechi on 6/21/2016.
 */
public class ShowNotifyPresenter implements ShowNotifyConstract.Presenter {

    private final ShowNotifyConstract.View mView;

    public ShowNotifyPresenter(ShowNotifyConstract.View view) {

        mView = view;
        view.setPresenter(this);
    }

    @Override
    public void doNotify() {
        String curTime = DateFormat.getTimeInstance().format(new Date());
        mView.setText("Time: " + curTime);
    }

    @Override
    public void start() {

    }
}
