package com.bitpolarity.helloapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bitpolarity.helloapi.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    ActivityLoginBinding binding;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
      sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
      editor = sharedPreferences.edit();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null){
            Intent intent = new Intent(LoginActivity.this, QRSelector.class);
            startActivity(intent);
            Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();

            finish();
        }else {
            Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.LoginBtn.setOnClickListener(view -> Login());


    }


    void Login(){

        String email = binding.Email.getText().toString();
        String password = binding.Password.getText().toString();


            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {

                            Log.d("TAG", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            editor.putString("email", user.getEmail().toString());
                            editor.putString("id", user.getUid().toString());
                            editor.commit();
                            Toast.makeText(LoginActivity.this, "Welcome",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, QRSelector.class));



                        } else {

                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    });




        }
}


