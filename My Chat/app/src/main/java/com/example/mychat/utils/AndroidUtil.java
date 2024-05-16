package com.example.mychat.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.mychat.models.User;

public class AndroidUtil {
    public static void showToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public  static void passUserModelAsIntent(Intent intent, User user){
        intent.putExtra("userName",user.getUserName());
        intent.putExtra("phone",user.getPhone());
        intent.putExtra("userId",user.getUserId());
    }

    public static User getUserModelFromIntent(Intent intent){
        User user = new User();
        user.setUserName(intent.getStringExtra("userName"));
        user.setPhone(intent.getStringExtra("phone"));
        user.setUserId(intent.getStringExtra("userId"));
        return user;
    }
}
