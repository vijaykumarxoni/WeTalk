package com.spy.vksoni.wetalk;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.activeandroid.query.Select;
import com.spy.vksoni.wetalk.adapter.ListViewAdapterConver;
import com.spy.vksoni.wetalk.db.ConversationModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConversationFragment extends Fragment {

    public static ConversationFragment instance=new ConversationFragment();
    List<ConversationModel> conversationList;
    ListView listView;
    ListViewAdapterConver listViewAdapterConver;



    public static ConversationFragment newIntenence(){

        return  instance;
    }
    public ConversationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_conversation, container, false);
        listView=(ListView)view.findViewById(R.id.conversationListView);
        conversationList=new Select().all().from(ConversationModel.class).execute();

        listViewAdapterConver=new ListViewAdapterConver(getContext().getApplicationContext(),conversationList);
        listView.setAdapter(listViewAdapterConver);
        listView.setFitsSystemWindows(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView <?> adapterView, View view, int i, long l) {

                String phone_no=conversationList.get(i).sender_name_number;

                Intent intent=new Intent(getContext(),MessageActivity.class);
                intent.putExtra("phone_no",phone_no );
                startActivity(intent);

            }
        });





        return  view; }

}
