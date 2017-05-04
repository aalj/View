package com.myview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.myview.widght.CheckView;
import com.myview.widght.Circle;

public class MainActivity extends AppCompatActivity {

    private com.myview.widght.CheckView checkview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle);
        Circle circle = (Circle) findViewById(R.id.test);
        circle.setOnClickListener(new Circle.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"kanaknkankan",Toast.LENGTH_SHORT).show();
            }
        });
//        this.checkview = (CheckView) findViewById(R.id.checkview);
    }


//    public void click(View view) {
//        int id = view.getId();
//        if(id== R.id.show){
//            checkview.check();
//        }else if(id == R.id.hide){
//            checkview.unCheck();
//        }
//    }
}
