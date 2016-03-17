
package com.bellevue.starter;

/**
 * Created by asusss on 3.03.2016.
 */

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.bellevue.starter.Utils.Tool;
import com.google.common.io.Files;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;

    private String vueName, vueDescription;
    private int vueRate;

    private int nbPicture;
    ArrayList<String> uriPictures;

    private String tabTitles[] = new String[] { "General Info", "Comments"};

    public PagerAdapter(FragmentManager fm, ParseObject vue, int nbPicture, ArrayList<String> uriPictures) {
        super(fm);
        vueName = vue.getString("name");
        vueDescription = vue.getString("description");
        vueRate = vue.getInt("rate");

        this.nbPicture = nbPicture;
        this.uriPictures = uriPictures;

    }

    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return InfoTab.newInstance(vueName, vueDescription, vueRate, nbPicture, uriPictures);
            case 1:
                return new CommentTab();
            default:
                return null;
        }
    }

    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    public int getCount() {
        return PAGE_COUNT;
    }
}