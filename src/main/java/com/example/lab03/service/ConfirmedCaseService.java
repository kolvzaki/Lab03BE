package com.example.lab03.service;

import com.example.lab03.entity.ConfirmedCase;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.lab03.entity.DateCase;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Kolvzaki
 * @since 2021-05-12
 */
public interface ConfirmedCaseService extends IService<ConfirmedCase> {

    public void insertBatch();
    public void updateDate();

    //更新今天的数据
    public List<ConfirmedCase> refreshData(String flag, int showValue);

    public List<ConfirmedCase> getOnesData(String flag,String name);

    public List<DateCase> getPast7DaysData();

    public List<ConfirmedCase> getData();

}
