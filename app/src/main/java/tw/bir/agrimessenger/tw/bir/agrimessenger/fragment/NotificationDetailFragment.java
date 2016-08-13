package tw.bir.agrimessenger.tw.bir.agrimessenger.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tw.bir.agrimessenger.R;
import tw.bir.agrimessenger.service.AgricultureMessage;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NotificationDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NotificationDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationDetailFragment extends Fragment {
    private static final String ARG_PARAM = "param1";
    private AgricultureMessage mMessage;

    private OnFragmentInteractionListener mListener;

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
        return inflater.inflate(R.layout.fragment_notification_detail, container, false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
