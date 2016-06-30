package com.t4pj.mvp_practices.CNNStudentNews;

import com.t4pj.mvp_practices.BasePresenter;
import com.t4pj.mvp_practices.BaseView;

/**
 * Khế ước áp lên view và presenter
 */
public interface CSNConstract {
    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {

    }
}
