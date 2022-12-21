package com.example.buildstruct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.net.URI;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        // 菜单栏
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("视频页");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // 本地视频播放
        String uri = "android.resource://" + getPackageName() + "/" + R.raw.begin;
        VideoView videoView = findViewById(R.id.video);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.parse(uri));
        videoView.start();

        // 创建一个 ExpandableListAdapter 对象
        final ExpandableListAdapter adapter = new ExpandableListAdapter() {
            private String[] videoTypes = new String[]{
                    "线性表", "栈和队列", "字符串", "二叉树", "排序", "图"
            };
            private String[][] video = new String[][]{
                    {"顺序表", "单链表"},
                    {"栈", "队列"},
                    {"KMP 算法"},
                    {"二叉树"},
                    {"直接插入排序", "折半插入排序", "希尔排序", "冒泡排序", "快速排序", "直接选择排序", "堆排序", "归并排序",},
                    {"Prim 算法"},
            };

            // 获得制定组的位置、指定子列表项处的字列表项数据
            private TextView getTextView() {
                AbsListView.LayoutParams layoutParams =
                        new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 164);
                TextView textView = new TextView(VideoActivity.this);
                textView.setLayoutParams(layoutParams);
                textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
                textView.setPadding(36, 0, 0, 0);
                textView.setTextSize(30);
                return textView;
            }

            @Override
            public void registerDataSetObserver(DataSetObserver observer) {
            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver observer) {
            }

            @Override
            public int getGroupCount() {
                return videoTypes.length;
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                return video[groupPosition].length;
            }

            //获取制定组位置处的组数据
            @Override
            public Object getGroup(int groupPosition) {
                return videoTypes[groupPosition];
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return video[groupPosition][childPosition];
            }

            @Override
            public long getGroupId(int groupPosition) {
                return groupPosition;
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }

            // 该方法决定每个组选项的外观
            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                LinearLayout linearLayout = new LinearLayout(VideoActivity.this);
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                TextView textView = getTextView();
                textView.setText(getGroup(groupPosition).toString());
                textView.setTextSize(25);
                textView.setTypeface(null, Typeface.BOLD);
                linearLayout.addView(textView);
                linearLayout.setPadding(55, 0, 0, 0);
                return linearLayout;
            }

            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                TextView textView = getTextView();
                textView.setText(getChild(groupPosition, childPosition).toString());
                textView.setTextSize(20);
                return textView;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return true;
            }

            @Override
            public boolean areAllItemsEnabled() {
                return false;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public void onGroupExpanded(int groupPosition) {
            }

            @Override
            public void onGroupCollapsed(int groupPosition) {
            }

            @Override
            public long getCombinedChildId(long groupId, long childId) {
                return 0;
            }

            @Override
            public long getCombinedGroupId(long groupId) {
                return 0;
            }
        };
        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.list);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(VideoActivity.this, "正在播放：" +
                        adapter.getChild(groupPosition, childPosition), Toast.LENGTH_SHORT).show();
                String uri = "android.resource://" + getPackageName() + "/" + R.raw.demo;
                videoView.setVideoURI(Uri.parse(uri));
                videoView.start();
                return true;
            }
        });
        expandableListView.setAdapter(adapter);
    }
}