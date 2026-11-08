package org.example.models;

import java.util.List;

/** Modelo para representar una respuesta que contiene héroes. */
public class Response{
	private List<Hero> response;

	// Actualiza response con el valor recibido.
	public void setResponse(List<Hero> response){
		this.response = response;
	}

	// Devuelve response.
	public List<Hero> getResponse(){
		return response;
	}

	@Override
	// Devuelve una representacion en texto de la respuesta.
	public String toString(){
		return 
			"Response{" + 
			"response = '" + response + '\'' + 
			"}";
		}
}