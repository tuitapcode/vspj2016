package com.t4pj.mvp_practices.RecyclerView03;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.t4pj.mvp_practices.R;

/**
 * Created by Akechi on 6/22/2016.
 */
public class CardType1Holder extends RecyclerView.ViewHolder {

    ImageView imageView;

    public CardType1Holder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.ivPhoto);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
