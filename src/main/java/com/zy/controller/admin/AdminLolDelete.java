package com.zy.controller.admin;

import com.zy.service.LolForcesService;
import com.zy.service.LolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminLolDelete {

    @Autowired
    private LolService lolService;
    @Autowired
    private LolForcesService lolForcesService;

    @RequestMapping("/delete")
    public String deleteLol(@RequestParam("hId") Integer hId){
        lolService.deleteLol(hId);
        return "redirect:/lol";
    }

    @RequestMapping("/deleteForces")
    public String deleteForces(@RequestParam("fId") Integer fId){
        lolForcesService.deleteForces(fId);
        return "redirect:/manageForces";
    }

}
