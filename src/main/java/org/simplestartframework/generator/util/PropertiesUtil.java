package org.simplestartframework.generator.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
	private static Properties prop = new Properties();

	public static Properties getProp(String path) {
		File file = new File(path);
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			prop.load(fis);
			fis.close();
		} catch (FileNotFoundException e) {
			System.out.println("找不到Properties配置文件");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

}
