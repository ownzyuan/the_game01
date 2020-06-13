package com.zy.mapper.lol;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.entity.lol.Lol;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LolMapper extends BaseMapper<Lol> {

    @Select("select *from lol where deleted = 1")
    List<Lol> selectDeleted();
}
