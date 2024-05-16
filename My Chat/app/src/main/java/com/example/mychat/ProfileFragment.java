package com.example.mychat;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mychat.models.User;
import com.example.mychat.utils.AndroidUtil;
import com.example.mychat.utils.FirebaseUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;


public class ProfileFragment extends Fragment {

    EditText userNameInput;

    ImageView profilePic;
    Button updateProfileBtn;
    TextView logoutBtn,phoneInput;

    ProgressBar progressBar;
    User user;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        profilePic = view.findViewById(R.id.profile_image_view);
        userNameInput = view.findViewById(R.id.profile_userName);
        phoneInput = view.findViewById(R.id.profile_phone);
        updateProfileBtn = view.findViewById(R.id.profile_update_btn);
        logoutBtn = view.findViewById(R.id.profile_logout);
        progressBar = view.findViewById(R.id.profile_ProgressBar);
        getUserData();

        updateProfileBtn.setOnClickListener((v->{
            updateBtnClick();
        }));

        logoutBtn.setOnClickListener((v->{
            FirebaseUtil.logout();
            Intent intent = new Intent(getContext(), SplashActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }));
        return view;
    }

    void updateBtnClick(){
        String newUserName = userNameInput.getText().toString();

        if(newUserName.isEmpty()||newUserName.length()<3){
            userNameInput.setError("Username length should be at least 3 chars");
            return;
        }
        setInProgress(true);
        user.setUserName(newUserName);
        updateToFirebase();
    }

    void updateToFirebase(){
        FirebaseUtil.currentUserDetails().set(user).addOnCompleteListener(task -> {
            setInProgress(false);
           if (task.isSuccessful()){
               AndroidUtil.showToast(getContext(),"Update successfully");
           }else {
               AndroidUtil.showToast(getContext(),"Update failed");

           }
        });
    }
    void getUserData() {
        setInProgress(true);
        FirebaseUtil.currentUserDetails().get().addOnCompleteListener(task -> {
            setInProgress(false);
            user = task.getResult().toObject(User.class);
            userNameInput.setText(user.getUserName());
            phoneInput.setText(user.getPhone());
        });
    }

    void setInProgress(boolean inProgress) {
        if (inProgress) {
            progressBar.setVisibility(View.VISIBLE);
            updateProfileBtn.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            updateProfileBtn.setVisibility(View.VISIBLE);
        }
    }
}