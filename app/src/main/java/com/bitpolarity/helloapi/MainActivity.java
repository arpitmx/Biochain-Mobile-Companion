package com.bitpolarity.helloapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.bitpolarity.helloapi.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseFirestore db;
    SharedPreferences sharedPreferences;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);

        Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotateinf);
        binding.doneico.setAnimation(anim);


        Bundle i = getIntent().getExtras();
        String uid = i.getString("link", "null");

        db = FirebaseFirestore.getInstance();
        Map<String, Object> userID = new HashMap<>();
        userID.put("Token",uid);
        userID.put("Email", sharedPreferences.getString("email","armaxdev@gmail.com"));
        db.collection("uid")
                .add(userID)

                .addOnSuccessListener(documentReference -> Log.v("Status ", "Success")).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Status ", "Failure");

                    }
                });

        binding.btnscanx.setOnClickListener(view -> {
            onBackPressed();
        });
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(MainActivity.this, QRActivity.class));
        finish();
    }




}

