package com.example.lab03;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;

public class CodeGenerator {

    @Test
    public static void main(String args[]){
        AutoGenerator autoGenerator = new AutoGenerator();

        //数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/lab03?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("root");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");

        autoGenerator.setDataSource(dataSourceConfig);

        //全局
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir("D:\\WorkPlace\\java\\lab03"+"/src/main/java");//存放路径
        globalConfig.setOpen(false);
        globalConfig.setFileOverride(false);
        globalConfig.setSwagger2(true);
        globalConfig.setIdType(IdType.ASSIGN_ID);
        globalConfig.setDateType(DateType.ONLY_DATE);
        globalConfig.setAuthor("Kolvzaki");
        globalConfig.setServiceName("%sService");

        autoGenerator.setGlobalConfig(globalConfig);

        //包信息
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.example.lab03");//父包
      /*  packageConfig.setModuleName("context");*/
        packageConfig.setController("controller");
        packageConfig.setService("service");
        packageConfig.setEntity("entity");
        packageConfig.setServiceImpl("service.impl");
        packageConfig.setMapper("mapper");

        autoGenerator.setPackageInfo(packageConfig);

        //配置策略
        StrategyConfig strategyConfig = new StrategyConfig();

        strategyConfig.setInclude("place","confirmed_case","death_case","recovered_case");
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setTablePrefix(packageConfig.getModuleName()+"_");

        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setControllerMappingHyphenStyle(true);

        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);//下划线转驼峰

        autoGenerator.setStrategy(strategyConfig);

        autoGenerator.execute();



    }
}
