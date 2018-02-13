package com.spy.vksoni.wetalk.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Vksoni on 2/10/2018.
 */
@Table(name="SmsCode")
public class SmsCode extends Model  {

    @Column(name="code",notNull = true)
    public String code;
}
