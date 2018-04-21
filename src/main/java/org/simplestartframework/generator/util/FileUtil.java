package org.simplestartframework.generator.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FileUtil {
	
	/**
	 * 读取目录结构
	 * @param dir
	 * @return
	 */
	public static String[] getFileNames(File dir)
	{
		String[] names = null;
		if(dir.isDirectory())
		{
			names=dir.list();
		}
		return names;
	}
	
	/**
	 * 文件输出
	 * @param temp
	 * @param path
	 * @param root
	 */
	public static void output(Template temp,String path,Map<String,String> root)
	{
		try {
		File mapper=new File(path);
		FileOutputStream out = new FileOutputStream(mapper);
		Writer writer = new OutputStreamWriter(out);
		temp.process(root, writer);
		out.flush();
		out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
