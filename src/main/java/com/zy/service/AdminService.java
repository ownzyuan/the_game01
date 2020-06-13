package com.zy.service;

import com.zy.entity.Admin;

public interface AdminService {

    /**
     * 通过username查询Admin
     * @param username
     * @return
     */
    Admin getByUserName(String username);

}
