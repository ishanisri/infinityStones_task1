package com.example.ishanisrivastava.delta_task1;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

import java.util.*;

import android.widget.ArrayAdapter;
import android.widget.ListAdapter;


import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static android.content.Context.*;


public class MainActivity extends AppCompatActivity {

    ArrayList achieved = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button choose = findViewById(R.id.choose);
        final TextView stoneText = findViewById(R.id.stonetext);
        final RelativeLayout currentLayout = findViewById(R.id.main_layout);
        final ListView stonelist = findViewById(R.id.stonelist);
        final Button reset = findViewById(R.id.resetButton);
         final ListAdapter adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, achieved);
         final SharedPreferences settings = getSharedPreferences("PREFS", 0);
        String list = settings.getString("achieved", "");
        String[] items = list.split(",");

        for (int i = 0; i < items.length; i++) {
            if (items[i].equals("") == false)
                achieved.add(items[i]);

        }
        if (achieved.size() != 0) {

            stonelist.setAdapter(adapter);
        }

         

        choose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                String[] stones = {"Power Stone", "Space Stone", "Time Stone", "Reality Stone", "Soul Stone", "Mind Stone"};
                String[] colour = {"PURPLE", "BLUE", "GREEN", "RED", "#ffa500", "YELLOW"};
                Random rand = new Random();
                int n = rand.nextInt(6);
                stoneText.setText("You have got the " + stones[n]);


                String s;
                s = colour[n];
                if (stones[n].equals("Soul Stone"))
                    currentLayout.setBackgroundColor(Color.parseColor("#ffa500"));
                else
                    currentLayout.setBackgroundColor(Color.parseColor(s));

                int c = 0;

                if (achieved.contains(stones[n]))
                    c++;

                if (c == 0)
                    achieved.add(stones[n]);


                if (achieved.size() == 6)

                {
                    stoneText.setText("You have got all the stones");
                    choose.setEnabled(false);
                    achieved.clear();
                    currentLayout.setBackgroundColor(Color.parseColor("white"));


                }


                //ListAdapter adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, achieved);

                stonelist.setAdapter(adapter);
                StringBuilder stringbuilder = new StringBuilder();

                for (int i = 0; i < achieved.size(); i++) {
                    String st = (String) achieved.get(i);
                    stringbuilder.append(st);
                    stringbuilder.append(",");
                }

                SharedPreferences.Editor editor = settings.edit();
                editor.putString("achieved", stringbuilder.toString());
                editor.putString("bg",colour[n]);
                editor.commit();


            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                achieved.clear();
                currentLayout.setBackgroundColor(Color.parseColor("white"));
                ListAdapter adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, achieved);
                choose.setEnabled(true);
                stonelist.setAdapter(adapter);
                stoneText.setText("");


            }
        });


    }
}