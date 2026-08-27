package com.example.intisar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin;
    TextView tvForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ربط العناصر
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);

        // زر Login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                // 1. التحقق من اليوزرنيم (فارغ)
                if (username.isEmpty()) {
                    etUsername.setError("Username is required");
                    etUsername.requestFocus();
                    return;
                }

                // 2. التحقق من اليوزرنيم (حروف إنجليزية أو عربية + مسافات)
                if (!Pattern.matches("[a-zA-Z\\u0600-\\u06FF\\s]+", username)) {
                    etUsername.setError("Username must contain only letters and spaces");
                    etUsername.requestFocus();
                    return;
                }

                // 3. التحقق من الباسوورد (فارغ)
                if (password.isEmpty()) {
                    etPassword.setError("Password is required");
                    etPassword.requestFocus();
                    return;
                }

                // 4. التحقق من الباسوورد (الطول 8 أحرف على الأقل)
                if (password.length() < 8) {
                    etPassword.setError("Password must be at least 8 characters");
                    etPassword.requestFocus();
                    return;
                }

                // 5. منع الأرقام فقط أو الأرقام + رمز فقط (بدون حروف)
                if (Pattern.matches("^[0-9_@#$%!&*]+$", password)) {
                    etPassword.setError("Password must contain at least one letter");
                    etPassword.requestFocus();
                    return;
                }

                // 6. التحقق من وجود حرف واحد على الأقل
                if (!Pattern.matches(".*[a-zA-Z].*", password)) {
                    etPassword.setError("Password must contain at least one letter");
                    etPassword.requestFocus();
                    return;
                }

                // 7. التحقق من وجود رقم واحد على الأقل
                if (!Pattern.matches(".*[0-9].*", password)) {
                    etPassword.setError("Password must contain at least one number");
                    etPassword.requestFocus();
                    return;
                }

                // 8. التحقق من وجود رمز واحد على الأقل
                if (!Pattern.matches(".*[_@#$%!&*].*", password)) {
                    etPassword.setError("Password must contain at least one special character (_ @ # $ % ! & *)");
                    etPassword.requestFocus();
                    return;
                }

                // 9. التحقق من أن الباسوورد يحتوي على أحرف وأرقام ورموز فقط (بدون مسافات)
                if (!Pattern.matches("[a-zA-Z0-9_@#$%!&*]+", password)) {
                    etPassword.setError("Password contains invalid characters");
                    etPassword.requestFocus();
                    return;
                }

                // ✅ إذا اجتاز جميع التحققات
                Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();

                // الانتقال إلى HomeActivity
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                intent.putExtra("USERNAME", username);
                startActivity(intent);
                finish();
            }
        });

        // Forgot Password
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Please contact support to reset your password", Toast.LENGTH_LONG).show();
            }
        });
    }
}