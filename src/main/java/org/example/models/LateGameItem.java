package org.example.models;

public class LateGameItem{
	private Object pickPct;
	private long itemId;
	private Object winratePct;
	private String className;

	public void setPickPct(Object pickPct){
		this.pickPct = pickPct;
	}

	public Object getPickPct(){
		return pickPct;
	}

	public void setItemId(long itemId){
		this.itemId = itemId;
	}

	public long getItemId(){
		return itemId;
	}

	public void setWinratePct(Object winratePct){
		this.winratePct = winratePct;
	}

	public Object getWinratePct(){
		return winratePct;
	}

	public void setClassName(String className){
		this.className = className;
	}

	public String getClassName(){
		return className;
	}

	@Override
 	public String toString(){
		return 
			"LateGameItem{" + 
			"pick_pct = '" + pickPct + '\'' + 
			",item_id = '" + itemId + '\'' + 
			",winrate_pct = '" + winratePct + '\'' + 
			",class_name = '" + className + '\'' + 
			"}";
		}
}
