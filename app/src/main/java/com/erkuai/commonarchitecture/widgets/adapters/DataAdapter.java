package com.erkuai.commonarchitecture.widgets.adapters;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.erkuai.commonarchitecture.R;

import java.util.List;

/**
 * Created by Administrator on 2019/11/12 17:37.
 */

public class DataAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public DataAdapter(@Nullable List<String> data) {
        super(R.layout.adapter_data, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        String[] split = item.split(",");
        TextView name = helper.getView(R.id.name);
        TextView tel = helper.getView(R.id.tel);

        name.setText(split[0]);
        tel.setText(split[1]);

    }
}
