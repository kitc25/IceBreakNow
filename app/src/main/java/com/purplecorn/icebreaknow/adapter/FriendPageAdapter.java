package com.purplecorn.icebreaknow.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.purplecorn.icebreaknow.FriendPreviewFrag;

import org.json.JSONArray;

/**
 * Created by Kit on 5/15/2016.
 */
public class FriendPageAdapter extends FragmentStatePagerAdapter {

    private JSONArray friends;

    public FriendPageAdapter(FragmentManager fm, JSONArray friends) {
        super(fm);
        this.friends = friends;
    }

    @Override
    public Fragment getItem(int position) {
        FriendPreviewFrag frag = FriendPreviewFrag.newInstance(friends.optJSONObject(position).toString());

        return frag;
    }

    @Override
    public int getCount() {
        return friends.length();
    }
}
