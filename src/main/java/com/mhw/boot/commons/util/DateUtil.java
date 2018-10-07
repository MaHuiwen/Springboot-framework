package com.mhw.boot.commons.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @Decription:
 * @auther: fzm_sdy
 * @date: 2018/5/30 16:19
 */
public abstract class DateUtil {

    /**
     * @Decription: 获取东八区北京时间
     * @param:
     * @return:
     * @auther: fzm_sdy
     * @date: 2018/5/30 16:21
     */
    public static Long getTimeOfEastEight(){
        Long utcTime = null;
        TimeZone timeZone = TimeZone.getTimeZone("GMT+08:00");
        Date currentDate = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        df.setTimeZone(timeZone);
        try {
            utcTime = getUTCTime(df.format(currentDate), "yyyyMMddHHmmss");
        } catch (ParseException e) {
            return utcTime;
        }
        return utcTime;
    }

    /**
     * @Decription:	获取utc时间
     * @param: [date, rule]
     * @return: java.lang.Long
     * @auther: fzm_sdy
     * @date: 2018/5/30 16:34
     */
    public static Long getUTCTime(String date, String rule) throws ParseException {

        Long utcTime = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(rule);
            Date d = sdf.parse(date);
            utcTime = d.getTime();
        } catch (Exception e) {
            return utcTime;
        }
        return utcTime;
    }

    /**
     * @Decription: 获取年月时间字符串
     * @param: []
     * @return: java.lang.String
     * @auther: fzm_sdy
     * @date: 2018/6/1 10:39
     */
    public static String getDate() {
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        result = sdf.format(new Date());
        return result;
    }

    public static void main(String[] args) {
        long millPerHour = 60*60*1000;
        /*花*//*
        Long flowerForthStageTime = DateUtil.getTimeOfEastEight() - (360)*millPerHour;
        Long flowerFifthStageTime = DateUtil.getTimeOfEastEight() - (480)*millPerHour;
        Long flowerDecayStageTime = DateUtil.getTimeOfEastEight() - (720+24)*millPerHour;
        Long flowerDisappearTime = DateUtil.getTimeOfEastEight() - (720+3*24)*millPerHour;
        *//*果*//*
        Long fruitForthStageTime = DateUtil.getTimeOfEastEight() - (1000)*millPerHour;
        Long fruitFifthStageTime = DateUtil.getTimeOfEastEight() - (1200)*millPerHour;
        Long fruitDecayStageTime = DateUtil.getTimeOfEastEight() - (1440+24)*millPerHour;
        Long fruitDisappearTime = DateUtil.getTimeOfEastEight() - (1440+3*24)*millPerHour;

        *//*打印时间*//*
        System.out.println("花第四阶段时间：" + flowerForthStageTime);
        System.out.println("花第五阶段时间：" + flowerFifthStageTime);
        System.out.println("花衰败时间：" + flowerDecayStageTime);
        System.out.println("花消失时间：" + flowerDisappearTime);
        System.out.println("果第四阶段时间：" + fruitForthStageTime);
        System.out.println("果第五阶段时间：" + fruitFifthStageTime);
        System.out.println("果衰败时间：" + fruitDecayStageTime);
        System.out.println("果消失时间：" + fruitDisappearTime);*/
        System.out.println(DateUtil.getTimeOfEastEight());
    }


}

