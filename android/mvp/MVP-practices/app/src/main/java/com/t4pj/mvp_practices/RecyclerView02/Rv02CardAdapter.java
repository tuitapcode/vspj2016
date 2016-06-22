package com.t4pj.mvp_practices.RecyclerView02;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.t4pj.mvp_practices.R;

import java.util.List;

/**
 * Created by Akechi on 6/22/2016.
 */
public class Rv02CardAdapter extends RecyclerView.Adapter<Rv02CardAdapter.ViewHolder> {

    Context mContext;
    List<Rv02Card> mCardList;

    public Rv02CardAdapter(Context context, List<Rv02Card> cardList) {
        mContext = context;
        mCardList = cardList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View dataView = inflater.inflate(R.layout.rv02_item1, parent, false);

        ViewHolder viewHolder = new ViewHolder(dataView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Rv02Card card = mCardList.get(position);

        ImageView imageView = holder.imgThumb;
        imageView.setImageResource(card.iThumb);

        TextView textView = holder.txtDes;
        textView.setText(card.getDes());
    }

    @Override
    public int getItemCount() {
        //
        return mCardList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgThumb;
        TextView txtDes;

        public ViewHolder(View itemView) {
            super(itemView);

            imgThumb = (ImageView) itemView.findViewById(R.id.imgThumb);
            txtDes = (TextView) itemView.findViewById(R.id.txtDescription);
        }
    }
}
