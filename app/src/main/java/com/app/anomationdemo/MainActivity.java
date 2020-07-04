package com.app.anomationdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 作者:胡涛
 * 日期:2020-7-4
 * 时间:10:44
 * 功能:注解的使用原理
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv)
    private TextView tv;

    @BindClick(R.id.tv)
    public void click(View view) {
        Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FindViewByReflection.bindTarget(this);
    }
}
