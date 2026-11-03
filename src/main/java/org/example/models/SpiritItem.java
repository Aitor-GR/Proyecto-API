package org.example.models;

public class SpiritItem{
	private Object bonus;
	private int goldThreshold;
	private Object percentOnGraph;

	public void setBonus(Object bonus){
		this.bonus = bonus;
	}

	public Object getBonus(){
		return bonus;
	}

	public void setGoldThreshold(int goldThreshold){
		this.goldThreshold = goldThreshold;
	}

	public int getGoldThreshold(){
		return goldThreshold;
	}

	public void setPercentOnGraph(Object percentOnGraph){
		this.percentOnGraph = percentOnGraph;
	}

	public Object getPercentOnGraph(){
		return percentOnGraph;
	}

	@Override
 	public String toString(){
		return 
			"SpiritItem{" + 
			"bonus = '" + bonus + '\'' + 
			",gold_threshold = '" + goldThreshold + '\'' + 
			",percent_on_graph = '" + percentOnGraph + '\'' + 
			"}";
		}
}
