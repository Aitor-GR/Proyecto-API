package org.example.models;

import java.util.List;

public class CostBonuses{
	private List<WeaponItem> weapon;
	private List<VitalityItem> vitality;
	private List<SpiritItem> spirit;

	public void setWeapon(List<WeaponItem> weapon){
		this.weapon = weapon;
	}

	public List<WeaponItem> getWeapon(){
		return weapon;
	}

	public void setVitality(List<VitalityItem> vitality){
		this.vitality = vitality;
	}

	public List<VitalityItem> getVitality(){
		return vitality;
	}

	public void setSpirit(List<SpiritItem> spirit){
		this.spirit = spirit;
	}

	public List<SpiritItem> getSpirit(){
		return spirit;
	}

	@Override
 	public String toString(){
		return 
			"CostBonuses{" + 
			"weapon = '" + weapon + '\'' + 
			",vitality = '" + vitality + '\'' + 
			",spirit = '" + spirit + '\'' + 
			"}";
		}
}