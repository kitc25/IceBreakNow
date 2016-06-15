package com.purplecorn.icebreaknow.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import com.purplecorn.icebreaknow.BLEFriendFrag;
import com.purplecorn.icebreaknow.GPSFriendFrag;

/**
 * Created by Kit on 5/21/2016.
 */
public class FriendsTypeAdapter extends FragmentPagerAdapter {

    private static final int NUM_FRIENDS_TYPE = 2;
    private static final int BLE_FRIEND = 0;
    private static final int GPS_FREIND = 1;

    public FriendsTypeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case BLE_FRIEND:
                return BLEFriendFrag.newInstance();
            default:
                return GPSFriendFrag.newInstance();
        }
    }

    @Override
    public int getCount() {
        return NUM_FRIENDS_TYPE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case BLE_FRIEND:
                return "Around You";
            case GPS_FREIND:
                return "GPS";
        }
        return null;
    }
}
