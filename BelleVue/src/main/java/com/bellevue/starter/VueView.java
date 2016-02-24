package com.bellevue.starter;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bellevue.starter.Utils.Tool;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by asusss on 24.02.2016.
 */
public class VueView extends AppCompatActivity {

    static int TAKE_PIC =1;
    Uri outPutfileUri;
    int pictureId = 0;
    List<File> files = new ArrayList<>();

    static SliderLayout sliderShow;

    private ListView listView;

    private String myLat;
    private String myLng;

    private TextView person_input;
    private TextView comment_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_vue);

        Toast.makeText(getBaseContext(), "Lat : " + myLng + "\nLng : " + myLng, Toast.LENGTH_SHORT).show();



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
        mTitle.setTypeface(com.bellevue.starter.CustomFontsLoader.getTypeface(this, com.bellevue.starter.CustomFontsLoader.AlexBrush));

        toolbar.inflateMenu(R.menu.menu_add_view);

        /* Initialise slider */
        sliderShow = (SliderLayout) findViewById(R.id.slider);

        if (pictureId == 0) {
            TextSliderView picture1 = new TextSliderView(this);
            picture1
                    .description("Take some pictures !")
                    .image("https://tedconfblog.files.wordpress.com/2014/12/8photography_tips.png");
            sliderShow.addSlider(picture1);
            sliderShow.stopAutoCycle();
        }


        /* Initialise select categorie */
        Resources res = getResources();
        int flat_blu_app = res.getColor(R.color.pri_dark);
        final int flat_blu = res.getColor(R.color.water_point);
        final int flat_green = res.getColor(R.color.garden_point);
        final int flat_yellow = res.getColor(R.color.landscape);
        final int flat_grey = res.getColor(R.color.place);


        /* Bubble help */
        final TextView categorie_info = (TextView) findViewById(R.id.info);
     /*   Drawable[] bubble = categorie_info.getCompoundDrawables();
        if (bubble[0] != null) {
            bubble[0].setColorFilter(flat_blu_app, PorterDuff.Mode.MULTIPLY);
        }
*/

        Drawable dr = ContextCompat.getDrawable(getBaseContext(), R.drawable.ic_add_location_white_24dp);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();

        Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 100, 100, true));
        Drawable clone = d.getConstantState().newDrawable();
        Drawable clone2 = clone.getConstantState().newDrawable();
        Drawable clone3 = clone2.getConstantState().newDrawable();

        // Array of strings storing country names
        String[] countries = new String[] {
                "XXXXX",
                "YYYYYY",
                "XXXXX",
                "YYYYYY",
                "XXXXX",
                "YYYYYY",
                "XXXXX",
                "YYYYYY",
                "XXXXX",
                "YYYYYY"
        };

        // Array of integers points to images stored in /res/drawable-ldpi/
        int[] pics = new int[]{
                R.drawable.backparis,
                R.drawable.backparis,
                R.drawable.backparis,
                R.drawable.backparis,
                R.drawable.backparis,
                R.drawable.backparis,
                R.drawable.backparis,
                R.drawable.backparis,
                R.drawable.backparis,
                R.drawable.backparis

        };

        // Array of strings to store currencies
        String[] currency = new String[]{
                " bgnhASDFGHJKASDFGHJasdfghjhgfdsfghjakkskkkakdkhdjhdhdhhdhdhdhdhdhhdhhdhd ",
                " bgnhASDFGHJKASDFGHJasdfghjhgfdsfghjakkskkkakdkhdjhdhdhhdhdhdhdhdhhdhhdhd ",
                " bgnhASDFGHJKASDFGHJasdfghjhgfdsfghjakkskkkakdkhdjhdhdhhdhdhdhdhdhhdhhdhd ",
                " bgnhASDFGHJKASDFGHJasdfghjhgfdsfghjakkskkkakdkhdjhdhdhhdhdhdhdhdhhdhhdhd ",
                " bgnhASDFGHJKASDFGHJasdfghjhgfdsfghjakkskkkakdkhdjhdhdhhdhdhdhdhdhhdhhdhd ",
                " bgnhASDFGHJKASDFGHJasdfghjhgfdsfghjakkskkkakdkhdjhdhdhhdhdhdhdhdhhdhhdhd ",
                " bgnhASDFGHJKASDFGHJasdfghjhgfdsfghjakkskkkakdkhdjhdhdhhdhdhdhdhdhhdhhdhd ",
                " bgnhASDFGHJKASDFGHJasdfghjhgfdsfghjakkskkkakdkhdjhdhdhhdhdhdhdhdhhdhhdhd ",
                " bgnhASDFGHJKASDFGHJasdfghjhgfdsfghjakkskkkakdkhdjhdhdhhdhdhdhdhdhhdhhdhd ",
                " bgnhASDFGHJKASDFGHJasdfghjhgfdsfghjakkskkkakdkhdjhdhdhhdhdhdhdhdhhdhhdhd "

        };


            // Each row in the list stores country name, currency and flag
            List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

            for(int i=0;i<10;i++){
                HashMap<String, String> hm = new HashMap<String,String>();
                hm.put("txt",  countries[i]);
                hm.put("com","Comment: " + currency[i]);
                hm.put("pic", Integer.toString(pics[i]) );
                aList.add(hm);
            }

            // Keys used in Hashmap
            String[] from = { "pic","txt","com" };

            // Ids of views in listview_layout
            int[] to = { R.id.icon,R.id.person,R.id.comment};

            // Instantiating an adapter to store each items
            // R.layout.listview_layout defines the layout of each item
            SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.comment_list_view, from, to);

            // Getting a reference to listview of main.xml layout file
            ListView listView = ( ListView ) findViewById(R.id.listview);

            // Setting the adapter to the listView
            listView.setAdapter(adapter);



    }




    public void saveVueClick(MenuItem item) {
        boolean sucess = false;

        person_input      = (TextView) findViewById(R.id.person);
        comment_input = (TextView) findViewById(R.id.comment);


        String name         = person_input.getText().toString();
        String description  = comment_input.getText().toString();

        Toast.makeText(getBaseContext(), name + "\n" + description , Toast.LENGTH_SHORT).show();

        // isSave = Tool.check_content_edittext(name, description, focusView);

        /* SEND VUE TO PARSE */
        //if (isSave) signUpProcess(user, password, email, name);
    }


    @Override
    public void onBackPressed() {
        sliderShow.stopAutoCycle();
        /* Check BelleVue directory */
        //Tool.reset_bellevue_dir();
        super.onBackPressed();
    }



}

class StableArrayAdapter extends ArrayAdapter<String> {

    HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

    public StableArrayAdapter(Context context, int textViewResourceId,
                              List<String> objects) {
        super(context, textViewResourceId, objects);
        for (int i = 0; i < objects.size(); ++i) {
            mIdMap.put(objects.get(i), i);
        }
    }

    @Override
    public long getItemId(int position) {
        String item = getItem(position);
        return mIdMap.get(item);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

}


