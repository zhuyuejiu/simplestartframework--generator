package org.simplestartframework.generator;

import java.io.File;

import org.simplestartframework.generator.db.DBGenerate;
import org.simplestartframework.generator.project.ProjectGenerate;
import org.simplestartframework.generator.util.Global;

/**
 * 
 * @author ranger
 * 
 */
public class Main {

	public static void main(String[] args) {
		new File(Global.BASE_DRI).delete();
		//构建项目结构
		ProjectGenerate.execute();
		//通过数据库生成类
		DBGenerate.execute();

	}

}
