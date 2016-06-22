package com.t4pj.mvp_practices.RecyclerView01;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.t4pj.mvp_practices.R;

import java.util.List;

/**
 * Created by Akechi on 6/22/2016.
 */
public class Rv01DataAdapter extends RecyclerView.Adapter<Rv01DataAdapter.ViewHolder> {

    private List<Rv01Data> mDatas;
    private Context mContext;

    public Rv01DataAdapter (Context context, List<Rv01Data> data) {
        this.mDatas = data;
        this.mContext = context;
    }

    private Context getContext() {
        //
        return mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View dataView = inflater.inflate(R.layout.rv01_item1, parent, false);

        ViewHolder viewHolder = new ViewHolder(dataView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Rv01Data data = mDatas.get(position);

        TextView textView = holder.txtText;
        textView.setText(data.getText());

        Button button = holder.btnLike;

        if (data.isLike()) {
            button.setText("Liked");

        }
        else {
            button.setText("Like");

        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtText;
        public Button btnLike;

        public ViewHolder(View itemView) {
            super(itemView);

            txtText = (TextView) itemView.findViewById(R.id.txtText);
            btnLike = (Button) itemView.findViewById(R.id.btnLike);

        }
    }
}
