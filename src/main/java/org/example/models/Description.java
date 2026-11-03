package org.example.models;

public class Description{
	private String role;
	private String lore;
	private String playstyle;

	public void setRole(String role){
		this.role = role;
	}

	public String getRole(){
		return role;
	}

	public void setLore(String lore){
		this.lore = lore;
	}

	public String getLore(){
		return lore;
	}

	public void setPlaystyle(String playstyle){
		this.playstyle = playstyle;
	}

	public String getPlaystyle(){
		return playstyle;
	}

	@Override
 	public String toString(){
		return 
			"Description{" + 
			"role = '" + role + '\'' + 
			",lore = '" + lore + '\'' + 
			",playstyle = '" + playstyle + '\'' + 
			"}";
		}
}
