package com.example.mychat.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mychat.models.User;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

public class AndroidUtil {
    public static void showToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public  static void passUserModelAsIntent(Intent intent, User user){
        intent.putExtra("userName",user.getUserName());
        intent.putExtra("phone",user.getPhone());
        intent.putExtra("userId",user.getUserId());
        intent.putExtra("fcmToken",user.getFcmToken());

    }

    public static User getUserModelFromIntent(Intent intent){
        User user = new User();
        user.setUserName(intent.getStringExtra("userName"));
        user.setPhone(intent.getStringExtra("phone"));
        user.setUserId(intent.getStringExtra("userId"));
        user.setFcmToken(intent.getStringExtra("fcmToken"));

        return user;
    }

    public static void setProfilePic(Context context, Uri imageUri, ImageView imageView){
        Glide.with(context).load(imageUri).apply(RequestOptions.circleCropTransform()).into(imageView);
    }

    public static String formatPhoneNumber(String phoneNumber, String countryCode) {
        try {
            PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
            Phonenumber.PhoneNumber number = phoneNumberUtil.parse(phoneNumber, countryCode);
            String formattedPhoneNumber = phoneNumberUtil.format(number, PhoneNumberUtil.PhoneNumberFormat.NATIONAL);

            formattedPhoneNumber = formattedPhoneNumber.replaceAll("\\s", "");

            return formattedPhoneNumber;
        } catch (NumberParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static boolean isNumeric(String phoneNumber) {
        String regex = "\\d+";
        return phoneNumber.matches(regex);
    }
}
