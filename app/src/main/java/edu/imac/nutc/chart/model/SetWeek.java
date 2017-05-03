package edu.imac.nutc.chart.model;

import java.util.Calendar;

/**
 * Created by cheng on 2017/5/2.
 */

public class SetWeek {

    public static String[] getWeek(){
        String[] week = { "Sun", "Mon", "Tue","Wed", "Thu", "Fri", "Sat"};
        Calendar c= Calendar.getInstance();
        String[] week2=new String[7];
        int weekday = c.get(Calendar.DAY_OF_WEEK);
        for(int i=0;i<week.length;i++){
            if(weekday+i<=week.length){
                week2[i]=week[weekday+i-1];
            }else{
                week2[i]=week[(weekday+i-1)-week.length];
            }
        }
        for(int i=0;i<week2.length;i++){
            week[week2.length-i-1]=week2[i];
        }
        return week;
    }
}
