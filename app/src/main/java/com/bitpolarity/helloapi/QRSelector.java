package com.bitpolarity.helloapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.bitpolarity.helloapi.databinding.ActivityQrselectorBinding;

public class QRSelector extends AppCompatActivity {

    ActivityQrselectorBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityQrselectorBinding.inflate(getLayoutInflater());
////        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
////                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        hideSystemBars();
        setContentView(binding.getRoot());
        Animation anim = AnimationUtils.loadAnimation(QRSelector.this, R.anim.rotate);
        Animation anim2 = AnimationUtils.loadAnimation(QRSelector.this, R.anim.slidedown);
        binding.ico.setAnimation(anim);
        binding.scnv.setAnimation(anim2);

        binding.btnscan.setOnClickListener(view -> {
            startActivity(new Intent(QRSelector.this, QRActivity.class));
        });


    }


}