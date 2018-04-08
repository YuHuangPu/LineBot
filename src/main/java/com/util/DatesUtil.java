package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatesUtil{
	public static final long Second = 1000;
	public static final long Minute = Second * 60;
	public static final long Hour = Minute * 60;
	public static final long Day = Hour * 24;
	public static final int TransYear = 1911;
	/**
	 * yyyyMMdd_hhmm
	 */
	public static SimpleDateFormat FileNameDateTimeFormat = new SimpleDateFormat("yyyyMMdd_hhmm");
	/**
	 * yyyy-MM-dd hh:mm:ss:SSS
	 */
	public static SimpleDateFormat DateTimeFormatII = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
	/**
	 * yyyy-MM-dd hh:mm:ss
	 */
	public static SimpleDateFormat DateTimeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	/**
	 * yyyy-MM-dd
	 */
	public static SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * yyyy/MM/dd
	 */
	public static SimpleDateFormat DateFormatII = new SimpleDateFormat("yyyy/MM/dd");
	/**
	 * HH:mm:ss
	 */
	public static SimpleDateFormat TimeFormat24 = new SimpleDateFormat("HH:mm:ss");
	/**
	 * must be this 106/07/07
	 * @throws ParseException 
	 * */
	public static Date TWDtoAD(String s) throws ParseException{
		if(StringsUtil.isNull(s)){
			return null;
		}
		String result = regulateString(s);
		result = (Integer.valueOf(result.split("-")[0])+TransYear) + result.replace(result.split("-")[0], "");
		return DateFormat.parse(result);
	}
	
	
	
	public static String [] DateFormatStrings = {"yyyy-MM-dd HH-mm-ss","yyyy-MM-dd HH-mm","yyyy-MM-dd HH","yyyy-MM-dd","yyyy-MM","yyyy"};

	
	public static Date getValue(Object d) throws ParseException{
		return StringsUtil.isNull(d)?null:DateTimeFormat.parse(d.toString());
	}
	public static Date getValue(Object d,SimpleDateFormat format) throws ParseException{
		return StringsUtil.isNull(d)?null:format.parse(d.toString());
	}
//	public static String getValue(String d,SimpleDateFormat format) throws Exception{
//		return StringsUtil.isNull(d)?null
//				:format.format(StringToTimestamp(d.toString()));
//	}
	public static java.sql.Timestamp StringToTimestamp(String s, SimpleDateFormat df) throws ParseException{
		return new java.sql.Timestamp(df.parse(s).getTime());
	}
	public static java.sql.Timestamp StringToTimestamp(String s) throws Exception{
		if (!StringsUtil.isNull(s)){
			java.sql.Timestamp result = null;
			s = regulateString(s);
			for (int i = 0; i < DateFormatStrings.length; i++) {
				int I = i;
				String df = DateFormatStrings[I];
				try {
					result = new java.sql.Timestamp(new SimpleDateFormat(df).parse(s).getTime());
				} catch (ParseException e) {
					continue;
				}
				break;
			}
			if (result == null) {
				throw new Exception("StringToTimestamp Error !!");
			}
			return result;
		}else{
			return null;	
		}
	}
	public static String regulateString (String s){
		if(!StringsUtil.isNull(s)){
			String result = s;
			String reString [] = {"/",":","--"};
			for(String re:reString)
				while(result.indexOf(re)!=-1){
					result = result.replace(re, "-");
				}
			result = result.trim();
			return result;
		}else{
			return null;
		}
	}
}
