package org.example.models;

import java.util.List;

public class WeaponStatsDisplay{
	private String weaponImageWebp;
	private List<String> displayStats;
	private String weaponImage;
	private List<String> weaponAttributes;
	private List<String> otherDisplayStats;

	public void setWeaponImageWebp(String weaponImageWebp){
		this.weaponImageWebp = weaponImageWebp;
	}

	public String getWeaponImageWebp(){
		return weaponImageWebp;
	}

	public void setDisplayStats(List<String> displayStats){
		this.displayStats = displayStats;
	}

	public List<String> getDisplayStats(){
		return displayStats;
	}

	public void setWeaponImage(String weaponImage){
		this.weaponImage = weaponImage;
	}

	public String getWeaponImage(){
		return weaponImage;
	}

	public void setWeaponAttributes(List<String> weaponAttributes){
		this.weaponAttributes = weaponAttributes;
	}

	public List<String> getWeaponAttributes(){
		return weaponAttributes;
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
			"WeaponStatsDisplay{" + 
			"weapon_image_webp = '" + weaponImageWebp + '\'' + 
			",display_stats = '" + displayStats + '\'' + 
			",weapon_image = '" + weaponImage + '\'' + 
			",weapon_attributes = '" + weaponAttributes + '\'' + 
			",other_display_stats = '" + otherDisplayStats + '\'' + 
			"}";
		}
}