package com.pccw.springframework.constant;

public class MessageConstant {
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
}
