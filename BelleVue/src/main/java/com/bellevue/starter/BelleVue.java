package com.bellevue.starter;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by guidis on 1/30/16.
 */

public class BelleVue extends AppCompatActivity {

    private String dTxt;
    private int dImg;
    private TextView tv, htv;
    private ImageView iv, hiv;
    private NavigationView nav;
    private DrawerLayout dl;
    private FloatingActionButton addButton;
    private TextView user_name;
    private Location currentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.belle_vue);

        user_name = (TextView) findViewById(R.id.user_name);
        addButton = (FloatingActionButton) findViewById(R.id.addBut);

        //  if (getIntent().getExtras().getString("user_name") != null)
        //   user_name.append(getIntent().getExtras().getString("user_name"));
        // else
            user_name.append("coucoutwa");

        dl = (DrawerLayout) findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dl.openDrawer(GravityCompat.START);
            }
        });

        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setTypeface(com.bellevue.starter.CustomFontsLoader.getTypeface(this, com.bellevue.starter.CustomFontsLoader.AlexBrush));

        //for add button
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vueTemp = new Intent(getApplicationContext(), AddVue.class);
                startActivity(vueTemp);
            }
        });


    }
}
