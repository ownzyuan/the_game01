package com.zy.controller.admin;

import com.zy.entity.lol.LolForces;
import com.zy.service.LolForcesService;
import com.zy.service.LolService;
import com.zy.vo.LolVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/add")
/**
 * 管理员添加跳转控制
 */
public class AdminLolAdd {

    @Autowired
    private LolService lolService;
    @Autowired
    private LolForcesService lolForcesService;

    /**
     * 携带必要数据，跳转到添加页面add.html
     * @param model
     * @return
     */
    @RequestMapping("/lol")
    public String toAddLol(Model model){
        model.addAttribute("LoL","LoL");
        model.addAttribute("deletedCount",lolService.selectDeleted().size());
        model.addAttribute("count",lolService.getLolCount()+1);
        model.addAttribute("forces",lolService.queryForces());
        model.addAttribute("occupation",lolService.queryOccupation());
        model.addAttribute("routes",lolService.queryRoutes());
        return "add";
    }

    /**
     * 添加英雄操作，成功后重定向到lol.html
     * @param lolVo
     * @return
     */
    @PostMapping("/insertLol")
    public String addLol(LolVo lolVo,Model model){
        int i = lolService.insertLol(lolVo);
        if (i == 0){
            model.addAttribute("msg","添加失败,该英雄已存在");
            model.addAttribute("LoL","LoL");
            return "add";
        }else return "redirect:/lol";
    }

    /**
     * 携带必要数据，跳转到添加页面add.html
     * @param model
     * @return
     */
    @RequestMapping("/forces")
    public String toAddForces(Model model){
        model.addAttribute("LolForces","LolForces");
        model.addAttribute("count",lolForcesService.getForcesCount()+1);
        return "add";
    }

    /**
     * 添加势力操作，成功后重定向到lol.html
     * @param lolForces
     * @return
     */
    @PostMapping("/insertForces")
    public String addForces(LolForces lolForces){
        lolForcesService.insertForces(lolForces);
        return "redirect:/lol";
    }

}
