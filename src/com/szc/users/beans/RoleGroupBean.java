package com.szc.users.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author TravisSong
 * @param :groupid 组id，为表主键
 * @param :groupname 组名
 * @param :treeidlist 组员对应的权限树列表
 * 数据库rolegroup表的原型，对应组以及组员有的权限
 */

@Entity
@Table(name = "rolegroup" , catalog = "sshProject")
public class RoleGroupBean extends GeneralFunction {


    private int groupid;
    private String groupname;
    private String treeidlist;

    @Id
    @Column(name = "groupid")
    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }

    @Column(name = "groupname")
    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    @Column(name = "treeidlist")
    public String getTreeidlist() {
        return treeidlist;
    }

    public void setTreeidlist(String treeidlist) {
        this.treeidlist = treeidlist;
    }
}
