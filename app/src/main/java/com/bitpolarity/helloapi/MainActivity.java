package com.bitpolarity.helloapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bitpolarity.helloapi.databinding.ActivityMainBinding;
import android.os.Build.*;


import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent i = getIntent();
        String midl = i.getExtras().getString("link", "null");
        String url = "https://" + midl + ".ngrok.io";


       requestConnection(url+"/request/?connect="+ Build.DEVICE);


        binding.button.setOnClickListener(view -> {
            String fUrl = url + "/users/?query=" + binding.input.getText().toString();
            qparse(fUrl);
        });
    }

        public void qparse(String raw_url){
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, raw_url, null, response ->  {
            try {
               // String Page = response.getString("Page");
                String Message = response.getString("Message");
               // String TimeStamp = response.getString("Timestamp");
                binding.output.append("Teeu > "+Message+"\n");

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> {
            Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
        });

        queue.add(request);

    }




    public void requestConnection(String raw_url){
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, raw_url, null, response ->  {
            try {
                String Message = response.getString("Message");
                binding.url.append(Message+"\n");

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> {
            Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
        });

        queue.add(request);

    }
}

