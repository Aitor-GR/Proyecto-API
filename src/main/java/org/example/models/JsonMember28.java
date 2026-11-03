package org.example.models;

import java.util.List;

public class JsonMember28{
	private boolean useStandardUpgrade;
	private List<String> bonusCurrencies;
	private int requiredGold;

	public void setUseStandardUpgrade(boolean useStandardUpgrade){
		this.useStandardUpgrade = useStandardUpgrade;
	}

	public boolean isUseStandardUpgrade(){
		return useStandardUpgrade;
	}

	public void setBonusCurrencies(List<String> bonusCurrencies){
		this.bonusCurrencies = bonusCurrencies;
	}

	public List<String> getBonusCurrencies(){
		return bonusCurrencies;
	}

	public void setRequiredGold(int requiredGold){
		this.requiredGold = requiredGold;
	}

	public int getRequiredGold(){
		return requiredGold;
	}

	@Override
 	public String toString(){
		return 
			"JsonMember28{" + 
			"use_standard_upgrade = '" + useStandardUpgrade + '\'' + 
			",bonus_currencies = '" + bonusCurrencies + '\'' + 
			",required_gold = '" + requiredGold + '\'' + 
			"}";
		}
}