package com.bellevue.starter;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bellevue.starter.Utils.Tool;
import com.google.common.io.Files;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.File;
import java.io.IOException;

/**
 * Created by asusss on 9.03.2016.
 */

public class VueViewTabs extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String objectId;
    private File root = new File(Tool.root_path);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs_main);

        objectId = getIntent().getExtras().getString("objectId");
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.pager);

        /* Initialise toolbar */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tool.reset_bellevue_dir(root);
                finish();
            }
        });

        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        if (mTitle != null)
            mTitle.setTypeface(com.bellevue.starter.CustomFontsLoader.getTypeface(this, com.bellevue.starter.CustomFontsLoader.AlexBrush));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

/*
        ParseQuery<ParseObject> query = ParseQuery.getQuery("BelleVue");
        query.getInBackground(objectId, new GetCallback<ParseObject>() {
            public void done(ParseObject vue, ParseException e) {
                if (e == null) {
                    viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), vue));
                    tabLayout.setupWithViewPager(viewPager);
                } else {
                    Log.d("ViewTabs", e.getMessage());
                }
            }
        });
        */

        ParseQuery<ParseObject> query = ParseQuery.getQuery("BelleVue");
        query.include("Pictures");
        query.getInBackground("x4kwaCoVyj", new GetCallback<ParseObject>() {
            public void done(ParseObject vue, ParseException e) {
                if (e == null) {
                    getParseFile("picture0");
                    pictures.getDataInBackground(new GetDataCallback() {
                        @Override
                        public void done(byte[] data, ParseException e) {
                            File photo = new File(Tool.root_path,
                                    "tmp_pic" + String.valueOf(nbPicture) + ".jpg");
                            outPutfileUri = Uri.fromFile(photo);
                            try {
                                Files.write(data, photo);
                                uriPicture = outPutfileUri.toString();
                                Log.d("PAGER ADAPTER", uriPicture);
                            } catch (IOException io) {
                                Log.d("PAGER ADAPTER", e.toString());
                            }
                        }
                    });
                    viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), vue, vue.getParseObject("Pictures")));
                    tabLayout.setupWithViewPager(viewPager);
                    Log.d("ViewTabs : VUE", vue.getString("name"));
                    Log.d("ViewTabs : PICTURE", String.valueOf(vue.getParseObject("Pictures").getInt("nbPicture")));

                } else {
                    Log.d("ViewTabs", e.getMessage());
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        /* Check BelleVue directory */
        Tool.reset_bellevue_dir(root);
        super.onBackPressed();
    }

}