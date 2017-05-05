package com.myview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.myview.R;
import com.myview.widght.CustomToast;

public class CustomToastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
    }


    public void onClick(View view){

        CustomToast.make(this,"abc",Toast.LENGTH_SHORT,R.layout.custom_toast,R.id.toasttext).show();

    }
}
