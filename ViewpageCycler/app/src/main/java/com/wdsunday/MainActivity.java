package com.wdsunday;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wdsunday.viewpagecycler.R;
import com.wdsunday.weight.AutoScrollViewPager;
import com.wdsunday.weight.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AutoScrollViewPager viewPager;
    private DisplayImageOptions mOptionsHeader;
    private List<String> imageUrl = new ArrayList<>();
    private ImageLoader mImageLoader;
    private CirclePageIndicator circle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageLoader = ImageLoader.getInstance();
//        mOptionsHeader = ((APP) getApplication()).getmOptionsHeader();

        setData();
        viewPager = (AutoScrollViewPager) findViewById(R.id.viewpage);
        circle = (CirclePageIndicator) findViewById(R.id.circle);
        viewPager.startAutoScroll();
        viewPager.setInterval(5000);
        viewPager.setCycle(true);
        viewPager.setAdapter(new AutoViewPagerAdapter(imageUrl));
        circle.setViewPager(viewPager);


    }

    private void setData() {
        imageUrl.add("http://7xi8d6.com1.z0.glb.clouddn.com/2017-02-17-16464474_721724277990542_654863958657728512_n.jpg");
        imageUrl.add("http://7xi8d6.com1.z0.glb.clouddn.com/2017-02-16-16790004_607292222809583_5160406710837313536_n.jpg");
        imageUrl.add("http://7xi8d6.com1.z0.glb.clouddn.com/2017-02-15-16464434_414363458902323_3665858302006263808_n.jpg");
        imageUrl.add("http://7xi8d6.com1.z0.glb.clouddn.com/2017-02-17-16464474_721724277990542_654863958657728512_n.jpg");
        imageUrl.add("http://7xi8d6.com1.z0.glb.clouddn.com/2017-02-16-16790004_607292222809583_5160406710837313536_n.jpg");
        imageUrl.add("http://7xi8d6.com1.z0.glb.clouddn.com/2017-02-15-16464434_414363458902323_3665858302006263808_n.jpg");
        imageUrl.add("http://7xi8d6.com1.z0.glb.clouddn.com/2017-02-17-16464474_721724277990542_654863958657728512_n.jpg");
        imageUrl.add("http://7xi8d6.com1.z0.glb.clouddn.com/2017-02-16-16790004_607292222809583_5160406710837313536_n.jpg");
        imageUrl.add("http://7xi8d6.com1.z0.glb.clouddn.com/2017-02-15-16464434_414363458902323_3665858302006263808_n.jpg");


    }

    class AutoViewPagerAdapter extends PagerAdapter {

        private List<String> datas = new ArrayList<String>();
        private List<View> views = new ArrayList<>();

        public AutoViewPagerAdapter(List<String> imgs) {
            datas.clear();
            if (imgs != null && imgs.size() > 0) {
                datas.addAll(imgs);
//                viewPageLay.setVisibility(View.VISIBLE);
            }

            initViews();
        }

        private void initViews() {
            views.clear();
            for (final String ads : datas) {
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setAdjustViewBounds(true);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                android.view.ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                imageView.setLayoutParams(params);
                mImageLoader.displayImage(ads, imageView);
                views.add(imageView);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //该方法的作用是根据返回数据的类型，跳转到对应的页面，这里的与推送的页面跳转共用方法
                        Toast.makeText(MainActivity.this, "点击", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            if (object instanceof ViewPager) {
                ((ViewPager) object).removeView(views.get(position));
            }
        }

        @Override
        public Object instantiateItem(View arg0, final int arg1) {
            if (arg0 instanceof ViewPager) {
                ((ViewPager) arg0).removeView(views.get(arg1));
                ((ViewPager) arg0).addView(views.get(arg1));
            }
            return views.get(arg1);
        }

        @Override
        public int getCount() {
            return views == null ? 0 : views.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }
    }


}
