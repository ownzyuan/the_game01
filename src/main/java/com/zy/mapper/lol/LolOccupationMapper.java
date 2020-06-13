package com.zy.mapper.lol;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.entity.lol.LolOccupation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LolOccupationMapper extends BaseMapper<LolOccupation> {
}
