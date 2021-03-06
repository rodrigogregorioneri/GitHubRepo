package net.paulacr.githubrepo.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NetworkConnectionReceiver extends BroadcastReceiver {

    private OnReceiverNetworkStatus listener;

    public void setListener(OnReceiverNetworkStatus listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(NetworkConnectionVerifier.isConnected(context)) {
            listener.onReceive(true);
        }
    }
}
