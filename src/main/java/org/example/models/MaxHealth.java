package org.example.models;

public class MaxHealth{
	private String displayStatName;
	private int value;

	public void setDisplayStatName(String displayStatName){
		this.displayStatName = displayStatName;
	}

	public String getDisplayStatName(){
		return displayStatName;
	}

	public void setValue(int value){
		this.value = value;
	}

	public int getValue(){
		return value;
	}

	@Override
 	public String toString(){
		return 
			"MaxHealth{" + 
			"display_stat_name = '" + displayStatName + '\'' + 
			",value = '" + value + '\'' + 
			"}";
		}
}
