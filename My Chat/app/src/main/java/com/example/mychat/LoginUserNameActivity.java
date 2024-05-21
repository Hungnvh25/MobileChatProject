package com.example.mychat;

import android.content.Intent;
import android.graphics.Insets;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mychat.models.User;
import com.example.mychat.utils.FirebaseUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;

public class LoginUserNameActivity extends AppCompatActivity {

    EditText userNameInput;
    Button letMeInBtn;
    ProgressBar progressBar;
    User user;

    String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_user_name);


        userNameInput = findViewById(R.id.login_userName);
        letMeInBtn = findViewById(R.id.login_letMeIn_btn);
        progressBar = findViewById(R.id.login_progress_bar);
        phoneNumber = getIntent().getExtras().getString("phone");

        getUserName();

        letMeInBtn.setOnClickListener((v -> {
            setUserName();
        }));
    }

    void setUserName(){

        String userName = userNameInput.getText().toString();

        if(userName.isEmpty()||userName.length()<3){
            userNameInput.setError("Username length should be at least 3 chars");
            return;
        }
        setInProgress(true);
        if(user!=null){
            user.setUserName(userName);
            user.setPhone(phoneNumber);
        }else {
            user = new User(phoneNumber,userName, Timestamp.now(),FirebaseUtil.currentUserId());
        }

        FirebaseUtil.currentUserDetails().set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                setInProgress(false);

                if(task.isSuccessful()){
                    Intent intent  = new Intent(LoginUserNameActivity.this,MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        });
    }

    void getUserName(){
        setInProgress(true);
        FirebaseUtil.currentUserDetails().get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                setInProgress(false);
                if(task.isSuccessful()){
                    user =  task.getResult().toObject(User.class);
                   if(user!=null){
                       userNameInput.setText(user.getUserName());
                   }
                }else {

                }

            }
        });
    }

    void setInProgress(boolean inProgress) {
        if (inProgress) {
            progressBar.setVisibility(View.VISIBLE);
            letMeInBtn.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            letMeInBtn.setVisibility(View.VISIBLE);
        }
    }
}