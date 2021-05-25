package com.example.lab03.mapper;

import com.example.lab03.entity.RecoveredCase;
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
public interface RecoveredCaseMapper extends BaseMapper<RecoveredCase> {

    void dropData();

    void insertBatch(@Param("list") List<RecoveredCase> cases);

    List<RecoveredCase> onlyChina(@Param("showValue")int showValue,@Param("dateTime") String dateTime);

    List<RecoveredCase> getData(@Param("showValue")int showValue,@Param("dateTime") String dateTime);

    Integer countTotal(@Param("dateTime")String dateTime);

}
