package com.example.asus.shologutionlinegaming;

import android.app.PendingIntent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch(position){

            case 0:

                AllFriends allFriends=new AllFriends();
                return allFriends;
            case 1:
                ActiveFriends activeFriends=new ActiveFriends();
                return  activeFriends;
            case 2:
                Profile profile=new Profile();
                return profile;
             default:
                return null;

        }

    }

    @Override
    public int getCount() {
        return 3;
    }



    public CharSequence getPageTitle(int position) {

        switch(position){

            case 0:

                return "AllFriends";
            case 1:

                return  "Active";
            case 2:

                return "Profile";
            default:
                return null;

        }

    }

}
