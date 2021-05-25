package com.example.lab03.controller;


import com.example.lab03.entity.DeathCase;
import com.example.lab03.service.DeathCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Kolvzaki
 * @since 2021-05-12
 */
@RestController
@RequestMapping(value = "/death")
@CrossOrigin(origins = "*",maxAge = 1800)
public class DeathCaseController {

    @Autowired
    private DeathCaseService service;

    @GetMapping("/refresh")
    @CrossOrigin(origins = "*",maxAge = 1800)
    public List<DeathCase> refreshData(@RequestParam(name = "onlyChina",required = true,defaultValue = "n")String flag, @RequestParam(name = "showValue",required = true,defaultValue = "10")int showValue){

        return service.refreshData(flag, showValue);

    }


}

