package com.t4pj.mvp_practices.CNNStudentNews;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.t4pj.mvp_practices.R;

/**
 * Chứa các View trên 1 item/card, dùng cho anh Adapter
 */
public class CSNCardHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView ivThumb;
    TextView txtDate, txtLike;
    IVHClick mIvhClick;

    public CSNCardHolder(View itemView) {
        super(itemView);

        ivThumb = (ImageView) itemView.findViewById(R.id.csn_ivThumb);
        txtDate = (TextView) itemView.findViewById(R.id.csn_txtDate);

    }

    public CSNCardHolder(View itemView, IVHClick ivhClick) {
        super(itemView);
        mIvhClick = ivhClick;

        ivThumb = (ImageView) itemView.findViewById(R.id.csn_ivThumb);
        txtDate = (TextView) itemView.findViewById(R.id.csn_txtDate);//txtLikeStr
        txtLike = (TextView) itemView.findViewById(R.id.txtLikeStr);

        ivThumb.setOnClickListener(this);
        txtDate.setOnClickListener(this);
        txtLike.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view instanceof ImageView) {
            mIvhClick.ivClick(this, (ImageView) view);
        } else if (view instanceof TextView) {
            //Cách 1:
//            if (view == txtDate) {
//                Log.d("Jea", "txtDate");
//            } else if (view == txtLike) {
//                Log.d("Jea", "txtLike");
//            }

            //Cách 2:
//            if (view.getId() == R.id.csn_txtDate) {
//                Log.d("Jea", "txtDate");
//            } else if (view.getId() == R.id.txtLikeStr) {
//                Log.d("Jea", "txtLike");
//            }


            if (view.getId() == R.id.csn_txtDate) {
                mIvhClick.txtDateClick(this, view);
            } else if (view.getId() == R.id.txtLikeStr) {
                mIvhClick.txtLikeClick(this, view);
            }


        }
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

    public static interface IVHClick {
        public void ivClick(RecyclerView.ViewHolder vh, ImageView iv);
        public void txtDateClick(RecyclerView.ViewHolder vh, View view);
        public void txtLikeClick(RecyclerView.ViewHolder vh, View view);
    }
}
