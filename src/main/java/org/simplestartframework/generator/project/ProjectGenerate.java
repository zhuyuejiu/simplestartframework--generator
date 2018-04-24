package org.simplestartframework.generator.project;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.simplestartframework.generator.util.FileUtil;
import org.simplestartframework.generator.util.Global;
import org.simplestartframework.generator.util.StringUtil;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class ProjectGenerate {
	private static Configuration cfg = new Configuration();
	private static List<String> dirs = new ArrayList<String>();
	private static Map<String, String> root = new HashMap<String, String>();

	static {
		//root.put("dialect", Global.DB_DIALECT);
		root.put("driver", Global.DB_DRIVER);
		System.out.println(Global.DB_URL + "==============================");
		root.put("url", Global.DB_URL);
		root.put("username", Global.DB_USERNAME);
		root.put("password", Global.DB_PASSWORD);
		root.put("package_prefix", Global.PACKAGE_PREFIX);
		root.put("package_path", Global.PACKAGE_PATH);
		root.put("web_config", Global.WEBINF + Global.CONFIG);

		dirs.add(Global.SRC_DIR);
	
		dirs.add(Global.WEB_ROOT_DIR);
		dirs.add(Global.WEBINF_DIR);
		dirs.add(Global.METAINF_DIR);
		dirs.add(Global.WEB_LIB_DIR);
		dirs.add(Global.WEB_CONFIG_DIR);
		dirs.add(Global.RESOURCE);
		
		try {
			File file = new File("./");
			cfg.setDirectoryForTemplateLoading(file);
			cfg.setObjectWrapper(new DefaultObjectWrapper());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 构造目录结构
	 */
	public static void buidDir() {

		File dir = new File(Global.PROJECT_DIR);
		if (dir.isDirectory() && dir.exists()) {
			dir.delete();
			dir.mkdirs();
		} else {
			dir.mkdirs();
		}
		for (int i = 0; i < dirs.size(); i++) {
			File e = new File(Global.PROJECT_DIR + "/" + dirs.get(i));
			e.mkdir();
		}

	}

	/**
	 * 构造web.xml配置文件
	 */
	public static void buidWebXml() {
		try {
			Template webtemp = cfg.getTemplate(Global.TEMP_DIR + "/web.xml");
			FileUtil.output(webtemp, Global.PROJECT_DIR + "/" + Global.WEBINF_DIR + "/web.xml", root);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 构造MANIFEST.MF描述文件
	 */
	public static void buildMateInf() {
		try {
			Template manifest = cfg.getTemplate(Global.TEMP_DIR + "/MANIFEST.MF");
			FileUtil.output(manifest, Global.PROJECT_DIR + "/" + Global.METAINF_DIR + "/MANIFEST.MF", root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 构建数据库连接配置文件
	 */
	public static void buildResources(){
		try {
			String dbResource = Global.PROJECT_DIR + "/"  + Global.RESOURCE;
			Template db = cfg.getTemplate(Global.TEMP_DIR + "/db.properties");
			FileUtil.output(db, dbResource + "/db.properties", root);
			Template log4j = cfg.getTemplate(Global.TEMP_DIR + "/log4j2.xml");
			FileUtil.output(log4j, dbResource + "/log4j2.xml", root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	/**
	 * 输出注解的配置类
	 */
	public static void buidConfigByAnnotation() {
		String template_dir = Global.TEMP_DIR + "/";
		try {
			
			String configPath =Global.OUT_DIR +StringUtil.pointToslash(Global.PACKAGE_PATH)+ "/config";
			File dir= FileUtils.getFile(configPath);
			if(!dir.exists()||!dir.isDirectory()){
				dir.mkdirs();
			}
			
			Template springConfig = cfg.getTemplate(template_dir + Global.CONFIG_CLASS);

			FileUtil.output(springConfig,configPath+"/"+"ContextConfig.java", root);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void buildLib() {
		String pathname = "./config/lib";
		File filelib = new File(pathname);
		String[] names = FileUtil.getFileNames(filelib);
		try {
			for (int i = 0; i < names.length; i++) {
				File file = new File(pathname + "/" + names[i]);

				FileUtils.copyFileToDirectory(file, new File(Global.PROJECT_DIR + "/" + Global.WEB_LIB_DIR));

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void execute() {
		buidDir();
		buidWebXml();
		buildMateInf();
		buildResources();
        buidConfigByAnnotation();
		buildLib();

	}

}
