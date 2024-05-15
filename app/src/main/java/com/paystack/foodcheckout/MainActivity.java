package com.paystack.foodcheckout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.paystack.android.core.Paystack;
import com.paystack.android.ui.paymentsheet.PaymentSheet;
import com.paystack.android.ui.paymentsheet.PaymentSheetResult;

public class MainActivity extends AppCompatActivity {

    private PaymentSheet paymentSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Paystack.builder()
                .setPublicKey("pk_test_xxxx")
                .setLoggingEnabled(true)
                .build();

        paymentSheet = new PaymentSheet(this, this::paymentComplete);

        Button mCheckout = findViewById(R.id.btn_checkout);

        mCheckout.setOnClickListener(v -> {
            makePayment();
        });
    }

    private void makePayment() {
        // Pass access_code from transaction initialize call
        paymentSheet.launch("rlv8m1tqz4z5uno");
    }

    private void paymentComplete(PaymentSheetResult paymentSheetResult) {
        String message;

        if (paymentSheetResult instanceof PaymentSheetResult.Cancelled) {
            message = "Cancelled";
        } else if (paymentSheetResult instanceof PaymentSheetResult.Failed) {
            PaymentSheetResult.Failed failedResult = (PaymentSheetResult.Failed) paymentSheetResult;
            Log.e("Payment failed", failedResult.getError().getMessage() != null ?
                    failedResult.getError().getMessage() : "Failed", failedResult.getError());
            message = failedResult.getError().getMessage() != null ?
                    failedResult.getError().getMessage() : "Failed";
        } else if (paymentSheetResult instanceof PaymentSheetResult.Completed) {
            Log.d("Payment successful", ((PaymentSheetResult.Completed) paymentSheetResult).getPaymentCompletionDetails().toString());
            message = "Successful";
        } else {
            message = "You shouldn't be here";
        }

        Toast.makeText(this, "Payment " + message, Toast.LENGTH_SHORT).show();
    }
}