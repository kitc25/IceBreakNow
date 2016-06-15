package com.purplecorn.icebreaknow.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.purplecorn.icebreaknow.R;

/**
 * Created by Kit on 5/14/2016.
 */
public class NavMenuAdapter extends ArrayAdapter<String> {

    String[] titles = {"Discover", "Favourites", "History", "Settings"};
    String[] subtitles = {"See who is around you now", "See the people you have liked", "See who you shared moments with", "Discover and app preferences"};

    public NavMenuAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null){
            v = ((LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.nav_item_layout, parent, false);
        }
        ((TextView) v.findViewById(R.id.nav_item_title)).setText(titles[position]);
        ((TextView) v.findViewById(R.id.nav_item_subtitle)).setText(subtitles[position]);
        return v;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }
}

