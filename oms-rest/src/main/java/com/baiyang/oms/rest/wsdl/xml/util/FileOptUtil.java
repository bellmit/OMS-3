package com.baiyang.oms.rest.wsdl.xml.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.PrefixFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by DELL on 2018/5/8.
 */
public class FileOptUtil {

    /**
     * 创建文件目录对象
     * @param fileDir 文件目录路径
     * @return
     */
    public static File createFileDir(String fileDir)  {
        File file=new File(fileDir);
        if(!file.isDirectory()){//判断是否是一个目录
            throw new RuntimeException();
        }else{
            if(!file.exists()){
                throw new RuntimeException();
            }
        }
        return file;
    }

    /**
     *通配符过滤目录下的文件
     * @param fileDir 文件目录
     * @param regString 正则表达式
     * @return
     */
    public static File[] wildcardFilter(String fileDir,String regString){
        FileFilter fileFilter = new WildcardFileFilter(regString);
        File dir=createFileDir(fileDir);
        File[]  files = dir.listFiles(fileFilter);
        return files;
    }

    /**
     *  过滤文件后缀名，比如过滤 .java后缀
     *
     * @param fileDir 文件目录
     * @param suffix  后缀名 如 .xml
     */
    public static File[] suffixFilter(String fileDir,String suffix){
        File dir=createFileDir(fileDir);
        FileFilter fileFilter =new SuffixFileFilter(suffix);
        File[]  files  = dir.listFiles(fileFilter);
        return files;
    }

    /**
     * 使用正则表达式过滤
     * @param fileDir 文件目录
     * @param regexStr 正则表达式 如："^Bas.*.java“
     * @return
     */
    public static  File[] regexFilter(String fileDir,String regexStr){
        File dir=createFileDir(fileDir);
        FileFilter fileFilter = new RegexFileFilter(regexStr);
        File[] files = dir.listFiles(fileFilter);
        return files;
    }

    /**
     * 过滤文件前缀为prefixStr 的文件
     * @param fileDir 文件目录
     * @param prefixStr 文件前缀
     * @return
     */
    public  static  File[] prefixFilter(String fileDir,String prefixStr){
        File dir=createFileDir(fileDir);
        FileFilter fileFilter =  new PrefixFileFilter(prefixStr);
        File[] files = dir.listFiles(fileFilter);
        return files;
    }

    /**
     * 文件大小
     * @param file 如果为文件，则返回文件大小，如果为目录 返回目录大小
     * @return
     */
    public static  long   fileSize(File file){
        return  FileUtils.sizeOf(file);
    }

    /**
     * 获取文件或目录的大小（带有单位如 ：MB）
     * @param file 如果为文件，则返回文件大小，如果为目录 返回目录大小
     * @return
     */
    public static String  fileSizeByUnit(File file){
        return    FileUtils.byteCountToDisplaySize(fileSize(file));
    }


    public static void main(String[] args) {
       /* File[]  files=FileOperator.suffixFilter("D:\\MyIDEA_Respository\\edu-common\\src\\main\\java\\wusc\\edu\\common\\entity",".java");
        for (File file:files) {

            System.out.println(file.getName());
        }*/

        //System.out.println(FileUtils.byteCountToDisplaySize(100000000));

    }
}
