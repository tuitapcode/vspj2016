package com.t4pj.mvp_practices.RecyclerView01;

import com.t4pj.mvp_practices.BasePresenter;
import com.t4pj.mvp_practices.BaseView;
import com.t4pj.mvp_practices.Main.MainConstract;

/**
 * Created by Akechi on 6/22/2016.
 */
public interface Rv01Constract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {

    }
}
