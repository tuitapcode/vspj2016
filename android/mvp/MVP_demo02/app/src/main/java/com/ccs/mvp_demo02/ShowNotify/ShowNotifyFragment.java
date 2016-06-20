package com.ccs.mvp_demo02.ShowNotify;

import static com.google.common.base.Preconditions.checkNotNull;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ccs.mvp_demo02.R;


/**
 * Created by Akechi on 6/21/2016.
 */
public class ShowNotifyFragment extends Fragment implements ShowNotifyConstract.View {

    private ShowNotifyConstract.Presenter mPresenter;

    private TextView txtText;
    private Button btnHello;

    @Override
    public Context getAppContext() {
        return getContext();
    }

    public static ShowNotifyFragment newInstance() {
        ShowNotifyFragment mShowNotifyFragment = new ShowNotifyFragment();

        return mShowNotifyFragment;
    }

    @Override
    public void setPresenter(ShowNotifyConstract.Presenter presenter) {
        checkNotNull(presenter, "presenter ko dc null");
        mPresenter = presenter;
    }

    @Override
    public void setText(String text) {
        txtText.setText(text);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.show_notify_fragment, container, false);

        txtText = (TextView) root.findViewById(R.id.textView);
        btnHello = (Button) getActivity().findViewById(R.id.btnHello);

        btnHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.doNotify();
            }
        });


        return root;
    }


}
