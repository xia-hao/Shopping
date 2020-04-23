package com.shopping.utils.utils;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @PACKAGE_NAME: com.shopping.utils.utils
 * @author: XIA
 * @NAME: TimeUtils
 * @DATE: 2020/4/20
 **/
public class TimeUtils {
    public static Date getNewTime(Date date,Integer addMinutes) {
        Calendar newTime = Calendar.getInstance();
        newTime.setTime(date);
        newTime.add(Calendar.MINUTE,addMinutes);//日期加n分
        Date newDate=newTime.getTime();
        return newDate;
    }


    public static void main(String[] args)   {
        Date date = new Date();
        SimpleDateFormat formatter =new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        System.out.println(formatter.format(getNewTime(date,10)));
    }
}
