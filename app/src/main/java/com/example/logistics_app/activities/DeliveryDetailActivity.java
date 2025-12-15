package com.example.logistics_app.activities; // CHECK PACKAGE NAME

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.logistics_app.R; // CHECK IMPORT

public class DeliveryDetailActivity extends AppCompatActivity {

    private CheckBox deliveryStatusCheckbox;
    private EditText otpInput;
    private Button verifyButton;
    private String currentOrderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_detail);

        currentOrderId = getIntent().getStringExtra("ORDER_ID");

        TextView tvHeader = findViewById(R.id.tv_order_header);
        if (currentOrderId != null) {
            tvHeader.setText("ORDER DETAILS: " + currentOrderId);
        }

        deliveryStatusCheckbox = findViewById(R.id.checkbox_parcel_sent);
        otpInput = findViewById(R.id.et_otp);
        verifyButton = findViewById(R.id.btn_verify);

        boolean isParcelSent = fetchInitialStatus(currentOrderId);
        deliveryStatusCheckbox.setChecked(isParcelSent);
        verifyButton.setEnabled(isParcelSent);

        // Logic for "Parcel Picked Up and Sent" checkbox
        deliveryStatusCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // API call: Update status to 'Picked Up'
                Toast.makeText(this, "Status: Picked Up.", Toast.LENGTH_SHORT).show();
                verifyButton.setEnabled(true);
            } else {
                verifyButton.setEnabled(false);
            }
        });

        // Logic for OTP verification and final delivery
        verifyButton.setOnClickListener(v -> {
            String enteredOtp = otpInput.getText().toString();

            if (enteredOtp.isEmpty() || enteredOtp.length() != 4) {
                otpInput.setError("Please enter the 4-digit OTP.");
                return;
            }

            // **MOCK OTP VALIDATION**: Replace with secure API call
            if (validateOtp(enteredOtp)) {
                Toast.makeText(this, "Delivery Confirmed!", Toast.LENGTH_LONG).show();

                // Lock the UI and signal delivery completion
                deliveryStatusCheckbox.setChecked(true);
                deliveryStatusCheckbox.setEnabled(false);
                verifyButton.setEnabled(false);

                // Finalize delivery on backend and return to dashboard
                finish();

            } else {
                otpInput.setError("Invalid OTP. Please check with the customer.");
            }
        });
    }

    // --- Mock Data/API Functions ---
    private boolean fetchInitialStatus(String orderId) {
        return orderId != null && orderId.equals("#12346");
    }
    private boolean validateOtp(String otp) {
        return otp.equals("1234"); // Hardcoded dummy OTP
    }
}