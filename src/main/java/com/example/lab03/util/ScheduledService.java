package com.example.lab03.util;

import com.example.lab03.service.ConfirmedCaseService;
import com.example.lab03.service.DeathCaseService;
import com.example.lab03.service.RecoveredCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {

    @Autowired
    ConfirmedCaseService confirmedCaseService;
    @Autowired
    DeathCaseService deathCaseService;
    @Autowired
    RecoveredCaseService recoveredCaseService;

    @Scheduled(cron = "0 0 8 * * ? *")
    public void update(){

        CovidUtil.downloadData();
        confirmedCaseService.updateDate();
        deathCaseService.updateDate();
        recoveredCaseService.updateDate();
        System.out.println("update complete!");

    }

}
