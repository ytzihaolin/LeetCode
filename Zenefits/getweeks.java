package com.test;
 
import java.text.SimpleDateFormat;
import java.util.Date;
 
public class date {
    public static void main(String[] args) {
 
        String start_date = "2009-6-10";
        String end_date = "2010-6-10";
        int temp = getDays(start_date,end_date);
        System.out.println(temp);
    }
 
    public static int getDays(String startdate, String enddate) {
        int temp = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateStart = sdf.parse(startdate);
            Date dateEnd = sdf.parse(enddate);
            temp = (int) ((dateEnd.getTime() - dateStart.getTime()) / 86400000 / 7);
        } catch (Exception ee) {
            ee.printStackTrace();
        }
        return temp;
    }
 
}