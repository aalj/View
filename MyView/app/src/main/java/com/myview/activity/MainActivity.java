package com.myview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.myview.R;

public class MainActivity extends AppCompatActivity {

    private com.myview.widght.CheckView checkview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClick(View view) {
        int id = view.getId();
        Intent intent = null;
        switch (id) {
            case R.id.button1:
                intent = new Intent(this, CircleActivity.class);

                break;
            case R.id.button2:
                intent = new Intent(this, CustomToastActivity.class);
                break;
            case R.id.button3:
                intent = new Intent(this, JumpActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }

    }


}
