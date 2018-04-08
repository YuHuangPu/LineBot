package com.util;


public class StringsUtil {
	public static String DBConn = "N";
	public static Object nvlString(Object a, String b){
		return StringsUtil.isNull(a)?b:a;
	}
	public static String changeZero(String s){
		if(isNull(s)){return null;}
		while(Boolean.TRUE){
			if(s.endsWith("0")){
				s = s.substring(0, s.length()-1);
			}else if(s.endsWith(".")){
				s = s.substring(0, s.length()-1);
				break;
			}else {
				break;
			}
		}
		return s;
	}
	public static String lastSplit(String [] s){
		int length = s.length;
		return s[length-1];
	}
	/**
	 * AWRQ_WWEWQ -> AwrqWwewq
	 * */
	public static String ZivString(String s){
		if(!isNull(s)){
			StringBuffer result = new StringBuffer();
			char[] charAry;
			for(String S : s.split("[_]")){
//				result.append(String.valueOf(S.charAt(0)).toUpperCase()).append(S.substring(1).toLowerCase());
				charAry = S.toLowerCase().toCharArray();
	            charAry[0] = Character.toUpperCase(charAry[0]);
	            result.append(charAry);
			}
			return result.toString();
		}
		return null;
	}
	public static String VizString(String s){
		if(!isNull(s)){
			StringBuffer result = new StringBuffer();
			String ss [] = s.split("[A-Z]");
			for(int i = 1,le = 0 ; i < ss.length ; i++){
				result.append(s.charAt(le)).append(ss[i].toUpperCase());
				le = ss[i].length() + le +1;
				if(i!=ss.length-1)result.append("_");
			}
			return result.toString();
		}
		return null;
	}
	public static Boolean isNull (Object s){
		if(s!=null){
			if(!s.toString().isEmpty()){
				if(s.toString().length()!=0){
					if(!s.toString().toLowerCase().equals("null")){
						return Boolean.FALSE;
					}
				}
			}
		}
		return Boolean.TRUE;
	}
	
	public static String getValue (Object s){
		return isNull(s)?"":s.toString();
	}
	public static String setComma(String... str){
		String re = "";
		for(String s : str){
			re = re + s + ", ";
		}
		
		return re.substring(0,re.length()-2);
	}
	public static final char reChar[] = {'\t','\n','\r','¡@'};
	public static final String reString[] = {"  "};
	public static String regulate(String s){
		if(!isNull(s)){
			String newS = s.trim();
			for(char rechar : reChar){
				while(newS.indexOf(rechar)!=-1){
					newS = newS.replace(rechar, ' ');
				}
			}
			for(String restring : reString){
				while(newS.indexOf(restring)!=-1){
					newS = newS.replace(restring, " ");
				}
			}
			return newS;
		}else{
			return s;
		}
	}
}
