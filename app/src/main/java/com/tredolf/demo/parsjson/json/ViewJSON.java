package com.tredolf.demo.parsjson.json;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONException;

/**
 * You just need to change this class corresponding to PostObject
 * Created by Farbod Sedghi on 3/3/2015.
 */
public class ViewJSON {

    Activity activity;
    TableLayout table;
    ParsJSON data;
    Integer length;
    Intent intent;


    public ViewJSON(TableLayout json, ParsJSON data, Activity activity, Intent intent) {
        this.activity = activity;
        this.table = json;
        this.data = data;
        this.length = data.getLength();
        this.intent = intent;
    }

    private void addRow(int row) throws JSONException {

        final PostObject post = data.read(row);
        int color;

        if(row%2==0){
           color = Color.LTGRAY;
        }else{
           color = Color.WHITE;
        }

        TableRow tr = new TableRow(activity);
        tr.setId(1000+row);
        tr.setBackgroundColor(color);
        tr.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));


        tr.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //
                int idx = v.getId() - 1000;
                try {
                    intent.putExtra("content", data.read(idx).getContent());
                    activity.startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        LinearLayout layout = new LinearLayout(activity);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setWeightSum(6f);
        layout.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT));
        tr.addView(layout);

        TextView date = new TextView(activity);
        date.setId(2000+row);
        date.setText(post.getDate());
        date.setTextColor(Color.BLACK);
        date.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        layout.addView(date);
        //tr.addView(date);

        TextView title = new TextView(activity);
        title.setId(3000 + row);
        title.setText(post.getTitle());
        title.setTextColor(Color.BLACK);
        title.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        layout.addView(title);
        //tr.addView(title);

        table.addView(tr, new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
    }

    /**
     * Creating a table view of JSON data summery
     * @throws JSONException
     */
    public void front() throws JSONException {
        for(int i=0; i<length; i++){
            addRow(i);
        }
    }

    public String content(int row) throws JSONException {
        return data.read(row).getContent();
    }
}
