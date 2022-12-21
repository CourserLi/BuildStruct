package com.example.buildstruct;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {
    Animation big_cloud_animation, small_cloud_animation, bg_ellipse_animation, bottom_drawable_animation, bottom_drawable_shadow_drawable_animation, screen_main_logo_animation, button_explore_animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 将应用程序窗口设置为全屏模式
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        ImageView big_cloud = findViewById(R.id.imageViewBigCloud);
        ImageView small_cloud = findViewById(R.id.imageViewSmallCloud);
        ImageView bg_ellipse = findViewById(R.id.imageViewEllipse);
        ImageView bottom_drawable = findViewById(R.id.imageViewBottomDrawable);
        ImageView bottom_drawable_shadow_drawable = findViewById(R.id.imageViewBottomDrawableShadow);
        ImageView screen_main_logo = findViewById(R.id.imageViewMainCloud);
        Button button_explore = findViewById(R.id.buttonExplore);
        TextView slogan = findViewById(R.id.slogan);
        TextView slogan2 = findViewById(R.id.slogan2);

        big_cloud_animation = AnimationUtils.loadAnimation(this, R.anim.big_cloud_animation);
        small_cloud_animation = AnimationUtils.loadAnimation(this, R.anim.small_cloud_animation);
        bg_ellipse_animation = AnimationUtils.loadAnimation(this, R.anim.bg_ellipse_animation);
        bottom_drawable_animation = AnimationUtils.loadAnimation(this, R.anim.bottom_drawable_animation);
        bottom_drawable_shadow_drawable_animation = AnimationUtils.loadAnimation(this, R.anim.bottom_drawable_shadow_drawable_animation);
        screen_main_logo_animation = AnimationUtils.loadAnimation(this, R.anim.screen_main_logo_animation);
        button_explore_animation = AnimationUtils.loadAnimation(this, R.anim.button_explore_animation);

        // 延迟
        bottom_drawable_shadow_drawable_animation.setStartOffset(1000);
        bg_ellipse_animation.setStartOffset(1300);
        big_cloud_animation.setStartOffset(2300);
        small_cloud_animation.setStartOffset(2300);
        screen_main_logo_animation.setStartOffset(3300);
        button_explore_animation.setStartOffset(4300);

        big_cloud.setAnimation(big_cloud_animation);
        small_cloud.setAnimation(small_cloud_animation);
        bg_ellipse.setAnimation(bg_ellipse_animation);
        bottom_drawable.setAnimation(bottom_drawable_animation);
        bottom_drawable_shadow_drawable.setAnimation(bottom_drawable_shadow_drawable_animation);
        screen_main_logo.setAnimation(screen_main_logo_animation);
        button_explore.setAnimation(button_explore_animation);
        slogan.setAnimation(button_explore_animation);
        slogan2.setAnimation(button_explore_animation);

        // 跳转按钮
        button_explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}