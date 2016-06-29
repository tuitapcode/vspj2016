package com.t4pj.mvp_practices.RecyclerView03;

import com.t4pj.mvp_practices.BasePresenter;
import com.t4pj.mvp_practices.BaseView;

/**
 * Created by Akechi on 6/22/2016.
 */
public interface CardConstract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {

    }
}
