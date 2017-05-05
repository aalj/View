package com.myview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.myview.R;
import com.myview.widght.Circle;

public class CircleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle);
        Circle circle = (Circle) findViewById(R.id.test);
        circle.setOnClickListener(new Circle.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CircleActivity.this,"我有点击",Toast.LENGTH_SHORT).show();
            }
        });
//        circle.setClickable(true);
        circle.isScanView(false);
        circle.setShowScaView(false);
    }
}
