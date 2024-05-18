package com.example.mychat.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
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

    public static void setProfilePic(Context context, Uri imageUri, ImageView imageView){
        Glide.with(context).load(imageUri).apply(RequestOptions.circleCropTransform()).into(imageView);
    }
}
