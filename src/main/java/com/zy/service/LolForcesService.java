package com.zy.service;

import com.zy.entity.lol.LolForces;

import java.util.List;

public interface LolForcesService {

    List<LolForces> queryForces();
    /**
     * 添加势力的2个方法接口
     */
    //获取到lol_forces表中的数据总数， 用于add.html页面中判断是第几个势力
    Integer getForcesCount();
    //添加势力
    int insertForces(LolForces lolForces);

    /**
     * 更新势力的2个方法接口
     */
    //通过fId获取势力
    LolForces getByFId(Integer fId);
    //更新势力
    int updateForces(LolForces lolForces);

    int deleteForces(Integer fId);

}
