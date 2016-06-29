package com.t4pj.mvp_practices.RecyclerView03;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.t4pj.mvp_practices.R;

import java.util.List;

/**
 * Created by Akechi on 6/22/2016.
 */
public class CardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> items;
    private final int FB_CARD = 0, TEXT_CARD = 1, I_CARD = 2;

    public CardAdapter(List<Object> items) {
        this.items = items;
    }

    /**
     * Phân loại card
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof CardType1) {
            Log.d("Jea", "FB_CARD check type..." + position);
            return FB_CARD;
        } else if (items.get(position) instanceof CardType2) {
            return TEXT_CARD;
        } else if (items.get(position) instanceof CardType3) {
            return I_CARD;
        } else {
            return -1;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case FB_CARD:
                View fbcard = inflater.inflate(R.layout.rv03_card_type_1, parent, false);
                viewHolder = new CardType1Holder(fbcard);
                Log.d("Jea", "FB_CARD holding...");
                break;
            case TEXT_CARD:
                View textcard = inflater.inflate(R.layout.rv03_card_type_2, parent, false);
                viewHolder = new CardType2Holder(textcard);
                break;
            case I_CARD:
                View imgcard = inflater.inflate(R.layout.rv03_card_type_3, parent, false);
                viewHolder = new CardType3Holder(imgcard);
                break;
            default:
                //
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case FB_CARD:
                CardType1Holder fbcard = (CardType1Holder) holder;
//                fbcard.getImageView().setImageResource(R.drawable.sample_golden_gate);
                CardType1 ct1 = (CardType1) items.get(position);
                if (ct1 != null) {
                    fbcard.getImageView().setImageResource(ct1.getId());
                    Log.d("Jea", "FB_CARD binding..." + position);
                }
                break;
            case TEXT_CARD:
                CardType2Holder txtcard = (CardType2Holder) holder;
                CardType2 ct2 = (CardType2) items.get(position);
                if (ct2 != null) {
                    txtcard.getRv03_txt1().setText(ct2.getText1());
                    txtcard.getRv03_txt2().setText(ct2.getText2());
                }
                break;
            case I_CARD:
                CardType3Holder imgcard = (CardType3Holder) holder;
                CardType3 ct3 = (CardType3) items.get(position);
                if (ct3 != null) {
                    imgcard.getIv_t3().setImageResource(ct3.getIv_id());
                }
                break;
            default:
                //
                break;
        }

    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        switch (holder.getItemViewType()) {
            case FB_CARD:
                Log.d("Jea", "FB_CARD detached..." + holder.getAdapterPosition());
                break;
        }
        super.onViewDetachedFromWindow(holder);
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        switch (holder.getItemViewType()) {
            case FB_CARD:
                Log.d("Jea", "FB_CARD Attached..." + holder.getAdapterPosition());
                break;
        }
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }
}
