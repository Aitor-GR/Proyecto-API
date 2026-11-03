package org.example.models;

import java.util.List;

public class HeroStatsUi{
	private List<DisplayStatsItem> displayStats;
	private String weaponStatDisplay;

	public void setDisplayStats(List<DisplayStatsItem> displayStats){
		this.displayStats = displayStats;
	}

	public List<DisplayStatsItem> getDisplayStats(){
		return displayStats;
	}

	public void setWeaponStatDisplay(String weaponStatDisplay){
		this.weaponStatDisplay = weaponStatDisplay;
	}

	public String getWeaponStatDisplay(){
		return weaponStatDisplay;
	}

	@Override
 	public String toString(){
		return 
			"HeroStatsUi{" + 
			"display_stats = '" + displayStats + '\'' + 
			",weapon_stat_display = '" + weaponStatDisplay + '\'' + 
			"}";
		}
}