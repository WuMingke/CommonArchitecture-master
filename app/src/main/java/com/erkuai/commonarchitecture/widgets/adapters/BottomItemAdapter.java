package com.erkuai.commonarchitecture.widgets.adapters;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.erkuai.commonarchitecture.R;

import java.util.List;

/**
 * Created by Administrator on 2019/8/9.
 */

public class BottomItemAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private int mPosition = 1;

    public BottomItemAdapter(@Nullable List<String> data) {
        super(R.layout.adapter_book_info, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        TextView bottom_item = helper.getView(R.id.bottom_item);
        bottom_item.setText(item);

        if (helper.getPosition() == mPosition) {
            bottom_item.setBackgroundResource(R.drawable.bottom_item_selected);
            bottom_item.setTextColor(Color.WHITE);
        } else {
            bottom_item.setBackgroundResource(R.drawable.bottom_item_un_selected);
            bottom_item.setTextColor(Color.BLACK);
        }
    }

    public void setPosition(int mPosition) {
        this.mPosition = mPosition;
        notifyDataSetChanged();
    }
}
