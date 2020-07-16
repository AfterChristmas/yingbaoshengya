package com.yingbao.career.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	public static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++){
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
			n += 256;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String MD5Encode(String origin, String charsetname) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (charsetname == null || "".equals(charsetname)) {
				resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
			} else {
				resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
			}
		} catch (Exception exception) {
		}
		return resultString;
	}

	public static String getMD5(String plainText, int length) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");//获取MD5实例
			md.update(plainText.getBytes());//此处传入要加密的byte类型值
			byte[] digest = md.digest();//此处得到的是md5加密后的byte类型值

            /*
               下边的运算就是自己添加的一些二次小加密，记住这个千万不能弄错乱，
                   否则在解密的时候，你会发现值不对的（举例：在注册的时候加密方式是一种，
                在我们登录的时候是不是还需要加密它的密码然后和数据库的进行比对，但是
            最后我们发现，明明密码对啊，就是打不到预期效果，这时候你就要想一下，你是否
             有改动前后的加密方式）
            */
			int i;
			StringBuilder sb = new StringBuilder();
			for (int offset = 0; offset < digest.length; offset++) {
				i = digest[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					sb.append(0);
				sb.append(Integer.toHexString(i));//通过Integer.toHexString方法把值变为16进制
			}
			return sb.toString().substring(0, length);//从下标0开始，length目的是截取多少长度的值
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

}
