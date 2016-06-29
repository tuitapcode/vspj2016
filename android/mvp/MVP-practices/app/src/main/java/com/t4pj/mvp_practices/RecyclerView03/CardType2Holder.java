package com.t4pj.mvp_practices.RecyclerView03;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.t4pj.mvp_practices.R;

/**
 * Created by Akechi on 6/22/2016.
 */
public class CardType2Holder extends RecyclerView.ViewHolder {
    TextView rv03_txt1,rv03_txt2;
    public CardType2Holder(View itemView) {
        super(itemView);
        rv03_txt1 = (TextView) itemView.findViewById(R.id.rv03_t2_txt1);
        rv03_txt2 = (TextView) itemView.findViewById(R.id.rv03_t2_txt2);
    }

    public TextView getRv03_txt1() {
        return rv03_txt1;
    }

    public void setRv03_txt1(TextView rv03_txt1) {
        this.rv03_txt1 = rv03_txt1;
    }

    public TextView getRv03_txt2() {
        return rv03_txt2;
    }

    public void setRv03_txt2(TextView rv03_txt2) {
        this.rv03_txt2 = rv03_txt2;
    }
}
