package com.tencent.qcloud.suixinbo.views;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;

import com.tencent.qcloud.suixinbo.R;
import com.tencent.qcloud.suixinbo.avcontrollers.QavsdkControl;

/**
 * 主界面
 */
public class HomeActivity extends FragmentActivity {
    private FragmentTabHost mTabHost;
    private LayoutInflater layoutInflater;
    private final Class fragmentArray[] = {FragmentLiveList.class,FragmentPublish.class, FragmentProfile.class};
    private int mImageViewArray[] = {R.drawable.tab_live,R.drawable.icon_publish,R.drawable.tab_profile};
    private String mTextviewArray[] = {"live" ,"publish","profile"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        layoutInflater = LayoutInflater.from(this);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.contentPanel);

        int fragmentCount = fragmentArray.length;
        for (int i = 0; i < fragmentCount; i++) {
            //为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));
            //将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            mTabHost.getTabWidget().setDividerDrawable(null);

        }
        mTabHost.getTabWidget().getChildTabViewAt(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                DialogFragment newFragment = InputDialog.newInstance();
//                newFragment.show(ft, "dialog");

                startActivity(new Intent(HomeActivity.this,PublishLiveActivity.class));

            }
        });

    }


    private View getTabItemView(int index) {
        View view = layoutInflater.inflate(R.layout.tab_content, null);
        ImageView icon = (ImageView) view.findViewById(R.id.tab_icon);
        icon.setImageResource(mImageViewArray[index]);
        return view;
    }


    @Override
    protected void onDestroy() {
        QavsdkControl.getInstance().stopContext();
        super.onDestroy();
    }


}