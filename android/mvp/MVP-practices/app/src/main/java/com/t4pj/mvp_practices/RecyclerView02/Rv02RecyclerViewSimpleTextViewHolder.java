package com.t4pj.mvp_practices.RecyclerView02;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.t4pj.mvp_practices.R;

/**
 * Created by Akechi on 6/22/2016.
 */
public class Rv02RecyclerViewSimpleTextViewHolder extends RecyclerView.ViewHolder {

    TextView txtText;

    public Rv02RecyclerViewSimpleTextViewHolder(View v) {
        super(v);

        txtText = (TextView) v.findViewById(android.R.id.text1);
    }

    public TextView getText() {
        return txtText;
    }

    public void setText(TextView txtText) {
        this.txtText = txtText;
    }
}
