package org.example.models;

import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Hero {

	public Hero() {
	}

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
	private Map<String, String> images;

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

	public void setImages(Map<String, String> images){
		this.images = images;
	}

	public Map<String, String> getImages(){
		return images;
	}

	@Override
	public String toString() {
		return "Hero{" +
				"id=" + id +
				", name='" + name + '\'' +
				", heroType='" + heroType + '\'' +
				", className='" + className + '\'' +
				", gender='" + gender + '\'' +
				", tags=" + tags +
				'}';
	}
}