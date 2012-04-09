package com.pccw.springframework.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.pccw.springframework.constant.CommonConstant;

public class StringEncodeUtility {

	public static String encode(String target, String type) {
		try {			
			if(StringUtils.isEmpty(target)){
				return CommonConstant.EMPTY_STRING;
			}
			if (CommonConstant.STRING_ENCODE_BY_MD5.equals(type)) {
				return encodeByMD5(target);
			}
			return encodeByDefault(target);
		} catch (Exception e) {
			e.printStackTrace();
			return CommonConstant.EMPTY_STRING;
		}	
	}

	private static String encodeByDefault(String target) {
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(target.getBytes());
	}

	private static String encodeByMD5(String target) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest
				.getInstance(CommonConstant.STRING_ENCODE_BY_MD5);
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(digest.digest(target.getBytes()));
	}
	
	public static String decode(String target , String type){
		if(StringUtils.isEmpty(target)){
			return CommonConstant.EMPTY_STRING;
		}
		return decodeByDefault(target);
	}

	private static String decodeByDefault(String target) {
		try{
			BASE64Decoder decoder = new BASE64Decoder();
			String result = new String(decoder.decodeBuffer(target),"UTF-8");
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return CommonConstant.EMPTY_STRING;
		}
	}

}
