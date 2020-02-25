package com.gunbombsandangels.engine.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class NameSetter extends JFrame {
		
	private List<JTextField> textFields;
	private List<String> teamNames;
	private JPanel mainPanel;
	private JPanel buttonPanel;
	private JButton okButton;
	private JButton cancelButton;
	private int numOfTeams;
	
	private Color borderColor = Color.decode("#098eaf");
	private Color backgroundColor = Color.decode("#80c2da");
	
	NameSetter(IntroFrame frame,int numOfTeams) {
		super();
		this.setUndecorated(true);
		this.numOfTeams = numOfTeams;
		teamNames = createTeamNames();
		textFields = new ArrayList<>();
		buttonPanel = new JPanel();
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		okButton = new JButton("Okay");
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				teamNames = new ArrayList<>();
				for(JTextField text : textFields) {
					teamNames.add(text.getText());
				}
				setVisible(false);
			}
		});
	}
	
	private List<String> createTeamNames() {
		List<String> names = new ArrayList<>();
		for(int i = 0; i < numOfTeams; i++) {
			names.add("Team " + (i+1));
		}
		return names;
	}

	private void createTextField() {
		for(int i = 0; i < numOfTeams; i++) {
			JPanel textPanel = new JPanel();
			JTextField textField = new JTextField(teamNames.get(i), 15);
			textPanel.add(textField);
			textPanel.setBackground(backgroundColor);
			
			textFields.add(textField);
			mainPanel.add(textPanel);
		}
	}
	
	private void assembleMainPanel() {
		mainPanel = new JPanel(new GridLayout(numOfTeams + 1, 1));
		mainPanel.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, borderColor));
		mainPanel.setBackground(backgroundColor);
		
		teamNames = createTeamNames();
		createTextField();
		
		buttonPanel.setBackground(backgroundColor);
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		mainPanel.add(buttonPanel);
		
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

	public List<String> getTeamNames() {
		return this.teamNames;
	}

	public void promptUser(int numOfTeams) {
		this.numOfTeams = numOfTeams;
		assembleMainPanel();
	}

	public void updateNameList(int currentTeamCount) {
		if(currentTeamCount != numOfTeams) {
			numOfTeams = currentTeamCount;
			teamNames = createTeamNames();
		}
	}
}
	

