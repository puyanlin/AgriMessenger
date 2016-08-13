package tw.bir.agrimessenger.tw.bir.agrimessenger.fragment;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;

import tw.bir.agrimessenger.R;
import tw.bir.agrimessenger.service.AgricultureMessage;

public class NotificationDetailFragment extends Fragment {
    private static final String ARG_PARAM = "param1";
    private AgricultureMessage mMessage;
    private RelativeLayout mShareRelativeLayout;
    private RelativeLayout mTypeView;

    public NotificationDetailFragment() {
        // Required empty public constructor
    }

    public static NotificationDetailFragment newInstance(AgricultureMessage msg) {
        NotificationDetailFragment fragment = new NotificationDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM,msg);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMessage = (AgricultureMessage)getArguments().getSerializable(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification_detail, container, false);
        TextView tvContent = (TextView) view.findViewById(R.id.tvNotificationContent);
        tvContent.setText(mMessage.getContent());
        mShareRelativeLayout = (RelativeLayout) view.findViewById(R.id.rlBtnShare);
        mTypeView = (RelativeLayout) view.findViewById(R.id.typeView);

        mShareRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "["+ mMessage.getCategory() +"] " + mMessage.getSubject() + "\n\n" +mMessage.getContent() + "\n\nby 農業報馬仔 http://agrimessenger.bir-taipei.co/app-debug.apk");
                startActivity(Intent.createChooser(sharingIntent, "分享給親友"));

            }
        });

        if (mMessage.getCategory().equalsIgnoreCase("氣象")) {
            mTypeView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.notification_item_color_2));
        } else if (mMessage.getCategory().equalsIgnoreCase("疫情"))  {
            mTypeView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.notification_item_color_1));
        } else {
            mTypeView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.notification_item_color_3));
        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
