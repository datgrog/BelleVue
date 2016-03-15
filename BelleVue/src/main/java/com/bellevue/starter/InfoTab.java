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

/**
 * Created by asusss on 9.03.2016.
 */
public class InfoTab extends Fragment {

    int pictureId = 0;
    private SliderLayout sliderShow;

    private static final String ARG_PARAM1 = "vueName";
    private static final String ARG_PARAM2 = "vueDescription";
    private static final String ARG_PARAM3 = "vueRate";
    private String vueName;
    private String vueDescription;
    private int    vueRate;

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

        tvName.setText(vueName);
        tvDescription.setText(vueDescription);
        // rtStar.setRating(vueRate);

        return myView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        if (pictureId == 0) {
            sliderShow.stopAutoCycle();
        }

    }

}
