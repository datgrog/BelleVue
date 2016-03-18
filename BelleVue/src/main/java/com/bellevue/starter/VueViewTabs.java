package com.bellevue.starter;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bellevue.starter.Utils.Tool;
import com.google.common.io.Files;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by asusss on 9.03.2016.
 */

public class VueViewTabs extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private android.support.v4.app.FragmentManager fragmentManager;
    private String objectId;

    static PagerAdapter pageAdapter;
    static private int currentPicture = 0;
    static private int cptr = 0;
    private ArrayList<String> outPutfileUri = new ArrayList<>();
    private File root = new File(Tool.root_path);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs_main);

        objectId = getIntent().getExtras().getString("objectId");
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.pager);

        if (currentPicture == 0) {
            cptr = 0;
            Tool.reset_bellevue_dir(root);
        }

        /* Initialise toolbar */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tool.reset_bellevue_dir(root);
                currentPicture = 0;
                pageAdapter.resetSlider();
                finish();
            }
        });

        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        if (mTitle != null)
            mTitle.setTypeface(com.bellevue.starter.CustomFontsLoader.getTypeface(this, com.bellevue.starter.CustomFontsLoader.AlexBrush));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        /* PHOTO PRINTED SUCESS ONE QUERY
        ParseQuery<ParseObject> query = ParseQuery.getQuery("BelleVue");
        query.include("Pictures");
        query.getInBackground("x4kwaCoVyj", new GetCallback<ParseObject>() {
            public void done(ParseObject vue, ParseException e) {
                if (e == null) {
                    ParseFile picture;
                    pageAdapter = new PagerAdapter(getSupportFragmentManager(), vue);

                    picture = vue.getParseObject("Pictures").getParseFile("picture0");
                    picture.getDataInBackground(new GetDataCallback() {
                        @Override
                        public void done(byte[] data, ParseException e) {
                            Uri test;
                            File photo = new File(Tool.root_path, "tmp_pic.jpg");
                            test = Uri.fromFile(photo);
                            try {
                                Files.write(data, photo);
                                pageAdapter.updatePagerAdapter(test.toString());
                                Log.d("PICTURE", test.toString());
                            } catch (IOException io) {
                                Log.d("PAGER ADAPTER", e.toString());
                            }
                        }
                    });

                    viewPager.setAdapter(pageAdapter);
                    tabLayout.setupWithViewPager(viewPager);
                } else {
                    Log.d("ViewTabs", e.getMessage());
                }
            }
        });*/

        /*
        ParseQuery<ParseObject> query = ParseQuery.getQuery("BelleVue");
        query.include("Pictures");
        query.getInBackground(objectId, new GetCallback<ParseObject>() {
            public void done(ParseObject vue, ParseException e) {
                if (e == null) {
                    ParseFile picture;
                    int nbPicture = vue.getParseObject("Pictures").getInt("nbPicture");
                    Log.d("Nb pic ", String.valueOf(nbPicture));
                    pageAdapter = new PagerAdapter(getSupportFragmentManager(), vue);

                    for (currentPicture = 0; currentPicture < nbPicture; currentPicture++) {
                        picture = vue.getParseObject("Pictures").getParseFile("picture" + String.valueOf(currentPicture));
                        picture.getDataInBackground(new GetDataCallback() {
                            @Override
                            public void done(byte[] data, ParseException e) {
                                // ArrayList<Uri> test = new ArrayList<Uri>();
                                // test.add(Uri.fromFile(photo));
                                try {
                                    File photo = new File(Tool.root_path,
                                            "tmp_pic" + String.valueOf(cptr) + ".jpg");
                                    Files.write(data, photo);
                                    Log.d("currentPicture", String.valueOf(cptr));
                                    pageAdapter.updatePagerAdapter(photo.getAbsolutePath());
                                    cptr++;
                                } catch (IOException io) {
                                    Log.d("PAGER ADAPTER", e.toString());
                                }
                            }
                        });
                    }
                    viewPager.setAdapter(pageAdapter);
                    tabLayout.setupWithViewPager(viewPager);
                } else {
                    Log.d("ViewTabs", e.getMessage());
                }
            }
        });*/

        ParseQuery<ParseObject> query = ParseQuery.getQuery("BelleVue");
        query.include("Pictures");
        query.getInBackground(objectId, new GetCallback<ParseObject>() {
            public void done(ParseObject vue, ParseException e) {
                if (e == null) {
                    ParseFile picture;
                    int nbPicture = vue.getParseObject("Pictures").getInt("nbPicture");
                    Log.d("Nb pic ", String.valueOf(nbPicture));
                    fragmentManager = getSupportFragmentManager();
                    pageAdapter = new PagerAdapter(fragmentManager, vue);

                    for (currentPicture = 0; currentPicture < nbPicture; currentPicture++) {
                        picture = vue.getParseObject("Pictures").getParseFile("picture" + String.valueOf(currentPicture));
                        picture.getDataInBackground(new GetDataCallback() {
                            @Override
                            public void done(byte[] data, ParseException e) {
                                // ArrayList<Uri> test = new ArrayList<Uri>();
                                // test.add(Uri.fromFile(photo));
                                try {
                                    if(cptr==0) {
                                        Log.d("VueViewTab", "RESET SLIDER STP PLZ");
                                    }

                                    Uri test;
                                    File photo = new File(Tool.root_path,
                                            "tmp_pic" + String.valueOf(cptr) + ".jpg");
                                    test = Uri.fromFile(photo);
                                    Files.write(data, photo);
                                    Log.d("currentPicture", String.valueOf(cptr));
                                    pageAdapter.updatePagerAdapter(test.toString());
                                    cptr++;
                                } catch (IOException io) {
                                    Log.d("PAGER ADAPTER", e.toString());
                                }
                            }
                        });
                    }
                    viewPager.setAdapter(pageAdapter);
                    tabLayout.setupWithViewPager(viewPager);
                } else {
                    Log.d("ViewTabs", e.getMessage());
                }
            }
        });

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
        });*/
    }

    @Override
    public void onBackPressed() {
        /* Check BelleVue directory */
        Tool.reset_bellevue_dir(root);
        currentPicture = 0;
        cptr = 0;
        pageAdapter.resetSlider();
        super.onBackPressed();
    }

}