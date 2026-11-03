package org.example.models;

import java.util.List;

public class SpiritStatsDisplay{
	private List<String> displayStats;

	public void setDisplayStats(List<String> displayStats){
		this.displayStats = displayStats;
	}

	public List<String> getDisplayStats(){
		return displayStats;
	}

	@Override
 	public String toString(){
		return 
			"SpiritStatsDisplay{" + 
			"display_stats = '" + displayStats + '\'' + 
			"}";
		}
}