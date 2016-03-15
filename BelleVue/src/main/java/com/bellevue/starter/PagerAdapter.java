
package com.bellevue.starter;

/**
 * Created by asusss on 3.03.2016.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.parse.ParseObject;

public class PagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;

    private String vueName, vueDescription;
    private int vueRate;
    private String tabTitles[] = new String[] { "General Info", "Comments"};

    public PagerAdapter(FragmentManager fm, ParseObject vue) {
        super(fm);
        vueName = vue.getString("name");
        vueDescription = vue.getString("description");
        vueRate = vue.getInt("rate");
        // Log.d("PagerAdapter", vue.getString("name"));
    }

    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return InfoTab.newInstance(vueName, vueDescription, vueRate);
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