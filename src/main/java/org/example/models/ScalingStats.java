package org.example.models;

public class ScalingStats{
	private ETechArmorDamageReduction eTechArmorDamageReduction;
	private EBulletDamage eBulletDamage;
	private ESprintSpeed eSprintSpeed;
	private EHeavyMeleeDamage eHeavyMeleeDamage;
	private EBaseHealthRegen eBaseHealthRegen;
	private EBulletArmorDamageReduction eBulletArmorDamageReduction;
	private EMaxMoveSpeed eMaxMoveSpeed;
	private EClipSize eClipSize;
	private ERoundsPerSecond eRoundsPerSecond;
	private EFireRate eFireRate;

	public void setETechArmorDamageReduction(ETechArmorDamageReduction eTechArmorDamageReduction){
		this.eTechArmorDamageReduction = eTechArmorDamageReduction;
	}

	public ETechArmorDamageReduction getETechArmorDamageReduction(){
		return eTechArmorDamageReduction;
	}

	public void setEBulletDamage(EBulletDamage eBulletDamage){
		this.eBulletDamage = eBulletDamage;
	}

	public EBulletDamage getEBulletDamage(){
		return eBulletDamage;
	}

	public void setESprintSpeed(ESprintSpeed eSprintSpeed){
		this.eSprintSpeed = eSprintSpeed;
	}

	public ESprintSpeed getESprintSpeed(){
		return eSprintSpeed;
	}

	public void setEHeavyMeleeDamage(EHeavyMeleeDamage eHeavyMeleeDamage){
		this.eHeavyMeleeDamage = eHeavyMeleeDamage;
	}

	public EHeavyMeleeDamage getEHeavyMeleeDamage(){
		return eHeavyMeleeDamage;
	}

	public void setEBaseHealthRegen(EBaseHealthRegen eBaseHealthRegen){
		this.eBaseHealthRegen = eBaseHealthRegen;
	}

	public EBaseHealthRegen getEBaseHealthRegen(){
		return eBaseHealthRegen;
	}

	public void setEBulletArmorDamageReduction(EBulletArmorDamageReduction eBulletArmorDamageReduction){
		this.eBulletArmorDamageReduction = eBulletArmorDamageReduction;
	}

	public EBulletArmorDamageReduction getEBulletArmorDamageReduction(){
		return eBulletArmorDamageReduction;
	}

	public void setEMaxMoveSpeed(EMaxMoveSpeed eMaxMoveSpeed){
		this.eMaxMoveSpeed = eMaxMoveSpeed;
	}

	public EMaxMoveSpeed getEMaxMoveSpeed(){
		return eMaxMoveSpeed;
	}

	public void setEClipSize(EClipSize eClipSize){
		this.eClipSize = eClipSize;
	}

	public EClipSize getEClipSize(){
		return eClipSize;
	}

	public void setERoundsPerSecond(ERoundsPerSecond eRoundsPerSecond){
		this.eRoundsPerSecond = eRoundsPerSecond;
	}

	public ERoundsPerSecond getERoundsPerSecond(){
		return eRoundsPerSecond;
	}

	public void setEFireRate(EFireRate eFireRate){
		this.eFireRate = eFireRate;
	}

	public EFireRate getEFireRate(){
		return eFireRate;
	}

	@Override
 	public String toString(){
		return 
			"ScalingStats{" + 
			"eTechArmorDamageReduction = '" + eTechArmorDamageReduction + '\'' + 
			",eBulletDamage = '" + eBulletDamage + '\'' + 
			",eSprintSpeed = '" + eSprintSpeed + '\'' + 
			",eHeavyMeleeDamage = '" + eHeavyMeleeDamage + '\'' + 
			",eBaseHealthRegen = '" + eBaseHealthRegen + '\'' + 
			",eBulletArmorDamageReduction = '" + eBulletArmorDamageReduction + '\'' + 
			",eMaxMoveSpeed = '" + eMaxMoveSpeed + '\'' + 
			",eClipSize = '" + eClipSize + '\'' + 
			",eRoundsPerSecond = '" + eRoundsPerSecond + '\'' + 
			",eFireRate = '" + eFireRate + '\'' + 
			"}";
		}
}
