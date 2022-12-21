package com.example.buildstruct;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;


public class MainActivity<User, UserAdapter> extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView nav_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("构筑天地");

        drawerLayout = findViewById(R.id.drawerLayout);
        nav_view = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        nav_view.setNavigationItemSelectedListener(this);

        // 从 header.xml 查找 id
        // CircleImageView nav_profile_image = nav_view.getHeaderView(0).findViewById(R.id.nav_user_image);
        TextView nav_fullname = nav_view.getHeaderView(0).findViewById(R.id.nav_user_fullname);
        TextView nav_email = nav_view.getHeaderView(0).findViewById(R.id.nav_user_email);
        TextView nav_study = nav_view.getHeaderView(0).findViewById(R.id.nav_user_study);

        // 从手机内容中提取 cookie
        SharedPreferences sp = getSharedPreferences("sp_buildstruct", MODE_PRIVATE);
        String profile_image = sp.getString("ImageURI", "");
        String fullname = sp.getString("fullName", "");
        String email = sp.getString("email", "");
        String study = sp.getString("Study", "");

        // 修改导航页信息
        // nav_profile_image.setImageURI(Uri.parse(profile_image));
        nav_fullname.setText(fullname);
        nav_email.setText(email);
        nav_study.setText(study);

        // 五个按钮对应五个页面
        Button btn_video = findViewById(R.id.btn_video);
        Button btn_detail = findViewById(R.id.btn_detail);
        Button btn_way = findViewById(R.id.btn_way);
        Button btn_book = findViewById(R.id.btn_book);
        Button btn_about = findViewById(R.id.btn_about);

        btn_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, VideoActivity.class);
                startActivity(intent);
            }
        });

        btn_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });

        btn_way.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WayActivity.class);
                startActivity(intent);
            }
        });

        btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BookActivity.class);
                startActivity(intent);
            }
        });

        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.about:
                Intent intent2 = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent2);
                break;
            case R.id.logout:
                // 退出前将登入状态设置为空
                SharedPreferences sp = getSharedPreferences("sp_buildstruct", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("state", "");
                editor.commit();

                Intent intent3 = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent3);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}