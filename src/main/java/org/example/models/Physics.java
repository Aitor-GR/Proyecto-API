package org.example.models;

public class Physics{
	private Object stealthSpeedMetersPerSecond;
	private Object stepHeight;
	private Object collisionRadius;
	private Object stepSoundTime;

	public void setStealthSpeedMetersPerSecond(Object stealthSpeedMetersPerSecond){
		this.stealthSpeedMetersPerSecond = stealthSpeedMetersPerSecond;
	}

	public Object getStealthSpeedMetersPerSecond(){
		return stealthSpeedMetersPerSecond;
	}

	public void setStepHeight(Object stepHeight){
		this.stepHeight = stepHeight;
	}

	public Object getStepHeight(){
		return stepHeight;
	}

	public void setCollisionRadius(Object collisionRadius){
		this.collisionRadius = collisionRadius;
	}

	public Object getCollisionRadius(){
		return collisionRadius;
	}

	public void setStepSoundTime(Object stepSoundTime){
		this.stepSoundTime = stepSoundTime;
	}

	public Object getStepSoundTime(){
		return stepSoundTime;
	}

	@Override
 	public String toString(){
		return 
			"Physics{" + 
			"stealth_speed_meters_per_second = '" + stealthSpeedMetersPerSecond + '\'' + 
			",step_height = '" + stepHeight + '\'' + 
			",collision_radius = '" + collisionRadius + '\'' + 
			",step_sound_time = '" + stepSoundTime + '\'' + 
			"}";
		}
}
