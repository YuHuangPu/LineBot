package com.util;

import java.math.BigDecimal;

public class NumbersUtil {
	public static BigDecimal regulateNumber(Object s){
		String result = ((String)s).replace(",", "").replace(" ", "");
		return new BigDecimal(result);
	}
	public static BigDecimal getValue(Object s){
		try{
			return new BigDecimal((String)s);
		}catch(Exception e){
			return BigDecimal.ZERO;
		}
	}
}
