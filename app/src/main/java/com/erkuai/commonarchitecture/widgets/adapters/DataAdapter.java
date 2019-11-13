package com.erkuai.commonarchitecture.widgets.adapters;

import android.support.annotation.Nullable;
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

public class DataAdapter extends BaseQuickAdapter<Person, BaseViewHolder> {

    public DataAdapter(@Nullable List<Person> data) {
        super(R.layout.adapter_data, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Person item) {

        TextView name = helper.getView(R.id.name);
        TextView tel = helper.getView(R.id.tel);

        name.setText(item.getName());
        tel.setText(item.getPhone_number());

    }
}
