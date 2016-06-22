package com.t4pj.mvp_practices.RecyclerView01;

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
public class Rv01Fragment extends Fragment implements Rv01Constract.View {

    Rv01Constract.Presenter mPresenter;

    ArrayList<Rv01Data> datas;
    RecyclerView recyclerView;

    public static Rv01Fragment newInstance() {
        Rv01Fragment fragment = new Rv01Fragment();

        return fragment;
    }

    @Override
    public void setPresenter(Rv01Constract.Presenter Presenter) {
        mPresenter = Presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.rv01_frag, container, false);

        recyclerView = (RecyclerView)root.findViewById(R.id.rv01);
        recyclerView.setHasFixedSize(true);

        datas = Rv01Data.createContactsList(5);
//        datas.addAll(Rv01Data.createContactsList(5));
        Rv01DataAdapter adapter = new Rv01DataAdapter(getContext(), datas);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        datas.addAll(Rv01Data.createContactsList(5));
        adapter.notifyItemRangeInserted(adapter.getItemCount(), datas.size());

        return root;
    }
}
