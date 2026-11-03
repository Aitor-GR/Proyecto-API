package org.example.models;

import java.util.List;

public class JsonMember1{
	private List<String> bonusCurrencies;
	private int requiredGold;

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
			"JsonMember1{" + 
			"bonus_currencies = '" + bonusCurrencies + '\'' + 
			",required_gold = '" + requiredGold + '\'' + 
			"}";
		}
}