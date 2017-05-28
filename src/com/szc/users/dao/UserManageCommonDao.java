package com.szc.users.dao;

/**
 * 用户管理模块，共有的数据库操作方法
 */
public interface UserManageCommonDao {

    /**
     *
     * @param searchgroups :若参数为空，则默认查询所有的组
     * @return
     */
    public String SearchGroup(String searchgroups);
}
