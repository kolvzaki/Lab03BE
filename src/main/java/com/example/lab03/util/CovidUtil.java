package com.example.lab03.util;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.http.HttpUtil;
import com.example.lab03.entity.ConfirmedCase;
import com.example.lab03.entity.DateCase;
import com.example.lab03.entity.DeathCase;
import com.example.lab03.entity.RecoveredCase;
import com.example.lab03.mapper.ConfirmedCaseMapper;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
/*import com.example.lab03.entity.*;*/

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CovidUtil {

    private static List<String> CovidDate;

    private static CsvReader getReader(){

        CsvReader reader = CsvUtil.getReader();
        reader.setContainsHeader(true);
        return reader;

    }

    public static void downloadData(){


        File file1 = FileUtil.file(CovidPath.DataPath + CovidPath.localNameForCData);
        System.out.println(HttpUtil.downloadFile(CovidPath.url1,file1));

        File file2 = FileUtil.file(CovidPath.DataPath + CovidPath.localNameForDData);
        System.out.println(HttpUtil.downloadFile(CovidPath.url2,file2));

        File file3 = FileUtil.file(CovidPath.DataPath + CovidPath.localNameForRData);
        System.out.println(HttpUtil.downloadFile(CovidPath.url3,file3));

        System.out.println("Download Completed");
    }

    private static void HandleDate(List<String> header){
        CovidDate = new ArrayList<>();
        CovidDate = header.subList(4,header.size());
    }

    public static List<DeathCase> HandleDeathData(){
        CsvReader reader = getReader();
        CsvData data = reader.read(FileUtil.file(CovidPath.DataPath + CovidPath.localNameForDData));

        HandleDate(data.getHeader());

        List<CsvRow> rows = data.getRows();
        List<DeathCase> deathCases = new ArrayList<>();

        for(CsvRow item: rows){
            for(int i = 0 ;i<CovidDate.size();i++){
                DeathCase a = new DeathCase();
                a.setRegionName(item.get(0))
                        .setCountryName(item.get(1))
                        .setLat(item.get(2))
                        .setLng(item.get(3))
                        .setDateTime(CovidDate.get(i))
                        .setCases(Integer.parseInt(item.get(i+4)));
                deathCases.add(a);
            }
        }
        return deathCases;
    }

    public static List<RecoveredCase> HandleRecoveredData(){
        CsvReader reader = getReader();
        CsvData data = reader.read(FileUtil.file(CovidPath.DataPath + CovidPath.localNameForRData));

        List<CsvRow> rows = data.getRows();
        List<RecoveredCase> recoveredCases = new ArrayList<>();
        for(CsvRow item: rows){
            for(int i = 0 ; i<CovidDate.size();i++){
                RecoveredCase a = new RecoveredCase();
                a.setRegionName(item.get(0))
                        .setCountryName(item.get(1))
                        .setLat(item.get(2))
                        .setLng(item.get(3))
                        .setDateTime(CovidDate.get(i))
                        .setCases(Integer.parseInt(item.get(i+4)));

                recoveredCases.add(a);
            }
        }
        return recoveredCases;
    }

    public static List<ConfirmedCase> HandleConfirmedData(){
        CsvReader reader = getReader();
        CsvData data = reader.read(FileUtil.file(CovidPath.DataPath + CovidPath.localNameForCData));

        //处理日期
        HandleDate(data.getHeader());

        List<CsvRow> rows = data.getRows();
        List<ConfirmedCase> confirmedCases = new ArrayList<>();
        for (CsvRow item:rows){
           for(int i = 0 ; i<CovidDate.size();i++){
               ConfirmedCase a = new ConfirmedCase();

               a.setRegionName(item.get(0))
                       .setCountryName(item.get(1))
                       .setLat(item.get(2))
                       .setLng(item.get(3))
                       .setDateTime(CovidDate.get(i))
                       .setCases(Integer.parseInt(item.get(i+4)));
               confirmedCases.add(a);
           }
        }
        return confirmedCases;
    }

    private static String trans2TargetFormat(Date date){
        int year = DateUtil.year(date);
        int month = DateUtil.month(date);
        int i = DateUtil.dayOfMonth(date);

        year = year % 100;
        month += 1;
        return ""+month+"/"+i+"/"+year;
    }


    public static String getToday(){
        Date date = DateUtil.date();
        int year = DateUtil.year(date);
        int month = DateUtil.month(date);
        int i = DateUtil.dayOfMonth(date) - 1; //github数据以美国时间为准，比中国时间快一天，所以-1.

        year = year % 100;
        month += 1;
        return ""+month+"/"+i+"/"+year;
      /*  return trans2TargetFormat(date);*/
    }

    public static List<DateCase> getPast7Days(){

        List<DateCase> list = new ArrayList<>();
        Date date = DateUtil.date();

        for(int i=0;i<7;i++){
            DateTime dateTime = DateUtil.offsetDay(date, -7 + i);
            Date offday = DateUtil.date(dateTime);
            DateCase dateCase = new DateCase();
            dateCase.setDate(trans2TargetFormat(offday))
                    .setCases(0);
            list.add(dateCase);
        }

        return list;
    }


}
