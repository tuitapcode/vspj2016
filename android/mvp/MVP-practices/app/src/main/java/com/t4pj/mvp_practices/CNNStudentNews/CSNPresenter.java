package com.t4pj.mvp_practices.CNNStudentNews;

/**
 * Phụ trách xử lý các tương tác do view gởi về
 */
public class CSNPresenter implements CSNConstract.Presenter {

    CSNConstract.View mView;

    @Override
    public void start() {

    }

    public CSNPresenter(CSNConstract.View view) {
        mView = view;
        mView.setPresenter(this);
    }
}
