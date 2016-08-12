package tw.bir.agrimessenger;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mysampleapp.Application;

import tw.bir.agrimessenger.service.APIManager;

/**
 * Created by puyan on 8/13/16.
 */
public class BIRActivity extends AppCompatActivity {
    protected APIManager mAPIManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAPIManager = ((Application) getApplication()).applicationAPIManager();
    }
}
