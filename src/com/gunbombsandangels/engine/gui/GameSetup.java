package com.gunbombsandangels.engine.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import com.gunbombsandangels.engine.board.Board;

@SuppressWarnings("serial")
public class GameSetup extends JFrame {
	
	private JPanel mainPanel;
	private JPanel optionsPanel;
	private JPanel buttonPanel;
	private JSpinner numOfTeams;
	private JSpinner numOfLives;
	private JComboBox<String> lifeIcon;
	private JButton teamNamesButton;
	//private JButton setGameSymbols;
	private JButton okButton;
	private JButton cancelButton;
	
	//private List<String> teamNames;
	private NameSetter names;
	private Board board;
	private Color borderColor = Color.decode("#098eaf");
	private Color backgroundColor = Color.decode("#80c2da");
	
	public GameSetup(IntroFrame frame) {
		super();
		this.setUndecorated(true);
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, borderColor));
		optionsPanel = new JPanel(new GridLayout(9,2));
		buttonPanel = new JPanel();
		mainPanel.setBackground(backgroundColor);
		optionsPanel.setBackground(backgroundColor);
		buttonPanel.setBackground(backgroundColor);
		
		numOfTeams = new JSpinner(new SpinnerNumberModel(3, 2, 6, 1));
		numOfLives = new JSpinner(new SpinnerNumberModel(5, 1, 7, 1));
		
		names = new NameSetter(frame,(int) numOfTeams.getValue());
		
		DefaultComboBoxModel<String> lifeIconSelect = new DefaultComboBoxModel<String>();
		lifeIconSelect.addElement("Original");
		lifeIconSelect.addElement("Heart");
		lifeIconSelect.addElement("Face");
		lifeIconSelect.addElement("Star");
		lifeIcon = new JComboBox<String>(lifeIconSelect);
		
		teamNamesButton = new JButton("Change Team Names");
		teamNamesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				names.promptUser((int) numOfTeams.getValue());
			}
		});
		
//		setGameSymbols = new JButton("Configure Cards");
//		setGameSymbols.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
		
		okButton = new JButton("Play");
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				names.updateNameList((int) numOfTeams.getValue());
				board = new Board((int) numOfTeams.getValue(), (int) numOfLives.getValue(), names.getTeamNames());
				frame.startGame(board, lifeIcon.getSelectedIndex());
				setVisible(false);
			}
			
		});
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		
		optionsPanel.add(new JLabel());
		optionsPanel.add(new JLabel());
		optionsPanel.add(new JLabel(" Number of teams: "));
		optionsPanel.add(numOfTeams);
		optionsPanel.add(new JLabel());
		optionsPanel.add(new JLabel());
		optionsPanel.add(new JLabel(" Number of lives per team: "));
		optionsPanel.add(numOfLives);
		optionsPanel.add(new JLabel());
		optionsPanel.add(new JLabel());
		optionsPanel.add(new JLabel(" Select the icon for team lives: "));
		optionsPanel.add(lifeIcon);
		optionsPanel.add(new JLabel());
		optionsPanel.add(new JLabel());
		optionsPanel.add(new JLabel(" Change team names: "));
		optionsPanel.add(teamNamesButton);
//		optionsPanel.add(new JLabel(" Change the card configuration: "));
//		optionsPanel.add(setGameSymbols);
		
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		
		mainPanel.add(optionsPanel, BorderLayout.CENTER);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		this.add(mainPanel);
		pack();
		setLocation();
		setVisible(true);
	}
	
	private void setLocation() {
		final int x = ((GameFrame.SCREEN_WIDTH - this.getWidth() ) / 2);
		final int y = (GameFrame.SCREEN_HEIGHT - this.getHeight()) / 2;
		this.setLocation(x, y);
	}
	
	
}
