package com.example.lab03.controller;

import com.example.lab03.entity.ConfirmedCase;
import com.example.lab03.entity.DateCase;
import com.example.lab03.entity.DeathCase;
import com.example.lab03.entity.RecoveredCase;
import com.example.lab03.service.ConfirmedCaseService;
import com.example.lab03.service.DeathCaseService;
import com.example.lab03.service.RecoveredCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*",maxAge = 1800)
public class IndexController {

    @Autowired
    private ConfirmedCaseService confirmedCaseService;

    @Autowired
    private DeathCaseService deathCaseService;

    @Autowired
    private RecoveredCaseService recoveredCaseService;


    @RequestMapping("/update")
    public void updateData(){
        confirmedCaseService.updateDate();
    }

    @RequestMapping(value = "/getConfirmedDate",method = RequestMethod.GET)
    @CrossOrigin(origins = "*",maxAge = 1800)
    public List<ConfirmedCase> getConfirmedDate(){

        return confirmedCaseService.getData();
    }

    @GetMapping("/getTotalRecovered")
    @CrossOrigin(origins = "*",maxAge = 1800)
    public List<DateCase> getRecovered7days(){
        return recoveredCaseService.getPast7DaysData();
    }

    @GetMapping("/getTotalConfirmed")
    @CrossOrigin(origins = "*",maxAge = 1800)
    public List<DateCase> getConfirmed7days(){
        return confirmedCaseService.getPast7DaysData();
    }

    @GetMapping("/getTotalDeath")
    @CrossOrigin(origins = "*",maxAge = 1800)
    public List<DateCase> getDeath7days(){
        return deathCaseService.getPast7DaysData();
    }

    @RequestMapping(value = "/getDeathDate",method = RequestMethod.GET)
    @CrossOrigin(origins = "*",maxAge = 1800)
    public List<DeathCase> getDeathDate(){
        return deathCaseService.getData();
    }

    @GetMapping("/getRecoveredDate")
    @CrossOrigin(origins = "*",maxAge = 1800)
    public List<RecoveredCase> getRecoveredDate(){
        return recoveredCaseService.getData();
    }


}
