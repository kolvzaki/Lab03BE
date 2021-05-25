package com.example.lab03.service.impl;

import com.example.lab03.entity.DateCase;
import com.example.lab03.entity.DeathCase;
import com.example.lab03.entity.RecoveredCase;
import com.example.lab03.mapper.RecoveredCaseMapper;
import com.example.lab03.service.RecoveredCaseService;
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
public class RecoveredCaseServiceImpl extends ServiceImpl<RecoveredCaseMapper, RecoveredCase> implements RecoveredCaseService {

    @Autowired
    private RecoveredCaseMapper mapper;

    @Override
    public void insertBatch() {
        List<RecoveredCase> list = CovidUtil.HandleRecoveredData();
        mapper.insertBatch(list);
    }

    @Override
    public void updateDate() {

        List<RecoveredCase> list = CovidUtil.HandleRecoveredData();
        mapper.dropData();
        mapper.insertBatch(list);

    }

    @Override
    public List<RecoveredCase> refreshData(String flag, int showValue) {

        List<RecoveredCase> recoveredCases;
        String dateTime = CovidUtil.getToday();

        if(flag.equals("y")){
            recoveredCases = mapper.onlyChina(showValue,dateTime);
        }
        else{
            recoveredCases = mapper.getData(showValue,dateTime);
        }

        return recoveredCases;
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
    public List<RecoveredCase> getData() {
        String dateTime = CovidUtil.getToday();
        return mapper.getData(10,dateTime);
    }


}
