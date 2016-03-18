package com.bellevue.starter;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.ArrayList;

/**
 * Created by asusss on 9.03.2016.
 */
public class InfoTab extends Fragment {

    static private SliderLayout sliderShow;

    private static final String ARG_PARAM1 = "vueName";
    private static final String ARG_PARAM2 = "vueDescription";
    private static final String ARG_PARAM3 = "vueRate";

    private String vueName;
    private String vueDescription;
    private int    vueRate;
    private int    nbPicture;
    private ArrayList<String>    uriPicture;

    public static InfoTab newInstance(String vueName, String vueDescription, int vueRate) {
        InfoTab fragment = new InfoTab();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, vueName);
        args.putString(ARG_PARAM2, vueDescription);
        args.putInt(ARG_PARAM3, vueRate);
        fragment.setArguments(args);
        return fragment;
    }

    public InfoTab() {
        // Required empty public constructor
    }

    public void updateFragment(String uriPicture) {
        TextSliderView pic_test;
        pic_test = new TextSliderView(getActivity());
        pic_test
                .description("C'est bon sa")
                .image(uriPicture);
        sliderShow.addSlider(pic_test);
    }

    public void resetSlider() {
        sliderShow.removeAllSliders();
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            vueName = getArguments().getString(ARG_PARAM1);
            vueDescription = getArguments().getString(ARG_PARAM2);
            vueRate = getArguments().getInt(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        CoordinatorLayout myView = (CoordinatorLayout) inflater.inflate(R.layout.view_vue_tab1, container, false);
        TextView  tvName         = (TextView) myView.findViewById(R.id.vueName);
        TextView  tvDescription  = (TextView) myView.findViewById(R.id.vueDescription);
        RatingBar rtStar         = (RatingBar) myView.findViewById(R.id.ratingBar);
        sliderShow = (SliderLayout) myView.findViewById(R.id.slider);
        sliderShow.stopAutoCycle();

        tvName.setText(vueName);
        tvDescription.setText(vueDescription);
        // rtStar.setRating(vueRate);

        return myView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        sliderShow.removeAllSliders();
/*
        TextSliderView pic_test;
        pic_test = new TextSliderView(getActivity());
        pic_test
                .description("C'est bon sa")
                .image("http://www.google.fr");
        sliderShow.addSlider(pic_test);
        Log.d("onViewCreated", "coucou");*/
    }
}
