package com.gunbombsandangels.engine.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.gunbombsandangels.engine.board.Board;
import com.gunbombsandangels.engine.board.Team;

@SuppressWarnings("serial")
public class LivingTeamPanel extends TeamPanel {
	
	private TeamContainerPanel teamContainerPanel;
	private JPanel teamInfoPanel;
	private JPanel teamLabelPanel;
	private JPanel teamLivesPanel;
	private JPanel buttonPanelContainer;
	private JPanel gameButtonPanel;
	private JButton overrideButton;
	private JButton gameButton;
	private JLabel teamNameLabel;
	private Team team;
	private SoundManager sm;
	
	private Dimension gameButtonSize;
	private Dimension gameButtonMaxSize;
	private int buttonPanelWidth;
	private int teamInfoWidth;
	private int teamPanelHeight;
	private String lifeIcon;
	
	LivingTeamPanel(final TeamContainerPanel teamContainerPanel, final Team team, Board board){
		super(new BorderLayout(), team);
		
		sm = new SoundManager();
		
		lifeIcon = "Original";
		
		buttonPanelWidth = (int) GameFrame.TEAM_PANEL_DIMENSION.getWidth() / 3;
		teamInfoWidth = (int) GameFrame.TEAM_PANEL_DIMENSION.getWidth() - buttonPanelWidth;
		teamPanelHeight = (int) GameFrame.TEAM_PANEL_DIMENSION.getHeight() / board.getNumberOfTeams();
		
		gameButtonSize = new Dimension(buttonPanelWidth - 5, teamPanelHeight / 2);
		gameButtonMaxSize = new Dimension(buttonPanelWidth, teamPanelHeight / 2);
		
		this.setBorder(BorderFactory.createMatteBorder(1, 2, 1, 2, Color.BLACK));
		this.teamContainerPanel = teamContainerPanel;
		setBackground(GameFrame.TEAM_PANEL_COLOR);
		teamInfoPanel = new JPanel(new GridLayout(2,1));
		teamInfoPanel.setBackground(GameFrame.TEAM_PANEL_COLOR);
		//teamInfoPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.WHITE));
		teamInfoPanel.setPreferredSize(new Dimension(teamInfoWidth, teamPanelHeight));
		teamLabelPanel = new JPanel();
		teamLabelPanel.setBackground(GameFrame.TEAM_PANEL_COLOR);
		//teamLabelPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLUE));
		teamLivesPanel = new JPanel();
		teamLivesPanel.setBackground(GameFrame.TEAM_PANEL_COLOR);
		//teamLivesPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GREEN));
		buttonPanelContainer = new JPanel();
		buttonPanelContainer.setLayout(new BoxLayout(buttonPanelContainer, BoxLayout.PAGE_AXIS));
		//buttonPanelContainer.setLayout(new BorderLayout());
		buttonPanelContainer.setBackground(GameFrame.TEAM_PANEL_COLOR);
		buttonPanelContainer.setPreferredSize(new Dimension(buttonPanelWidth, teamPanelHeight));
		//buttonPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
		gameButtonPanel = new JPanel();
		gameButtonPanel.setBackground(GameFrame.TEAM_PANEL_COLOR);
		
		
		try {
			makeButtonPanel();
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.team = team;
		
		teamNameLabel = new JLabel(team.getTeamName());
		formatTeamName();
		teamLabelPanel.add(teamNameLabel);
		teamInfoPanel.add(teamLabelPanel);
		
		drawLives();
		
		this.add(teamInfoPanel, BorderLayout.WEST);
		this.add(buttonPanelContainer, BorderLayout.EAST);
	}
	
	private void makeButtonPanel() throws IOException {
		
		final BufferedImage image = ImageIO.read(new File("res/overrideButton.png"));
		final ImageIcon imageIcon = new ImageIcon(image);
		overrideButton = new JButton(imageIcon);
		overrideButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				team.setLives(team.getLives() - 1);
				teamContainerPanel.resetButtons();
				teamContainerPanel.endTurn();
				redo();
			}
			
		});
		overrideButton.setPreferredSize(gameButtonSize);
		overrideButton.setMaximumSize(gameButtonMaxSize);
		
		buttonPanelContainer.add(gameButtonPanel, 0);
		buttonPanelContainer.add(overrideButton, 1);
	}

	private void formatTeamName() {
		Font teamNameFont = teamNameLabel.getFont();
		String teamNameText = teamNameLabel.getText();
		
		int stringWidth = teamNameLabel.getFontMetrics(teamNameFont).stringWidth(teamNameText);
		int componentWidth = teamInfoWidth;//teamInfoPanel.getWidth();
		
		double widthRatio = (double)componentWidth / (double)stringWidth;
		
		int newFontSize = (int)(teamNameFont.getSize() * widthRatio);
		int componentHeight = teamPanelHeight/2;//teamNameLabel.getHeight();
		
		int fontSizeToUse = Math.min(newFontSize, componentHeight);
		
		teamNameLabel.setFont(new Font(teamNameFont.getName(), Font.PLAIN, fontSizeToUse));
	}

	public void makeAttackButton(int damage) throws IOException {
			final BufferedImage image = ImageIO.read(new File("res/attackButton.png"));
			final ImageIcon imageIcon = new ImageIcon(image);
			JButton button = new JButton(imageIcon);
			
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					if(damage == 1) {
						sm.playSmallGun();
					}
					else {
						sm.playBigGun();
					}
					
					team.setLives(team.getLives() - damage);
					teamContainerPanel.resetButtons();
					teamContainerPanel.endTurn();
					redo();
					System.out.println("Button Clicked - Team " + team.getTeam() + " Lives: " + team.getLives());
				}
			});
			
			button.setPreferredSize(gameButtonSize);
			button.setMaximumSize(gameButtonMaxSize);
			
			gameButton = button;
			
			buttonPanelContainer.remove(gameButtonPanel);
			
			buttonPanelContainer.add(gameButton, 0);
			validate();
	}
	
	private void drawLives() {
		for(int i = 0; i < team.getLives(); i++) {
			try {
				final BufferedImage image = ImageIO.read(new File("res/lifeIcons/" + lifeIcon + ".png"));
				final ImageIcon imageIcon = new ImageIcon(image);
				teamLivesPanel.add(new JLabel(imageIcon));
			}
			 catch (IOException e) {
				e.printStackTrace();
			}
		}
		teamInfoPanel.add(teamLivesPanel);
	}
	
	public void redo() {
		teamLivesPanel.removeAll();
		drawLives();
		validate();
		repaint();
	}
	
	public void updateLifeIcon(int icon) {
		switch(icon) {
		case 0:
			lifeIcon = "Original";
			break;
		case 1:
			lifeIcon = "Heart";
			break;
		case 2:
			lifeIcon = "Face";
			break;
		case 3:
			lifeIcon = "Star";
			break;
		}
		
		
		redo();
	}
	
	public JPanel getButtonPanel() {
		return this.buttonPanelContainer;
	}
	public JPanel getGameButtonPanel() {
		return this.gameButtonPanel;
	}

	public void makeTeamSelectButton() throws IOException {
		final BufferedImage image = ImageIO.read(new File("res/teamSelectButton.png"));
		final ImageIcon imageIcon = new ImageIcon(image);
		JButton button = new JButton(imageIcon);
		button.setMaximumSize(gameButtonPanel.getSize());
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				teamContainerPanel.setCurrentTeam(team);
				teamContainerPanel.resetButtons();
			}
		});
		button.setPreferredSize(gameButtonSize);
		button.setMaximumSize(gameButtonMaxSize);
		
		buttonPanelContainer.remove(gameButtonPanel);
		
		gameButton = button;
		buttonPanelContainer.add(gameButton, 0);
		validate();
	}

	@Override
	public void updateName() {
		teamLabelPanel.removeAll();
		teamNameLabel.setText(team.getTeamName());
		teamLabelPanel.add(teamNameLabel);
		validate();
		repaint();
	}

	@Override
	public JButton getGameButton() {
		return this.gameButton;
	}

}

