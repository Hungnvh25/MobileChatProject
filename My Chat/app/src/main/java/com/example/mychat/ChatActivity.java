package com.example.mychat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mychat.models.User;
import com.example.mychat.utils.AndroidUtil;

public class ChatActivity extends AppCompatActivity {

    User otherUser;
    EditText messageInput;
    ImageButton sendMessageBtn,backBtn;
    TextView otherUserName;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat);

        otherUser = AndroidUtil.getUserModelFromIntent(getIntent());

        messageInput = findViewById(R.id.chat_message_input);
        sendMessageBtn = findViewById(R.id.message_send_btn);
        backBtn = findViewById(R.id.back_btn);
        otherUserName = findViewById(R.id.other_username);
        recyclerView = findViewById(R.id.chat_RecyclerView);

        backBtn.setOnClickListener((v)->{
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        });

        otherUserName.setText(otherUser.getUserName());
    }
}