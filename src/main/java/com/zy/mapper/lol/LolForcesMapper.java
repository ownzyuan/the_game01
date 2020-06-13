package com.zy.mapper.lol;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.entity.lol.LolForces;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LolForcesMapper extends BaseMapper<LolForces> {
}
