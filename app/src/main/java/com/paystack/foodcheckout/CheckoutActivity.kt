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

        initializePaystack()
        initializeFormVariables()
    }

    private fun initializePaystack() {
        PaystackSdk.initialize(applicationContext)
        PaystackSdk.setPublicKey(BuildConfig.PSTK_PUBLIC_KEY)
    }

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
        val cardNumber = mCardNumber.editText!!.text.toString()
        val cardExpiry = mCardExpiry.editText!!.text.toString()
        val cvv = mCardCVV.editText!!.text.toString()

        val cardExpiryArray = cardExpiry.split("/").toTypedArray()
        val expiryMonth = cardExpiryArray[0].toInt()
        val expiryYear = cardExpiryArray[1].toInt()
        var amount = intent.getIntExtra(getString(R.string.meal_name), 0)
        amount *= 100

        val card = Card(cardNumber, expiryMonth, expiryYear, cvv)

        val charge = Charge()
        charge.amount = amount
        charge.email = "customer@email.com"
        charge.card = card

        PaystackSdk.chargeCard(this, charge, object : TransactionCallback {
            override fun onSuccess(transaction: Transaction) {
                parseResponse(transaction.reference)
            }

            override fun beforeValidate(transaction: Transaction) {
                Log.d("Main Activity", "beforeValidate: " + transaction.reference)
            }

            override fun onError(error: Throwable, transaction: Transaction) {
                Log.d("Main Activity", "onError: " + error.localizedMessage)
                Log.d("Main Activity", "onError: $error")
            }
        })
    }

    private fun parseResponse(transactionReference: String) {
        val message = "Payment Successful - $transactionReference"
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        Log.d("Payment success: ", transactionReference)
    }
}