package com.example.lab03;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.example.lab03.mapper.ConfirmedCaseMapper;
import com.example.lab03.service.ConfirmedCaseService;
import com.example.lab03.service.DeathCaseService;
import com.example.lab03.service.RecoveredCaseService;
import com.example.lab03.util.CovidPath;
import com.example.lab03.util.CovidUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class Lab03ApplicationTests {

    @Autowired
    ConfirmedCaseService service;

    @Autowired
    DeathCaseService deathCaseService;

    @Autowired
    RecoveredCaseService recoveredCaseService;

    @Autowired
    ConfirmedCaseMapper mapper;

    @Test
    void loadData(){
        CovidUtil.downloadData();
        service.updateDate();
        deathCaseService.updateDate();
        recoveredCaseService.updateDate();
    }

    @Test
    void testDateTrans(){
        System.out.println(CovidUtil.getPast7Days());
    }

}
