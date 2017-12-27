package com.szc.users.beans;

import javax.persistence.*;

/**
 * Created by travissong on 2017/12/27.
 * 数据库表UserPower表，是用户与权限一一对应的表
 */

@Entity
@Table(name="userpower",catalog = "sshProject")
public class UserPowerBean extends GeneralFunction {
    private  int id;
    private  int userid;
    private  String powerid_list;

    public UserPowerBean(int id,int userid,String powerid_list){
        this.id=id;
        this.userid=userid;
        this.powerid_list=powerid_list;
    }

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //设置主键
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "userid")
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Column(name="powerid_list")
    public String getPowerid_list() {
        return powerid_list;
    }

    public void setPowerid_list(String powerid_list) {
        this.powerid_list = powerid_list;
    }
}
