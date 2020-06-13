package com.zy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zy.entity.Admin;
import com.zy.mapper.AdminMapper;
import com.zy.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 通过username查询Admin
     * @param username
     * @return
     */
    @Override
    public Admin getByUserName(String username) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username",username);
        return adminMapper.selectOne(wrapper);
    }
}
