package com.erkuai.commonarchitecture.widgets.adapters;

import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.erkuai.commonarchitecture.R;
import com.erkuai.commonarchitecture.bean.Person;

import java.util.List;

/**
 * Created by Administrator on 2019/11/12 17:37.
 */

public class DataAdapter extends BaseQuickAdapter<Person, BaseViewHolder> implements View.OnTouchListener {

    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public DataAdapter(@Nullable List<Person> data) {
        super(R.layout.adapter_data, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Person item) {

        TextView name = helper.getView(R.id.name);
        TextView tel = helper.getView(R.id.tel);

        name.setText(item.getName());
        tel.setText(item.getPhone_number());

        helper.getView(R.id.item_layout).setOnTouchListener(this);

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        x = (int) event.getRawX();
        y = (int) event.getRawY();
        return false;
    }
}
