package com.ken.firebaselearn.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.ken.firebaselearn.R;
import com.ken.firebaselearn.application.MyApp;
import com.ken.firebaselearn.ui.adapter.ChatAdapter;
import com.ken.firebaselearn.ui.entity.MessageItem;
import com.ken.firebaselearn.ui.listener.IOnItemClickListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ken on 18/03/2016.
 */
public class ChatActivity extends BaseActivity {

    @Bind(R.id.button_send) Button buttonSend;
    @Bind(R.id.image_view_info) ImageView imageViewInfo;
    @Bind(R.id.image_view_logout) ImageView imageViewLogout;
    @Bind(R.id.edit_text_message) EditText editTextMessage;
    @Bind(R.id.recycler_view_list_message) RecyclerView recyclerViewListMessage;

    private ChatAdapter mChatAdapter;
    private Firebase mFirebaseNodeMsg;


    // [START] Activity life cycle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        mFirebaseNodeMsg = MyApp.mFirebaseRoot.child("msg");
        mFirebaseNodeMsg.addChildEventListener(mFirebaseNodeMsgChildListener);

        mChatAdapter = new ChatAdapter(getApplicationContext(), MyApp.listMessage, new IOnItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                Toast.makeText(ChatActivity.this, mChatAdapter.getItem(position).getMessageEmail(), Toast.LENGTH_SHORT).show();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewListMessage.setLayoutManager(linearLayoutManager);

        recyclerViewListMessage.setAdapter(mChatAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        MyApp.listMessage.clear();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    // [END] Activity life cycle

    // [START] firebase event

    ChildEventListener mFirebaseNodeMsgChildListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            MessageItem post = dataSnapshot.getValue(MessageItem.class);
            MyApp.listMessage.add(post);
            mChatAdapter.notifyDataSetChanged();
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(FirebaseError firebaseError) {

        }
    };

    // [END] firebase event


    @OnClick(R.id.button_send)
    void buttonSendClick() {
        MessageItem messageItem = new MessageItem(MyApp.getPrefInstance().getPrefUserEmail(), editTextMessage.getText().toString());
        mFirebaseNodeMsg.push().setValue(messageItem, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                if (firebaseError != null) {

                } else {
                    // String key = firebase.getKey();
                }
            }
        });
    }

    @OnClick(R.id.image_view_info) void imageViewInfoOnClick () {
        Intent intent = new Intent (ChatActivity.this, InfoActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.image_view_logout) void setImageViewLogoutOnClick () {
        MyApp.getPrefInstance().setPrefUserEmail("");
        Intent intent = new Intent (ChatActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}