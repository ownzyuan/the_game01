package com.zy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.entity.lol.Lol;
import com.zy.entity.lol.LolForces;
import com.zy.entity.lol.LolOccupation;
import com.zy.entity.lol.LolRoutes;
import com.zy.enums.GenderEnum;
import com.zy.mapper.lol.*;
import com.zy.service.LolService;
import com.zy.utils.GenderEnumUtil;
import com.zy.utils.LolServiceUtil;
import com.zy.vo.DataVo;
import com.zy.vo.LolVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class LolServiceImpl implements LolService {

    @Autowired
    private LolMapper lolMapper;
    @Autowired
    private LolForcesMapper lolForcesMapper;
    @Autowired
    private LolOccupationMapper lolOccupationMapper;
    @Autowired
    private LolRoutesMapper lolRoutesMapper;

    /**
     * 分页查询，把传输值发送给前端
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public DataVo<LolVo> getByPage(Integer pageNum, Integer pageSize) {
        //创建DataVo的对象，用于获取传输的4个基本值信息
        DataVo dataVo = new DataVo();
        //code默认为0
        dataVo.setCode(0);
        //msg默认为""
        dataVo.setMsg("");

        //分页查询
        IPage<Lol> lolIPage = new Page<>(pageNum, pageSize);
        IPage<Lol> lolResult = lolMapper.selectPage(lolIPage, null);

        //count为单页的数量
        dataVo.setCount(lolResult.getTotal());

        //得到单页的数据
        List<Lol> lolList = lolResult.getRecords();

        //lolVoList用于存储传输的data属性
        List<LolVo> lolVoList = new ArrayList<>();

        for (Lol lol : lolList) {
            LolVo lolVo = new LolVo();
            //先将 lol中的属性全部赋给 lolVo
            BeanUtils.copyProperties(lol, lolVo);
            lolVo.setHId(lol.getHId());

            //通过势力编号，在lol_forces表中查询
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("f_id", lol.getForceId());
            //查询到对应势力后，将势力名赋给lolVo
            lolVo.setForce(lolForcesMapper.selectOne(wrapper).getFName());

            //通过职业编号，在lol_occupation表中查询
            wrapper = new QueryWrapper();
            //wrapper.eq("occupationOne",lol.getOccupationOne());
            wrapper.eq("hc_id", lol.getOccupationOne());
            //查询到对应职业后，将职业名（主）赋给lolVo
            lolVo.setOccupationOne(lolOccupationMapper.selectOne(wrapper).getNameCn());

            //通过职业编号，在lol_occupation表中查询
            wrapper = new QueryWrapper();
            //wrapper.eq("occupationTwo",lol.getOccupationTwo());
            wrapper.eq("hc_id", lol.getOccupationTwo());
            //查询到对应职业后，将职业名（次）赋给lolVo
            lolVo.setOccupationTwo(lolOccupationMapper.selectOne(wrapper).getNameCn());

            //通过分路编号，在lol_routes表中查询
            wrapper = new QueryWrapper();
            //wrapper.eq("routeOne",lol.getRouteOne());
            wrapper.eq("r_id", lol.getRouteOne());
            //查询到对应分路后，将分路推荐一赋给lolVo
            lolVo.setRouteOne(lolRoutesMapper.selectOne(wrapper).getRoute());

            //通过分路编号，在lol_routes表中查询
            wrapper = new QueryWrapper();
            //wrapper.eq("routeTwo",lol.getRouteTwo());
            wrapper.eq("r_id", lol.getRouteTwo());
            //查询到对应分路后，将分路推荐二赋给lolVo
            lolVo.setRouteTwo(lolRoutesMapper.selectOne(wrapper).getRoute());

            //全部赋值之后，传入lolVoList中，并进行下一次循环
            lolVoList.add(lolVo);
        }
        //将本页的全部信息赋值之后，传入data中
        dataVo.setData(lolVoList);

        return dataVo;
    }

    /**
     *
     * 通过页面传来的值condition，查询结果并返回
     * @param condition 可以为任意字段，任意类型
     * @return
     */
    @Override
    public List<LolVo> selectByCondition(Object condition) {
        //首先判断条件不能为空
        if (condition == null) {
            return null;
        }

        //用于传输的集合lolVoList
        List<LolVo> lolVoList = new ArrayList<>();
        //创建lolList1集合，用于存储各个条件查询到的对象
        List<Lol> lolList1 = new ArrayList<>();
        //创建4个泛型不同的 QueryWrapper，用于后续判断条件condition
        QueryWrapper<Lol> wrapperLol = new QueryWrapper<Lol>();
        QueryWrapper<LolForces> wrapperLolForces = new QueryWrapper<LolForces>();
        QueryWrapper<LolRoutes> wrapperLolRoutes = new QueryWrapper<LolRoutes>();
        QueryWrapper<LolOccupation> wrapperLolOccupation = new QueryWrapper<LolOccupation>();

        //condition可能是
        //h_id(英雄编号)-----因为网页传来的值不为Integer类型
        //gender(性别)-----因为网页上的gender是枚举类型的
        //designation(称号)、hero_name(名字)、
        //force(势力名)、occupation(职业，包括主副)、route(分路，包括推荐一二)
        //使用for循环：
        //通过for循环，并结合if-else语句，依次判断String可能的属性
        int i = 0;
        for (; i < 100; i++) {
            //第一次循环，判断是否为h_id字段
            if (i == 0){
                System.out.println(condition.toString());
                System.out.println(condition.getClass());
                wrapperLol = new QueryWrapper<Lol>();
                wrapperLol.eq("h_id",condition);
                Lol lol = lolMapper.selectOne(wrapperLol);
                if (lol!= null){
                    //如果可以查询到，则就是h_id，直接结束循环
                    lolList1.add(lol);
                    break;
                }
            }
            //第二次循环，判断是否为gender字段
            if (i == 1) {
                //如果为男，说明gender属性为0
                if (condition .equals("男") ) {
                    wrapperLol = new QueryWrapper<Lol>();
                    wrapperLol.eq("gender", 0);
                    List<Lol> lols = lolMapper.selectList(wrapperLol);
                    if (!lols.isEmpty()) {
                        for (Lol lol : lols) {
                            lolList1.add(lol);
                        }
                        break;
                    }
                } else if (condition.equals("女")){
                    wrapperLol = new QueryWrapper<Lol>();
                    wrapperLol.eq("gender", 1);
                    List<Lol> lols = lolMapper.selectList(wrapperLol);
                    if (!lols.isEmpty()) {
                        for (Lol lol : lols) {
                            lolList1.add(lol);
                        }
                        break;
                    }
                }else {
                    continue;
                }

            }
            //第三次循环，判断是否为designation字段
            if (i == 2) {
                wrapperLol = new QueryWrapper<Lol>();
                wrapperLol.like("designation", condition);
                List<Lol> result = lolMapper.selectList(wrapperLol);
                if (!result.isEmpty()) {
                    //如果可以查询到，则designation吻合
                    // 遍历所有结果到lolList1中，并跳出此次循环
                    for (Lol lol : result) {
                        lolList1.add(lol);
                    }
                    continue;
                }
            }
            //第四次循环，判断是否为hero_name字段
            if (i == 3) {
                wrapperLol = new QueryWrapper<Lol>();
                wrapperLol.like("hero_name", condition);
                List<Lol> result =lolMapper.selectList(wrapperLol);
                if (!result.isEmpty()) {
                    //如果可以查询到，则hero_name吻合
                    // 遍历所有结果到lolList1中，并跳出此次循环
                    for (Lol lol : result) {
                        lolList1.add(lol);
                    }
                    continue;
                }
            }
            //第五次循环，判断是否为forces表的f_name字段
            if (i == 4) {
                wrapperLolForces.like("f_name", condition);
                List<LolForces> result = lolForcesMapper.selectList(wrapperLolForces);
                if (!result.isEmpty()) {
                    //如果能在forces表中查询到数据，那说明此条件吻合，
                    // 遍历所有结果到lolList1中，并跳出此次循环
                    wrapperLol = new QueryWrapper<Lol>();
                    for (LolForces lolForces : result) {
                        wrapperLol.eq("force_id", lolForces.getFId());
                        List<Lol> lols = lolMapper.selectList(wrapperLol);
                        if (!lols.isEmpty()){
                            for (Lol lol : lols) {
                                lolList1.add(lol);
                            }
                        }
                    }
                    continue;
                }
            }
            //第六次循环，判断是否为occupation表中的name_cn或name_us字段
            if (i == 5) {
                //先判断是否为中文
                wrapperLolOccupation.like("name_us", condition);
                List<LolOccupation> result1 = lolOccupationMapper.selectList(wrapperLolOccupation);
                if (!result1.isEmpty()) {
                    //如果不为空，则说明是name_us
                    // 并遍历所有结果到lolList1中，并跳出此次循环
                    wrapperLol = new QueryWrapper();
                    for (LolOccupation lolOccupation : result1) {
                        wrapperLol
                                .eq("occupation_one", lolOccupation.getHcId())
                                .or()
                                .eq("occupation_two", lolOccupation.getHcId());
                        List<Lol> lols = lolMapper.selectList(wrapperLol);
                        if (!lols.isEmpty()) {
                            for (Lol lol : lols) {
                                lolList1.add(lol);
                            }
                        }
                    }
                    continue;
                } else {
                    wrapperLolOccupation = new QueryWrapper<LolOccupation>();
                    wrapperLolOccupation.like("name_cn", condition);
                    List<LolOccupation> result2 = lolOccupationMapper.selectList(wrapperLolOccupation);
                    if (!result2.isEmpty()) {
                        //如果不为空，则说明是name_cn
                        // 并遍历所有结果到lolList1中，并跳出此次循环
                        wrapperLol = new QueryWrapper();
                        for (LolOccupation lolOccupation : result2) {
                            wrapperLol
                                    .eq("occupation_one", lolOccupation.getHcId())
                                    .or()
                                    .eq("occupation_two", lolOccupation.getHcId());
                            List<Lol> lols = lolMapper.selectList(wrapperLol);
                            if (!lols.isEmpty()) {
                                for (Lol lol : lols) {
                                    lolList1.add(lol);
                                }
                            }
                        }
                        continue;
                    }
                }
            }
            //第七次循环，判断是否为routes表中的route字段
            if (i == 6) {
                wrapperLolRoutes.like("route", condition);
                LolRoutes result = lolRoutesMapper.selectOne(wrapperLolRoutes);
                if (result != null) {
                    //如果可以查询到，则route吻合
                    // 遍历所有结果到lolList1中，并跳出此次循环
                    wrapperLol = new QueryWrapper();
                    wrapperLol
                            .eq("route_one", result.getRId())
                            .or()
                            .eq("route_two", result.getRId());
                    List<Lol> lols = lolMapper.selectList(wrapperLol);
                    for (Lol lol : lols) {
                        lolList1.add(lol);
                    }
                    continue;
                }
            } else if (i > 7){
                //第八次直接跳出循环
                break;
            }
        }
        //i > 9，说明没有查询到，直接返回lolVoList
        if (i >= 9){
            return lolVoList;
        }

        //将lolList1转换成set集合
        //利用set集合不重复的特性，把重复的属性去掉
        Set<Lol> set = new HashSet<>(lolList1);
        //再次转为List集合
        List<Lol> lolList = new ArrayList<>(set);

        for (Lol lol : lolList) {
            LolVo lolVo = new LolVo();
            BeanUtils.copyProperties(lol, lolVo);
            lolVo.setHId(lol.getHId());

            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("f_id", lol.getForceId());
            lolVo.setForce(lolForcesMapper.selectOne(wrapper).getFName());

            wrapper = new QueryWrapper();
            wrapper.eq("hc_id", lol.getOccupationOne());
            lolVo.setOccupationOne(lolOccupationMapper.selectOne(wrapper).getNameCn());

            wrapper = new QueryWrapper();
            wrapper.eq("hc_id", lol.getOccupationTwo());
            lolVo.setOccupationTwo(lolOccupationMapper.selectOne(wrapper).getNameCn());

            wrapper = new QueryWrapper();
            wrapper.eq("r_id", lol.getRouteOne());
            lolVo.setRouteOne(lolRoutesMapper.selectOne(wrapper).getRoute());

            wrapper = new QueryWrapper();
            wrapper.eq("r_id", lol.getRouteTwo());
            lolVo.setRouteTwo(lolRoutesMapper.selectOne(wrapper).getRoute());

            lolVoList.add(lolVo);
        }

        return lolVoList;

    }

    /**
     * 获取lol表的数据，并分页封装
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public IPage<Lol> queryLol(Integer pageNum, Integer pageSize) {
        IPage<Lol> page = new Page<>(pageNum,pageSize);
        IPage<Lol> lolIPage = lolMapper.selectPage(page, null);
        return lolIPage;
    }

    /**
     * 将lolVo对象封装为List
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public List<LolVo> queryLolVo(Integer pageNum, Integer pageSize) {
        IPage<Lol> page1 = new Page<>(pageNum,pageSize);
        IPage<Lol> lolIPage = lolMapper.selectPage(page1, null);
        List<Lol> lolList = lolIPage.getRecords();
        List<LolVo> lolVoList = new ArrayList<>();
        for (Lol lol : lolList) {
            LolVo lolVo = new LolVo();
            BeanUtils.copyProperties(lol, lolVo);
            lolVo.setHId(lol.getHId());
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("f_id", lol.getForceId());
            lolVo.setForce(lolForcesMapper.selectOne(wrapper).getFName());

            wrapper = new QueryWrapper();
            wrapper.eq("hc_id", lol.getOccupationOne());
            lolVo.setOccupationOne(lolOccupationMapper.selectOne(wrapper).getNameCn());

            wrapper = new QueryWrapper();
            wrapper.eq("hc_id", lol.getOccupationTwo());
            lolVo.setOccupationTwo(lolOccupationMapper.selectOne(wrapper).getNameCn());

            wrapper = new QueryWrapper();
            wrapper.eq("r_id", lol.getRouteOne());
            lolVo.setRouteOne(lolRoutesMapper.selectOne(wrapper).getRoute());

            wrapper = new QueryWrapper();
            wrapper.eq("r_id", lol.getRouteTwo());
            lolVo.setRouteTwo(lolRoutesMapper.selectOne(wrapper).getRoute());

            lolVoList.add(lolVo);
        }
        return lolVoList;
    }

    /**
     *
     * @param lolVo
     * @return
     */
    @Override
    public int insertLol(LolVo lolVo) {
        Lol lol = new Lol();
        QueryWrapper wrapper = new QueryWrapper();

        lol.setHId(lolVo.getHId());

        String designation = lolVo.getDesignation();
        if ( designation != null){
            //如果英雄称号不为空，判断是否存在
            wrapper.eq("designation",designation);
            if (lolMapper.selectOne(wrapper) != null){
                //数据库已存在，直接返回0
                return 0;
            }else {
                lol.setDesignation(designation);
            }
        }

        String heroName = lolVo.getHeroName();
        if (heroName != null){
            //如果英雄名不为空，判断是否存在
            wrapper = new QueryWrapper();
            wrapper.eq("hero_name",heroName);
            if (lolMapper.selectOne(wrapper) != null){
                //数据库已存在，直接返回0
                return 0;
            }else {
                lol.setHeroName(heroName);
            }
            lol.setHeroName(heroName);
        }

        if (lolVo.getGender() != null){
            lol.setGender(lolVo.getGender());
        }
        if (lolVo.getForce() != null){
            wrapper = new QueryWrapper();
            wrapper.eq("f_name",lolVo.getForce());
            lol.setForceId(lolForcesMapper.selectOne(wrapper).getFId());
        }
        if (lolVo.getOccupationOne() != null){
            wrapper = new QueryWrapper();
            wrapper.eq("name_cn",lolVo.getOccupationOne());
            LolOccupation result = lolOccupationMapper.selectOne(wrapper);
            if (result != null){
                lol.setOccupationOne(result.getHcId());
            }else {
                wrapper = new QueryWrapper();
                wrapper.eq("name_us",lolVo.getOccupationOne());
                result = lolOccupationMapper.selectOne(wrapper);
                lol.setOccupationOne(result.getHcId());
            }
        }
        if (lolVo.getOccupationTwo() != null){
            wrapper = new QueryWrapper();
            wrapper.eq("name_cn",lolVo.getOccupationTwo());
            LolOccupation result = lolOccupationMapper.selectOne(wrapper);
            if (result != null){
                lol.setOccupationTwo(result.getHcId());
            }else {
                wrapper = new QueryWrapper();
                wrapper.eq("name_us",lolVo.getOccupationTwo());
                result = lolOccupationMapper.selectOne(wrapper);
                lol.setOccupationTwo(result.getHcId());
            }
        }
        if (lolVo.getRouteOne() != null){
            wrapper = new QueryWrapper();
            wrapper.eq("route",lolVo.getRouteOne());
            lol.setRouteOne(lolRoutesMapper.selectOne(wrapper).getRId());
        }
        if (lolVo.getRouteTwo() != null){
            wrapper = new QueryWrapper();
            wrapper.eq("route",lolVo.getRouteTwo());
            lol.setRouteTwo(lolRoutesMapper.selectOne(wrapper).getRId());
        }
        return lolMapper.insert(lol);
    }

    @Override
    public List<Lol> selectDeleted() {
        return lolMapper.selectDeleted();
    }

    /**
     * 获取到lol表中的数据总数，
     *  用于add.html页面中判断是第几位英雄
     * @return
     */
    @Override
    public Integer getLolCount() {
        return lolMapper.selectCount(null);
    }

    /**
     * 获取到lol_forces表中所有数据并封装为List集合，
     *  用于add.html页面中遍历所有势力
     * @return
     */
    @Override
    public List<LolForces> queryForces() {
        return lolForcesMapper.selectList(null);
    }

    /**
     * 获取到lol_occupation表中所有数据并封装为List集合，
     *  用于add.html页面中遍历所有职业
     * @return
     */
    @Override
    public List<LolOccupation> queryOccupation() {
        return lolOccupationMapper.selectList(null);
    }

    /**
     * 获取到lol_routes表中所有数据并封装为List集合，
     *  用于add.html页面中遍历所有分路
     * @return
     */
    @Override
    public List<LolRoutes> queryRoutes() {
        return lolRoutesMapper.selectList(null);
    }

    /**
     * 通过传来的hId来获取到lolVo对象的属性
     * @param hId
     * @return
     */
    @Override
    public LolVo getLolVoByHId(Integer hId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("h_id",hId);
        Lol lol = lolMapper.selectOne(wrapper);
        LolVo lolVo = new LolVo();
        wrapper = new QueryWrapper();
        wrapper.eq("f_id", lol.getForceId());
        LolForces lolForces = lolForcesMapper.selectOne(wrapper);

        wrapper = new QueryWrapper();
        wrapper.eq("hc_id", lol.getOccupationOne());
        LolOccupation occupationOne = lolOccupationMapper.selectOne(wrapper);

        wrapper = new QueryWrapper();
        wrapper.eq("hc_id", lol.getOccupationTwo());
        LolOccupation occupationTwo = lolOccupationMapper.selectOne(wrapper);

        wrapper = new QueryWrapper();
        wrapper.eq("r_id", lol.getRouteOne());
        LolRoutes routesOne = lolRoutesMapper.selectOne(wrapper);

        wrapper = new QueryWrapper();
        wrapper.eq("r_id", lol.getRouteTwo());
        LolRoutes routesTwo =lolRoutesMapper.selectOne(wrapper);
        return LolServiceUtil.lolToLolVo(lol,lolVo,lolForces,occupationOne,occupationTwo,routesOne,routesTwo);
    }

    /**
     * 更新英雄数据
     * @param lolVo
     * @return
     */
    @Override
    public int updateLol(LolVo lolVo) {
        Lol lol = new Lol();
        QueryWrapper wrapper = new QueryWrapper();

        lol.setHId(lolVo.getHId());
        if (lolVo.getDesignation() != null){
            lol.setDesignation(lolVo.getDesignation());
        }
        if (lolVo.getHeroName() != null){
            lol.setHeroName(lolVo.getHeroName());
        }
        if (lolVo.getGender() != null){
            lol.setGender(lolVo.getGender());
        }
        if (lolVo.getForce() != null){
            wrapper.eq("f_name",lolVo.getForce());
            lol.setForceId(lolForcesMapper.selectOne(wrapper).getFId());
        }
        if (lolVo.getOccupationOne() != null){
            wrapper = new QueryWrapper();
            wrapper.eq("name_cn",lolVo.getOccupationOne());
            LolOccupation result = lolOccupationMapper.selectOne(wrapper);
            if (result != null){
                lol.setOccupationOne(result.getHcId());
            }else {
                wrapper = new QueryWrapper();
                wrapper.eq("name_us",lolVo.getOccupationOne());
                result = lolOccupationMapper.selectOne(wrapper);
                lol.setOccupationOne(result.getHcId());
            }
        }
        if (lolVo.getOccupationTwo() != null){
            wrapper = new QueryWrapper();
            wrapper.eq("name_cn",lolVo.getOccupationTwo());
            LolOccupation result = lolOccupationMapper.selectOne(wrapper);
            if (result != null){
                lol.setOccupationTwo(result.getHcId());
            }else {
                wrapper = new QueryWrapper();
                wrapper.eq("name_us",lolVo.getOccupationTwo());
                result = lolOccupationMapper.selectOne(wrapper);
                lol.setOccupationTwo(result.getHcId());
            }
        }
        if (lolVo.getRouteOne() != null){
            wrapper = new QueryWrapper();
            wrapper.eq("route",lolVo.getRouteOne());
            lol.setRouteOne(lolRoutesMapper.selectOne(wrapper).getRId());
        }
        if (lolVo.getRouteTwo() != null){
            wrapper = new QueryWrapper();
            wrapper.eq("route",lolVo.getRouteTwo());
            lol.setRouteTwo(lolRoutesMapper.selectOne(wrapper).getRId());
        }
        return lolMapper.updateById(lol);
    }

    /**
     * 在lol_forces表中查询到对应force对象，并将其列在List中的首位
     * @param force
     * @return
     */
    @Override
    public List<LolForces> forces_UpdateLol(String force) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("f_name",force);
        LolForces lolForce = lolForcesMapper.selectOne(wrapper);
        List<LolForces> lolForces = lolForcesMapper.selectList(null);
        List<LolForces> forcesList = new ArrayList<>();
        forcesList.add(lolForce);
        for (LolForces forces : lolForces) {
            if (lolForce.equals(forces)){
                continue;
            }
            forcesList.add(forces);
        }
        return forcesList;
    }

    /**
     * 在lol_occupation表中查询到对应occupation对象，并将其列在List中的首位
     * @param occupation
     * @return
     */
    @Override
    public List<LolOccupation> occupation_UpdateLol(String occupation) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("name_cn",occupation);
        LolOccupation lolOccupation = lolOccupationMapper.selectOne(wrapper);
        List<LolOccupation> lolOccupations = lolOccupationMapper.selectList(null);
        List<LolOccupation> occupationList = new ArrayList<>();
        occupationList.add(lolOccupation);
        for (LolOccupation occupation1 : lolOccupations) {
            if (lolOccupation.equals(occupation1)){
                continue;
            }
            occupationList.add(occupation1);
        }
        return occupationList;
    }

    /**
     * 在lol_routes表中查询到对应route对象，并将其列在List中的首位
     * @param route
     * @return
     */
    @Override
    public List<LolRoutes> routes_UpdateLol(String route) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("route",route);
        LolRoutes lolRoute = lolRoutesMapper.selectOne(wrapper);
        List<LolRoutes> lolRoutes = lolRoutesMapper.selectList(null);
        List<LolRoutes> lolRoutesList = new ArrayList<>();
        lolRoutesList.add(lolRoute);
        for (LolRoutes routes : lolRoutes) {
            if (route.equals(routes)){
                continue;
            }
            lolRoutesList.add(routes);
        }
        return lolRoutesList;
    }

    @Override
    public int deleteLol(Integer hId) {
        return lolMapper.deleteById(hId);
    }

}
