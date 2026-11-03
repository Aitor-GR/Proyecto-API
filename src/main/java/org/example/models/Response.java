package org.example.models;

import java.util.List;

public class Response{
	private List<Hero> response;

	public void setResponse(List<Hero> response){
		this.response = response;
	}

	public List<Hero> getResponse(){
		return response;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"response = '" + response + '\'' + 
			"}";
		}
}