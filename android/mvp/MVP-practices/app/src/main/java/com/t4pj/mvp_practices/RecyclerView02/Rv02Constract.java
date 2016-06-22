package com.t4pj.mvp_practices.RecyclerView02;

import android.content.Context;

import com.t4pj.mvp_practices.BasePresenter;
import com.t4pj.mvp_practices.BaseView;

/**
 * Created by Akechi on 6/22/2016.
 */
public interface Rv02Constract {
    interface View extends BaseView<Presenter> {
        Context getCurContext();
    }

    interface Presenter extends BasePresenter {

    }
}
