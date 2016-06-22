package com.t4pj.mvp_practices.Main;

import android.content.Context;

import com.t4pj.mvp_practices.BasePresenter;
import com.t4pj.mvp_practices.BaseView;

/**
 * Created by Akechi on 6/22/2016.
 */
public interface MainConstract {
    interface View extends BaseView<Presenter> {
        Context getCurContext();
    }

    interface Presenter extends BasePresenter {
        void onClick_btnRecyclerView01();
    }
}
