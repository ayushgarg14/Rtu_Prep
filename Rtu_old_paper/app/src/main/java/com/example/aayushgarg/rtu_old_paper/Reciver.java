package com.example.aayushgarg.rtu_old_paper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

/**
 * Created by aayushgarg on 10/4/18.
 */

public class Reciver extends BroadcastReceiver {

    private static SmsListener mListener;


    public void onReceive(Context context, Intent intent) {
        Bundle data  = intent.getExtras();

        Object[] pdus = (Object[]) data.get("pdus");

        for(int i=0;i<pdus.length;i++){
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);

            String sender = smsMessage.getDisplayOriginatingAddress();
            //You must check here if the sender is your provider and not another one with same text.

            String messageBody = smsMessage.getMessageBody();

            //Pass on the text to our listener.
            mListener.messageReceived(messageBody);
        }

    }

    public static void bindListener(SmsListener listener) {
        mListener = listener;
    }
}
