package com.szc.users.beans;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author TravisSong
 * 数据库TreeUrl表的原型
 */

@Entity
@Table(name = "treeurl" ,catalog = "sshProject")
public class TreeUrlBean {
    private int treeid;
    private String treename;
    private int parentid;
    private String url;
    private int isshow;

    public TreeUrlBean(){

    }

    @Id
    @Column(name = "treeid")
    public int getTreeid() {
        return treeid;
    }

    public void setTreeid(int treeid) {
        this.treeid = treeid;
    }

    @Column(name = "treename")
    public String getTreename() {
        return treename;
    }

    public void setTreename(String treename) {
        this.treename = treename;
    }

    @Column(name = "parentid")
    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "isshow")
    public int getIsshow() {
        return isshow;
    }

    public void setIsshow(int isshow) {
        this.isshow = isshow;
    }
}
