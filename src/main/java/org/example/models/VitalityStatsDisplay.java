package org.example.models;

import java.util.List;

public class VitalityStatsDisplay{
	private List<String> displayStats;
	private List<String> otherDisplayStats;

	public void setDisplayStats(List<String> displayStats){
		this.displayStats = displayStats;
	}

	public List<String> getDisplayStats(){
		return displayStats;
	}

	public void setOtherDisplayStats(List<String> otherDisplayStats){
		this.otherDisplayStats = otherDisplayStats;
	}

	public List<String> getOtherDisplayStats(){
		return otherDisplayStats;
	}

	@Override
 	public String toString(){
		return 
			"VitalityStatsDisplay{" + 
			"display_stats = '" + displayStats + '\'' + 
			",other_display_stats = '" + otherDisplayStats + '\'' + 
			"}";
		}
}