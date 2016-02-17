package com.playical.aprendelgtb;

/**
 * Created by Natalia on 12/02/2015.
 */
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

public class SpecialAdapter extends SimpleAdapter {
    private int[] colors = new int[] { 0x30FF0000, 0x30FF7F00, 0x30FFFF00, 0x3000FF00, 0x300000FF, 0x308B00FF };
    private int colorGray = 0x30979797;
    private int nivel;

    public SpecialAdapter(Context context, List<HashMap<String, String>> items, int resource, String[] from, int[] to, int nivel) {
        super(context, items, resource, from, to);
        this.nivel = nivel;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	    	
        View view = super.getView(position, convertView, parent);
        int colorPos = position % colors.length;
        if(position < nivel)
        	view.setBackgroundColor(colors[colorPos]);
        else
        	view.setBackgroundColor(colorGray);
        view.getBackground().setAlpha(255);
        return view;
    }
}