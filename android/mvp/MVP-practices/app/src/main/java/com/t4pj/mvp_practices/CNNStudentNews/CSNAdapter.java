package com.t4pj.mvp_practices.CNNStudentNews;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.t4pj.mvp_practices.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.jar.Pack200;

/**
 * Lo các xử lý trên RecyclerView
 */
public class CSNAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;
    private List<Object> copyItems;
    private final int CSN_CARD = 0;

    private final static int FADE_DURATION = 1000; // in milliseconds
    private int lastPos = -1;

    public List<Object> getData(){
        return items;
    }

    //Copy data khi click search
    public void setCopyItems(){
        copyItems.clear();
        copyItems.addAll(items);
    }

    //Copy data khi click search
    public void ResetItems(){
        items.clear();
        items.addAll(copyItems);
        copyItems.clear();
        notifyDataSetChanged();
    }

    public void filter(String text) {

        //Phục hồi data
        if (text.isEmpty()) {
            items.clear();
            items.addAll(copyItems);
        } else {
            List<Object> result = new ArrayList<>();
            text = text.toLowerCase();
            for (Object item: copyItems) {
                CSNCard i = (CSNCard) item;
                if (i.getDescription().toLowerCase().contains(text)) {
                    result.add(item);
                }
            }

            items.clear();
            items.addAll(result);
        }

        notifyDataSetChanged();

    }

    public CSNAdapter(List<Object> items) {
        this.items = items;
        this.copyItems = new ArrayList<>();
    }

    public void AddNew(List<Object> newItems) {
        int oldNumItem = items.size();
        items.addAll(newItems);
        notifyItemRangeChanged(oldNumItem, items.size());
    }

    @Override
    public int getItemViewType(int position) {

        if (items.get(position) instanceof CSNCard) {
            return CSN_CARD;
        }

        return -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case CSN_CARD:
                View csnCard = inflater.inflate(R.layout.csn_card, parent, false);
                viewHolder = new CSNCardHolder(csnCard);
                break;
            default:
                // chưa xử lý
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case CSN_CARD:
                CSNCardHolder csnCardHolder = (CSNCardHolder) holder;
                CSNCard csnCard = (CSNCard) items.get(position);

                if (csnCard != null) {
                    csnCardHolder.getIvThumb().setImageBitmap(csnCard.getPhoto());
                    csnCardHolder.getTxtDate().setText(csnCard.getDescription());
                }
                break;
            default:
                //
                break;
        }

//        if (position > lastPos) {
//            inFromRightAnimation(holder.itemView);
//            lastPos++;
//        }


    }

    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }

    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.5f, 1.0f, 0.5f, 1.0f, Animation.RELATIVE_TO_SELF, 0.75f, Animation.RELATIVE_TO_SELF, 0.75f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }

    private void inFromRightAnimation(View view) {

        Animation inFromRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromRight.setDuration(500);
        inFromRight.setInterpolator(new AccelerateInterpolator());
        view.startAnimation(inFromRight);
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }
}
