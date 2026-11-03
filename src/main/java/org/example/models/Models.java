package org.example.models;

public class Models{
	private String heroSelect;
	private String idle;

	public void setHeroSelect(String heroSelect){
		this.heroSelect = heroSelect;
	}

	public String getHeroSelect(){
		return heroSelect;
	}

	public void setIdle(String idle){
		this.idle = idle;
	}

	public String getIdle(){
		return idle;
	}

	@Override
 	public String toString(){
		return 
			"Models{" + 
			"hero_select = '" + heroSelect + '\'' + 
			",idle = '" + idle + '\'' + 
			"}";
		}
}
