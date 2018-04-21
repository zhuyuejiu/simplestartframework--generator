package org.simplestartframework.generator.db;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author zhuyj
 * 
 */
public class Table {

	private List<Column> columns = new ArrayList<Column>();
	private List<String> primaryKeys = new ArrayList<String>();
	private String tableName;
	private List<ImportedKey> importedKeys = new ArrayList<ImportedKey>();
	private List<ExportedKey> exportedKeys = new ArrayList<ExportedKey>();

	public List<ExportedKey> getExportedKeys() {
		return exportedKeys;
	}

	public void setExportedKeys(List<ExportedKey> exportedKeys) {
		this.exportedKeys = exportedKeys;
	}

	public List<ImportedKey> getImportedKeys() {
		return importedKeys;
	}

	public void setImportedKeys(List<ImportedKey> importedKeys) {
		this.importedKeys = importedKeys;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public List<String> getPrimaryKeys() {
		return primaryKeys;
	}

	public String getTableName() {
		return tableName;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public void setPrimaryKeys(List<String> primaryKeys) {
		this.primaryKeys = primaryKeys;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
