package org.example.models;

import java.util.List;

public class PopularItems{
	private List<LateGameItem> lateGame;
	private List<MidGameItem> midGame;
	private List<EarlyGameItem> earlyGame;
	private int timestamp;

	public void setLateGame(List<LateGameItem> lateGame){
		this.lateGame = lateGame;
	}

	public List<LateGameItem> getLateGame(){
		return lateGame;
	}

	public void setMidGame(List<MidGameItem> midGame){
		this.midGame = midGame;
	}

	public List<MidGameItem> getMidGame(){
		return midGame;
	}

	public void setEarlyGame(List<EarlyGameItem> earlyGame){
		this.earlyGame = earlyGame;
	}

	public List<EarlyGameItem> getEarlyGame(){
		return earlyGame;
	}

	public void setTimestamp(int timestamp){
		this.timestamp = timestamp;
	}

	public int getTimestamp(){
		return timestamp;
	}

	@Override
 	public String toString(){
		return 
			"PopularItems{" + 
			"late_game = '" + lateGame + '\'' + 
			",mid_game = '" + midGame + '\'' + 
			",early_game = '" + earlyGame + '\'' + 
			",timestamp = '" + timestamp + '\'' + 
			"}";
		}
}