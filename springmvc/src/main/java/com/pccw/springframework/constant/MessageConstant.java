package com.pccw.springframework.constant;

public class MessageConstant {
	public static String KEY_SYSTEM_ERROR = "OA-E-0500";
	public static String DEFAULT_SYSTEM_ERROR_MSG = "系统错误，请稍后再试";
	
	public static String KEY_ERROR_EMAIL_TO_REQUIRED = "OA-E-0030";
	public static String DEFAULT_ERROR_MSG_EMAIL_TO_REQUIRED = "请输入收件人邮箱.";
	
	public static String KEY_ERROR_INVALID_EMAIL_TO = "OA-E-0301";
	public static String DEFAULT_ERR_MSG_INVALID_EMAIL_TO = "您输入的收件人邮箱格式不正确，请重新输入.";
	
	public static String KEY_ERROR_NOT_EXIST_EMAIL_TO = "OA-E-0302";
	public static String DEFAULT_ERR_MSG_NOT_EXIST_EMAIL_TO = "不存在该收件人，请查询后重新输入";
	
	public static String KEY_ERROR_INVALID_EMAIL_CC = "OA-E-0303";
	public static String DEFAULT_ERR_MSG_IVALID_EMAIL_CC = "您输入的抄送人邮箱格式不正确，请重新输入";
	
	public static String KEY_ERROR_NOT_EXIST_EMAIL_CC = "OA-E-0304";
	public static String DEFAULT_ERR_MSG_NOT_EXIST_EMAIL_CC = "不存在该抄送人邮箱，请查询后重新输入";
	
	public static String KEY_ERROR_INVALID_EMAIL_TITLE_LENGTH = "OA-E-0305";
	public static String DEFAULT_ERR_MSG_INVALID_EMAIL_TITLE_LENGTH = "邮件主题超过最大长度";
	
	public static String KEY_ERROR_INVALID_EMAIL_CONTENT_LENGTH = "OA-E-0306";
	public static String DEFAULT_ERR_MSG_INVALID_EMAIL_CONTENT_LENGTH = "邮件内容超过最大长度";
	
	public static String KEY_ERROR_LOGIN_ID_REQUIRED = "OA-E-0501";
	public static String DEFAULT_ERR_MSG_LOGIN_ID_REQUIRED = "请输入用户登录名";
	
	public static String KEY_ERROR_INVALID_LOGIN_ID = "OA-E-0502";
	public static String DEFAULT_ERR_MSG_INVALID_LOGIN_ID = "您输入的用户登录名不正确 (用户登录名必须只包含字母，数字或下划线)";
	
	public static String KEY_ERROR_EMAIL_REQUIRED = "OA-E-0503";
	public static String DEFAULT_ERR_MSG_EMAIL_REQUIRED = "请输入用户邮箱";
	
	public static String KEY_ERROR_INVALID_EMAIL = "OA-E-0504";
	public static String DEFAULT_ERR_MSG_INVALID_EMAIL = "您输入的邮箱格式不正确";
	
	public static String KEY_ERROR_EXISTED_EMAIL = "OA-E-0505";
	public static String DEFAULT_ERR_MSG_EXISTED_EMAIL = "该用户邮箱已存在，请重新输入";
	
	public static String KEY_ERROR_ACC_ST_REQUIRED = "OA-E-0506";
	public static String DEFAULT_ERR_MSG_ACC_ST_REQUIRED = "请选择用户状态";
}
