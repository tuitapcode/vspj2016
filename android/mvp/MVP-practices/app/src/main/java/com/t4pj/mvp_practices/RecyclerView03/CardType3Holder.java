package com.t4pj.mvp_practices.RecyclerView03;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.t4pj.mvp_practices.R;

/**
 * Created by Akechi on 6/22/2016.
 */
public class CardType3Holder extends RecyclerView.ViewHolder {
    ImageView iv_t3;

    public CardType3Holder(View itemView) {
        super(itemView);

        iv_t3 = (ImageView) itemView.findViewById(R.id.rv03_iv_ex);
    }

    public ImageView getIv_t3() {
        return iv_t3;
    }

    public void setIv_t3(ImageView iv_t3) {
        this.iv_t3 = iv_t3;
    }
}
