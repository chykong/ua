package com.critc.util.code;

import java.util.HashMap;
import java.util.Random;

import com.critc.util.date.DateUtil;

/**
 * 
 * 功能描述:流水号生成工具 
 * 
 * @version 1.0.0
 * @author 孔垂云
 * @2015年3月4日
 */
public class SerialNumUtil {
	private static final char[] codeSequenceRandom = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
			'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	private static final char[] codeSequenceNumRandom = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	/**
	 * 
	 * 功能描述:生成随机数
	 * 
	 * @return String
	 * @version 1.0.0
	 * @author 孔垂云
	 */
	public static String createSerialNum() {
		String serialNum = "";
		Random random = new Random();
		for (int i = 0; i < 5; i++) {
			String strRand = String.valueOf(codeSequenceRandom[random.nextInt(codeSequenceRandom.length)]);
			serialNum += strRand;
		}
		return DateUtil.getShortSystemTime() + serialNum;
	}

	/**
	 * 生成字母+数字随机数
	 * @param length
	 * @return
	 */
	public static String createRandowmLetter(int length) {
		String serialNum = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			String strRand = String.valueOf(codeSequenceRandom[random.nextInt(codeSequenceRandom.length)]);
			serialNum += strRand;
		}
		return serialNum;
	}

	/**
	 * 生成数字随机数
	 * @param length
	 * @return
	 */
	public static String createRandowmNum(int length) {
		String serialNum = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			String strRand = String.valueOf(codeSequenceNumRandom[random.nextInt(codeSequenceNumRandom.length)]);
			serialNum += strRand;
		}
		return serialNum;
	}

	public static void main(String[] args) {
		for (int k = 0; k < 10000; k++) {
			int cnt = 100;
			HashMap<String, String> hashMap = new HashMap<String, String>();
			for (int i = 0; i < cnt; i++) {
				String num = SerialNumUtil.createSerialNum();
				hashMap.put(num, num);
				//				System.out.println(num);
			}
			if (cnt != hashMap.size()) {
				System.out.println(hashMap.size());
			}
		}
	}

}
