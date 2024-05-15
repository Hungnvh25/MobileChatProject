package com.example.mychat.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mychat.ChatActivity;
import com.example.mychat.R;
import com.example.mychat.models.ChatRoom;
import com.example.mychat.models.User;

import com.example.mychat.utils.AndroidUtil;
import com.example.mychat.utils.FirebaseUtil;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class RecentChatRecyclerAdapter extends FirestoreRecyclerAdapter<ChatRoom, RecentChatRecyclerAdapter.ChatroomModelViewHolder> {

    Context context;

    public RecentChatRecyclerAdapter(@NonNull FirestoreRecyclerOptions<ChatRoom> options, Context context) {
        super(options);
        this.context = context;
    }


    @Override
    protected void onBindViewHolder(@NonNull ChatroomModelViewHolder ChatroomModelViewHolder, int i, @NonNull ChatRoom model) {

        FirebaseUtil.getOtherUserFromChatroom(model.getUserIds())
                .get().addOnCompleteListener(task -> {
                   if(task.isSuccessful()){
                       boolean lastMessageSentByme = model.getLastMessageSenderId().equals(FirebaseUtil.currentUserId());
                       User user = task.getResult().toObject(User.class);
                       ChatroomModelViewHolder.usernameText.setText(user.getUserName());
                       if(lastMessageSentByme){
                           ChatroomModelViewHolder.lastMessageText.setText("You: "+model.getLastMessage());
                       }else {
                           ChatroomModelViewHolder.lastMessageText.setText(model.getLastMessage());
                       }
                       ChatroomModelViewHolder.lastMessageTime.setText(FirebaseUtil.timestampToString(model.getLastMessageTimestamp()));

                       ChatroomModelViewHolder.itemView.setOnClickListener(v->{
                           Intent intent = new Intent(context, ChatActivity.class);
                           AndroidUtil.passUserModelAsIntent(intent,user);
                           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                           context.startActivity(intent);
                       });
                   }
                });
    }

    @NonNull
    @Override
    public ChatroomModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recent_chat_recycler_row,parent,false);
        return new ChatroomModelViewHolder(view);
    }

    class ChatroomModelViewHolder extends RecyclerView.ViewHolder {

        TextView usernameText, lastMessageText,lastMessageTime;
        ImageView profilePic;

        public ChatroomModelViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameText = itemView.findViewById(R.id.user_name_text);
            lastMessageText = itemView.findViewById(R.id.last_message_text);
            lastMessageTime = itemView.findViewById(R.id.last_message_time_text);
            profilePic = itemView.findViewById(R.id.profile_pic_image_view);
        }
    }
}
