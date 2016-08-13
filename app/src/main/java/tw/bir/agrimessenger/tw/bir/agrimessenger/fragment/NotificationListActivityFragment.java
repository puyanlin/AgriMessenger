package tw.bir.agrimessenger.tw.bir.agrimessenger.fragment;

import android.app.AlertDialog;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import tw.bir.agrimessenger.R;
import tw.bir.agrimessenger.service.AgricultureMessage;
import tw.bir.agrimessenger.service.CallbackInterface.MessageListCallback;

/**
 * A placeholder fragment containing a simple view.
 */
public class NotificationListActivityFragment extends BIRFragment {

    private ArrayList<AgricultureMessage> listMsg = new ArrayList<>();
    public NotificationListActivityFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notification_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAPIManager.queryMessages(new MessageListCallback() {
            @Override
            public void notifyList(AgricultureMessage[] list, String msg) {
                if(list!=null&&list.length>0){
                    for(int i = 0; i < list.length ; i++){
                        listMsg.add(list[i]);
                    }
                    // test entering notification detail
                    NotificationDetailFragment fragment = NotificationDetailFragment.newInstance(listMsg.get(0));
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment, fragment);
                    transaction.commit();
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle(msg).setNeutralButton("好", null).show();
                }
            }
        });
    }
}
