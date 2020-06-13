package com.zy.service.impl;

import com.zy.entity.lol.LolForces;
import com.zy.mapper.lol.LolForcesMapper;
import com.zy.service.LolForcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LolForcesServiceImpl implements LolForcesService {

    @Autowired
    private LolForcesMapper lolForcesMapper;

    /**
     * 获取到lol_forces表中的数据总数，
     *  用于add.html页面中判断是第几个势力
     * @return
     */
    @Override
    public Integer getForcesCount() {
        return lolForcesMapper.selectCount(null);
    }

    /**
     * 添加势力
     * @param lolForces
     * @return
     */
    @Override
    public int insertForces(LolForces lolForces) {
        return lolForcesMapper.insert(lolForces);
    }

    @Override
    public LolForces getByFId(Integer fId) {
        return lolForcesMapper.selectById(fId);
    }

    @Override
    public List<LolForces> queryForces() {
        return lolForcesMapper.selectList(null);
    }

    @Override
    public int updateForces(LolForces lolForces) {
        return lolForcesMapper.updateById(lolForces);
    }

    @Override
    public int deleteForces(Integer fId) {
        return lolForcesMapper.deleteById(fId);
    }

}
