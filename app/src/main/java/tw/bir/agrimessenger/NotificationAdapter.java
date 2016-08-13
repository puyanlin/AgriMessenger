package tw.bir.agrimessenger;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tw.bir.agrimessenger.service.AgricultureMessage;

/**
 * Created by po-hsiangchen on 2016/8/13.
 */
public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.SimpleViewHolder> {

    private final Context mContext;
    private List<AgricultureMessage> mData = Collections.emptyList();
    private RecyclerViewListener mItemListener;

    public NotificationAdapter(Context context, List<AgricultureMessage> data) {
        mContext = context;
        if (data != null) mData = data;
        else mData = new ArrayList<AgricultureMessage>();
    }

    public NotificationAdapter(Context context, List<AgricultureMessage> data, RecyclerViewListener itemListener) {
        mContext = context;
        mItemListener = itemListener;
        if (data != null) mData = data;
        else mData = new ArrayList<AgricultureMessage>();
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.notification_list_item, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, final int position) {
        AgricultureMessage agriMsg = mData.get(position);
        holder.typeView.setBackgroundColor(Color.BLUE);

        if (agriMsg.getCategory().equalsIgnoreCase("氣象")) {
            holder.typeView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.notification_item_color_2));
        } else {
            holder.typeView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.notification_item_color_1));
        }

        holder.tvType.setText(agriMsg.getCategory());
        holder.tvTitle.setText(agriMsg.getSubject());
        holder.tvSummary.setText(agriMsg.getContent());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemListener.onClick(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void add(AgricultureMessage s, int position) {
        position = position == -1 ? getItemCount() : position;
        mData.add(position, s);
        notifyItemInserted(position);
    }

    public void add(AgricultureMessage s) {
        mData.add(s);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        if (position < getItemCount()) {
            mData.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void removeAll() {
        mData.removeAll(mData);
        notifyDataSetChanged();
    }

    public void swap(List<AgricultureMessage> datas) {
        mData.clear();
        mData.addAll(datas);
        notifyDataSetChanged();
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public final RelativeLayout typeView;
        public final TextView tvType;
        public final TextView tvTitle;
        public final TextView tvSummary;

        public SimpleViewHolder(View view) {
            super(view);
            typeView = (RelativeLayout) view.findViewById(R.id.typeView);
            tvType = (TextView) view.findViewById(R.id.tvType);
            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            tvSummary = (TextView) view.findViewById(R.id.tvSummary);
        }
    }
}

