package com.t4pj.mvp_practices.RecyclerView02;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.t4pj.mvp_practices.R;

import java.util.List;

/**
 * Created by Akechi on 6/22/2016.
 */
public class Rv02ComplexRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;
    private final int USER = 0, IMAGE = 1;

    public Rv02ComplexRecyclerViewAdapter(List<Object> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (items.get(position) instanceof User) {
            return USER;
        } else if (items.get(position) instanceof String) {
            return IMAGE;
        }

        return -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case USER:
                View v1 = inflater.inflate(R.layout.rv02_view_holder_01, parent, false);
                viewHolder = new Rv02ViewHolder1(v1);
                break;
            case IMAGE:
                View v2 = inflater.inflate(R.layout.rv02_view_holder_02, parent, false);
                viewHolder = new Rv02ViewHolder2(v2);
                break;
            default:
                View v = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
                viewHolder = new Rv02RecyclerViewSimpleTextViewHolder(v);
                break;

        }


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case USER:
                Rv02ViewHolder1 vh1 = (Rv02ViewHolder1)holder;
                configureViewHolder1(vh1, position);
                break;
            case IMAGE:
                Rv02ViewHolder2 vh2 = (Rv02ViewHolder2)holder;
                configureViewHolder2(vh2, position);
                break;
            default:
                Rv02RecyclerViewSimpleTextViewHolder vh = (Rv02RecyclerViewSimpleTextViewHolder) holder;
                configureDefaultViewHolder(vh, position);
                break;

        }

    }

    private void configureDefaultViewHolder(Rv02RecyclerViewSimpleTextViewHolder vh, int position) {
        vh.getText().setText((CharSequence) items.get(position));
    }

    private void configureViewHolder2(Rv02ViewHolder2 vh2, int position) {
        vh2.getImageView().setImageResource(R.drawable.sample_golden_gate);
    }

    private void configureViewHolder1(Rv02ViewHolder1 vh1, int position) {
        User user = (User) items.get(position);
        if (user != null) {
            vh1.getLabel1().setText("Name: " + user.getName());
            vh1.getLabel2().setText("Discription: " + user.getDescription());
        }
    }

}
