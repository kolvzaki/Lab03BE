package com.example.lab03.mapper;

import com.example.lab03.entity.ConfirmedCase;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Kolvzaki
 * @since 2021-05-12
 */
@Mapper
public interface ConfirmedCaseMapper extends BaseMapper<ConfirmedCase> {

    void dropData();

    void insertBatch(@Param("list")List<ConfirmedCase> cases);

    List<ConfirmedCase> onlyChina(@Param("showValue")int showValue,@Param("dateTime") String dateTime);

    List<ConfirmedCase> getData(@Param("showValue")int showValue,@Param("dateTime")String dateTime);

    List<ConfirmedCase> getOnesByCountryName(@Param("countryName")String countryName);

    List<ConfirmedCase> getOnesByRegionName(@Param("regionName")String regionName);

    Integer countTotal(@Param("dateTime")String dateTime);

}
