package com.zy.utils;

import com.zy.entity.lol.Lol;
import com.zy.entity.lol.LolForces;
import com.zy.entity.lol.LolOccupation;
import com.zy.entity.lol.LolRoutes;
import com.zy.vo.LolVo;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

public class LolServiceUtil {

    @Bean
    public static LolVo lolToLolVo(Lol lol, LolVo lolVo, LolForces lolForces,
                                   LolOccupation lolOccupationOne, LolOccupation lolOccupationTwo,
                                   LolRoutes lolRoutesOne,LolRoutes lolRoutesTwo){
        BeanUtils.copyProperties(lol, lolVo);
        lolVo.setHId(lol.getHId());
        lolVo.setForce(lolForces.getFName());
        lolVo.setOccupationOne(lolOccupationOne.getNameCn());
        lolVo.setOccupationTwo(lolOccupationTwo.getNameCn());
        lolVo.setRouteOne(lolRoutesOne.getRoute());
        lolVo.setRouteTwo(lolRoutesTwo.getRoute());

        return lolVo;
    }

    @Bean
    public static List pageManage(Integer pageNum, Long pageLength) {
        List<Long> pageList = new ArrayList<>();
        Long pageNumMax = 6L;
        Long startNum;
        Long endNum;
        Long midPage = pageNumMax / 2;
        if (pageLength > pageNumMax) {
            if (pageNum <= pageNumMax) {
                startNum = pageNum - midPage;
                if (startNum <= 0) {
                    startNum = 1L;
                    endNum = pageNumMax;
                    for (long i = startNum; i <= endNum; i++) {
                        pageList.add(i);
                    }
                } else {
                    startNum = pageNumMax - midPage + 1;
                    endNum = startNum + pageNumMax - 1;
                    for (long i = startNum; i <= endNum; i++) {
                        pageList.add(i);
                    }
                }
            } else if (pageNum + pageNumMax <= pageLength) {
                startNum = pageNum - midPage;
                endNum = pageNum + pageNumMax - 1;
                if (startNum % 3 == 1) {
                    for (long i = pageNum; i <= endNum; i++) {
                        pageList.add(i);
                    }
                } else if (startNum % 3 == 2) {
                    for (long i = pageNum - 1; i <= endNum - 1; i++) {
                        pageList.add(i);
                    }
                } else if (startNum % 3 == 0) {
                    for (long i = pageNum - 2; i <= endNum - 2; i++) {
                        pageList.add(i);
                    }
                }
            } else {
                for (long i = pageLength - pageNumMax + 1; i <= pageLength; i++) {
                    pageList.add(i);
                }
            }
        }else if (pageLength <= pageNumMax){
            for (long i = 1; i <= pageLength; i++) {
                pageList.add(i);
            }
        }
        return pageList;
    }

}
