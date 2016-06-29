package com.t4pj.mvp_practices.RecyclerView03;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.t4pj.mvp_practices.R;

import java.util.ArrayList;

/**
 * Created by Akechi on 6/22/2016.
 */
public class CardFragment extends Fragment implements CardConstract.View {

    CardConstract.Presenter mPresenter;

    RecyclerView recyclerView;

    @Override
    public void setPresenter(CardConstract.Presenter Presenter) {
        mPresenter = Presenter;
    }

    public static CardFragment newInstance() {
        CardFragment cardFragment = new CardFragment();
        return cardFragment;
    }

    private ArrayList<Object> getSampleArrayList() {
        ArrayList<Object> items = new ArrayList<>();
        items.add(new CardType1(R.drawable.sample_golden_gate));
        items.add(new CardType2("Hello", "Boy"));
        items.add(new CardType3(R.drawable.road));
        items.add(new CardType1(R.drawable.flower1));
        items.add(new CardType2("Hi", "Girl"));
        items.add(new CardType2("Ending", "He he!"));
        items.add(new CardType1(R.drawable.flower2));
        items.add(new CardType3(R.drawable.chim));
        return items;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.rv03_frag, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.rv03);

        recyclerView.setAdapter(new CardAdapter(getSampleArrayList()));
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        return root;
    }
}
