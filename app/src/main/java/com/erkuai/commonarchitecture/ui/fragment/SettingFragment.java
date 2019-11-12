package com.erkuai.commonarchitecture.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.erkuai.commonarchitecture.R;
import com.erkuai.commonarchitecture.base.BaseFragment;
import com.erkuai.commonarchitecture.constants.StringConstants;
import com.erkuai.commonarchitecture.http.contract.SimpleContract;
import com.erkuai.commonarchitecture.http.presenter.SimplePresenter;
import com.erkuai.commonarchitecture.utils.Utils;

import java.util.IdentityHashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2019/11/12 14:38.
 */

public class SettingFragment extends BaseFragment<SimplePresenter> implements SimpleContract.SimpleView {

    @BindView(R.id.pwd_et)
    EditText pwd_et;

    @BindView(R.id.pwd)
    LinearLayout pwd;

    @BindView(R.id.set_lottery)
    LinearLayout set_lottery;

    @BindView(R.id.lottery_et)
    EditText lottery_et;

    @BindView(R.id.lottery_layout)
    LinearLayout lottery_layout;

    @BindView(R.id.lottery_text)
    TextView lottery_text;

    @Override
    protected void initInject(Bundle bundle) {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void onCreateBefore() {

    }

    @Override
    protected void initEventAndData() {

        lottery_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                lottery_text.setText(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @OnClick({R.id.btn_sure})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sure:
                switch (pwd.getVisibility()) {
                    case View.VISIBLE:
                        if (!TextUtils.isEmpty(pwd_et.getText()) &&
                                pwd_et.getText().toString().equals("huangzhongyi")) {
                            pwd_et.setText("");
                            pwd.setVisibility(View.GONE);
                            lottery_layout.setVisibility(View.VISIBLE);
                            set_lottery.setVisibility(View.VISIBLE);

                            String data = Utils.readData(StringConstants.JIANGJIANGJIANG, StringConstants.LOTTERY);
                            if (!TextUtils.isEmpty(data)) {
                                lottery_text.setText(data);
                            }
                        } else {
                            Utils.showToast(getContext(), "请输入正确的密码");
                        }
                        break;
                    case View.GONE:

                        //存下修改
                        Utils.saveData(getContext(), lottery_et.getText().toString(),
                                StringConstants.JIANGJIANGJIANG, StringConstants.LOTTERY);

                        if (TextUtils.isEmpty(lottery_et.getText())) {
                            Utils.showToast(getContext(), "已取消内定");
                        } else {
                            Utils.showToast(getContext(), "已修改为：\n" + lottery_et.getText().toString());
                        }

                        lottery_et.setText("");
                        pwd.setVisibility(View.VISIBLE);
                        lottery_layout.setVisibility(View.GONE);
                        set_lottery.setVisibility(View.GONE);

                        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInput(lottery_et, 0);
                        break;
                }
                break;
        }

    }
}
