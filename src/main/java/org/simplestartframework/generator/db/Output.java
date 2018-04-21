package org.simplestartframework.generator.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.simplestartframework.generator.util.Global;
import org.simplestartframework.generator.util.StringUtil;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 
 * @author ranger
 * 
 */
public class Output {
	public static Configuration cfg = new Configuration();
	
	/**
	 * 根据模板生成代码文件
	 * 
	 * @param tables
	 */
	public static void create(List<Table> tables) {
		String outDir = Global.OUT_DIR + "/" + Global.PACKAGE_PATH + "/";
		Map<String, String> root = new HashMap<String, String>();
		
		root.put("package_prefix", StringUtil.slashToPoint(Global.PACKAGE_PREFIX));
		
		String templateDir = Global.TEMP_DIR + "/";
		try {
			File base = new File(outDir);
			if (base.isDirectory() && base.exists()) {
				base.delete();
			}
			File file = new File("./");

			cfg.setDirectoryForTemplateLoading(file);
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			Template builderTemp = cfg.getTemplate(templateDir + Global.SQL_BUILDER_CLASS);
			Template mapperTemp = cfg.getTemplate(templateDir + Global.MAPPER_INTERFACE);
			Template serviceImplTemp = cfg.getTemplate(templateDir + Global.SERVICE_IMPL_CLASS);
			Template serviceTemp = cfg.getTemplate(templateDir + Global.SERVICE_INTERFACE);
			Template actionTemp = cfg.getTemplate(templateDir + Global.ACTION_CLASS);
			
			for (int i = 0; i < tables.size(); i++) {
				root.put("feildName", StringUtil.fieldScheme(tables.get(i).getTableName()));
				root.putAll(generateByAnnotation(tables.get(i)));
				createSQLBuilder(root,tables.get(i),outDir,templateDir,builderTemp);
				createMapper(root, tables.get(i), outDir, templateDir, mapperTemp);
				createServiceImpl(root, tables.get(i), outDir, templateDir, serviceImplTemp);
				createService(root, tables.get(i), outDir, templateDir, serviceTemp);
				createAction(root, tables.get(i), outDir, templateDir, actionTemp);
	
				
			}
			
	
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 
	 * @param root
	 * @param table
	 * @param outDir
	 * @param templateDir
	 * @param template
	 */
	private static void createMapper(Map<String, String> root,Table table,String outDir,String templateDir,Template template) {
	

		File mapper = new File(outDir + "mapper/");
		if (!mapper.exists() || !mapper.isDirectory())
			mapper.mkdirs();
		
		String path = outDir + "mapper/" + root.get("className") + "Mapper.java";
		output(template, path, root);
	}
	
	/**
	 * 
	 * @param root
	 * @param table
	 * @param outDir
	 * @param templateDir
	 * @param template
	 */
	private static void createSQLBuilder(Map<String, String> root,Table table,String outDir,String templateDir,Template template) {

		File builder = new File(outDir + "mapper/builder/");
		if (!builder.exists() || !builder.isDirectory())
			builder.mkdirs();
		String builderPath = outDir + "mapper/builder/" + root.get("className") + "Builder.java";

		output(template, builderPath, root);
	}
	
	/**
	 * 
	 * @param root
	 * @param table
	 * @param outDir
	 * @param templateDir
	 * @param template
	 */
	private static void createServiceImpl(Map<String, String> root,Table table,String outDir,String templateDir,Template template) {
		
		File builder = new File(outDir + "service/impl/");
		if (!builder.exists() || !builder.isDirectory())
			builder.mkdirs();
		String builderPath = outDir + "service/impl/" + root.get("className") + "ServiceImpl.java";

		output(template, builderPath, root);
	}
	
	private static void createService(Map<String, String> root,Table table,String outDir,String templateDir,Template template) {
	
		File builder = new File(outDir + "service/");
		if (!builder.exists() || !builder.isDirectory())
			builder.mkdirs();
		String builderPath = outDir + "service/" + root.get("className") + "Service.java";

		output(template, builderPath, root);
	}
	
	/**
	 * 
	 * @param root
	 * @param table
	 * @param outDir
	 * @param templateDir
	 * @param template
	 */
	private static void createAction(Map<String, String> root,Table table,String outDir,String templateDir,Template template) {
	
		File builder = new File(outDir + "action/");
		if (!builder.exists() || !builder.isDirectory())
			builder.mkdirs();
		String builderPath = outDir + "action/" + root.get("className") + "Action.java";

		output(template, builderPath, root);
	}
	

	
	/**
	 * 格式化列数据
	 * 
	 * @param cols
	 * @return
	 */
	public static List<Column> formatColumn(List<Column> cols) {
		List<Column> lists = new ArrayList<Column>();
		for (Column col : cols) {
			col.setColName(StringUtil.fieldScheme(col.getColName()));
			// column.setDataType(col.getDataType());
			lists.add(col);
		}

		return lists;
	}

	/**
	 * 格式化列数据
	 * 
	 * @param fks
	 * @return
	 */
	public static List<ImportedKey> formatFk(List<ImportedKey> fks) {
		List<ImportedKey> lists = new ArrayList<ImportedKey>();
		for (ImportedKey fk : fks) {
			String col = fk.getFkcolumnName().toLowerCase();
			if (col.endsWith("_id")) {
				String fkc = StringUtils.remove(col, "_id");
				fk.setFkcolumnName(fkc);
			}
			fk.setFkcolumnName(StringUtil.fieldScheme(fk.getFkcolumnName()));
			fk.setPktableName(StringUtil.classScheme(fk.getPktableName()));
			System.out.println(fk.getFkcolumnName());
			System.out.println(fk.getPktableName());
			lists.add(fk);
		}
		return lists;
	}

	public static List<ExportedKey> formatEK(List<ExportedKey> eks) {
		List<ExportedKey> lists = new ArrayList<ExportedKey>();
		for (ExportedKey ek : eks) {
			String col = ek.getFkcolumnName().toLowerCase();
			if (col.endsWith("_id")) {
				String ekc = StringUtils.remove(col, "_id");
				ek.setFkcolumnName(ekc);
			}
			ek.setFktableName(StringUtil.classScheme(ek.getFktableName()));
			ek.setFkcolumnName(StringUtil.fieldScheme(ek.getFkcolumnName()) + ek.getFktableName() + "List");
			lists.add(ek);
		}
		return lists;
	}
	
	
	
	
	/**
	 * 构建模板信息
	 * 
	 * @param table
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<String, String> generateByAnnotation(Table table) {

		Map root = new HashMap();
		try {
			List<Column> col = table.getColumns();
			StringBuilder insertHeader = new StringBuilder();
			StringBuilder insertValue = new StringBuilder();
			StringBuilder batchInsertValue = new StringBuilder();
			StringBuilder updateValue = new StringBuilder();
			StringBuilder providerBatchInsertValue = new StringBuilder();
			StringBuilder batchUpdateValue = new StringBuilder();
			StringBuilder where = new StringBuilder();
			StringBuilder form = new StringBuilder();
			StringBuilder like = new StringBuilder();
			
			insertHeader.append("(");			
			for (int j = 0; j < col.size(); ++j) {

				if (j > 1){
					updateValue.append(",");
					batchUpdateValue.append(",");
					insertHeader.append(",");
					insertValue.append(",");
					batchInsertValue.append(",");
					providerBatchInsertValue.append(",");
				}
				String colName = col.get(j).getColName();
				String colType = col.get(j).getDataType();
				
				//col.get(j).getDataType();
				System.out.println(col.get(j).getDataType());
				if (j > 0) {
					form.append("<p><label>"+colName+"</label><input  type=\"text\" id=\""+colName+"\" name=\""+colName+"\" /></p>\r\n");
					where.append("\t\tif(entity.get(\""+colName+"\")!=null){\r\n");
					where.append("\t\t\tsql.append(\" and "+colName+"=#{" + colName + "}\");\r\n");
					where.append("\t\t}\r\n");
					
					if (colType.equals("String")){
						like.append("\t\tif(entity.get(\""+colName+"\")!=null){\r\n");
						like.append("\t\t\tsql.append(\" and "+colName+"  like CONCAT('%',${" + colName + "},'%') \");\r\n");
						like.append("\t\t}\r\n");
					}else if (colType.equals("Date")){
						like.append("\t\tif(entity.get(\"BEGIN_DATE\")!=null && entity.get(\"END_DATE\")!=null){\r\n");
						like.append("\t\t\tsql.append(\" and "+colName+" between #{BEGIN_DATE} and #{END_DATE}\");\r\n");
						like.append("\t\t}\r\n");
					}else{
						like.append("\t\tif(entity.get(\""+colName+"\")!=null){\r\n");
						like.append("\t\t\tsql.append(\" and "+colName+"=#{" + colName + "}\");\r\n");
						like.append("\t\t}\r\n");
					}
					updateValue.append(col.get(j).getColName() + "= #{" + colName + "}");
					batchUpdateValue.append(col.get(j).getColName() + "= #{list[\"+i+\"]." + colName + "}");
					providerBatchInsertValue.append("#{list[\"+i+\"]." + colName + "}");
					insertHeader.append(col.get(j).getColName());
					batchInsertValue.append("#{item." + colName + "}");
					insertValue.append("#{" + colName + "}");
				}else{
					form.append("<p><label>"+colName+"</label><input  type=\"text\" id=\""+colName+"\" name=\""+colName+"\" /></p>\r\n");
					where.append("if(entity.get(\""+colName+"\")!=null){\r\n");
					where.append("\t\t\t sql.append(\""+colName+"=#{" + colName + "}\");\r\n");
					where.append("\t\t}\r\n");
					if (colType.equals("String")){
						like.append("if(entity.get(\""+colName+"\")!=null){\r\n");
						like.append("\t\t\t sql.append(\""+colName+" like CONCAT('%',${" + colName + "},'%')\");\r\n");
						like.append("\t\t}\r\n");
					}else if (colType.equals("Date")){
						like.append("\t\tif(entity.get(\""+colName+"\")!=null\"){\r\n");
						like.append("\t\t\t sql.append(\""+colName+" between #{BEGIN_DATE} and #{END_DATE}\");\r\n");
						like.append("\t\t}\r\n");
					}else{
						like.append("if(entity.get(\""+colName+"\")!=null){\r\n");
						like.append("\t\t\t sql.append(\""+colName+"=#{" + colName + "}\");\r\n");
						like.append("\t\t}\r\n");
					}				
				}
				
			}
			insertHeader.append(") ");
			// System.out.println(batchInsertValue.toString());
			// System.out.println(table.getTableName());
			if (table.getPrimaryKeys() != null && table.getPrimaryKeys().size() > 0)
				root.put("id", table.getPrimaryKeys().get(0));
			root.put("tableName", table.getTableName().toLowerCase());
			root.put("pojoName", StringUtil.classScheme(table.getTableName()));
			root.put("insertHeader", insertHeader.toString());
			if ("annotation_hashmap".equalsIgnoreCase(Global.TEMP)) {

				root.put("batchInsertValue", batchInsertValue.toString());
				root.put("providerBatchInsertValue", providerBatchInsertValue.toString());
				root.put("insertValue", insertValue.toString());
				root.put("batchUpdateValue", batchUpdateValue.toString());
				root.put("updateValue", updateValue.toString());
				root.put("where", where.toString());
				root.put("form", form.toString());
				root.put("like", like.toString());
				
			} else {

				root.put("batchInsertValue", StringUtil.fieldScheme(batchInsertValue.toString()));
				root.put("providerBatchInsertValue",  StringUtil.fieldScheme(providerBatchInsertValue.toString()));
				root.put("insertValue", StringUtil.fieldScheme(insertValue.toString()));
				root.put("batchUpdateValue", StringUtil.fieldScheme(batchUpdateValue.toString()));
				root.put("updateValue", StringUtil.fieldScheme(updateValue.toString()));
				root.put("where", StringUtil.fieldScheme(where.toString()));
				root.put("form", StringUtil.fieldScheme(form.toString()));
				root.put("like", StringUtil.fieldScheme(like.toString()));
			}
			root.put("className", StringUtil.classScheme(table.getTableName()));
			root.put("col", formatColumn(col));
			root.put("fk", formatFk(table.getImportedKeys()));
			root.put("ek", formatEK(table.getExportedKeys()));
			System.out.println(like);
			System.out.println(where);
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return root;
	}
	
	

	
	public static void output(Template temp, String path, Map<String, String> root) {
		try {
			File mapper = new File(path);
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
