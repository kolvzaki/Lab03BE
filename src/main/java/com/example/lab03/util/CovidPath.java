package com.example.lab03.util;



import java.util.List;


//建议科学上网更新
public interface CovidPath {
     final String DataPath = "..//..//src//main//resources//data//";

     final String localNameForCData = "CovidConfirmedCases.csv";
     final String localNameForDData = "CovidDeathCases.csv";
     final String localNameForRData = "CovidRecoveredCases.csv";

     final String url1 = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
     final String url2 = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";
     final String url3 = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv";

}
