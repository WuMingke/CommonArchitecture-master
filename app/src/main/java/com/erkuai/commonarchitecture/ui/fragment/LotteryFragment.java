package com.erkuai.commonarchitecture.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erkuai.commonarchitecture.R;
import com.erkuai.commonarchitecture.base.BaseFragment;
import com.erkuai.commonarchitecture.http.contract.SimpleContract;
import com.erkuai.commonarchitecture.http.presenter.SimplePresenter;
import com.erkuai.commonarchitecture.widgets.adapters.BottomItemAdapter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Administrator on 2019/11/12 14:37.
 */

public class LotteryFragment extends BaseFragment<SimplePresenter> implements SimpleContract.SimpleView, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.btn_layout)
    RecyclerView btn_layout;

    @BindView(R.id.lottery)
    TextView lottery;

    private BottomItemAdapter bottomItemAdapter;

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
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        bottomItemAdapter.setPosition(position);
    }
}
