package com.erkuai.commonarchitecture.ui.fragment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ajts.androidmads.library.SQLiteToExcel;
import com.erkuai.commonarchitecture.R;
import com.erkuai.commonarchitecture.base.BaseFragment;
import com.erkuai.commonarchitecture.bean.DaoMaster;
import com.erkuai.commonarchitecture.bean.DaoSession;
import com.erkuai.commonarchitecture.bean.Person;
import com.erkuai.commonarchitecture.bean.PersonDao;
import com.erkuai.commonarchitecture.constants.StringConstants;
import com.erkuai.commonarchitecture.http.contract.SimpleContract;
import com.erkuai.commonarchitecture.http.presenter.SimplePresenter;
import com.erkuai.commonarchitecture.utils.SoftKeyBoardListener;
import com.erkuai.commonarchitecture.widgets.adapters.DataAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2019/8/9.
 */

public class DataFragment extends BaseFragment<SimplePresenter> implements SimpleContract.SimpleView, View.OnClickListener, SoftKeyBoardListener.OnSoftKeyBoardChangeListener {

    @BindView(R.id.tel_et)
    EditText tel_et;

    @BindView(R.id.name_et)
    EditText name_et;

    @BindView(R.id.data_recycler)
    RecyclerView data_recycler;

    @BindView(R.id.sure)
    Button sure;

    @BindView(R.id.export_data)
    Button export_data;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.bottom_layout)
    LinearLayout bottom_layout;

    private DataAdapter dataAdapter;
    private PersonDao personDao;

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    protected void initInject(Bundle bundle) {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_data;
    }

    @Override
    protected void onCreateBefore() {

    }

    @Override
    protected void initEventAndData() {

        List<Person> list = personDao.queryBuilder().list();

        title.setText("已参与：" + list.size());

        data_recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        dataAdapter = new DataAdapter(list);
        data_recycler.setAdapter(dataAdapter);
        sure.setOnClickListener(this);
        export_data.setOnClickListener(this);

        SoftKeyBoardListener.setListener(getActivity(),this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sure:
                String name = name_et.getText().toString();
                String tel = tel_et.getText().toString();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(tel)) {
                    showToast("请输入正确的姓名/电话号码");
                    return;
                }

                //数据库存储
                personDao.insert(new Person(name, tel));

                List<Person> list = personDao.queryBuilder().list();
                dataAdapter.setNewData(list);
                title.setText("已参与：" + list.size());
                name_et.setText("");
                tel_et.setText("");

               /* InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(sure.getWindowToken(), 0);*/

                break;

            case R.id.export_data:
                SQLiteToExcel sqliteToExcel = new SQLiteToExcel(getContext(), "person.db", StringConstants.PERSON_EXCEL_PATH);
                sqliteToExcel.exportAllTables(StringConstants.PERSON_EXCEL, new SQLiteToExcel.ExportListener() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onCompleted(String filePath) {
                        showToast("导出成功");
                    }

                    @Override
                    public void onError(Exception e) {
                        showToast("导出失败：" + e.toString());
                    }
                });
                break;
        }
    }

    @Override
    public void keyBoardShow(int height) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) bottom_layout.getLayoutParams();
        params.bottomMargin = height;
        bottom_layout.setLayoutParams(params);
    }

    @Override
    public void keyBoardHide(int height) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) bottom_layout.getLayoutParams();
        params.bottomMargin = 0;
        bottom_layout.setLayoutParams(params);
    }
}
