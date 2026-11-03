package org.example.models;

public class UpgradeWarpStone{
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
			"UpgradeWarpStone{" + 
			"bucket = '" + bucket + '\'' + 
			",weight = '" + weight + '\'' + 
			"}";
		}
}
