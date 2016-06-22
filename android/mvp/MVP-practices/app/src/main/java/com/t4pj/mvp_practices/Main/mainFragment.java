package com.t4pj.mvp_practices.Main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.t4pj.mvp_practices.R;

/**
 * TODO: Fragment load các button mở các activity bài tập
 */
public class MainFragment extends Fragment implements MainConstract.View {

    MainConstract.Presenter mPresenter;

    Button btnRecyclerView01;

    @Override
    public void setPresenter(MainConstract.Presenter Presenter) {
        mPresenter = Presenter;
    }

    public static MainFragment newInstance() {
        MainFragment mainFragment = new MainFragment();

        return mainFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.main_frag, container, false);

        btnRecyclerView01 = (Button) root.findViewById(R.id.recycler_view);
        btnRecyclerView01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.onClick_btnRecyclerView01();
            }
        });

        return root;
    }

    @Override
    public Context getCurContext() {
        return getContext();
    }
}
