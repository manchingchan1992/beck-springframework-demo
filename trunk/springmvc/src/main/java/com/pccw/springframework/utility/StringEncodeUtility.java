package com.pccw.springframework.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

import com.pccw.springframework.constant.CommonConstant;

public class StringEncodeUtility {

	public String encode(String target, String type)
			throws NoSuchAlgorithmException {
		if (CommonConstant.STRING_ENCODE_BY_MD5.equals(type)) {
			return encodeByMD5(target);
		}
		return null;
	}

	private String encodeByMD5(String target) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest
				.getInstance(CommonConstant.STRING_ENCODE_BY_MD5);
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(digest.digest(target.getBytes()));
	}

}
