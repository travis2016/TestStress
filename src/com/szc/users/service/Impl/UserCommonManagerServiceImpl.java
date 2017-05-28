package com.szc.users.service.Impl;

import com.szc.users.dao.Impl.UserManageCommonDaoImpl;
import com.szc.users.service.UserCommonManagerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户管理模块，共有的操作，包括组操作等
 */
@Service("UserCommonManagerService")
public class UserCommonManagerServiceImpl implements UserCommonManagerService {

    @Resource
    private UserManageCommonDaoImpl groupCommonDao;

    public UserManageCommonDaoImpl getGroupCommonDao() {
        return groupCommonDao;
    }

    public void setGroupCommonDao(UserManageCommonDaoImpl groupCommonDao) {
        this.groupCommonDao = groupCommonDao;
    }

    @Override
    public List SearchGroup(String searchGroup) {
        return groupCommonDao.SearchGroup(searchGroup);
    }
}
