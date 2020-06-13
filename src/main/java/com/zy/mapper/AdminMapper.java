package com.zy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdminMapper extends BaseMapper<Admin> {
}
