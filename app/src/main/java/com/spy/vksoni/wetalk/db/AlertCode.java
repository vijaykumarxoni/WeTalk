package com.spy.vksoni.wetalk.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Dell on 2018-02-13.
 */
@Table(name="AlertCode")

public class AlertCode extends Model {

    @Column(name="code",notNull = true)
    public String code;


}
