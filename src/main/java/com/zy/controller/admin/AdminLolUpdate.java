package com.zy.controller.admin;

import com.zy.entity.lol.LolForces;
import com.zy.service.LolForcesService;
import com.zy.service.LolService;
import com.zy.vo.LolVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/up")
/**
 * 管理员更新英雄跳转控制
 */
public class AdminLolUpdate {

    @Autowired
    private LolService lolService;
    @Autowired
    private LolForcesService lolForcesService;

    /**
     * 携带必要数据，跳转到更新页面update.html
     * @param hId
     * @param model
     * @return
     */
    @RequestMapping("/lol")
    public String toUpdateLol(@RequestParam("hId") Integer hId, Model model){
        LolVo lolVo = lolService.getLolVoByHId(hId);
        model.addAttribute("lolVo",lolVo);
        model.addAttribute("LoL","LoL");
        model.addAttribute("forces",lolService.forces_UpdateLol(lolVo.getForce()));
        model.addAttribute("occupationOne",lolService.occupation_UpdateLol(lolVo.getOccupationOne()));
        model.addAttribute("occupationTwo",lolService.occupation_UpdateLol(lolVo.getOccupationTwo()));
        model.addAttribute("routesOne",lolService.routes_UpdateLol(lolVo.getRouteOne()));
        model.addAttribute("routesTwo",lolService.routes_UpdateLol(lolVo.getRouteTwo()));
        return "update";
    }

    /**
     * 更新英雄操作，成功后重定向到lol.html
     * @param lolVo
     * @return
     */
    @RequestMapping("/updateLol")
    public String updateLol(LolVo lolVo){
        lolService.updateLol(lolVo);
        return "redirect:/lol";
    }

    @RequestMapping("/forces")
    public String toUpdateForces(@RequestParam("fId") Integer fId,
                                Model model){
        model.addAttribute("LoLForces","LoLForces");
        LolForces lolForce = lolForcesService.getByFId(fId);
        model.addAttribute("lolForce",lolForce);
        return "update";
    }

    @RequestMapping("/updateForces")
    public String updateForces(LolForces lolForces){
        lolForcesService.updateForces(lolForces);
        return "redirect:/manageForces";
    }

}
