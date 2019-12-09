package com.andreveryman.figurepainter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ModeAdapter extends BaseAdapter {


    @Override
    public int getCount() {
        return PaintMode.values().length;
    }

    @Override
    public Object getItem(int position) {
        return PaintMode.values()[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = (TextView) LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_dropdown_item_1line, parent, false);
        ((TextView) (convertView)).setText(PaintMode.values()[position].name);
        return convertView;
    }

}
