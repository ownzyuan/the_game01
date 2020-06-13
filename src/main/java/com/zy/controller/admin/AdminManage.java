package com.zy.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zy.entity.lol.Lol;
import com.zy.entity.lol.LolForces;
import com.zy.service.LolForcesService;
import com.zy.service.LolService;
import com.zy.utils.LolServiceUtil;
import com.zy.vo.LolVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminManage {

    @Autowired
    private LolService lolService;
    @Autowired
    private LolForcesService lolForcesService;

    /**
     * 跳转到lolManage.html页面，需要管理员先登录才能进行
     * @param pageNum
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping("/manageLol")
    public String toManageLol(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                              @RequestParam(value = "pageSize", defaultValue = "8") Integer pageSize,
                              Model model) {
        List<LolVo> lolVoList = lolService.queryLolVo(pageNum, pageSize);
        model.addAttribute("lolVoList",lolVoList);
        IPage<Lol> lolIPage = lolService.queryLol(pageNum, pageSize);
        model.addAttribute("lolIPage", lolIPage);
        List pageList = LolServiceUtil.pageManage(pageNum, lolIPage.getPages());
        model.addAttribute("pageList", pageList);
        return "lolManage";
    }

    @RequestMapping("/adminLol")
    public String toIndex(){
        return "redirect:/lol";
    }

    @RequestMapping("/manageForces")
    public String toUpdateForces(Model model){
        List<LolForces> lolForces = lolForcesService.queryForces();
        model.addAttribute("lolForces",lolForces);
        return "forcesManage";
    }

}
