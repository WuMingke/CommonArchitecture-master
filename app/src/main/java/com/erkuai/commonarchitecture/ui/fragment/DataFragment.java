package com.erkuai.commonarchitecture.ui.fragment;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
import com.erkuai.commonarchitecture.widgets.adapters.DataAdapter;


import org.greenrobot.greendao.query.QueryBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2019/8/9.
 */

public class DataFragment extends BaseFragment<SimplePresenter> implements SimpleContract.SimpleView, View.OnClickListener {

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

    private DataAdapter dataAdapter;
    private PersonDao personDao;

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

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getContext(), "person.db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        personDao = daoSession.getPersonDao();
        List<Person> list = personDao.queryBuilder().list();


        data_recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        dataAdapter = new DataAdapter(list);
        data_recycler.setAdapter(dataAdapter);
        sure.setOnClickListener(this);
        export_data.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sure:
                String name = name_et.getText().toString();
                String tel = tel_et.getText().toString();

                //数据库存储
                personDao.insert(new Person(name, tel));

                List<Person> list = personDao.queryBuilder().list();
                dataAdapter.setNewData(list);

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
}
