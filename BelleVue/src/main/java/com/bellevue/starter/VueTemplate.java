package com.bellevue.starter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bellevue.starter.Utils.Tool;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VueTemplate extends AppCompatActivity {
    static int TAKE_PIC =1;
    Uri outPutfileUri;
    int pictureId = 0;
    List<File> files = new ArrayList<>();

    static  SliderLayout sliderShow;
    private FloatingActionButton addPhoto;
    RadioButton m_one, m_two, m_three, m_four;
    android.support.design.widget.TextInputLayout t1,t2;
    LinearLayout botLay;

    private String myLat;
    private String myLng;

    private EditText name_input;
    private EditText description_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_vue);

        myLat = String.valueOf(MapFragment.mCurrentLocation.getLatitude());
        myLng = String.valueOf(MapFragment.mCurrentLocation.getLongitude());

        Toast.makeText(getBaseContext(), "Lat : " + myLng + "\nLng : " + myLng, Toast.LENGTH_SHORT).show();

        addPhoto = (FloatingActionButton) findViewById(R.id.camera);

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

        /* Take Picture */
        takePhoto();

        /* Initialise select categorie */
        Resources res = getResources();
        int flat_blu_app    = res.getColor(R.color.pri_dark);
        final int flat_blu        = res.getColor(R.color.water_point);
        final int flat_green      = res.getColor(R.color.garden_point);
        final int flat_yellow     = res.getColor(R.color.landscape);
        final int flat_grey       = res.getColor(R.color.place);


        /* Bubble help */
        final TextView categorie_info = (TextView) findViewById(R.id.info);
        Drawable[] bubble = categorie_info.getCompoundDrawables();
        if (bubble[0] != null) {
            bubble[0].setColorFilter(flat_blu_app, PorterDuff.Mode.MULTIPLY);
        }

        /* ImageView Color */
        ImageView cat1 = (ImageView) findViewById(R.id.water_point);
        ImageView cat2 = (ImageView) findViewById(R.id.garden_point);
        ImageView cat3 = (ImageView) findViewById(R.id.landscape);
        ImageView cat4 = (ImageView) findViewById(R.id.place);

        Drawable dr = ContextCompat.getDrawable(getBaseContext(), R.drawable.ic_add_location_white_24dp);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();

        Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 100, 100, true));
        Drawable clone = d.getConstantState().newDrawable();
        Drawable clone2 = clone.getConstantState().newDrawable();
        Drawable clone3 = clone2.getConstantState().newDrawable();

        cat1.setImageDrawable(d);
        cat1.setColorFilter(flat_blu);
        cat2.setImageDrawable(clone);
        cat2.setColorFilter(flat_green);
        cat3.setImageDrawable(clone2);
        cat3.setColorFilter(flat_yellow);
        cat4.setImageDrawable(clone3);
        cat4.setColorFilter(flat_grey);

        m_one = (RadioButton) findViewById(R.id.rad1);
        m_two = (RadioButton) findViewById(R.id.rad2);
        m_three = (RadioButton) findViewById(R.id.rad3);
        m_four = (RadioButton) findViewById(R.id.rad4);

        t1 = (android.support.design.widget.TextInputLayout) findViewById(R.id.text1);
        t2 = (android.support.design.widget.TextInputLayout) findViewById(R.id.text2);

        botLay = (LinearLayout) findViewById(R.id.bottomLay);

        m_one.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                m_one.setChecked(true);
                m_two.setChecked(false);
                m_three.setChecked(false);
                m_four.setChecked(false);
                Toast.makeText(getBaseContext(), "Vous selectionnez Point d'eau",
                        Toast.LENGTH_SHORT).show();
                t1.setBackgroundColor(flat_blu);
                t2.setBackgroundColor(flat_blu);
                botLay.setBackgroundColor(flat_blu);

            }
        });

        m_two.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                m_one.setChecked(false);
                m_two.setChecked(true);
                m_three.setChecked(false);
                m_four.setChecked(false);
                Toast.makeText(getBaseContext(), "Vous selectionnez Jardin",
                        Toast.LENGTH_SHORT).show();
                t1.setBackgroundColor(flat_green);
                t2.setBackgroundColor(flat_green);
                botLay.setBackgroundColor(flat_green);
            }
        });

        m_three.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                m_one.setChecked(false);
                m_two.setChecked(false);
                m_three.setChecked(true);
                m_four.setChecked(false);
                Toast.makeText(getBaseContext(), "Vous selectionnez Panorama ",
                        Toast.LENGTH_SHORT).show();
                t1.setBackgroundColor(flat_yellow);
                t2.setBackgroundColor(flat_yellow);
                botLay.setBackgroundColor(flat_yellow);
            }
        });

        m_four.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                m_one.setChecked(false);
                m_two.setChecked(false);
                m_three.setChecked(false);
                m_four.setChecked(true);
                Toast.makeText(getBaseContext(), "Vous selectionnez Places",
                        Toast.LENGTH_SHORT).show();

                t1.setBackgroundColor(flat_grey);
                t2.setBackgroundColor(flat_grey);
                botLay.setBackgroundColor(flat_grey);
            }
        });
    }

    public void saveVueClick(MenuItem item) {
        boolean sucess = false;

        name_input        = (EditText) findViewById(R.id.vue_name);
        description_input = (EditText) findViewById(R.id.vue_description);

        if (Tool.check_content_radio_button(m_one, m_two, m_three, m_four))
            Toast.makeText(getBaseContext(), "ONE IS CHECKED" , Toast.LENGTH_SHORT).show();
        else {
            Toast.makeText(getBaseContext(), "NO_ONE IS CHECKED" , Toast.LENGTH_SHORT).show();
        }

        boolean isSave;
        View focusView = null;

        String name         = name_input.getText().toString();
        String description  = description_input.getText().toString();

        Toast.makeText(getBaseContext(), name + "\n" + description , Toast.LENGTH_SHORT).show();

        // isSave = Tool.check_content_edittext(name, description, focusView);

        /* SEND VUE TO PARSE */
        //if (isSave) signUpProcess(user, password, email, name);
    }


    private void takePhoto() {

        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File root = new File(Tool.root_path);
                boolean dir_created = false;
                if (!root.exists()) {
                    dir_created = root.mkdir();
                    if (!dir_created) {
                        Toast.makeText(getBaseContext(), "Probleme save directory", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                /* if pictureId == 0 reset root directory */
                if (pictureId == 0) Tool.reset_bellevue_dir(root);

                File photo = new File(Tool.root_path,
                        "tmp_pic" + String.valueOf(pictureId));
                outPutfileUri = Uri.fromFile(photo);
                files.add(photo);
                Log.d("var file[" + String.valueOf(pictureId) + "] : ", Uri.fromFile(files.get(pictureId)).toString());
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutfileUri);
                startActivityForResult(intent, TAKE_PIC);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == TAKE_PIC && resultCode==RESULT_OK){
            if (pictureId == 0) sliderShow.removeAllSliders();
            TextSliderView pic_test = new TextSliderView(this);
            pic_test
                    .description("C'est bon sa")
                    .image(outPutfileUri.toString());
            sliderShow.addSlider(pic_test);
            pictureId++;
        }
    }

    @Override
    public void onBackPressed() {
        sliderShow.stopAutoCycle();
        /* Check BelleVue directory */
        //Tool.reset_bellevue_dir();
        super.onBackPressed();
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rad1:
                if (checked)
                    Toast.makeText(getBaseContext(), "Vous selectionnez Point d'eau",
                            Toast.LENGTH_SHORT).show();
                    break;
            case R.id.rad2:
                if (checked)
                    Toast.makeText(getBaseContext(), "Vous selectionnez Jardin",
                            Toast.LENGTH_SHORT).show();
                    break;
            case R.id.rad3:
                if (checked)
                    Toast.makeText(getBaseContext(), "Vous selectionnez Panorama ",
                            Toast.LENGTH_SHORT).show();
                    break;
            case R.id.rad4:
                if (checked)
                    Toast.makeText(getBaseContext(), "Vous selectionnez Places",
                        Toast.LENGTH_SHORT).show();
                    break;
        }
    }
}