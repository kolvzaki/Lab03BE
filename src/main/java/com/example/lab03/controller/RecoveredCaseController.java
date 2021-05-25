package com.example.lab03.controller;


import com.example.lab03.entity.RecoveredCase;
import com.example.lab03.service.RecoveredCaseService;
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
@RequestMapping("/recovered")
@CrossOrigin(origins = "*",maxAge = 1800)
public class RecoveredCaseController {

    @Autowired
    private RecoveredCaseService service;

    @GetMapping("/refresh")
    @CrossOrigin(origins = "*",maxAge = 1800)
    public List<RecoveredCase> refreshData(@RequestParam(name = "onlyChina",required = true,defaultValue = "n")String flag, @RequestParam(name = "showValue",required = true,defaultValue = "10")int showValue){
        return service.refreshData(flag, showValue);
    }


}

