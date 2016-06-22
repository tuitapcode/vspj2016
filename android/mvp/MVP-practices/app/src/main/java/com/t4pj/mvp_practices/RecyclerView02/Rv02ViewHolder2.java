package com.t4pj.mvp_practices.RecyclerView02;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.t4pj.mvp_practices.R;

/**
 * Created by Akechi on 6/22/2016.
 */
public class Rv02ViewHolder2 extends RecyclerView.ViewHolder {

    ImageView imageView;

    public Rv02ViewHolder2(View itemView) {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.ivExample);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
