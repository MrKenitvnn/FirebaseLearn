package com.ken.firebaselearn.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ken.firebaselearn.R;
import com.ken.firebaselearn.ui.entity.MessageItem;
import com.ken.firebaselearn.ui.listener.IOnItemClickListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ken on 18/03/2016.
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private Context mContext;
    private static List<MessageItem> mListMessage = null;
    private static List<MessageItem> mListMessageTmp = null;
    private IOnItemClickListener mIOnItemClickListener;


    public ChatAdapter (Context context, List<MessageItem> listMessage, IOnItemClickListener IOnItemClickListener) {
        this.mContext = context;
        this.mListMessage = listMessage;
        this.mListMessageTmp = listMessage;
        this.mIOnItemClickListener = IOnItemClickListener;
    }


    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.item_message, parent, false);
        final ChatViewHolder viewHolder = new ChatViewHolder(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIOnItemClickListener.onItemClickListener(v, viewHolder.getPosition());
            }
        });

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        MessageItem item = mListMessage.get(position);

        holder.textViewUserName.setText(String.valueOf(item.getMessageEmail().charAt(0)));
        holder.textViewMessage.setText(item.getMessageContent());
    }


    @Override
    public int getItemCount() {
        if (!mListMessage.isEmpty())
            return mListMessage.size();
        return 0;
    }


    public MessageItem getItem (int position) {
        return mListMessage.get(position);
    }


    class ChatViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.text_view_user_name) TextView textViewUserName;
        @Bind(R.id.text_view_message) TextView textViewMessage;

        public ChatViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}