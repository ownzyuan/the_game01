package com.zy.mapper.lol;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.entity.lol.LolRoutes;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LolRoutesMapper extends BaseMapper<LolRoutes> {
}
