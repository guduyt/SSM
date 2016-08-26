package com.yt.commons;

/**
 * ContextType
 *定义应用程序使用的常量，任意实现此接口的类，可以引用该常量
 * @author yitao
 * @version 1.0.0
 * @date 2016/7/22 16:34
 */
public interface ContextType {

    public static final Integer bufferMaxSize=2048;


    /*文件类型*/
    public static final String BMP="image/bmp";
    public static final String GIF="image/gif";
    public static final String JPEG="image/jpeg";
    public static final String TIFF="image/tiff";
    public static final String DCX="image/x-dcx";
    public static final String PCX="image/x-pcx";
    public static final String HTML="text/html";
    public static final String TXT="text/plain";
    public static final String XML="text/xml";
    public static final String JSON="application/json";
    public static final String AFP="application/afp";
    public static final String PDF="application/pdf";
    public static final String RTF="application/rtf";
    public static final String MSWORD="application/msword";
    public static final String MSEXCEL="application/vnd.ms-excel";
    public static final String MSPOWERPOINT="application/vnd.ms-powerpoint";
    public static final String WORDPERFECT="application/wordperfect5.1";
    public static final String WORDPRO="application/vnd.lotus-wordpro";
    public static final String VISIO="application/vnd.visio";
    public static final String FRAMEMAKER="application/vnd.framemaker";
    public static final String LOTUS123="application/vnd.lotus-1-2-3";
}
