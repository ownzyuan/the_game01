package com.zy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.entity.Games;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface GamesMapper extends BaseMapper<Games> {
}
