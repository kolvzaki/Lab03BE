package com.example.lab03.service.impl;

import com.example.lab03.entity.DateCase;
import com.example.lab03.entity.DeathCase;
import com.example.lab03.mapper.DeathCaseMapper;
import com.example.lab03.service.DeathCaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.lab03.util.CovidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class DeathCaseServiceImpl extends ServiceImpl<DeathCaseMapper, DeathCase> implements DeathCaseService {

    @Autowired
    private DeathCaseMapper mapper;

    @Override
    public void insertBatch() {
        List<DeathCase> deathCases = CovidUtil.HandleDeathData();
        mapper.insertBatch(deathCases);
    }

    @Override
    public void updateDate() {

        //更新数据要在下载最新数据后进行,否则该操作只能是重新写入旧数据
        List<DeathCase> deathCases = CovidUtil.HandleDeathData();
        mapper.dropData();
        mapper.insertBatch(deathCases);

    }

    @Override
    public List<DeathCase> refreshData(String flag, int showValue) {

        List<DeathCase> deathCases;
        String dateTime = CovidUtil.getToday();

        if(flag.equals("y")){
            deathCases = mapper.onlyChina(showValue,dateTime);
        }
        else{
            deathCases = mapper.getData(showValue,dateTime);
        }

        return deathCases;
    }

    @Override
    public List<DateCase> getPast7DaysData() {

        List<DateCase> list = CovidUtil.getPast7Days();
        for(DateCase item: list){
            item.setCases(mapper.countTotal(item.getDate()));
        }
        return list;
    }

    @Override
    public List<DeathCase> getData() {

        String dateTime = CovidUtil.getToday();
        return mapper.getData(10,dateTime);
    }
}
