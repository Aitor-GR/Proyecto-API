package org.example.models;

import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
/** Modelo que representa los datos de un héroe. */
public class Hero {

	// Crea un objeto Hero vacío para cargar sus datos.
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

	// Actualiza assigned players only con el valor recibido.
	public void setAssignedPlayersOnly(boolean assignedPlayersOnly){
		this.assignedPlayersOnly = assignedPlayersOnly;
	}

	// Devuelve assigned players only.
	public boolean isAssignedPlayersOnly(){
		return assignedPlayersOnly;
	}

	// Actualiza gender con el valor recibido.
	public void setGender(String gender){
		this.gender = gender;
	}

	// Devuelve gender.
	public String getGender(){
		return gender;
	}

	// Actualiza description con el valor recibido.
	public void setDescription(Description description){
		this.description = description;
	}

	// Devuelve description.
	public Description getDescription(){
		return description;
	}

	// Actualiza development state con el valor recibido.
	public void setDevelopmentState(String developmentState){
		this.developmentState = developmentState;
	}

	// Devuelve development state.
	public String getDevelopmentState(){
		return developmentState;
	}

	// Actualiza in development con el valor recibido.
	public void setInDevelopment(boolean inDevelopment){
		this.inDevelopment = inDevelopment;
	}

	// Devuelve in development.
	public boolean isInDevelopment(){
		return inDevelopment;
	}

	// Actualiza tags con el valor recibido.
	public void setTags(List<String> tags){
		this.tags = tags;
	}

	// Devuelve tags.
	public List<String> getTags(){
		return tags;
	}

	// Actualiza name con el valor recibido.
	public void setName(String name){
		this.name = name;
	}

	// Devuelve name.
	public String getName(){
		return name;
	}

	// Actualiza gun tag con el valor recibido.
	public void setGunTag(String gunTag){
		this.gunTag = gunTag;
	}

	// Devuelve gun tag.
	public String getGunTag(){
		return gunTag;
	}

	// Actualiza disabled con el valor recibido.
	public void setDisabled(boolean disabled){
		this.disabled = disabled;
	}

	// Devuelve disabled.
	public boolean isDisabled(){
		return disabled;
	}

	// Actualiza hero type con el valor recibido.
	public void setHeroType(String heroType){
		this.heroType = heroType;
	}

	// Devuelve hero type.
	public String getHeroType(){
		return heroType;
	}

	// Actualiza id con el valor recibido.
	public void setId(int id){
		this.id = id;
	}

	// Devuelve id.
	public int getId(){
		return id;
	}

	// Actualiza player selectable con el valor recibido.
	public void setPlayerSelectable(boolean playerSelectable){
		this.playerSelectable = playerSelectable;
	}

	// Devuelve player selectable.
	public boolean isPlayerSelectable(){
		return playerSelectable;
	}

	// Actualiza search name con el valor recibido.
	public void setSearchName(String searchName){
		this.searchName = searchName;
	}

	// Devuelve search name.
	public String getSearchName(){
		return searchName;
	}

	// Actualiza needs testing con el valor recibido.
	public void setNeedsTesting(boolean needsTesting){
		this.needsTesting = needsTesting;
	}

	// Devuelve needs testing.
	public boolean isNeedsTesting(){
		return needsTesting;
	}

	// Actualiza class name con el valor recibido.
	public void setClassName(String className){
		this.className = className;
	}

	// Devuelve class name.
	public String getClassName(){
		return className;
	}

	// Actualiza hideout rich presence con el valor recibido.
	public void setHideoutRichPresence(String hideoutRichPresence){
		this.hideoutRichPresence = hideoutRichPresence;
	}

	// Devuelve hideout rich presence.
	public String getHideoutRichPresence(){
		return hideoutRichPresence;
	}

	// Actualiza images con el valor recibido.
	public void setImages(Map<String, String> images){
		this.images = images;
	}

	// Devuelve el valor de este atributo.
	public Map<String, String> getImages(){
		return images;
	}

	@Override
	// Realiza la operación indicada por el nombre del método.
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