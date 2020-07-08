/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.util;

import com.toedter.calendar.DateUtil;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Padovano
 */
public class DateUtils {
    private Calendar dateCal;
    private static DateUtils dateUtils;
    private Date date=new Date();
    private DateUtils() {
        
        dateCal=Calendar.getInstance();
        dateCal.setTime(date);
        dateCal.setMinimalDaysInFirstWeek(7);
        
    }
    
    public static synchronized DateUtils getInstance(){
        if(dateUtils==null)
            dateUtils=new DateUtils();
        return dateUtils;
    }
    
    public  int year(){
        return dateCal.getWeekYear();
    }
    
    public  int weekno(){
        return dateCal.get(Calendar.WEEK_OF_YEAR);
    }
    
    public int month(){
        return dateCal.get(Calendar.MONTH);
    }
    
    public int weekInMonth(){
        return dateCal.get(Calendar.DAY_OF_WEEK_IN_MONTH);
    }
    
    public int dayInWeek(){
        return dateCal.get(Calendar.DAY_OF_WEEK);
    }
    
    public int day(){
        return dateCal.get(Calendar.DATE);
    }
    
    public int weeksInYear(){
        return dateCal.getWeeksInWeekYear();
    }
    
    public DateUtils setDate(Date d){
        date=d;
        dateCal.setTime(date);
        return this;
    }
    
    public DateUtils setWeek(int week){
        dateCal.set(Calendar.DAY_OF_WEEK,week);
        return this;
    }
    
    public int startWeek(){
        Calendar c=Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -(c.get(Calendar.DAY_OF_WEEK)-2));
        return c.get(Calendar.DATE);
    }
    
    public int endWeek(){
        Calendar c=Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 7-c.get(Calendar.DAY_OF_WEEK));
        return c.get(Calendar.DATE);
    }
    
    public Date FromDate(){
        Calendar c=Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -(c.get(Calendar.DAY_OF_WEEK)-2));
        return c.getTime();
    }
    
    public Date ToDate(){
        Calendar c=Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 7-c.get(Calendar.DAY_OF_WEEK));
        return c.getTime();
    }
    public void tString(){
        System.out.println("year:"+year());
        System.out.println("week:"+weekno());
        System.out.println("month:"+month());
        System.out.println("week in month:"+weekInMonth());
        System.out.println("day of week:"+dayInWeek());
        System.out.println("day:"+day());
        System.out.println("week in year:"+weeksInYear());
        System.out.println("first day in week:"+startWeek());
        System.out.println("last day in week:"+endWeek());
    }
    public static Weeks getWeek(Date d){
        Weeks w=new Weeks(d);
        return w;
    }
    
    public static Weeks getFrom(Date d,int nb){
        Calendar c=Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.DATE, 7*nb);
        Weeks w=new Weeks(c.getTime());
        return w;
    }
}
