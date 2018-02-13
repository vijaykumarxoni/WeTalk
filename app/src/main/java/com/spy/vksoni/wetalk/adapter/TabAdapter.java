package com.spy.vksoni.wetalk.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.spy.vksoni.wetalk.ConversationFragment;
import com.spy.vksoni.wetalk.LocationFragment;

/**
 * Created by Vksoni on 1/20/2018.
 */

public class TabAdapter extends FragmentStatePagerAdapter {
    String [] titles=new String[]{"Conversation","Location"};

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {


        switch (position) {

            case 0:
                ConversationFragment conversationFragment = ConversationFragment.newIntenence();
                return conversationFragment;
            case 1:
                LocationFragment locationFragment = LocationFragment.newIntenence();

                return locationFragment;
        }
        return null;

    }
        /**
         * Return the number of views available.
         */
        @Override
        public int getCount () {
            return 2;
        }

        @Override
        public CharSequence getPageTitle ( int position){
            return titles[position];
        }
    }
