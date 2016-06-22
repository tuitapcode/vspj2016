package com.t4pj.mvp_practices.RecyclerView01;

/**
 * Created by Akechi on 6/22/2016.
 */
public class Rv01Presenter implements Rv01Constract.Presenter {

    private final Rv01Constract.View mView;

    @Override
    public void start() {

    }

    public Rv01Presenter(Rv01Constract.View view){
        mView = view;

        mView.setPresenter(this);
    }

}
