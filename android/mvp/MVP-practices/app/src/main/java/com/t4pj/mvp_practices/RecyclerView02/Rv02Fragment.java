package com.t4pj.mvp_practices.RecyclerView02;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.t4pj.mvp_practices.R;

import java.util.ArrayList;

/**
 * Created by Akechi on 6/22/2016.
 */
public class Rv02Fragment extends Fragment implements Rv02Constract.View {

    Rv02Constract.Presenter mPresenter;

//    ImageView imgThumb;
//    TextView txtDes;

    RecyclerView recyclerView;
    ArrayList<Rv02Card> cards;

    public static Rv02Fragment newInstance() {
        Rv02Fragment rv02Fragment = new Rv02Fragment();
        return rv02Fragment;
    }

    @Override
    public void setPresenter(Rv02Constract.Presenter Presenter) {
        mPresenter = Presenter;
    }

    @Override
    public Context getCurContext() {
        return getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.rv02_frag, container, false);

//        imgThumb = (ImageView) root.findViewById(R.id.imgThumb);
//        txtDes = (TextView) root.findViewById(R.id.txtDescription);

        recyclerView = (RecyclerView)root.findViewById(R.id.rv02);

        cards = Rv02Card.createContactsList(10);
        Rv02CardAdapter cardAdapter = new Rv02CardAdapter(getContext(), cards);

        recyclerView.setAdapter(cardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        return root;
    }
}
