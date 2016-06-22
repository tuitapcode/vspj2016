package com.t4pj.mvp_practices.RecyclerView02;

import java.util.ArrayList;

/**
 * Created by Akechi on 6/22/2016.
 */
public class Rv02Card {
    int iThumb;
    String txtDes;

    public Rv02Card(int iThumb, String txtDes) {
        this.txtDes = txtDes;
        this.iThumb = iThumb;
    }

    public int getThumb() {
        return iThumb;
    }

    public String getDes() {
        return txtDes;
    }

    private static int lastItemId = 0;

    public static ArrayList<Rv02Card> createContactsList(int numDatas) {
        ArrayList<Rv02Card> cards = new ArrayList<Rv02Card>();

        for (int i = 1; i <= numDatas; i++) {
            lastItemId++;
            cards.add(new Rv02Card(android.R.drawable.sym_def_app_icon, "Chào " + lastItemId));
        }

        return cards;
    }
}
