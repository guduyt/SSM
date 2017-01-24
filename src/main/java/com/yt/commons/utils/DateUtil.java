package com.yt.commons.utils;

import com.yt.commons.DateFormat;
import com.yt.commons.IUtil;
import com.yt.commons.exceptions.CustomException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Utils
 *
 * @author yitao
 * @version 1.0.0
 * @date 2016/5/27 16:44
 */
public class DateUtil implements DateFormat,IUtil {


    /**
     * 判断字符串是否是空字符串
     * @param s
     * @return
     */
    public static Boolean isNotEmpty(String s){

        if(null!=s && s.length()>0)
            return true;
        return false;
    }


    /**
     *判断字符串是否是时间
     * @param s
     * @return
     */
    public static Boolean isDate(String s){
        if(isNotEmpty(s)){
            Pattern p=Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\.]?((((0?[13578])|(1[02]))[\\-\\/\\.]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\.]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\.]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\.]?((((0?[13578])|(1[02]))[\\-\\/\\.]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\.]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\.]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$", Pattern.CASE_INSENSITIVE|Pattern.MULTILINE);
            return p.matcher(s).matches();
        }
        return false;
    }

    /**
     *判断字符串是否是数字
     * @param s
     * @return
     */
    public static Boolean isNumber(String s){
        if(isNotEmpty(s)) {
            Pattern p = Pattern.compile("^(-?\\d+)(\\.\\d+)?$");
            return p.matcher(s).matches();
        }
        return false;
    }

    /**
     * 日期转换为对应格式字符串
     * @param data
     * @param formatType
     * @return
     */
    public static String dateToString(Date data, String formatType) {
        try {
            return new SimpleDateFormat(formatType).format(data);
        } catch (Exception ex){

            throw  new CustomException(50011,"时间类型转换字符串失败！",ex);
        }
    }

    /**
     * 字符串转换为对应格式日期
     * @param str
     * @param formatType
     * @return
     */
    public static Date stringToDate(String str, String formatType){
        Date date=null;
        try {
            SimpleDateFormat dateFormat=new SimpleDateFormat(formatType);
            if(isNotEmpty(str)){
                date=dateFormat.parse(str);
            }
        } catch (Exception ex){
            throw  new CustomException(50012,"字符串转换指定格式的时间类型失败！",ex);
        }

        return date;
    }

    /**
     * 将字符串按照默认格式转换为时间类型，默认格式"yyyy-MM-dd HH:mm:ss"
     * @param str
     * @return
     */
    public static Date stringToDate(String str){
        Date date=null;
        try {
            String  format=DateFormat_DEFAULT;
            SimpleDateFormat dateFormat=new SimpleDateFormat(format);
            if(isNotEmpty(str)){
                dateFormat.setLenient(false);
                date=dateFormat.parse(str);

            }
        } catch (Exception ex){
            throw  new CustomException(50013,"字符串转换默认格式的时间类型失败！",ex);
        }

        return date;
    }

    /**
     * 根据字符串符合日期格式，转换为对应格式时间类型
     * @param str
     * @return
     */
    public static Date stringToDateForFormat(String str){
        Date date=null;
        try {
            String  format=getDateFormat(str)  ;
            SimpleDateFormat dateFormat=new SimpleDateFormat(format);
            if(isNotEmpty(str)){
                dateFormat.setLenient(false);
                date=dateFormat.parse(str);

            }
        } catch (Exception ex){
            throw  new CustomException(50014,"字符串转换时间类型失败！",ex);
        }

        return date;
    }

    /**
     *获得字符串符合的日期格式
     * @param s
     * @return
     */
    public static String getDateFormat(String s){
        String  format= DateUtil.DateFormat_DEFAULT;
        Pattern p=Pattern.compile("^\\d{4}(\\-)\\d{1,2}\\1\\d{1,2}(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        if(p.matcher(s).matches()) {
            p=Pattern.compile("^\\d{4}(\\-)\\d{1,2}\\1\\d{1,2}$");
            if(p.matcher(s).matches()){
                format= DateUtil.DateFormat_DATE_DEFAULT;
            } else {
                format= DateUtil.DateFormat_DEFAULT;
            }
        }
        p=Pattern.compile("^\\d{4}(\\/)\\d{1,2}\\1\\d{1,2}(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        if(p.matcher(s).matches()){
            p=Pattern.compile("^\\d{4}(\\/)\\d{1,2}\\1\\d{1,2}$");
            if(p.matcher(s).matches()){
                format= DateUtil.DateFormat__DATE_ONE;
            } else {
                format= DateUtil.DateFormat_ONE;
            }

        }
        p=Pattern.compile("^\\d{4}(\\.)\\d{1,2}\\1\\d{1,2}(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        if(p.matcher(s).matches()){
            p=Pattern.compile("^\\d{4}(\\.)\\d{1,2}\\1\\d{1,2}$");
            if(p.matcher(s).matches()){
                format= DateUtil.DateFormat_DATE_TWO;
            } else {
                format= DateUtil.DateFormat_TWO;
            }

        }
        p=Pattern.compile("^\\d{4}\\d{1,2}\\d{1,2}((((0?[0-9])|([1-2][0-3]))([0-5]?[0-9])((\\s)|(([0-5]?[0-9])))))?$");
        if(p.matcher(s).matches()){
            p=Pattern.compile("^\\d{4}\\d{1,2}\\d{1,2}$");
            if(p.matcher(s).matches()){
                format=DateUtil.DateFormat_DATE_THREE;
            } else {
                format=DateUtil.DateFormat_THREE ;
            }

        }

        return format;
    }

}
