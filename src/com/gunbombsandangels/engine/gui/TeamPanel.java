package com.gunbombsandangels.engine.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.gunbombsandangels.engine.board.Team;

@SuppressWarnings("serial")
public abstract class TeamPanel extends JPanel {
	
	private Team team;
	
	public TeamPanel(Team team) {
		super();
		this.team = team;
	}
	
	public TeamPanel(BorderLayout borderLayout, Team team) {
		super(borderLayout);
		this.team = team;
	}


	public abstract void redo();

	public Team getTeam() {
		return this.team;
	};

	public abstract void makeAttackButton(int damage) throws IOException;

	public abstract Container getButtonPanel();
	
	public abstract Container getGameButtonPanel();

	public abstract void makeTeamSelectButton() throws IOException;

	public abstract void updateLifeIcon(int i);

	public abstract void updateName();

	public abstract JButton getGameButton(); 

}
