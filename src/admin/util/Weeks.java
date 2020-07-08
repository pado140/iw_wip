/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.util;

import java.util.Date;

/**
 *
 * @author Padovano
 */
public class Weeks {
    private int no,noinmonth;
    private int month;
    private int year;
    private int from,to;
    private int hollydays;
    private int worksDay;
    private Date fromDate,toDate;
    
    private DateUtils du;
    
    private Date date;

    public Weeks(DateUtils d) {
        du=d;
        
        no=du.weekno();
        noinmonth=du.weekInMonth();
        month=du.month()+1;
        year=du.year();
        from=du.startWeek();
        to=du.endWeek();
        worksDay=5;
        fromDate=du.FromDate();
        toDate=du.ToDate();
    }

    public Weeks(Date date) {
        
        this(DateUtils.getInstance().setDate(date));
        this.date = date;
        du=DateUtils.getInstance().setDate(date);
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getNoinmonth() {
        return noinmonth;
    }

    public void setNoinmonth(int noinmonth) {
        this.noinmonth = noinmonth;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getHollydays() {
        return hollydays;
    }

    public void setHollydays(int hollydays) {
        this.hollydays = hollydays;
    }

    public int getWorksDay() {
        return worksDay-hollydays;
    }

    public void setWorksDay(int worksDay) {
        this.worksDay = worksDay;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
}
