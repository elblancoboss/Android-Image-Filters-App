package com.example.hussein.filtersapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

/**
 * Created by Hussein on 24/03/2017.
 */

public class EffectsList extends ListActivity{

    ArrayList<String> effects= new ArrayList<String>();
    String path;
    String effect_chosen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i=getIntent();
        path=i.getStringExtra("image thumbnail path");


        effects.add("tint");
        effects.add("violet");
        effects.add("Green effect");
        effects.add("Amaro");
        effects.add("RedEye");
        effects.add("greyscale");
        effects.add("winter");
        effects.add("Willow");
        effects.add("Warm");
        effects.add("Hudson");

        setListAdapter(new ArrayAdapter<String>(this, R.layout.effects_list,effects));

        ListView listView = getListView();
        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                effect_chosen=effects.get(position);

                applyEffects();

            }
        });
    }
    void applyEffects()
    {
        Intent i=new Intent(EffectsList.this, AddedEffects.class);
        i.putExtra("path",path);
        i.putExtra("effect",effect_chosen);
        startActivity(i);
    }
}
