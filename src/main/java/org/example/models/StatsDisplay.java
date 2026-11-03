package org.example.models;

import java.util.List;

public class StatsDisplay{
	private List<String> healthStats;
	private List<String> magicStats;
	private List<String> weaponStats;
	private List<String> weaponHeaderStats;
	private List<String> magicHeaderStats;
	private List<String> healthHeaderStats;

	public void setHealthStats(List<String> healthStats){
		this.healthStats = healthStats;
	}

	public List<String> getHealthStats(){
		return healthStats;
	}

	public void setMagicStats(List<String> magicStats){
		this.magicStats = magicStats;
	}

	public List<String> getMagicStats(){
		return magicStats;
	}

	public void setWeaponStats(List<String> weaponStats){
		this.weaponStats = weaponStats;
	}

	public List<String> getWeaponStats(){
		return weaponStats;
	}

	public void setWeaponHeaderStats(List<String> weaponHeaderStats){
		this.weaponHeaderStats = weaponHeaderStats;
	}

	public List<String> getWeaponHeaderStats(){
		return weaponHeaderStats;
	}

	public void setMagicHeaderStats(List<String> magicHeaderStats){
		this.magicHeaderStats = magicHeaderStats;
	}

	public List<String> getMagicHeaderStats(){
		return magicHeaderStats;
	}

	public void setHealthHeaderStats(List<String> healthHeaderStats){
		this.healthHeaderStats = healthHeaderStats;
	}

	public List<String> getHealthHeaderStats(){
		return healthHeaderStats;
	}

	@Override
 	public String toString(){
		return 
			"StatsDisplay{" + 
			"health_stats = '" + healthStats + '\'' + 
			",magic_stats = '" + magicStats + '\'' + 
			",weapon_stats = '" + weaponStats + '\'' + 
			",weapon_header_stats = '" + weaponHeaderStats + '\'' + 
			",magic_header_stats = '" + magicHeaderStats + '\'' + 
			",health_header_stats = '" + healthHeaderStats + '\'' + 
			"}";
		}
}