package com.t4pj.mvp_practices.CNNStudentNews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.t4pj.mvp_practices.R;

/**
 * Entity của card
 */
public class CSNCard {
    private Bitmap Photo;
    private String Description;

    public CSNCard(Bitmap photo, String description) {
        Photo = photo;
        Description = description;
    }

    public CSNCard(Context context, String description) {
        Photo = BitmapFactory.decodeResource(context.getResources(),android.R.drawable.ic_menu_mapmode);
        Description = description;
    }

    public Bitmap getPhoto() {
        return Photo;
    }

    public void setPhoto(Bitmap photo) {
        Photo = photo;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
