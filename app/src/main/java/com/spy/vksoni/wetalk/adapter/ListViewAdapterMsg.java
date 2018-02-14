package com.spy.vksoni.wetalk.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.spy.vksoni.wetalk.R;
import com.spy.vksoni.wetalk.db.SMSModel;

import java.util.List;

/**
 * Created by Vksoni on 1/31/2018.
 */

public class ListViewAdapterMsg extends BaseAdapter {

    public Context context;
    public List<SMSModel> smsList;



    public ListViewAdapterMsg(Context context, List <SMSModel> smsList){
        this.context = context;
        this.smsList = smsList;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {

        SMSModel item=getItem(position);
if(item.msg_type.equalsIgnoreCase("Received")) {
    return 0;}
    else{
    return 1;
}
}

    @Override
    public int getCount() {
        return smsList.size();
    }

    @Override
    public SMSModel getItem(int i) {
        return smsList.get(i);
    }


    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view=convertView;


        int type=getItemViewType(position);
        SMSModel sms = getItem(position);
        LayoutInflater inflater = LayoutInflater.from(context);


        if(view == null){

            if (getItemViewType(position)==0) {

                view = inflater.inflate(R.layout.received_row, viewGroup, false);
            } else {
                view = inflater.inflate(R.layout.send_row, viewGroup, false);
            }
        }
        if(type==0){
            TextView SenderNameRec = (TextView)view.findViewById(R.id.textViewCallerName);
            TextView msgTimeRec = (TextView)view.findViewById(R.id.textViewTime);
            TextView msgDateRec = (TextView)view.findViewById(R.id.textViewCallDate);
            TextView msgBodyRec = (TextView)view.findViewById(R.id.textViewMsgBody);
            ImageView friendImageRec=(ImageView)view.findViewById(R.id.imageViewCallPic);

            SenderNameRec.setText("" + sms.phone_no);
            msgDateRec.setText("" + sms.msg_rec_date);
            msgTimeRec.setText("" + sms.msg_rec_time);
            msgBodyRec.setText("" + sms.msg_body);
            friendImageRec.setImageResource(R.drawable.profile);

        }
else{
            TextView msgTimeSend = (TextView)view.findViewById(R.id.textViewSendTime);
            TextView msgDateSend = (TextView)view.findViewById(R.id.textViewSendDate);
            TextView msgBodySend= (TextView)view.findViewById(R.id.textViewMsgSendBody);

            msgDateSend.setText(""+sms.msg_rec_date);
            msgTimeSend.setText(""+sms.msg_rec_time);
            msgBodySend.setText(""+sms.msg_body);

        }


        return view;}



    }






