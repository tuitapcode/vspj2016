package com.t4pj.mvp_practices.RecyclerView02;

/**
 * Created by Akechi on 6/22/2016.
 */
public class Rv02Presenter implements Rv02Constract.Presenter {

    Rv02Constract.View mView;

    public Rv02Presenter(Rv02Constract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }


}
