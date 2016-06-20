package com.ccs.mvp_demo02.ShowNotify;

import android.content.Context;

import com.ccs.mvp_demo02.BasePresenter;
import com.ccs.mvp_demo02.BaseView;

/**
 * Created by Akechi on 6/21/2016.
 */
public interface ShowNotifyConstract {

    interface View extends BaseView<Presenter>{
        void setText(String text);
        Context getAppContext();
    }

    interface Presenter extends BasePresenter {
        void doNotify();
    }
}
