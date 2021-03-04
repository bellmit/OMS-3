package com.baiyang.oms.core.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * 读取Properties属性文件
 * @author wanghai
 *
 */
public class ReadProperties {
//	private static final Logger log = Logger.getLogger(ReadProperties.class);
	
//	默认配置文件
	private static final String defaultConfFile="conf.properties";
	
	private ReadProperties(){
		
	}
	
	
	private static class SingletonHolder{
		private static ReadProperties readpro=new ReadProperties();
	} 
	
	
	/**
	 * 获取实例
	 * @return
	 */
	public static ReadProperties getInstance(){
		return SingletonHolder.readpro;
	}
	
	
	/**
	 * 读取属性文件
	 * @param path
	 * @return
	 */
	private   Properties getReadPropertyFile(String path){
		Properties pro=null;
		try {
			pro=PropertiesLoaderUtils.loadProperties(new ClassPathResource(path));
		} catch (IOException e) {
//			log.info(e.getMessage());
		}
		return pro;
	}
	
	/**
	 * 获取具体的属性值
	 * @param path 属性文件所在路径
	 * @param proKey
	 * @return
	 */
	public   String getValue(String path,String proKey){
		Properties pro=getReadPropertyFile(path);
		return pro.getProperty(proKey);
	}
	/**
	 * 获取具体的属性值,属性文件默认为：conf.properties
	 * @param proKey
	 * @return
	 */
	public   String getValue(String proKey){
		Properties pro=getReadPropertyFile(defaultConfFile);
		return pro.getProperty(proKey);
	}
	
}
