package com.paystack.foodcheckout

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.paystack.android.core.Paystack
import com.paystack.android.ui.paymentsheet.PaymentSheet
import com.paystack.android.ui.paymentsheet.PaymentSheetResult

class MainActivity : AppCompatActivity() {
    private lateinit var paymentSheet: PaymentSheet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Paystack.builder()
            .setPublicKey("pk_test_xxxx")
            .build()
        paymentSheet = PaymentSheet(this, ::paymentComplete)

        val mCheckout: Button = findViewById(R.id.btn_checkout)
        mCheckout.setOnClickListener {
            makePayment()
        }
    }

    private fun makePayment() {
        // Pass access_code from transaction initialize call
        paymentSheet.launch("br6cgmvflhn3qtd")
    }


    private fun paymentComplete(paymentSheetResult: PaymentSheetResult) {
        val message = when (paymentSheetResult) {
            PaymentSheetResult.Cancelled -> "Cancelled"
            is PaymentSheetResult.Failed -> {
                Log.e("Something went wrong", paymentSheetResult.error.message.orEmpty(), paymentSheetResult.error)
                paymentSheetResult.error.message ?: "Failed"
            }

            is PaymentSheetResult.Completed -> {
                // Returns the transaction reference  PaymentCompletionDetails(reference={TransactionRef})
                Log.d("Payment successful", paymentSheetResult.paymentCompletionDetails.toString())
                "Successful"
            }
        }

        Toast.makeText(this, "Payment $message", Toast.LENGTH_SHORT).show()
    }
}