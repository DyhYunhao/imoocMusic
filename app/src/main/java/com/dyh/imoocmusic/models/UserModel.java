package com.dyh.imoocmusic.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * describe:
 * create by daiyh on 2021-1-13
 */
public class UserModel extends RealmObject {

    @PrimaryKey
    private String phone;
    @Required
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
