package com.example.buildstruct;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    private TextView backButton;
    private TextInputEditText loginPhone, loginPassword;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        backButton = findViewById(R.id.backButton);
        loginPhone = findViewById(R.id.loginPhone);
        loginPassword = findViewById(R.id.loginPassword);
        loginButton = findViewById(R.id.loginButton);

        // 如果已经登入，则自动跳转至主页
        SharedPreferences sp = getSharedPreferences("sp_buildstruct", MODE_PRIVATE);
        String sp_state = sp.getString("state", "");
        if (sp_state != "") {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }

        // 进入注册页
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        // 进入主页
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String phone = loginPhone.getText().toString().trim();
                final String password = loginPassword.getText().toString().trim();

                if (TextUtils.isEmpty(phone)) {
                    loginPhone.setError("请输入电话号码~");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    loginPassword.setError("请输入密码~");
                    return;
                } else {
                    // 从手机内容中提取 cookie
                    SharedPreferences sp = getSharedPreferences("sp_buildstruct", MODE_PRIVATE);
                    String sp_phone = sp.getString("phone", "");
                    String sp_password = sp.getString("password", "");
                    // 判定
                    if ((phone.equals(sp_phone)) && (password.equals(sp_password))) {
                        // 将登入状态设置重新设置为已登入
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("state", "yes");
                        editor.commit();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "电话号码或密码错误~", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }
}

