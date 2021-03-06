package com.t4pj.mvp_practices.Main;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.t4pj.mvp_practices.BasePresenter;
import com.t4pj.mvp_practices.BaseView;

/**
 * Created by Akechi on 6/22/2016.
 */
public interface MainConstract {
    interface View extends BaseView<Presenter> {
        Context getCurContext();
        FragmentActivity getCurActivity();
    }

    interface Presenter extends BasePresenter {
        void onClick_btnRecyclerView01();
        void onClick_btnRecyclerView02();
        void onClick_btnRecyclerView03();
        void onClick_btnTmp();
        void onClick_btnCSN();
    }
}
