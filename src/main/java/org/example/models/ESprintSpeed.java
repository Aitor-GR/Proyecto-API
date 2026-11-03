package org.example.models;

public class ESprintSpeed{
	private String scalingStat;
	private Object scale;

	public void setScalingStat(String scalingStat){
		this.scalingStat = scalingStat;
	}

	public String getScalingStat(){
		return scalingStat;
	}

	public void setScale(Object scale){
		this.scale = scale;
	}

	public Object getScale(){
		return scale;
	}

	@Override
 	public String toString(){
		return 
			"ESprintSpeed{" + 
			"scaling_stat = '" + scalingStat + '\'' + 
			",scale = '" + scale + '\'' + 
			"}";
		}
}
