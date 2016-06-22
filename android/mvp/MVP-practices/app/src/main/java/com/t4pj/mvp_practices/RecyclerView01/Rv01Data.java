package com.t4pj.mvp_practices.RecyclerView01;

import java.util.ArrayList;

/**
 * Created by Akechi on 6/22/2016.
 */
public class Rv01Data {
    private String mText;
    private boolean mLike;

    public Rv01Data(String text, boolean like) {
        mText = text;
        mLike = like;
    }

    public String getText() {
        return mText;
    }

    public boolean isLike() {
        return mLike;
    }

    private static int lastItemId = 0;

    public static ArrayList<Rv01Data> createContactsList(int numDatas) {
        ArrayList<Rv01Data> datas = new ArrayList<Rv01Data>();

        for (int i = 1; i <= numDatas; i++) {
            datas.add(new Rv01Data("Text " + ++lastItemId, i <= numDatas / 2));
        }

        return datas;
    }
}
