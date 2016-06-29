package com.t4pj.mvp_practices.RecyclerView03;

/**
 * Created by Akechi on 6/22/2016.
 */
public class CardPresenter implements CardConstract.Presenter {

    CardConstract.View mView;

    @Override
    public void start() {

    }

    public CardPresenter(CardConstract.View view) {
        mView = view;
        mView.setPresenter(this);
    }
}
