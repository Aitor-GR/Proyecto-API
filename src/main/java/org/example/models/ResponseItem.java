package org.example.models;

import java.util.List;

public class ResponseItem{
	private boolean assignedPlayersOnly;
	private String gender;
	private Description description;
	private String developmentState;
	private boolean inDevelopment;
	private List<String> tags;
	private String name;
	private String gunTag;
	private boolean disabled;
	private String heroType;
	private int id;
	private boolean playerSelectable;
	private String searchName;
	private boolean needsTesting;
	private String className;
	private String hideoutRichPresence;

	public void setAssignedPlayersOnly(boolean assignedPlayersOnly){
		this.assignedPlayersOnly = assignedPlayersOnly;
	}

	public boolean isAssignedPlayersOnly(){
		return assignedPlayersOnly;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public void setDescription(Description description){
		this.description = description;
	}

	public Description getDescription(){
		return description;
	}

	public void setDevelopmentState(String developmentState){
		this.developmentState = developmentState;
	}

	public String getDevelopmentState(){
		return developmentState;
	}

	public void setInDevelopment(boolean inDevelopment){
		this.inDevelopment = inDevelopment;
	}

	public boolean isInDevelopment(){
		return inDevelopment;
	}

	public void setTags(List<String> tags){
		this.tags = tags;
	}

	public List<String> getTags(){
		return tags;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setGunTag(String gunTag){
		this.gunTag = gunTag;
	}

	public String getGunTag(){
		return gunTag;
	}

	public void setDisabled(boolean disabled){
		this.disabled = disabled;
	}

	public boolean isDisabled(){
		return disabled;
	}

	public void setHeroType(String heroType){
		this.heroType = heroType;
	}

	public String getHeroType(){
		return heroType;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setPlayerSelectable(boolean playerSelectable){
		this.playerSelectable = playerSelectable;
	}

	public boolean isPlayerSelectable(){
		return playerSelectable;
	}

	public void setSearchName(String searchName){
		this.searchName = searchName;
	}

	public String getSearchName(){
		return searchName;
	}

	public void setNeedsTesting(boolean needsTesting){
		this.needsTesting = needsTesting;
	}

	public boolean isNeedsTesting(){
		return needsTesting;
	}

	public void setClassName(String className){
		this.className = className;
	}

	public String getClassName(){
		return className;
	}

	public void setHideoutRichPresence(String hideoutRichPresence){
		this.hideoutRichPresence = hideoutRichPresence;
	}

	public String getHideoutRichPresence(){
		return hideoutRichPresence;
	}

	@Override
 	public String toString(){
		return 
			"ResponseItem{" + 
			"assigned_players_only = '" + assignedPlayersOnly + '\'' + 
			",gender = '" + gender + '\'' + 
			",description = '" + description + '\'' + 
			",development_state = '" + developmentState + '\'' + 
			",in_development = '" + inDevelopment + '\'' + 
			",tags = '" + tags + '\'' + 
			",name = '" + name + '\'' + 
			",gun_tag = '" + gunTag + '\'' + 
			",disabled = '" + disabled + '\'' + 
			",hero_type = '" + heroType + '\'' + 
			",id = '" + id + '\'' + 
			",player_selectable = '" + playerSelectable + '\'' + 
			",search_name = '" + searchName + '\'' + 
			",needs_testing = '" + needsTesting + '\'' + 
			",class_name = '" + className + '\'' + 
			",hideout_rich_presence = '" + hideoutRichPresence + '\'' + 
			"}";
		}
}