package com.chinaso.so.basecomponent.share;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.chinaso.so.basecomponent.R;
import com.chinaso.so.basecomponent.entity.ShareEntity;

import java.util.List;

/**
 * @author yanxu
 * @date:2019/1/24
 * @description:
 */

public class ShareAdapter extends RecyclerView.Adapter<ShareAdapter.MyHolder> {

    private Context mContext;
    private List<ShareEntity> mList;
    private ShareAdapterOnClickListener mListener;

    public ShareAdapter(Context context, List list, ShareAdapterOnClickListener listener) {
        this.mContext = context;
        this.mList = list;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.base_item_share_gride, parent, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        holder.text.setText(mList.get(position).getTitle());
        Drawable drawable = mContext.getResources().getDrawable(mList.get(position).getImg());
        holder.text.setCompoundDrawablesRelativeWithIntrinsicBounds(null, drawable, null, null);
        holder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView text;

        public MyHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.tv_share);
        }
    }

    interface ShareAdapterOnClickListener {
        void onClick(int position);
    }
}
