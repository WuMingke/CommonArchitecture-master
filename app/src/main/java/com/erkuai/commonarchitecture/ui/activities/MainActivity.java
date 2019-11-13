package com.erkuai.commonarchitecture.ui.activities;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erkuai.commonarchitecture.R;
import com.erkuai.commonarchitecture.base.BaseActivity;
import com.erkuai.commonarchitecture.http.contract.MainContract;
import com.erkuai.commonarchitecture.http.presenter.MainPresenter;
import com.erkuai.commonarchitecture.ui.fragment.DataFragment;
import com.erkuai.commonarchitecture.ui.fragment.LotteryFragment;
import com.erkuai.commonarchitecture.ui.fragment.SettingFragment;
import com.erkuai.commonarchitecture.widgets.adapters.BottomItemAdapter;
import com.erkuai.commonarchitecture.widgets.adapters.UIItemAdapter;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.MainView, BaseQuickAdapter.OnItemClickListener, ViewPager.OnPageChangeListener {


    @BindView(R.id.view_pager)
    ViewPager view_pager;

    @BindView(R.id.bottom_layout)
    RecyclerView bottom_layout;

    private BottomItemAdapter bottomItemAdapter;
    private UIItemAdapter uiItemAdapter;

    @Override
    protected void initInject(Bundle bundle) {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreateBefore() {

    }

    @Override
    protected void initEventAndData() {

        ArrayList<String> bottom_items = new ArrayList<>();
        bottom_items.add("添加数据");
        bottom_items.add("抽奖");
        bottom_items.add("设置");

        bottom_layout.setLayoutManager(new GridLayoutManager(this, 3));
        bottomItemAdapter = new BottomItemAdapter(bottom_items);
        bottomItemAdapter.setOnItemClickListener(this);
        bottom_layout.setAdapter(bottomItemAdapter);

        ArrayList<Fragment> fragments = new ArrayList<>();
        DataFragment dataFragment = new DataFragment();
        LotteryFragment lotteryFragment = new LotteryFragment();
        SettingFragment settingFragment = new SettingFragment();
        fragments.add(dataFragment);
        fragments.add(lotteryFragment);
        fragments.add(settingFragment);

        uiItemAdapter = new UIItemAdapter(fragments, getSupportFragmentManager());
        view_pager.setAdapter(uiItemAdapter);
        view_pager.setOnPageChangeListener(this);
        view_pager.setCurrentItem(0);

        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (!aBoolean) {
                    showToast("请给予读写文件夹的权限");
                }
            }
        });

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        bottomItemAdapter.setPosition(position);
        view_pager.setCurrentItem(position);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        bottomItemAdapter.setPosition(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
