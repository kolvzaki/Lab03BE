package com.example.lab03.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.lab03.entity.ConfirmedCase;
import com.example.lab03.entity.DateCase;
import com.example.lab03.mapper.ConfirmedCaseMapper;
import com.example.lab03.service.ConfirmedCaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.lab03.util.CovidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Kolvzaki
 * @since 2021-05-12
 */
@Service

public class ConfirmedCaseServiceImpl extends ServiceImpl<ConfirmedCaseMapper, ConfirmedCase> implements ConfirmedCaseService {

    @Autowired
    private ConfirmedCaseMapper mapper;

    @Override
    public void insertBatch() {
        List<ConfirmedCase> confirmedCases = CovidUtil.HandleConfirmedData();
        mapper.insertBatch(confirmedCases);
    }

    @Override
    public void updateDate() {

        List<ConfirmedCase> confirmedCases = CovidUtil.HandleConfirmedData();
        mapper.dropData();
        mapper.insertBatch(confirmedCases);

    }

    @Override
    public List<ConfirmedCase> refreshData(String flag, int showValue) {

        List<ConfirmedCase> confirmedCases = new ArrayList<>();
        String dateTime = CovidUtil.getToday();


        if(flag.equals("y")){
            confirmedCases = mapper.onlyChina(showValue,dateTime);
        }
        else{
            confirmedCases = mapper.getData(showValue,dateTime);
        }
        return confirmedCases;
    }

    @Override
    public List<ConfirmedCase> getOnesData(String flag, String name) {
        List<ConfirmedCase> confirmedCases = null;

        if(!name.equals("null")){
            if (flag.equals("country")){
                confirmedCases = mapper.getOnesByCountryName(name);
            }
            else{
                confirmedCases = mapper.getOnesByRegionName(name);
            }
        }
        else{
            confirmedCases = mapper.getData(10,CovidUtil.getToday());
        }

        return confirmedCases;
    }

    @Override
    public List<DateCase> getPast7DaysData() {
        List<DateCase> list = CovidUtil.getPast7Days();

        for (DateCase item:list)
        {
            item.setCases(mapper.countTotal(item.getDate()));
        }
        return list;
    }

    @Override
    public List<ConfirmedCase> getData() {
        //获取今日的病例数据,默认获取前10个
        String dateTime = CovidUtil.getToday();
        return mapper.getData(10,dateTime);
    }


}
