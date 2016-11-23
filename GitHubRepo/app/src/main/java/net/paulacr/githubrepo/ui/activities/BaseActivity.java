package net.paulacr.githubrepo.ui.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import net.paulacr.githubrepo.R;

import org.androidannotations.annotations.EActivity;
import org.greenrobot.eventbus.EventBus;

@EActivity
public class BaseActivity extends AppCompatActivity {

    private EventBus bus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bus = EventBus.getDefault();
    }

    private ProgressDialog dialog;

    public void showProgressDialog() {
        dialog = new ProgressDialog(this);
        dialog.setTitle(getString(R.string.dialog_title));
        dialog.setMessage(getString(R.string.dialog_message));
        dialog.show();
    }

    public void dismissProgressDialog() {
        if(dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        bus.register(this);
    }

    @Override
    public void onStop() {
        bus.unregister(this);
        super.onStop();
    }
}
