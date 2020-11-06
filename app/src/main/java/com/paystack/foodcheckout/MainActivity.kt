package com.paystack.foodcheckout

import androidx.appcompat.app.AppCompatActivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val price  = 4000
        val mCheckout: Button = findViewById(R.id.btn_checkout)
        mCheckout.setOnClickListener {
            val intent = Intent(this, CheckoutActivity::class.java).apply {
                putExtra(getString(R.string.meal_name), price)
            }
            startActivity(intent)
        }
    }
}