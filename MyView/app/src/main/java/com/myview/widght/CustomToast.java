package com.myview.widght;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by liangjun on 2017/5/5.
 * 简单的toast 只能显示上面图片线面文字
 */

public class CustomToast {

   static  CustomToast customToast = new CustomToast();
    static Toast toast =null;

    /**
     *
     * @param activity
     * @param text  显示文字
     * @param duration  显示的时间
     * @param resource 加载toast的布局
     * @param id 显示文字的id
     * @return
     */
    public static CustomToast make(Activity activity,
                                   String text ,
                                   int duration,
                                   @LayoutRes int resource,
                                   @IdRes int id){
        View layout = activity.getLayoutInflater().inflate(resource,
                null);

        // set a message
        TextView toastText = (TextView) layout.findViewById(id);
        toastText.setText(text);
        toast = new Toast(activity);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(duration);
        toast.setView(layout);

        return customToast;
    }

    public static void show(){
        if (toast!=null) {
            toast.show();
        }

    }
}
