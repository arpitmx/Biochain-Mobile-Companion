package com.bitpolarity.helloapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bitpolarity.helloapi.databinding.ActivityQractivityBinding;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.jetbrains.annotations.Nullable;

public class QRActivity extends AppCompatActivity {

    private CodeScanner mCodeScanner;
    ActivityQractivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQractivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(this, scannerView);

        binding.close.setOnClickListener(view -> onBackPressed());
        mCodeScanner.setDecodeCallback(result -> runOnUiThread(() -> {
            Toast.makeText(QRActivity.this, result.getText(), Toast.LENGTH_SHORT).show();
            String rawUrl = result.getText();
            Intent intent = new Intent(QRActivity.this, MainActivity.class);
            intent.putExtra("link", rawUrl);
            startActivity(intent);
        }));
        scannerView.setOnClickListener(view -> mCodeScanner.startPreview());
    }


    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(QRActivity.this, QRSelector.class));
        finish();
    }
}