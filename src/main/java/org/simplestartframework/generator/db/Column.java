package org.simplestartframework.generator.db;

import java.sql.Types;

/**
 * 
 * @author ranger
 * 
 */
public class Column {
	private String colName;
	private String dataType;	
	private String comment;
	private String defaultValue;

	public String getDataType() {
		return dataType;
	}
	
	
	public void setDataType(String dataType) {

		int t = Integer.valueOf(dataType);
		//System.out.println(t);
		switch (t) {
		case Types.VARBINARY:
			this.dataType = "byte[]";
			break;
		case Types.OTHER:
			this.dataType = "Object";
			break;
		case Types.TIMESTAMP:
			this.dataType = "Date";
			break;
		case Types.TINYINT:
			this.dataType = "int";
			break;
		case Types.SMALLINT:
			this.dataType = "int";
			break;
		case Types.INTEGER:
			this.dataType = "int";
			break;
		case Types.BIT:
			this.dataType = "long";
			break;
		case Types.BIGINT:
			this.dataType = "long";
			break;
		case Types.VARCHAR:
			this.dataType = "String";
			break;
		case Types.CHAR:
			this.dataType = "String";
			break;
		case Types.BOOLEAN:
			this.dataType = "boolean";
			break;
		case Types.DECIMAL:
			//this.dataType = "BigDecimal";
			this.dataType = "long";
			break;
		case Types.REAL:
			this.dataType = "float";
			break;
		case Types.FLOAT:
			this.dataType = "float";
			break;
		case Types.DOUBLE:
			this.dataType = "double";
			break;
		case Types.DATE:
			this.dataType = "Date";
			break;
		case Types.TIME:
			this.dataType = "Date";
			break;
		case Types.NVARCHAR:
			this.dataType = "String";
			break;
		case Types.NCHAR:
			this.dataType = "String";
			break;
		case Types.NUMERIC:
			this.dataType = "String";
			break;
		case Types.BINARY:
			this.dataType = "byte[]";
			break;
		case Types.BLOB:
			this.dataType = "byte[]";
			break;
		case Types.CLOB:
			this.dataType = "String";
			break;
		case Types.LONGNVARCHAR:
			this.dataType = "String";
			break;
		case Types.LONGVARBINARY:
			this.dataType = "String";
			break;
		case Types.LONGVARCHAR:
			this.dataType = "String";
			break;
		default:
			break;
		}
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getDefaultValue() {
		return defaultValue;
	}
	
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

}
