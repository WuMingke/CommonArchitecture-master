package com.erkuai.commonarchitecture.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.security.Principal;

/**
 * Created by Administrator on 2019/11/13 9:20.
 */

@Entity
public class Person {


    @Property(nameInDb = "姓名")
    private String name;

    @Id
    @Property(nameInDb = "电话号码")
    private String phone_number;

    @Generated(hash = 503321745)
    public Person(String name, String phone_number) {
        this.name = name;
        this.phone_number = phone_number;
    }

    @Generated(hash = 1024547259)
    public Person() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return this.phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
