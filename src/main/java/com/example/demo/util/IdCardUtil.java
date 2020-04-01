
/**  
* @Title: IdCardUtil.java
* @Package cn.com.libertymutual.claims.task.common
* @Description: TODO(用一句话描述该文件做什么)
* @author dy
* @date 2019年6月21日
* @version V1.0  
*/

package com.example.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
    * @ClassName: IdCardUtil
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author dy
    * @date 2019年6月21日
    */

public class IdCardUtil {
	public static final int IDENTITYCODE_OLD = 15; // 老身份证15位
	public static final int IDENTITYCODE_NEW = 18; // 新身份证18位
	public static int[] Wi = new int[17];
	private static Logger logger = LoggerFactory.getLogger(IdCardUtil.class);
	public static boolean checkId(String id) {
		try {
			if (id.length() != IDENTITYCODE_NEW
					&& id.length() != IDENTITYCODE_OLD) {
				return false;
			}
			if (id.length() == 15) {
				id = transIDCard15to18(id);
			}
			char[] ch = id.toCharArray();
			boolean flag1 = verForm(id);
			boolean flag2 = verify(ch);
			if (flag1 == true && flag2 == true) {
				return true;
			}
		} catch (Exception e) {
			logger.error("身份证校验时发生异常 id:{} case:{}", id, e.getMessage(), e);
			return false;
		}
		return false;
	}
	// <------------------身份证格式的正则校验----------------->
	public static boolean verForm(String num) {
		String reg = "^\\d{15}$|^\\d{17}[0-9Xx]$";
		if (!num.matches(reg)) {
			return false;
		}
		return true;
	}
	// <------------------身份证最后一位的校验算法----------------->
	public static boolean verify(char[] id) {
		int sum = 0;
		int w[] = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
		char[] ch = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
		for (int i = 0; i < id.length - 1; i++) {
			sum += (id[i] - '0') * w[i];
		}
		int c = sum % 11;
		char code = ch[c];
		char last = id[id.length - 1];
		last = last == 'x' ? 'X' : last;
		return last == code;
	}

	/**
	
	 * @param IdCardNO
	
	 * @return 18位标准身份证号
	
	 * 方法用途：15位身份证转化为18位标准证件号
	
	 * @return String
	
	 * @author 我心自在
	
	 */

	public static String transIDCard15to18(String IdCardNO) {

		String cardNo = null;

		if (null != IdCardNO && IdCardNO.trim().length() == 15) {

			IdCardNO = IdCardNO.trim();

			StringBuffer sb = new StringBuffer(IdCardNO);

			sb.insert(6, "19");

			sb.append(transCardLastNo(sb.toString()));

			cardNo = sb.toString();

		}

		return cardNo;

	}
	private static String transCardLastNo(String newCardId) {

		char[] ch = newCardId.toCharArray();

		int m = 0;

		int[] co = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

		char[] verCode = new char[]{'1', '0', 'X', '9', '8', '7', '6', '5', '4',
				'3', '2'};

		for (int i = 0; i < newCardId.length(); i++) {

			m += (ch[i] - '0') * co[i];

		}

		int residue = m % 11;

		return String.valueOf(verCode[residue]);

	}
	public static void main(String[] args) {
		String string = "511111111111111";
		Boolean b = checkId(string);
		System.out.println(b);

	}
}
