package org.example.models;

import java.util.List;

public class Colors{
	private String styleHex;
	private List<Integer> ui;
	private List<Integer> style;

	public void setStyleHex(String styleHex){
		this.styleHex = styleHex;
	}

	public String getStyleHex(){
		return styleHex;
	}

	public void setUi(List<Integer> ui){
		this.ui = ui;
	}

	public List<Integer> getUi(){
		return ui;
	}

	public void setStyle(List<Integer> style){
		this.style = style;
	}

	public List<Integer> getStyle(){
		return style;
	}

	@Override
 	public String toString(){
		return 
			"Colors{" + 
			"style_hex = '" + styleHex + '\'' + 
			",ui = '" + ui + '\'' + 
			",style = '" + style + '\'' + 
			"}";
		}
}