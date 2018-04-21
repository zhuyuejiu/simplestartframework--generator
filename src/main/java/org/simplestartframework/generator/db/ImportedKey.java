package org.simplestartframework.generator.db;

public class ImportedKey {
	private String pktableName;
	private String fkcolumnName;
	public String getPktableName() {
		return pktableName;
	}
	public void setPktableName(String pktableName) {
		this.pktableName = pktableName;
	}
	public String getFkcolumnName() {
		return fkcolumnName;
	}
	public void setFkcolumnName(String fkcolumnName) {
		this.fkcolumnName = fkcolumnName;
	}


}
