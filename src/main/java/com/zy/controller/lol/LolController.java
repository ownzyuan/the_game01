package com.zy.controller.lol;

import com.zy.service.LolService;
import com.zy.vo.DataVo;
import com.zy.vo.LolVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LolController {

    @Autowired
    private LolService lolService;

    /**
     * 分页查询lol表，通过json传递给lol.html
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/lolList")
    @ResponseBody
    public DataVo list(Integer page, Integer limit) {
        return lolService.getByPage(page, limit);
    }

    /**
     * 通过condition条件查询
     * @param condition 可以为lol表中任意字段
     * @param model
     * @return
     */
    @RequestMapping(value = "/select")
    public String select(@RequestParam("condition") Object condition,
                         Model model) {
        List<LolVo> lolVoList = lolService.selectByCondition(condition);
        if (lolVoList.isEmpty()){
            model.addAttribute("msg","未查询到对应英雄");
            return "lol";
        }else {
            model.addAttribute("lolVoList",lolVoList);
            return "selectResult";
        }
    }
}
