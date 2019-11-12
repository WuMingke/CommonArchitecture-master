package com.erkuai.commonarchitecture.di.component;

import android.app.Activity;

import com.erkuai.commonarchitecture.di.FragmentScope;
import com.erkuai.commonarchitecture.di.module.FragmentModule;
import com.erkuai.commonarchitecture.ui.fragment.DataFragment;
import com.erkuai.commonarchitecture.ui.fragment.LotteryFragment;
import com.erkuai.commonarchitecture.ui.fragment.SettingFragment;

import dagger.Component;

/**
 * Created by Administrator on 2019/8/9.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(DataFragment fragment);

    void inject(LotteryFragment lotteryFragment);

    void inject(SettingFragment settingFragment);
}
