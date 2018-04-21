package org.simplestartframework.generator.util;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author ranger
 * 
 */
public class StringUtil {

	public static void main(String[] args) {
		System.out.println(StringUtil.classScheme("as_db"));
		System.out.println(StringUtil.fieldScheme("as_db"));
		System.out.println(StringUtil.slashToPoint("com/deeelon"));
	}

	/**
	 * 将表名换为java规范类名格式
	 * 
	 * @param source
	 * @return
	 */
	public static String classScheme(String source) {
		source = source.replaceFirst("tb_", "");
		source = source.replaceFirst("Tb_", "");
		source = source.replaceFirst("TB_", "");
		StringBuilder sb = new StringBuilder(source.toLowerCase());
		sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		while (sb.indexOf("_") > 0) {
			if (sb.indexOf("_") == sb.length() - 1) {
				sb.deleteCharAt(sb.indexOf("_"));
			} else {
				sb.setCharAt(sb.indexOf("_") + 1, Character.toUpperCase(sb.charAt(sb.indexOf("_") + 1)));
				sb.deleteCharAt(sb.indexOf("_"));
			}
		}
		
		return sb.toString();
	}

	public static String fieldScheme(String source) {
		source = source.replaceFirst("tb_", "");
		source = source.replaceFirst("Tb_", "");
		source = source.replaceFirst("TB_", "");
		StringBuilder sb = new StringBuilder(source.toLowerCase());
		sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
		while (sb.indexOf("_") > 0) {
			if (sb.indexOf("_") == sb.length() - 1) {
				sb.deleteCharAt(sb.indexOf("_"));
			} else {
				sb.setCharAt(sb.indexOf("_") + 1, Character.toUpperCase(sb.charAt(sb.indexOf("_") + 1)));
				sb.deleteCharAt(sb.indexOf("_"));
			}
		}
		return sb.toString();
	}
	
	public static String toUpperCase(String source) {

	
		return source.toUpperCase();
	}

	public static String slashToPoint(String source) {
		String target = StringUtils.replaceChars(source, '/', '.');
		return target;
	}

	public static String pointToslash(String source) {
		String target = StringUtils.replaceChars(source, '.', '/');
		return target;
	}

}
