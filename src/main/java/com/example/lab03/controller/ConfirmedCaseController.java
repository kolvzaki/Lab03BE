package com.example.lab03.controller;


import com.example.lab03.entity.ConfirmedCase;
import com.example.lab03.entity.DateCase;
import com.example.lab03.service.ConfirmedCaseService;
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
@RequestMapping(value = "/confirmed",method = RequestMethod.GET)
@CrossOrigin(origins = "*",maxAge = 1800)
public class ConfirmedCaseController {

    @Autowired
    private ConfirmedCaseService service;

    //用于查找某个范围的病例数，但因为懒，没搞
    /*@GetMapping("/getDate")
    @ResponseBody
    public List<ConfirmedCase> getData
            (@RequestParam(name = "range",defaultValue = "country",required = true)String range,
             @RequestParam(name = "name",required = false,defaultValue = "null")String name){

        return service.getOnesData(range,name);
    }*/

    @RequestMapping(value = "/refresh",method = RequestMethod.GET)
    @CrossOrigin(origins = "*",maxAge = 1800)
    public List<ConfirmedCase> refreshData(@RequestParam(name = "onlyChina",required = true,defaultValue = "n")String flag, @RequestParam(name = "showValue",required = true,defaultValue = "10")int showValue){
        List<ConfirmedCase> confirmedCases = service.refreshData(flag, showValue);
        System.out.println(confirmedCases.size());
        return confirmedCases;
    }

}

