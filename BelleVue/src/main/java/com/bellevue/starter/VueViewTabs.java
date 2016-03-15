package com.bellevue.starter;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by asusss on 9.03.2016.
 */
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;



public class VueViewTabs extends AppCompatActivity {

    String vueName;
    String vueDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs_main);

        /* Initialise toolbar */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        if (mTitle != null)
            mTitle.setTypeface(com.bellevue.starter.CustomFontsLoader.getTypeface(this, com.bellevue.starter.CustomFontsLoader.AlexBrush));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("General Info"));
        tabLayout.addTab(tabLayout.newTab().setText("Comments"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("BelleVue");
        query.whereEqualTo("objectId", getIntent().getExtras().getString("objectId"));

        query.getFirstInBackground(new GetCallback<ParseObject>() {

            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    vueName = object.getString("name");
                    vueDescription = object.getString("description");
                    Toast.makeText(VueViewTabs.this, "Success "+ vueName + " " + vueDescription, Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("problem : ", e.getMessage());
                }
            }
        });


        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}