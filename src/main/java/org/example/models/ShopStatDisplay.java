package org.example.models;

public class ShopStatDisplay{
	private VitalityStatsDisplay vitalityStatsDisplay;
	private WeaponStatsDisplay weaponStatsDisplay;
	private SpiritStatsDisplay spiritStatsDisplay;

	public void setVitalityStatsDisplay(VitalityStatsDisplay vitalityStatsDisplay){
		this.vitalityStatsDisplay = vitalityStatsDisplay;
	}

	public VitalityStatsDisplay getVitalityStatsDisplay(){
		return vitalityStatsDisplay;
	}

	public void setWeaponStatsDisplay(WeaponStatsDisplay weaponStatsDisplay){
		this.weaponStatsDisplay = weaponStatsDisplay;
	}

	public WeaponStatsDisplay getWeaponStatsDisplay(){
		return weaponStatsDisplay;
	}

	public void setSpiritStatsDisplay(SpiritStatsDisplay spiritStatsDisplay){
		this.spiritStatsDisplay = spiritStatsDisplay;
	}

	public SpiritStatsDisplay getSpiritStatsDisplay(){
		return spiritStatsDisplay;
	}

	@Override
 	public String toString(){
		return 
			"ShopStatDisplay{" + 
			"vitality_stats_display = '" + vitalityStatsDisplay + '\'' + 
			",weapon_stats_display = '" + weaponStatsDisplay + '\'' + 
			",spirit_stats_display = '" + spiritStatsDisplay + '\'' + 
			"}";
		}
}
