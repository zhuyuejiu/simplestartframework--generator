package org.simplestartframework.generator.db;

public class ExportedKey {
	private String fktableName;
	private String fkcolumnName;

	public String getFktableName() {
		return fktableName;
	}

	public void setFktableName(String fktableName) {
		this.fktableName = fktableName;
	}

	public String getFkcolumnName() {
		return fkcolumnName;
	}

	public void setFkcolumnName(String fkcolumnName) {
		this.fkcolumnName = fkcolumnName;
	}

}
