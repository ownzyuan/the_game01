package com.zy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zy.entity.lol.Lol;
import com.zy.entity.lol.LolForces;
import com.zy.entity.lol.LolOccupation;
import com.zy.entity.lol.LolRoutes;
import com.zy.vo.DataVo;
import com.zy.vo.LolVo;

import java.util.List;

public interface LolService {

    /**
     * 分页查询，把传输值发送给前端
     * @param pageNum
     * @param pageSize
     * @return
     */
    DataVo<LolVo> getByPage(Integer pageNum,Integer pageSize);

    /**
     * 通过页面传来的值condition，查询结果并返回
     * @param condition 可以为任意字段，任意类型
     * @return
     */
    List<LolVo> selectByCondition(Object condition);

    /**
     * 获取lol表的数据，并分页封装
     * @param pageNum
     * @param pageSize
     * @return
     */
    IPage<Lol> queryLol(Integer pageNum, Integer pageSize);

    /**
     * 将lolVo对象封装为List
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<LolVo> queryLolVo(Integer pageNum, Integer pageSize);

    /**
     * 添加英雄的5个方法接口
     */
    //获取到lol表中的数据总数， 用于add.html页面中判断是第几位英雄
    Integer getLolCount();
    //获取到lol_forces表中所有数据并封装为List集合， 用于add.html页面中遍历所有势力
    List<LolForces> queryForces();
    //获取到lol_occupation表中所有数据并封装为List集合， 用于add.html页面中遍历所有职业
    List<LolOccupation> queryOccupation();
    //获取到lol_routes表中所有数据并封装为List集合， 用于add.html页面中遍历所有分路
    List<LolRoutes> queryRoutes();
    //添加英雄
    int insertLol(LolVo lolVo);
    //获取到被删除英雄的数量
    List<Lol> selectDeleted();

    /**
     * 更新英雄的5个方法接口
     */
    //通过传来的hId来获取到lolVo对象的属性
    LolVo getLolVoByHId(Integer hId);
    //更新英雄数据
    int updateLol(LolVo lolVo);
    //在lol_forces表中查询到对应force对象，并将其列在List中的首位
    List<LolForces> forces_UpdateLol(String force);
    //在lol_occupation表中查询到对应occupation对象，并将其列在List中的首位
    List<LolOccupation> occupation_UpdateLol(String occupation);
    //在lol_routes表中查询到对应route对象，并将其列在List中的首位
    List<LolRoutes> routes_UpdateLol(String route);

    int deleteLol(Integer hId);

}
