package com.example.buildstruct;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // 菜单栏
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("个人信息页");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // 获取 id
        TextView name = findViewById(R.id.name);
        TextView email = findViewById(R.id.email);
        TextView study = findViewById(R.id.study);
        TextView gender = findViewById(R.id.gender);
        TextView phone = findViewById(R.id.phone);

        // 从手机内容中提取 cookie
        SharedPreferences sp = getSharedPreferences("sp_buildstruct", MODE_PRIVATE);
        String myprofile_name = sp.getString("fullName", "");
        String myprofile_email = sp.getString("email", "");
        String myprofile_study = sp.getString("Study", "");
        String myprofile_gender = sp.getString("genderGroup", "");
        String myprofile_phone = sp.getString("phone", "");

        // 修改导航页信息
        name.setText(myprofile_name);
        email.setText(myprofile_email);
        study.setText(myprofile_study);
        gender.setText(myprofile_gender);
        phone.setText(myprofile_phone);

        Button button = findViewById(R.id.back_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}