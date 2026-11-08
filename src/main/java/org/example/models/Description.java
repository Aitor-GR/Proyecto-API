package org.example.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
/** Modelo con la descripción, el rol y el estilo de juego. */
public class Description{

	// Crea una descripción vacía para rellenarla con datos.
	public Description() {
	}

	private String role;
	private String lore;
	private String playstyle;

	// Actualiza role con el valor recibido.
	public void setRole(String role){
		this.role = role;
	}

	// Devuelve role.
	public String getRole(){
		return role;
	}

	// Actualiza lore con el valor recibido.
	public void setLore(String lore){
		this.lore = lore;
	}

	// Devuelve lore.
	public String getLore(){
		return lore;
	}

	// Actualiza playstyle con el valor recibido.
	public void setPlaystyle(String playstyle){
		this.playstyle = playstyle;
	}

	// Devuelve playstyle.
	public String getPlaystyle(){
		return playstyle;
	}

	@Override
	// Realiza la operación indicada por el nombre del método.
	public String toString() {
		return "Description{" +
				"role='" + role + '\'' +
				", lore='" + lore + '\'' +
				", playstyle='" + playstyle + '\'' +
				'}';
	}
}
