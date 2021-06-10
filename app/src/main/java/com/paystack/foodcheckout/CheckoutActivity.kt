package com.paystack.foodcheckout

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import co.paystack.android.Paystack.TransactionCallback
import co.paystack.android.PaystackSdk
import co.paystack.android.Transaction
import co.paystack.android.model.Card
import co.paystack.android.model.Charge
import com.google.android.material.textfield.TextInputLayout

class CheckoutActivity : AppCompatActivity() {
    private lateinit var mCardNumber: TextInputLayout
    private lateinit var mCardExpiry: TextInputLayout
    private lateinit var mCardCVV: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)


        // TODO - 3: Call the initializePaystack method
        initializeFormVariables()
    }

    // TODO - 1: Create the initializePaystack method
    // TODO - 2: Initialize the Paystack SDK in the initializePaystack method

    private fun initializeFormVariables() {
        mCardNumber = findViewById(R.id.til_card_number)
        mCardExpiry = findViewById(R.id.til_card_expiry)
        mCardCVV = findViewById(R.id.til_card_cvv)

        // this is used to add a forward slash (/) between the cards expiry month
        // and year (11/21). After the month is entered, a forward slash is added
        // before the year
        mCardExpiry.editText!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().length == 2 && !s.toString().contains("/")) {
                    s!!.append("/")
                }
            }

        })

        val button = findViewById<Button>(R.id.btn_make_payment)
        button.setOnClickListener { performCharge() }
    }

    private fun performCharge() {
        // TODO - 4: Flesh out the performCharge method
        // TODO - 5: Get the cardNumber, cardExpiry and cvv from the checkout form
        // TODO - 6: Parse the cardNumber, cardExpiry and cvv to ensure they are ready for charge
        // TODO - 7: Create a card object with the cardNumber, cardExpiry and cvv

        // TODO - 8: Create a charge object
        // TODO - 9: Charge card
    }

    // TODO - 10: Create a parseResponse method to parse a successful response
}