package org.example.models;

public class UpgradeRapidRounds{
	private String bucket;
	private Object weight;

	public void setBucket(String bucket){
		this.bucket = bucket;
	}

	public String getBucket(){
		return bucket;
	}

	public void setWeight(Object weight){
		this.weight = weight;
	}

	public Object getWeight(){
		return weight;
	}

	@Override
 	public String toString(){
		return 
			"UpgradeRapidRounds{" + 
			"bucket = '" + bucket + '\'' + 
			",weight = '" + weight + '\'' + 
			"}";
		}
}
