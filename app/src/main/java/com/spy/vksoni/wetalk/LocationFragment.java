package com.spy.vksoni.wetalk;


import android.media.AudioManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.activeandroid.query.Select;
import com.spy.vksoni.wetalk.db.SMSModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class LocationFragment extends Fragment {
    public static LocationFragment instance=new LocationFragment();;

    public static LocationFragment newIntenence(){

        return  instance;
    }


    public LocationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_location, container, false);

        Button btn=(Button)view.findViewById(R.id.button);



        return  view;
    }

}
