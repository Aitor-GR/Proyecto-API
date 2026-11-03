package org.example.models;

public class SprintSpeed{
	private String displayStatName;
	private Object value;

	public void setDisplayStatName(String displayStatName){
		this.displayStatName = displayStatName;
	}

	public String getDisplayStatName(){
		return displayStatName;
	}

	public void setValue(Object value){
		this.value = value;
	}

	public Object getValue(){
		return value;
	}

	@Override
 	public String toString(){
		return 
			"SprintSpeed{" + 
			"display_stat_name = '" + displayStatName + '\'' + 
			",value = '" + value + '\'' + 
			"}";
		}
}
