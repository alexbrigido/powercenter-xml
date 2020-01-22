package br.com.firstdecision.powercenter.xml;

/**
 * Planilha (sheet) Campos
 * */
public class CamposExcel {

	String table;
	String name;
	String comment;
	String dataType;
	Integer length;
	Integer precision;
	String primary;
	String nullable;
	String sistema;
	
	public String getTable() {
		return table;
	}
	public String getName() {
		return name;
	}
	public String getComment() {
		return comment;
	}
	public String getDataType() {
		return dataType;
	}
	public Integer getLength() {
		return length;
	}
	public Integer getPrecision() {
		return precision;
	}
	public String getPrimary() {
		return primary;
	}
	public String getNullable() {
		return nullable;
	}
	public String getSistema() {
		return sistema;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public void setPrecision(Integer precision) {
		this.precision = precision;
	}
	public void setPrimary(String primary) {
		this.primary = primary;
	}
	public void setNullable(String nullable) {
		this.nullable = nullable;
	}
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}
	public String getKey() {
		return "x".equalsIgnoreCase(primary) ? "PRIMARY KEY" : "NOT A KEY";
	}
	public String getNull() {
		return "x".equalsIgnoreCase(nullable) ? "NULL" : "NOTNULL";
	}
	public int getDobroTamanho() {
		if(dataType.contains("string") || dataType.contains("char")) {
			return length * 2;
		}
		return length;
	}
	public String getTipoTarget() {
		if(dataType.contains("char")) {
			return "string";
		}else if(dataType.contains("date")) {
			return "datetime";
		}
		return "bigint";
	}
	
}
