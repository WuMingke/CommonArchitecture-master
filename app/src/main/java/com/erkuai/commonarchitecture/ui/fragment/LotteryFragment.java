package com.erkuai.commonarchitecture.ui.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erkuai.commonarchitecture.R;
import com.erkuai.commonarchitecture.base.BaseFragment;
import com.erkuai.commonarchitecture.bean.Person;
import com.erkuai.commonarchitecture.bean.PersonDao;
import com.erkuai.commonarchitecture.constants.StringConstants;
import com.erkuai.commonarchitecture.http.contract.SimpleContract;
import com.erkuai.commonarchitecture.http.presenter.SimplePresenter;
import com.erkuai.commonarchitecture.utils.Utils;
import com.erkuai.commonarchitecture.widgets.adapters.BottomItemAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2019/11/12 14:37.
 */

public class LotteryFragment extends BaseFragment<SimplePresenter> implements SimpleContract.SimpleView, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.btn_layout)
    RecyclerView btn_layout;

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.tel)
    TextView tel;

    private BottomItemAdapter bottomItemAdapter;

    private List<Person> dataList;

    private CountDownTimer timer;

    private ArrayList<Integer> integers;

    @Override
    protected void initInject(Bundle bundle) {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_lottery;
    }

    @Override
    protected void onCreateBefore() {

    }

    @Override
    protected void initEventAndData() {

        ArrayList<String> bottom_items = new ArrayList<>();
        bottom_items.add("开始");
        bottom_items.add("停止");

        btn_layout.setLayoutManager(new GridLayoutManager(getContext(), 2));
        bottomItemAdapter = new BottomItemAdapter(bottom_items);
        bottomItemAdapter.setOnItemClickListener(this);
        btn_layout.setAdapter(bottomItemAdapter);
        bottomItemAdapter.setPosition(1);

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        bottomItemAdapter.setPosition(position);
        switch (position) {
            case 0://start

                if (dataList.size() == 0) return;

                //生成随机数
                if (integers == null) {
                    integers = new ArrayList<>();
                }
                integers.clear();

                for (int i = 0; i < dataList.size(); i++) {
                    int preNum = (int) (Math.random() * (dataList.size()));
                    if (integers.contains(preNum)) {
                        i--;
                    } else {
                        integers.add(preNum);
                    }
                }

                if (timer == null) {
                    timer = new CountDownTimer(2 * 60 * 1000, 150) {

                        int i = 0;

                        @Override
                        public void onTick(long millisUntilFinished) {
                            name.setText(dataList.get(integers.get(i)).getName());
                            tel.setText(dataList.get(integers.get(i)).getPhone_number());
                            if (i == integers.size() - 1) {
                                i = -1;
                            }
                            i++;
                        }

                        @Override
                        public void onFinish() {
                            timer.start();
                        }
                    };
                }
                timer.start();

                break;
            case 1://stop
                String phone_number_data = Utils.readData(StringConstants.JIANGJIANGJIANG, StringConstants.LOTTERY);
                if (!TextUtils.isEmpty(phone_number_data)) {
                    for (int i = 0; i < dataList.size(); i++) {
                        if (dataList.get(i).getPhone_number().equals(phone_number_data)) {
                            name.setText(dataList.get(i).getName());
                            tel.setText(dataList.get(i).getPhone_number());
                            break;
                        }
                    }
                }
                if (timer != null) {
                    timer.cancel();
                }
                break;
        }
    }

    public void setDataList(List<Person> dataList) {
        this.dataList = dataList;
        if (dataList.size() != 0) {
            name.setText(dataList.get(0).getName());
            tel.setText(dataList.get(0).getPhone_number());
            tel.setVisibility(View.VISIBLE);
        } else {
            name.setText("当前还没有人参与哦..");
            tel.setVisibility(View.GONE);
        }

    }


}
