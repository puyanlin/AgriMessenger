package tw.bir.agrimessenger.tw.bir.agrimessenger.fragment;

import android.app.AlertDialog;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import tw.bir.agrimessenger.NotificationAdapter;
import tw.bir.agrimessenger.R;
import tw.bir.agrimessenger.RecyclerViewListener;
import tw.bir.agrimessenger.service.AgricultureMessage;
import tw.bir.agrimessenger.service.CallbackInterface.MessageListCallback;

/**
 * A placeholder fragment containing a simple view.
 */
public class NotificationListActivityFragment extends BIRFragment implements RecyclerViewListener {

    private String TAG = "NotificationListActivityFragment";

    private RecyclerView mRecyclerView;
    private NotificationAdapter mAdapter;
    private ArrayList<AgricultureMessage> listMsg = new ArrayList<>();
    public NotificationListActivityFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.notification_item_list);

        // Initial
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new NotificationAdapter(getContext(), null, this);
        mRecyclerView.setAdapter(mAdapter);

        return view;
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
                    mAdapter.swap(listMsg); // update message

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

    /**
     * The on-click listener for all devices in the ListViews
     */
    @Override
    public void onClick(View v, int position) {
        Log.e(TAG, "Click list index: " + Integer.toString(position));
    }
}
