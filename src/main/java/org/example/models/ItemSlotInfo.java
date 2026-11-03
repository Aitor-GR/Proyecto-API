package org.example.models;

public class ItemSlotInfo{
	private Weapon weapon;
	private Vitality vitality;
	private Spirit spirit;

	public void setWeapon(Weapon weapon){
		this.weapon = weapon;
	}

	public Weapon getWeapon(){
		return weapon;
	}

	public void setVitality(Vitality vitality){
		this.vitality = vitality;
	}

	public Vitality getVitality(){
		return vitality;
	}

	public void setSpirit(Spirit spirit){
		this.spirit = spirit;
	}

	public Spirit getSpirit(){
		return spirit;
	}

	@Override
 	public String toString(){
		return 
			"ItemSlotInfo{" + 
			"weapon = '" + weapon + '\'' + 
			",vitality = '" + vitality + '\'' + 
			",spirit = '" + spirit + '\'' + 
			"}";
		}
}
