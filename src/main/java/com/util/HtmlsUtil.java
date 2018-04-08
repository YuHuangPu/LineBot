package com.util;

import java.util.List;

public class HtmlsUtil {
	private static final String selectEx = "<select %s> " +
			"%s" +
			"</select>";
	public static String getSelectEx(String id, String name, String Class, List<String []> options) {
		StringBuffer opts = new StringBuffer();
		for(String[] opt : options){
			opts.append(getOptionEx(opt[0], opt[1], opt[2], Boolean.valueOf(opt[3])));
		}
		String select = String.format(selectEx,
				(StringsUtil.isNull(id)?"":String.format("id='%s' ", id))
			+	(StringsUtil.isNull(name)?"":String.format("name='%s' ", name))
			+	(StringsUtil.isNull(Class)?"":String.format("class='%s' ", Class))
				, opts==null? "":opts);
		return select;
	}
	
	private static final String optionEx = "<option %s >%s</option>";
	public static String getOptionEx(String value, String text, String Class, Boolean selected) {
		String option = String.format(optionEx, 
				(StringsUtil.isNull(value)?"":String.format("value='%s' ", value))
			+	(StringsUtil.isNull(Class)?"":String.format("class='%s' ", Class))
			+	(selected?"selected ":"")
				, text);
		return option;
	}
}
