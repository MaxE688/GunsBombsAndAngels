package com.gunbombsandangels.engine.board;

import com.gunbombsandangels.engine.gui.GameFrame;
import com.gunbombsandangels.engine.gui.SoundManager;

public class EffectManager {
	
	private SoundManager sm;
	private GameFrame gameFrame;
	private Board board;
	
	EffectManager(final Board board){
		sm = new SoundManager();
		this.board = board;
	}
	
	public void setGameFrame(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
	}

	public void executeSmallGun() {
		gameFrame.makeAttackButtons(1);
		
	}

	public void executeBigGun() {
		gameFrame.makeAttackButtons(2);
	}

	public void executeBomb() {
		board.getCurrentTeam().setLives(board.getCurrentTeam().getLives() - 1);
		sm.playBomb();
		endTurn();
	}

	public void executeAngel() {
		board.getCurrentTeam().setLives(board.getCurrentTeam().getLives() + 1);
		sm.playAngel();
		endTurn();
	}

	public void selectTeam() {
		gameFrame.makeTeamSelectButtons();
	}
	
	public void endTurn() {
		for(Team team : board.getTeams()) {
			if(!team.isAlive()) {
				gameFrame.killTeam(team.getTeam());
				if(board.isGameOver()) {
					gameFrame.gameOver(board.getWinner());
				}
			}
		}
		
		board.isTeamSelected(false);
		board.makeNextTurn();
	}

}
