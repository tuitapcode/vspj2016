package com.t4pj.mvp_practices.CNNStudentNews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.t4pj.mvp_practices.R;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.FadeInDownAnimator;
import jp.wasabeef.recyclerview.animators.FadeInRightAnimator;
import jp.wasabeef.recyclerview.animators.FadeInUpAnimator;
import jp.wasabeef.recyclerview.animators.FlipInRightYAnimator;
import jp.wasabeef.recyclerview.animators.LandingAnimator;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

/**
 * Load các layout chính, đóng vai trò như 1 view/form
 */
public class CSNFragment extends Fragment implements CSNConstract.View {

    CSNConstract.Presenter mPresenter;

    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    CSNAdapter csnAdapter;

    int count = 0;


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

    private ArrayList<Object> getSampleArrayList2(String text) {
        ArrayList<Object> items = new ArrayList<>();
        items.add(new CSNCard(getContext(), text));
        return items;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        View root = inflater.inflate(R.layout.csn_frag, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.csn_rv);
        floatingActionButton = (FloatingActionButton) root.findViewById(R.id.fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                csnAdapter.AddNew(getSampleArrayList2("New "+ ++count));
                recyclerView.scrollToPosition(csnAdapter.getItemCount()-1);
            }
        });


        csnAdapter = new CSNAdapter(getSampleArrayList());

//        recyclerView.setItemAnimator(new SlideInUpAnimator());
        recyclerView.setAdapter(csnAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

//        recyclerView.setItemAnimator(new SlideInLeftAnimator());
//        recyclerView.getItemAnimator().setAddDuration(500);
        recyclerView.setItemAnimator(new FadeInUpAnimator());
//        recyclerView.getItemAnimator().setAddDuration(100);


        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_main, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_seach);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                csnAdapter.setCopyItems();
                floatingActionButton.hide();
//                Toast.makeText(getContext(), "On Click", Toast.LENGTH_SHORT).show();
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                csnAdapter.ResetItems();
                floatingActionButton.show();
//                Toast.makeText(getContext(), "On Close", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


            @Override
            public boolean onQueryTextSubmit(String query) {
                csnAdapter.filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                csnAdapter.filter(newText);
                return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }
}
