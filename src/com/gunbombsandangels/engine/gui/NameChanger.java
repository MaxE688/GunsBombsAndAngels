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

import com.gunbombsandangels.engine.board.Team;

@SuppressWarnings("serial")
public class NameChanger extends JFrame {
	
	private TeamContainerPanel teamContainerPanel;
	private List<JPanel> teamNamePanels;
	private JPanel mainPanel;
	private JPanel buttonPanel;
	private JButton cancelButton;
	private JButton okButton;
	private Color backgroundColor = Color.decode("#80c2da");
	private Color borderColor = Color.decode("#098eaf");
	
	public NameChanger(JFrame frame, GameFrame gameFrame) {
		super();
		this.setUndecorated(true);
		teamContainerPanel = gameFrame.getTeamContainerPanel();
		teamNamePanels = new ArrayList<JPanel>();
		mainPanel = new JPanel(new GridLayout(teamContainerPanel.getTeams().size() + 1, 1));
		mainPanel.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, borderColor));
		buttonPanel = new JPanel();
		
		for(Team team : teamContainerPanel.getTeams()) {
			JPanel textPanel = new JPanel();
			textPanel.setBackground(backgroundColor);
			JTextField textField = new JTextField(team.getTeamName(), 15);
			JButton set = new JButton("Set Name");
			set.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					team.setName(textField.getText());
					teamContainerPanel.updateNames();
				}
			});
			textPanel.add(textField);
			textPanel.add(set);
			
			teamNamePanels.add(textPanel);
			mainPanel.add(textPanel);
		}
		
		okButton = new JButton("Okay");
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
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
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		mainPanel.add(buttonPanel);
		
		this.add(mainPanel);
		
		mainPanel.setBackground(backgroundColor);
		
		buttonPanel.setBackground(backgroundColor);
		
		setLocationRelativeTo(frame);
		pack();
		setVisible(false);
		
	}

	public void promptUser() {
		setVisible(true);
		repaint();
	}
}
