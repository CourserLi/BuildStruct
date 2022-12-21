package com.example.buildstruct;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegistrationActivity extends AppCompatActivity {

    private CircleImageView profile_image;
    private Uri resultUri;
    private ProgressDialog loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // 回到登入页
        TextView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        // 注册，并进入主页
        profile_image = findViewById(R.id.profile_image);
        TextInputEditText registerFullName = findViewById(R.id.registerFullName);
        TextInputEditText registerStudy = findViewById(R.id.registerStudy);
        TextInputEditText registerPhone = findViewById(R.id.registerPhone);
        TextInputEditText registerEmail = findViewById(R.id.registerEmail);
        TextInputEditText registerPassword = findViewById(R.id.registerPassword);
        Spinner genderSpinner = findViewById(R.id.genderSpinner);
        Button registerButton = findViewById(R.id.registerButton);
        loader = new ProgressDialog(this);

        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = registerEmail.getText().toString().trim();
                final String password = registerPassword.getText().toString().trim();
                final String fullName = registerFullName.getText().toString().trim();
                final String Study = registerStudy.getText().toString().trim();
                final String phone = registerPhone.getText().toString().trim();
                final String genderGroup = genderSpinner.getSelectedItem().toString();

                if (TextUtils.isEmpty(fullName)) {
                    registerFullName.setError("请输入名字~");
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    registerEmail.setError("请输入邮箱~");
                    return;
                }
                if (TextUtils.isEmpty(Study)) {
                    registerStudy.setError("请写下学习寄语~");
                    return;
                }
                if (TextUtils.isEmpty(phone)) {
                    registerPhone.setError("请输入电话号码~");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    registerPassword.setError("请输入密码~");
                    return;
                }
                if (genderSpinner.getSelectedItem().toString().contains("性别")) {
                    Toast.makeText(RegistrationActivity.this, "请选择你的性别", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    loader.setMessage("正在注册中...");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();

                    // 存储 cookie
                    SharedPreferences sp = getSharedPreferences("sp_buildstruct", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("fullName", fullName);
                    editor.putString("email", email);
                    editor.putString("Study", Study);
                    editor.putString("genderGroup", genderGroup);
                    editor.putString("phone", phone);
                    editor.putString("password", password);
                    editor.putString("state", "yes");
                    editor.commit();

                    // 跳转
                    Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                    Toast.makeText(RegistrationActivity.this, "注册成功，正在跳转至主页", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                    loader.dismiss();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            resultUri = data.getData();
            profile_image.setImageURI(resultUri);
            // 存储图片
            SharedPreferences sp = getSharedPreferences("sp_buildstruct", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("ImageURI", String.valueOf(resultUri));
            editor.commit();
        }
    }
}















