package org.simplestartframework.generator.util;

import java.util.Properties;
/**
 * 
 * 全局变量定义类
 * @author zhuyj
 * @version 1.0
 * 
 *
 */
public class Global {
	private static Properties prop = PropertiesUtil.getProp("config/sys.properties");
	/**------------数据库----------------*/
	public static String DB_DRIVER=prop.getProperty("driver");
	public static String DB_URL=prop.getProperty("url");
	public static String DB_USERNAME=prop.getProperty("username");
	public static String DB_PASSWORD=prop.getProperty("password");
	/**
	 * 测试功能。。结合自已写的分页插件
	 */
	//public static String DB_DIALECT=prop.getProperty("dialect");
	
	/**-------------路径-----------------*/
	/**
	 * 生成项目代码输出文件夹
	 */
	public  static String BASE_DRI="./output";
	/**
	 * 源码目录名称
	 */
	public  static String SRC_DIR="src";
	
	/**
	 * 资源文件目录名称
	 */
	public  static String RESOURCE="resources";
	/**
	 * 获得生成配置的包路径
	 */
	public  static String PACKAGE_PATH=prop.getProperty("package_path");
	/**
	 * 获得包的前缀
	 */
	public  static String PACKAGE_PREFIX=StringUtil.slashToPoint(prop.getProperty("package_path"));
	
	/**
	 * 模板存放目录
	 */
	public  static String CONFIG_DIR="./config";
	public  static String PAGE_DIR=prop.getProperty("page_dir");
	public  static String WEB_ROOT_DIR=prop.getProperty("webroot");
	public 	static  String WEBINF="/WEB-INF";
	public  static String CONFIG="/config";
	public  static String WEBINF_DIR=WEB_ROOT_DIR+"/WEB-INF";
	public  static String TEMP=prop.getProperty("template");
	public  static String TEMP_DIR=CONFIG_DIR+"/"+TEMP;
	public  static String PROJECT_NAME=prop.getProperty("project_name");
	public  static String PROJECT_DIR=BASE_DRI+"/"+PROJECT_NAME;
	public  static String OUT_DIR = BASE_DRI +"/"+ PROJECT_NAME + "/"+SRC_DIR;
	public  static String TEMPLATE_DIR = CONFIG_DIR+"/" + TEMP_DIR;
	public  static String WEB_LIB_DIR=WEBINF_DIR+"/"+"lib";
	public  static String METAINF_DIR=WEBINF_DIR+"/META-INF";
	public static String  WEB_CONFIG_DIR=WEBINF_DIR+"/config";
	
	
	/*模板声明*/
	public static String CONFIG_CLASS="ConfigurationClassTemplate.java";
	public static String SQL_BUILDER_CLASS="SQLBuilderTemplate.java";
	public static String MAPPER_INTERFACE="MapperTemplate.java";
	public static String SERVICE_IMPL_CLASS="ServiceImplTemplate.java";
	public static String SERVICE_INTERFACE="ServiceTemplate.java";
	public static String ACTION_CLASS="ActionTemplate.java";
	
	
}
