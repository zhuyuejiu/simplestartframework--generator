package org.simplestartframework.generator.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.simplestartframework.generator.util.CharEscape;
import org.simplestartframework.generator.util.ConnUtil;
import org.simplestartframework.generator.util.Global;

/**
 * 
 * @author ranger
 * 
 */
public class DBGenerate {

	/**
	 * 构造表信息
	 * 
	 * @return
	 */
	public static List<Table> getTables() {

		List<Table> list = new ArrayList<Table>();
		try {
			Connection conn = ConnUtil.getConnection();
			DatabaseMetaData meta = conn.getMetaData();

			String databaseName = conn.getCatalog();
			ResultSet tables = meta.getTables(databaseName, meta.getUserName(), null, new String[] { "TABLE", "VIEW" });

			while (tables.next()) {
				// 获得表字段
				String talbeName = tables.getString("TABLE_NAME");

				System.out.println(talbeName);
				// 获得表ID
				ResultSet primaryKesys = meta.getPrimaryKeys(databaseName, null, talbeName);
				Table table = new Table();
				table.setTableName(talbeName);
				while (primaryKesys.next()) {
					table.getPrimaryKeys().add(primaryKesys.getString("COLUMN_NAME"));
					// System.out.println(primaryKesys.getString("COLUMN_NAME"));
				}

				// 获得列信息

				talbeName = StringUtils.replaceChars(talbeName, "/", CharEscape.FRASL);
				talbeName = StringUtils.replaceChars(talbeName, "@", CharEscape.AT);
				talbeName = StringUtils.replaceChars(talbeName, ">", CharEscape.GT);
				talbeName = StringUtils.replaceChars(talbeName, "<", CharEscape.LT);
				talbeName = StringUtils.replaceChars(talbeName, "\"", CharEscape.QUOT);
				talbeName = StringUtils.replaceChars(talbeName, "%", CharEscape.PERCENT);

				// System.out.println(talbeName);
				ResultSet columns = meta.getColumns(databaseName, null, talbeName, "%");
				while (columns.next()) {
					Column column = new Column();
					column.setColName(columns.getString("COLUMN_NAME"));
					column.setDataType(columns.getString("DATA_TYPE"));
					// System.out.println(column.getDataType());
					table.getColumns().add(column);
				}
				// 获得外键对映主键信息
				ResultSet importedKeys = meta.getImportedKeys(databaseName, null, talbeName);
				while (importedKeys.next()) {
					ImportedKey importedKey = new ImportedKey();
					importedKey.setFkcolumnName(importedKeys.getString("FKCOLUMN_NAME"));
					importedKey.setPktableName(importedKeys.getString("PKTABLE_NAME"));

					System.out.println(talbeName + "--" + importedKey.getPktableName() + "--" + importedKey.getFkcolumnName());
					table.getImportedKeys().add(importedKey);
				}
				importedKeys.close();
				primaryKesys.close();
				columns.close();
				list.add(table);
			}

			tables.close();

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i < list.size(); i++) {
			Table table = list.get(i);
			// 获得引用表名
			for (int j = 0; j < list.size(); j++) {
				for (ImportedKey importedKey : list.get(j).getImportedKeys()) {
					if (table.getTableName().equals(importedKey.getPktableName())) {
						ExportedKey exportedKey = new ExportedKey();
						exportedKey.setFktableName(list.get(j).getTableName());
						exportedKey.setFkcolumnName(importedKey.getFkcolumnName());
						table.getExportedKeys().add(exportedKey);
					}
				}
			}
		}

		return list;
	}

	public static void execute() {

		Output.create(DBGenerate.getTables());

	}

	public static void main(String[] args) {
		DBGenerate.getTables();
	}

}
