package com.yt.commons.utils;

import com.yt.commons.ContextType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * UploadFileUtils
 *
 * @author yitao
 * @version 1.0.0
 * @date 2016/7/22 11:00
 */
public class FileUtils implements ContextType {

    public static final Logger log= LoggerFactory.getLogger(FileUtils.class);

    /**
     * web项目中，得到web的绝对路径
     * @param request
     * @return
     */
    public static String getWebPath(HttpServletRequest request){
        return request.getSession().getServletContext().getRealPath("/") ;
    }

    /**
     *  web项目中，根据相对路径得到文件的绝对路径
     * @param request
     * @param path
     * @return
     */
    public static String getRealPath(HttpServletRequest request,String path){
        return request.getSession().getServletContext().getRealPath(path) ;
    }

    /**
     * web项目中，判断是否是绝对路径，是绝对路径返回true
     * @param request
     * @param path
     * @return
     */
    public static Boolean isRealPath(HttpServletRequest request,String path){
        if(null==path)
            throw new  RuntimeException("文件路径不能为空！") ;

        if( path.indexOf(request.getSession().getServletContext().getRealPath("/")) ==0){
            return true;
        }
        return false;
    }

    /**
     * web项目中，保存上传文件到服务器
     * @param request
     * @return 服务器上文件保存绝对路径地址
     */
    public static  String getUploadFile(HttpServletRequest request) {
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext()) ;

        if(null==multipartResolver)
            throw new  RuntimeException("上传文件解析异常，在HttpServletRequest请求找不到MultipartResolver解析文件！");

        if(multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest)request;

            Iterator<String> iterator=multipartHttpServletRequest.getFileNames();
            while(iterator.hasNext()){
                MultipartFile file=  multipartHttpServletRequest.getFile(iterator.next());
                if(null==file || file.isEmpty())
                    throw new  RuntimeException("没有找到上传文件！") ;

                String fileName=file.getOriginalFilename() ;
                if(null==fileName ||fileName.isEmpty())
                    throw new  RuntimeException("上传文件不存在！");

                String fileType=fileName.substring(fileName.lastIndexOf("."));
                String name=fileName.substring(0,fileName.lastIndexOf("."));
                String path="/uploadFiles"+ File.separator+name+ UUID.randomUUID()+fileType ;
                String realPath=request.getSession().getServletContext().getRealPath(path) ;

                try {
                    File localFile=new File(realPath)  ;
                    file.transferTo(localFile);
                    return realPath;
                }   catch (Exception ex){
                    log.error("保存文件失败，详细错误：s%"+ex);
                    throw new  RuntimeException("保存文件失败！");
                }
            }
        }
        return null;
    }

    /**
     * web项目中，保存上传文件到服务器，指定文件保存路径和文件名
     * @param path
     * @param fileName
     * @param request
     * @return
     */
    public static  String getUploadFile(String path,String fileName,HttpServletRequest request) {
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext()) ;

        if(null==multipartResolver)
            throw new  RuntimeException("上传文件解析异常，在HttpServletRequest请求找不到MultipartResolver解析文件！");

        if(multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest)request;

            Iterator<String> iterator=multipartHttpServletRequest.getFileNames();
            while(iterator.hasNext()){
                MultipartFile file=  multipartHttpServletRequest.getFile(iterator.next());
                if(null==file || file.isEmpty())
                    throw new  RuntimeException("没有找到上传文件！") ;

                if(null==fileName)
                    fileName=file.getOriginalFilename() ;
                if(null==fileName ||fileName.isEmpty())
                    throw new  RuntimeException("上传文件不存在！");

                String fileType=fileName.substring(fileName.lastIndexOf("."));
                String name=fileName.substring(0,fileName.lastIndexOf("."));
                if(null==path)
                    path="/uploadFiles"+ File.separator;
                path=path+name+fileType;
                String realPath=request.getSession().getServletContext().getRealPath(path) ;

                try {
                    File localFile=new File(realPath)  ;
                    file.transferTo(localFile);
                    return realPath;
                }   catch (Exception ex){
                    log.error("保存文件失败，详细错误：s%"+ex);
                    throw new  RuntimeException("保存文件失败！");
                }
            }
        }
        return null;
    }

    /**
     * web项目中，多个上传文件保存到服务器
     * @param request
     * @return 服务器上文件保存绝对路径地址的集合
     */
    public static  String[] getUploadFiles(HttpServletRequest request) {
        List<String> list=new ArrayList<>();
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext()) ;

        if(null==multipartResolver)
            throw new  RuntimeException("上传文件解析异常，在HttpServletRequest请求找不到MultipartResolver解析文件！");

        if(multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest)request;

            Iterator<String> iterator=multipartHttpServletRequest.getFileNames();
            while(iterator.hasNext()){
                MultipartFile file=  multipartHttpServletRequest.getFile(iterator.next());
                if(null==file || file.isEmpty())
                    throw new  RuntimeException("没有找到上传文件！") ;

                String fileName=file.getOriginalFilename() ;
                if(null==fileName ||fileName.isEmpty())
                    throw new  RuntimeException("上传文件不存在！");

                String fileType=fileName.substring(fileName.lastIndexOf("."));
                String name=fileName.substring(0,fileName.lastIndexOf("."));
                String path="/uploadFiles"+ File.separator+name+ UUID.randomUUID()+fileType ;
                String realPath=request.getSession().getServletContext().getRealPath(path) ;

                try {
                    File localFile=new File(realPath)  ;
                    file.transferTo(localFile);
                    list.add(realPath);
                }   catch (Exception ex){
                    log.error("保存文件失败，详细错误：s%"+ex);
                    throw new  RuntimeException("保存文件失败！");
                }
            }
        }

        return (String[])list.toArray();
    }

    /**
     * 根据绝对路径删除上传的文件
     * @param realPath
     * @return 操作成功返回true
     */
    public static Boolean deleteUploadFile(String realPath){
        try {
            File localFile=new File(realPath);
            if(localFile.exists() && localFile.canWrite()) {
                localFile.delete();
                return true;
            }
        } catch (Exception ex){
            log.error("删除文件失败，详细错误：s%"+ex);
               throw new RuntimeException("删除文件失败！");
        }

        return false ;
    }

    /**
     * web项目中，根据文件在web下面的相对路径下载文件
     * @param path
     * @param request
     * @param response
     */
    public static void downloadFile(String path,HttpServletRequest request, HttpServletResponse response){
        BufferedInputStream inputStream=null;
        BufferedOutputStream outputStream=null;

        String realPath=request.getSession().getServletContext().getRealPath(path) ;
        if(null==realPath || realPath.isEmpty())
            throw new  RuntimeException("文件下载路径不存在！") ;

        try {
            File localFile=new File(realPath);
            if(!localFile.exists())
                throw new  RuntimeException("下载文件不存在！") ;

            String name=localFile.getName();
            InputStream stream= new FileInputStream(localFile) ;
            response.setContentType(request.getSession().getServletContext().getMimeType(name));  //设置文件MIME类型
            response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(name,"utf-8"));

            ServletOutputStream servletOutputStream=response.getOutputStream();
            inputStream=new BufferedInputStream(stream);
            outputStream=new BufferedOutputStream(servletOutputStream);

            byte[] buff=new byte[FileUtils.bufferMaxSize] ;
            int bytesRead=inputStream.read(buff,0,buff.length);
            while (-1!=bytesRead){
                outputStream.write(buff,0,bytesRead);
                bytesRead=inputStream.read(buff,0,buff.length);
            }
        } catch (Exception ex){
            log.error("下载文件失败，详细错误：s%"+ex);
            throw new RuntimeException("下载文件失败！");
        }   finally {
            if(null!=inputStream){
                try {
                    inputStream.close();
                }   catch (IOException ex){
                     ex.printStackTrace();}
            }

            if(null!=outputStream){
                try {
                    outputStream.close();
                }   catch (IOException ex){
                    ex.printStackTrace();}
            }
        }
    }

    /**
     * web项目中，根据文件在web下面的相对路径下载文件,并且指定文件名称
     * @param path
     * @param fileName
     * @param request
     * @param response
     */
    public static void downloadFile(String path,String fileName,HttpServletRequest request, HttpServletResponse response){
        BufferedInputStream inputStream=null;
        BufferedOutputStream outputStream=null;

        String realPath=request.getSession().getServletContext().getRealPath(path) ;
        if(null==realPath || realPath.isEmpty())
            throw new  RuntimeException("文件下载路径不存在！") ;

        try {
            File localFile=new File(realPath);
            if(!localFile.exists())
                throw new  RuntimeException("下载文件不存在！") ;

            String name=localFile.getName();
            InputStream stream= new FileInputStream(localFile) ;
            response.setContentType(request.getSession().getServletContext().getMimeType(name));  //设置文件MIME类型
            response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName,"utf-8"));

            ServletOutputStream servletOutputStream=response.getOutputStream();
            inputStream=new BufferedInputStream(stream);
            outputStream=new BufferedOutputStream(servletOutputStream);

            byte[] buff=new byte[FileUtils.bufferMaxSize] ;
            int bytesRead=inputStream.read(buff,0,buff.length);
            while (-1!=bytesRead){
                outputStream.write(buff,0,bytesRead);
                bytesRead=inputStream.read(buff,0,buff.length);
            }
        } catch (Exception ex){
            log.error("下载文件失败，详细错误：s%"+ex);
            throw new RuntimeException("下载文件失败！");
        }   finally {
            if(null!=inputStream){
                try {
                    inputStream.close();
                }   catch (IOException ex){
                    ex.printStackTrace();}
            }

            if(null!=outputStream){
                try {
                    outputStream.close();
                }   catch (IOException ex){
                    ex.printStackTrace();}
            }
        }
    }


    /**
     * 根据文件目录和文件名获取文件。
     * @param directory
     * @param names
     * @return
     */
    public static File getFile(File directory, String... names) {
        if (directory == null) {
            throw new NullPointerException(
                    "文件目录不能为空！");
        }
        if (names == null) {
            throw new NullPointerException("文件名不能为空！");
        }
        File file = directory;
        for (String name : names) {
            file = new File(file, name);
        }
        return file;
    }

    /**
     * 根据文件名获取文件。
     * @param names
     * @return
     */
    public static File getFile(String... names) {
        if (names == null) {
            throw new NullPointerException("文件名不能为空！");
        }
        File file = null;
        for (String name : names) {
            if (file == null) {
                file = new File(name);
            }
            else {
                file = new File(file, name);
            }
        }
        return file;
    }

    /**
     * 打开一个读操作文件流
     * @param file
     * @return
     * @throws IOException
     */
    public static FileInputStream openInputStream(File file){
        try {
            if (file.exists()) {
                if (file.isDirectory()) {
                    throw new IOException("文件 '" + file
                            + "'存在，但是是一个目录！");
                }
                if (file.canRead() == false) {
                    throw new IOException("文件 '" + file + "' 不能进行读操作！");
                }
            }
            else {
                throw new FileNotFoundException("文件 '" + file
                        + "' 不存在！");
            }
            return new FileInputStream(file);
        } catch (Exception ex){
            log.error("打开读操作文件流异常，详细错误：s%"+ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    /**
     * 打开一个写操作文件流,append表示是否向文件追加内容
     * @param file
     * @param append
     * @return
     * @throws IOException
     */
    public static FileOutputStream openOutputStream(File file, boolean append){
        try {
            if (file.exists()) {
                if (file.isDirectory()) {
                    throw new IOException("文件 '" + file
                            + "' 存在，但是是一个目录！");
                }
                if (file.canWrite() == false) {
                    throw new IOException("文件 '" + file
                            + "' 不能进行写操作！");
                }
            }
            else {
                File parent = file.getParentFile();
                if (parent != null) {
                    if (!parent.mkdirs() && !parent.isDirectory()) {
                        throw new IOException("目录 '" + parent
                                + "' 不存在，需要创建！");
                    }
                }
            }
            return new FileOutputStream(file, append);

        } catch (Exception ex){
            log.error("打开写操作文件流异常，详细错误：s%"+ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    /**
     * 打开一个写操作文件流
     * @param file
     * @return
     * @throws IOException
     */
    public static FileOutputStream openOutputStream(File file){
        return openOutputStream(file, false);
    }


    /**
     * 判断文件是否存在
     * @param fileName
     * @return
     */
    public static boolean exist(String fileName) {
        return new File(fileName).isFile();
    }

    /**
     * Windows下的文件路径转换为Linux文件路径
     * @param filePath
     * @return
     */
    public static String toUNIXpath(String filePath) {
        return filePath.replace('\\', '/');
    }

}
