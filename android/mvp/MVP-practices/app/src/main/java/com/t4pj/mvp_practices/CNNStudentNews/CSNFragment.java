package com.t4pj.mvp_practices.CNNStudentNews;

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
 * Load các layout chính, đóng vai trò như 1 view/form
 */
public class CSNFragment extends Fragment implements CSNConstract.View {

    CSNConstract.Presenter mPresenter;

    RecyclerView recyclerView;
    CSNAdapter csnAdapter;

    public static CSNFragment newInstance() {
        CSNFragment csnFragment = new CSNFragment();

        return csnFragment;
    }

    @Override
    public void setPresenter(CSNConstract.Presenter Presenter) {
        mPresenter = Presenter;
    }

    private ArrayList<Object> getSampleArrayList() {
        ArrayList<Object> items = new ArrayList<>();
        items.add(new CSNCard(getContext(), "Hello"));
        items.add(new CSNCard(getContext(), "Hi"));
        items.add(new CSNCard(getContext(), "Hm?"));
        return items;
    }

    private ArrayList<Object> getSampleArrayList2() {
        ArrayList<Object> items = new ArrayList<>();
        items.add(new CSNCard(getContext(), "Them~"));
        return items;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.csn_frag, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.csn_rv);

        csnAdapter = new CSNAdapter(getSampleArrayList());

        recyclerView.setAdapter(csnAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        csnAdapter.AddNew(getSampleArrayList2());

        return root;
    }
}
