package com.gunbombsandangels.engine.board;

public class Team {
	
	private final int teamNumber;
	private int lives;
	private String teamName;
	//private EffectManager effectManager;
	
	public Team(final int teamNumber, final int lives, String name){
		this.teamNumber = teamNumber;
		this.lives = lives;
		teamName = name;
	}
	
	public void setLives(final int lives) {
		if(lives < 0) {
			this.lives = 0;
		}
		else {
			this.lives = lives;
		}
	}
	public int getLives() {
		return this.lives;
	}
	public int getTeam() {
		return this.teamNumber;
	}
	public boolean isAlive() {
		return this.lives > 0 ? true : false;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setName(String text) {
		teamName = text;
	}
	
}
