package com.yt.commons.utils;

import com.yt.commons.IUtil;
import com.yt.commons.exceptions.CustomException;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * ExcelUtils
 *
 * @author yitao
 * @version 1.0.0
 * @date 2016/7/22 9:32
 */
public class ExcelUtil implements IUtil {

    /**
     * 半角转全角
     * @param s
     * @return 全角字符串
     */
    public static String toSBC(String s) {
        if(null==s|| s.length()<=0)
            return null;

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                chars[i] = '\u3000';
            } else if (chars[i] < '\177') {
                chars[i] = (char) (chars[i] + 65248);
            }
        }
        return new String(chars);
    }

    /**
     * 全角转半角
     * @param s
     * @return 半角字符串
     */
    public static String toDBC(String s){
        if(null==s|| s.length()<=0)
            return null;

        char[] chars=s.toCharArray();
        for (int i=0;i<chars.length;i++){
             if (chars[i]=='\u3000') {
                 chars[i]=' ';
             }
             else  if(chars[i]>'\uff00' && chars[i]<'\uff5f'){
                 chars[i]=(char)(chars[i]-65248) ;
             }
        }
        return  new String(chars);
    }

    /**
     *   根据Excel文件绝对地址读取文件内容
     * @param realPath  Excel文件绝对地址
     * @param beginRow 开始读取数据行
     * @param sheetNum Excel的sheet位置
     * @return
     */
    public static List<Object[]> getExcelData(String realPath,int beginRow,int sheetNum){

        List<Object[]> list=new ArrayList<Object[]>();
        FileInputStream inputStream=null;
        try {
            inputStream =new FileInputStream(realPath) ;
            Workbook workbook=null;
            if(realPath.endsWith("xlsx")){
                workbook=new XSSFWorkbook(inputStream) ;
            }else {
                workbook=new HSSFWorkbook(inputStream) ;
            }
            Sheet sheet=workbook.getSheetAt(sheetNum);
            int totleRow= sheet.getLastRowNum();
            if(beginRow<0 || beginRow>totleRow)
                throw new RuntimeException("读取excel数据开始于:"+beginRow+"行,该行数据不存在！") ;
            Row row;
            Cell cell;
            int countCell=0;

            for(int i=beginRow;i<=totleRow;i++){
                row=sheet.getRow(i);
                countCell=row.getLastCellNum();
                if(countCell<1){
                    continue;
                }
                Object[] objects=new Object[countCell];
                int objCount=0;
                for (int j=0;j<countCell;j++){
                    cell=row.getCell(j);
                    if(null==cell){
                        objects[j]=null;
                        objCount++;
                        continue;
                    }
                    objects[j]=getValue(cell);
                }
                if(objCount==countCell){
                    break;
                }
                list.add(objects) ;
            }
        }
        catch (FileNotFoundException ex){
             throw new CustomException(50016,"找不到Excel文件！");
        }catch (Exception ex){
            throw new CustomException(50017,"读取Excel文件异常！",ex);
        }  finally {
            try {
                if(null!=inputStream)
                    inputStream.close();
            } catch (IOException ex){
                ex.printStackTrace();
            }

        }

        return  list;
    }

    /**
     * 根据Excel单元格的数据类型，获得相应类型的数据
     * @param cell
     * @return
     */
    private static Object getValue(Cell cell){
        String value;
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {//如果是date类型则 ，获取该cell的date值
                    Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    value = format.format(date);
                }else {// 纯数字
                    BigDecimal big=new BigDecimal(cell.getNumericCellValue());//获取高精度的数值，实际需要进行转换
                    value = big.toString();
                    if(null!=value && !"".equals(value.trim())){//解决1234.0  去掉后面的.0
                        String[] item = value.split(".");
                        if(1<item.length && "0".equals(item[1])){
                            value=item[0];
                        }
                    }
                }
                break;
            case Cell.CELL_TYPE_STRING: //字符串类型
                value = cell.getStringCellValue().toString();
                break;
            case Cell.CELL_TYPE_FORMULA:// 公式类型
                value = String.valueOf(cell.getNumericCellValue());
                if (value.equals("NaN")) {// 如果获取的数据值为非法值,则转换为获取字符串
                    value = cell.getStringCellValue().toString();
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN: // 布尔类型
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_BLANK:// 空值
                value = "";
                log.error("excel出现空值！");
                break;
            case Cell.CELL_TYPE_ERROR:// 故障
                value = String.valueOf(cell.getErrorCellValue());
                log.error("excel出现故障！");
                break;
            default:
                value = cell.getStringCellValue().toString();
        }
        return (Object)value;
    }

    /**
     * 根据传入对象，导入数据，导入数据字段集，初始化对象
     * @param t
     * @param fields
     * @param objects
     * @param <T>
     * @return
     */
    public static <T>T getObjectValue(T t,String[] fields,Object[] objects) {
        Map<String,Object> map=new HashMap<>();
        for(int i=0;i<fields.length;i++){
            map.put(fields[i],objects[i]);
        }
        try {
            Class<?>  tClass=t.getClass();
            Field[] tFields=tClass.getDeclaredFields() ;
            for(int i=0;i<tFields.length;i++){
                String fieldName= tFields[i].getName();

                Class<?> fieldType= tFields[i].getType();
                String fieldTypeName= fieldType.getTypeName();
                Object o=map.get(fieldName);
                if(null==o){
                    continue;
                }
                Method setMethod=tClass.getMethod(getSetMethodName(fieldName),new Class<?>[]{fieldType});
                setMethod.setAccessible(true);

                if("java.lang.String".equals(fieldTypeName)) {
                    setMethod.invoke(t,valueToString(o));
                } else if ("java.lang.Integer".equals(fieldTypeName)){
                    setMethod.invoke(t,Integer.valueOf(valueToInt(o)));
                } else if ("java.lang.Double".equals(fieldTypeName)){
                    setMethod.invoke(t,Double.valueOf(valueToNumber(o)));
                }else if ("java.lang.Long".equals(fieldTypeName)){
                    setMethod.invoke(t,Long.valueOf(valueToNumber(o)));
                }else if ("java.lang.Float".equals(fieldTypeName)){
                    setMethod.invoke(t,Float.valueOf(valueToNumber(o)));
                }else if ("java.lang.Boolean".equals(fieldTypeName)){
                    setMethod.invoke(t, Boolean.valueOf(valueToString(o)));
                }else if ("java.lang.Byte".equals(fieldTypeName)){
                    setMethod.invoke(t,Byte.valueOf(valueToInt(o)));
                }else if ("java.lang.Short".equals(fieldTypeName)){
                    setMethod.invoke(t,Short.valueOf(valueToInt(o)));
                }else if ("java.math.BigDecimal".equals(fieldTypeName)){
                    setMethod.invoke(t,new BigDecimal(Float.valueOf(valueToNumber(o))));
                }else if ("java.util.Date".equals(fieldTypeName)){
                    setMethod.invoke(t, valueToDate(o));
                }

            }
        } catch (NoSuchMethodException ex){
            throw new CustomException(50018,"读取数据错误！",ex) ;
        }catch (Exception ex){
            throw new CustomException(50019,"读取数据错误！",ex) ;
        }

        return  t;
    }

    /**
     * 根据传入对象，导入数据，初始化对象 ，对象属性的顺序和导入数据字段的顺序要求一致。
     * @param t
     * @param objects
     * @param <T>
     * @return
     */
    public static <T>T getObjectValue(T t,Object[] objects){

        try {
            Class<?>  tClass=t.getClass();
            Field[] tFields=tClass.getDeclaredFields() ;
            for(int i=0;i<tFields.length;i++){
                String fieldName= tFields[i].getName();

                Class<?> fieldType= tFields[i].getType();
                String fieldTypeName= fieldType.getTypeName();
                Object o=objects[i];
                if(null==o){
                    continue;
                }
                Method setMethod=tClass.getMethod(getSetMethodName(fieldName),new Class<?>[]{fieldType});
                setMethod.setAccessible(true);

                if("java.lang.String".equals(fieldTypeName)) {
                    setMethod.invoke(t,valueToString(o));
                } else if ("java.lang.Integer".equals(fieldTypeName)){
                    setMethod.invoke(t,Integer.valueOf(valueToInt(o)));
                } else if ("java.lang.Double".equals(fieldTypeName)){
                    setMethod.invoke(t,Double.valueOf(valueToNumber(o)));
                }else if ("java.lang.Long".equals(fieldTypeName)){
                    setMethod.invoke(t,Long.valueOf(valueToNumber(o)));
                }else if ("java.lang.Float".equals(fieldTypeName)){
                    setMethod.invoke(t,Float.valueOf(valueToNumber(o)));
                }else if ("java.lang.Boolean".equals(fieldTypeName)){
                    setMethod.invoke(t, Boolean.valueOf(valueToString(o)));
                }else if ("java.lang.Byte".equals(fieldTypeName)){
                    setMethod.invoke(t,Byte.valueOf(valueToInt(o)));
                }else if ("java.lang.Short".equals(fieldTypeName)){
                    setMethod.invoke(t,Short.valueOf(valueToInt(o)));
                }else if ("java.math.BigDecimal".equals(fieldTypeName)){
                    setMethod.invoke(t,new BigDecimal(Float.valueOf(valueToNumber(o))));
                }else if ("java.util.Date".equals(fieldTypeName)){
                    setMethod.invoke(t, valueToDate(o));
                }

            }
        } catch (NoSuchMethodException ex){
            throw new CustomException(50018,"读取数据错误！",ex) ;
        }catch (Exception ex){
            throw new CustomException(50019,"读取数据错误！",ex) ;
        }

        return  t;
    }

    public static Boolean isMatchType(String fieldType,Object o) {
        boolean isMatch=false;

        try {
            switch (fieldType) {
                case "java.lang.String":
                    notNullValueToString(o);
                    isMatch=true;
                    break;
                case "java.lang.Integer":
                    Integer.parseInt(notNullValueToString(o));
                    isMatch=true;
                    break;
                case "java.lang.Long":
                    Long.parseLong(notNullValueToString(o));
                    isMatch=true;
                    break;
                case "java.lang.Float":
                    Float.parseFloat(notNullValueToString(o));
                    isMatch=true;
                    break;
                case "java.lang.Double":
                    Double.parseDouble(notNullValueToString(o));
                    isMatch=true;
                    break;
                case "java.lang.Boolean":
                    Boolean.parseBoolean(notNullValueToString(o));
                    isMatch=true;
                    break;
                case "java.lang.Byte":
                    Byte.parseByte(notNullValueToString(o));
                    isMatch=true;
                    break;
                case "java.lang.Short":
                    Short.parseShort(notNullValueToString(o));
                    isMatch=true;
                    break;
                case "java.math.BigDecimal":BigDecimal.valueOf(Double.valueOf(notNullValueToString(o)));
                    isMatch=true;
                    break;
                case "java.util.Date":
                    isMatch=isDate(o);
                    break;
                default:notNullValueToString(o);
                    isMatch=true;
            }
        }catch (Exception ex){
            isMatch=false;
        }
        return isMatch ;
    }

    private static String getSetMethodName(String fildeName){
        byte[] items = fildeName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        String methodName="set"+ new String(items);
        return methodName;
    }

    private static String valueToString(Object o){
         if(null==o || null==o.toString() || o.toString().length()==0){
             return  "";
         }
        return o.toString();
    }

    private static String notNullValueToString(Object o){
        if(null==o || null==o.toString() || o.toString().length()==0){
            throw new CustomException(50020,"数据不能为空值！") ;
        }
        return o.toString();
    }

    private static String valueToNumber(Object o){
        if(null==o || null==o.toString() || o.toString().length()==0){
            return  "0";
        }
        return o.toString();
    }
    private static String valueToInt(Object o) {
        String intString=valueToNumber(o);
        if(intString.contains(".")){
            intString=intString.substring(0,intString.indexOf("."));
        }
        return  intString;
    }

    private static Date valueToDate(Object o){
        String dataStr=valueToString(o);
        Pattern p=Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\.]?((((0?[13578])|(1[02]))[\\-\\/\\.]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\.]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\.]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\.]?((((0?[13578])|(1[02]))[\\-\\/\\.]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\.]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\.]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$", Pattern.CASE_INSENSITIVE|Pattern.MULTILINE);
         Date date=null;
        if(!dataStr.isEmpty() && p.matcher(dataStr).matches()){
            date= DateUtil.stringToDate(dataStr) ;
        }
        else {
            date=(Date)o;
        }
        return date;
    }

    private static Boolean isDate(Object o){
        String dataStr=notNullValueToString(o);
        Pattern p=Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\.]?((((0?[13578])|(1[02]))[\\-\\/\\.]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\.]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\.]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\.]?((((0?[13578])|(1[02]))[\\-\\/\\.]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\.]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\.]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$", Pattern.CASE_INSENSITIVE|Pattern.MULTILINE);

        return p.matcher(dataStr).matches();
    }
}
