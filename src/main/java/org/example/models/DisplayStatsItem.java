package org.example.models;

public class DisplayStatsItem{
	private String statType;
	private String category;

	public void setStatType(String statType){
		this.statType = statType;
	}

	public String getStatType(){
		return statType;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	@Override
 	public String toString(){
		return 
			"DisplayStatsItem{" + 
			"stat_type = '" + statType + '\'' + 
			",category = '" + category + '\'' + 
			"}";
		}
}
