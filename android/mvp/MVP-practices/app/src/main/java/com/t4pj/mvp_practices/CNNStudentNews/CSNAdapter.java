package com.t4pj.mvp_practices.CNNStudentNews;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.t4pj.mvp_practices.R;

import java.util.List;
import java.util.jar.Pack200;

/**
 * Lo các xử lý trên RecyclerView
 */
public class CSNAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;
    private final int CSN_CARD = 0;

    public CSNAdapter(List<Object> items) {
        this.items = items;
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

    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }
}
