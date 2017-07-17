package com.szc.users.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author :travissong
 * TreeDao处理列表树的逻辑
 */

@Repository("treedao")
public interface TreeDao {

    /**
     * 查询用户拥有哪些权限
     */
    public List selectTreeList(String username);

    /**
     * 根据提供的listid，查找对应的名字
     * @param searchListId
     * @return
     */
    public List selectTreeName(String searchListId);
}
