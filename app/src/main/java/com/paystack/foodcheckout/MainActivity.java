package com.paystack.foodcheckout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  Logging the FCM Registration Token.
        logRegToken();

        int price = 5000;
        Button mCheckout = findViewById(R.id.btn_checkout);

        mCheckout.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CheckoutActivity.class);
            intent.putExtra(getString(R.string.meal_name), price);
            startActivity(intent);
        });

    }

//    fetches this installation token from Firebase
    private void logRegToken() {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast the FCM Token
                        String msg = "FCM Registration token: " + token;
                        Log.d(TAG, msg);
                        // Don't do this in prod, just for demo
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
                    }
                });
    }

}