package com.bellevue.starter;

/**
 * Created by asusss on 9.03.2016.
 */
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommentTab extends Fragment {

    List<File> files = new ArrayList<>();

    private ListView listView;


    private TextView person_input;
    private TextView comment_input;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_vue_tab2, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        // Array of strings storing country names
        String[] countries = new String[]{
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
        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < 10; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("txt", countries[i]);
            hm.put("com",  currency[i]);
            hm.put("pic", Integer.toString(pics[i]));
            aList.add(hm);
        }

        // Keys used in Hashmap
        String[] from = {"pic", "txt", "com"};

        // Ids of views in listview_layout
        int[] to = {R.id.icon, R.id.person, R.id.comment};

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), aList, R.layout.comment_list_view, from, to);

        // Getting a reference to listview of main.xml layout file
        ListView listView = (ListView) getView().findViewById(R.id.listview);

        // Setting the adapter to the listView
        listView.setAdapter(adapter);

    }

}