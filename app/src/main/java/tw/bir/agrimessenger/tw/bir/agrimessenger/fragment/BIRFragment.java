package tw.bir.agrimessenger.tw.bir.agrimessenger.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.mysampleapp.Application;

import tw.bir.agrimessenger.BIRActivity;
import tw.bir.agrimessenger.service.APIManager;

/**
 * Created by puyan on 8/13/16.
 */
public class BIRFragment extends Fragment {
    protected APIManager mAPIManager;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAPIManager = ((BIRActivity) getActivity()).apiManager();
    }
}
