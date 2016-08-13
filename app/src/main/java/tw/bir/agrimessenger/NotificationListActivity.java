package tw.bir.agrimessenger;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


public class NotificationListActivity extends BIRActivity{
    public static String EXTRA_OPEN_ID = "EXTRA_OPEN_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public String getOpenId(){
        return getIntent().getStringExtra(EXTRA_OPEN_ID);
    }

}
