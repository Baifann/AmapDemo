package com.baifan.amapdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

public class MainActivity extends AppCompatActivity implements AMapLocationListener, OnClickListener{
    private Button mBtnLocate;
    private TextView mTvLocateDescribe;

    private AMapLocationClient mLocationClient = null;

    private AMapLocationClientOption mLocationClientOption = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initEvents();
        initMap();
    }

    /**
     * 开始定位
     */
    private void startLocate(){
        //设置是否需要详细地理位置，例如靠近哪里
        mLocationClientOption.setNeedAddress(true);
        mLocationClient.setLocationOption(mLocationClientOption);
        mLocationClient.startLocation();
    }

    /**
     * 停止定位
     */
    private void stopLocate(){
        mLocationClient.stopLocation();
    }

    private void initViews(){
        mBtnLocate = (Button) findViewById(R.id.btn_locate);
        mTvLocateDescribe = (TextView) findViewById(R.id.tv_loacte_describe);
    }

    private void initEvents(){
        mBtnLocate.setOnClickListener(this);
    }

    /**
     * 初始化地图相关
     */
    private void initMap(){
        mLocationClient = new AMapLocationClient(this);
        mLocationClientOption = new AMapLocationClientOption();
        //设置模式为低耗红能模式
        mLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        //设置定位监听
        mLocationClient.setLocationListener(this);
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if(aMapLocation != null){
            //经度
            String longitude = String.valueOf(aMapLocation.getLongitude());
            //纬度
            String latitide = String.valueOf(aMapLocation.getLatitude());
            mTvLocateDescribe.setText("经度：" + aMapLocation.getLongitude() + ",纬度：" + aMapLocation.getLatitude() + ",地址：" + aMapLocation.getAddress());
            stopLocate();
        }
    }

    @Override
    public void onClick(View v) {
        startLocate();
    }
}
