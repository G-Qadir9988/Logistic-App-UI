package com.example.logistics_app.activities; // CHECK PACKAGE NAME

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.logistics_app.R; // CHECK IMPORT

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(v -> attemptLogin());
    }

    private void attemptLogin() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // **MOCK AUTHENTICATION**:
        if (username.equals("rider01") && password.equals("pass123")) {
            Toast.makeText(this, "Login Successful! Loading Dashboard.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, RiderDashboardActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Invalid credentials. (Try rider01 / pass123)", Toast.LENGTH_LONG).show();
        }
    }
}