package com.example.lab03.service;

import com.example.lab03.entity.DateCase;
import com.example.lab03.entity.DeathCase;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Kolvzaki
 * @since 2021-05-12
 */
public interface DeathCaseService extends IService<DeathCase> {

    void insertBatch();

    void updateDate();

    List<DeathCase> refreshData(String flag, int showValue);

    List<DateCase> getPast7DaysData();

    List<DeathCase> getData();

}
