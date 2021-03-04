package com.baiyang.oms.rest.wsdl.xml.common;

public class EdiBizException extends RuntimeException {

	private static final long serialVersionUID = -5875371379845226068L;

	/**
	 * 数据库操作,insert返回0
	 */
	public static final EdiBizException DB_INSERT_RESULT_0 = new EdiBizException(90040001, "数据库操作,insert返回0");

	/**
	 * 数据库操作,update返回0
	 */
	public static final EdiBizException DB_UPDATE_RESULT_0 = new EdiBizException(90040002, "数据库操作,update返回0");

	/**
	 * 数据库操作,selectOne返回null
	 */
	public static final EdiBizException DB_SELECTONE_IS_NULL = new EdiBizException(90040003, "数据库操作,selectOne返回null");

	/**
	 * 数据库操作,list返回null
	 */
	public static final EdiBizException DB_LIST_IS_NULL = new EdiBizException(90040004, "数据库操作,list返回null");

	/**
	 * Token 验证不通过
	 */
	public static final EdiBizException TOKEN_IS_ILLICIT = new EdiBizException(90040005, "Token 验证非法");
	/**
	 * 会话超时　获取session时，如果是空，throws 下面这个异常 拦截器会拦截爆会话超时页面
	 */
	public static final EdiBizException SESSION_IS_OUT_TIME = new EdiBizException(90040006, "会话超时");
	
	
	/**
	 * 10010001=要监控的目录不存在
	 */
	public static final EdiBizException MONITOR_DIRECTORY_DOES_NOT_EXIST = new EdiBizException(10010001, "要监控的目录不存在");
	
	
	/**
	 * 10010002=edi文件不存在 
	 */
	public static final EdiBizException EDIFILE_DOES_NOT_EXIST = new EdiBizException(10010002, "edi文件不存在");
	
	
	/**
	 * 10020001=加载证书文件出错
	 */
	public static final EdiBizException LOAD_CERTIFICATE_IS_ERROR = new EdiBizException(10020001, "加载证书文件出错");
	
	/**
	 * 异常信息
	 */
	protected String msg;

	/**
	 * 具体异常码
	 */
	protected int code;

	public EdiBizException(int code, String msgFormat, Object... args) {
		super(String.format(msgFormat, args));
		this.code = code;
		this.msg = String.format(msgFormat, args);
	}

	public EdiBizException() {
		super();
	}

	public String getMsg() {
		return msg;
	}

	public int getCode() {
		return code;
	}

	/**
	 * 实例化异常
	 * 
	 * @param msgFormat
	 * @param args
	 * @return
	 */
	public EdiBizException newInstance(String msgFormat, Object... args) {
		return new EdiBizException(this.code, msgFormat, args);
	}

	public EdiBizException(String message, Throwable cause) {
		super(message, cause);
	}

	public EdiBizException(Throwable cause) {
		super(cause);
	}

	public EdiBizException(String message) {
		super(message);
	}
}