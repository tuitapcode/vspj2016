package com.t4pj.mvp_practices.CNNStudentNews;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.t4pj.mvp_practices.R;

/**
 * Chứa các View trên 1 item/card, dùng cho anh Adapter
 */
public class CSNCardHolder extends RecyclerView.ViewHolder {

    ImageView ivThumb;
    TextView txtDate;

    public CSNCardHolder(View itemView) {
        super(itemView);

        ivThumb = (ImageView) itemView.findViewById(R.id.csn_ivThumb);
        txtDate = (TextView) itemView.findViewById(R.id.csn_txtDate);
    }

    public ImageView getIvThumb() {
        return ivThumb;
    }

    public void setIvThumb(ImageView ivThumb) {
        this.ivThumb = ivThumb;
    }

    public TextView getTxtDate() {
        return txtDate;
    }

    public void setTxtDate(TextView txtDate) {
        this.txtDate = txtDate;
    }
}
