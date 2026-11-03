package org.example.models;

import java.util.List;

public class Spirit{
	private List<Integer> maxPurchasesForTier;

	public void setMaxPurchasesForTier(List<Integer> maxPurchasesForTier){
		this.maxPurchasesForTier = maxPurchasesForTier;
	}

	public List<Integer> getMaxPurchasesForTier(){
		return maxPurchasesForTier;
	}

	@Override
 	public String toString(){
		return 
			"Spirit{" + 
			"max_purchases_for_tier = '" + maxPurchasesForTier + '\'' + 
			"}";
		}
}