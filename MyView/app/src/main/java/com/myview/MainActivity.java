package com.myview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.myview.widght.CheckView;

public class MainActivity extends AppCompatActivity {

    private com.myview.widght.CheckView checkview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pathdemo);

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
