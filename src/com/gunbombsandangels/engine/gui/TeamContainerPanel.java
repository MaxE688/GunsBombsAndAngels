package com.gunbombsandangels.engine.gui;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.gunbombsandangels.engine.board.Board;
import com.gunbombsandangels.engine.board.Team;

@SuppressWarnings("serial")
public class TeamContainerPanel extends JPanel {
	
	//private GameFrame gameFrame;
	private int lifeIcon;
	private Board board;
	private List<Team> teams;
	private List<TeamPanel> teamPanels;
	
	TeamContainerPanel(final GameFrame gameFrame, final Board board){
		super(new GridLayout(board.getNumberOfTeams(), 1));
		this.board = board;
		teams = board.getTeams();
		setPreferredSize(GameFrame.TEAM_PANEL_DIMENSION);
		setBackground(GameFrame.TEAM_PANEL_COLOR);
		teamPanels = new ArrayList<>();
		
		for(final Team team : teams) {
			LivingTeamPanel newPanel = new LivingTeamPanel(this, team, board);
			
			teamPanels.add(newPanel);
			this.add(newPanel);
		}
	}
	
	public void redo() {
		for(TeamPanel panel : teamPanels) {
			panel.redo();
		}
	}
	public void updateNames() {
		for(TeamPanel panel : teamPanels) {
			panel.updateName();
		}
	}

	public void makeAttackButtons(int damage) {
		try {
			for(TeamPanel panel : teamPanels) {
				if(panel.getTeam().getTeam() != board.getCurrentTeam().getTeam()) {
					panel.makeAttackButton(damage);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void resetButtons() {
		for(TeamPanel panel : teamPanels) {
			if(panel instanceof LivingTeamPanel) {
				panel.getButtonPanel().remove(panel.getGameButton());
				panel.getButtonPanel().add(panel.getGameButtonPanel(), 0);
			}
		}
		validate();
		repaint();
	}

	public void makeTeamSelectButtons() {
		try {
			if(!board.isTeamSelected()) {
				for(TeamPanel panel : teamPanels) {
					panel.makeTeamSelectButton();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setCurrentTeam(Team team) {
		board.setCurrentTeam(team);
		board.isTeamSelected(true);
		
	}

	public void endTurn() {
		board.getEffectManager().endTurn();
	}

	public void killTeam(int team) {
		
		this.removeAll();
		
		for(int i = 0; i < teamPanels.size(); i++) {
			if(teamPanels.get(i).getTeam().getTeam() == team && teamPanels.get(i) instanceof LivingTeamPanel) {
				teamPanels.set(i, new DeadTeamPanel(teamPanels.get(i).getTeam()));
			}
			this.add(teamPanels.get(i));
		}
		validate();
	}

	public void updateLifeIcon(int i) {
		this.lifeIcon = i;
		for(TeamPanel team : teamPanels) {
			if(team instanceof LivingTeamPanel) {
				team.updateLifeIcon(lifeIcon);
			}
		}
	}
	
	public List<Team> getTeams(){
		return this.teams;
	}

	public int getLifeIcon() {
		return this.lifeIcon;
	}
}
