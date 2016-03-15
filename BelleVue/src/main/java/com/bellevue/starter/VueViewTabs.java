package com.bellevue.starter;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by asusss on 9.03.2016.
 */

public class VueViewTabs extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs_main);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.pager);

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

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("BelleVue");
        query.getInBackground(getIntent().getExtras().getString("objectId"), new GetCallback<ParseObject>() {
            public void done(ParseObject vue, ParseException e) {
                if (e == null) {
                    viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), vue));
                    tabLayout.setupWithViewPager(viewPager);
                } else {
                    Log.d("ViewTabs", e.getMessage());
                }
            }
        });
    }
}