package com.gunbombsandangels.engine.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.gunbombsandangels.engine.board.Team;

@SuppressWarnings("serial")
public class GameOver extends JFrame {
	
	private JPanel mainPanel;
	private JPanel textPanel;
	private JPanel buttonPanel;
	private JLabel textLabel;
	private JButton okButton;
	private JButton cancelButton;
	private Color backgroundColor = Color.decode("#80c2da");
	private Color borderColor = Color.decode("#098eaf");

	public GameOver(Team winner, JFrame frame, GameFrame gameFrame) {
		super();
		this.setUndecorated(true);
		this.setPreferredSize(new Dimension(500, 150));
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, borderColor));
		textPanel = new JPanel();
		buttonPanel = new JPanel();
		textLabel = new JLabel("Congratulations: " + winner.getTeamName() + ", You win!!!");
		
		this.setBackground(backgroundColor);
		mainPanel.setBackground(backgroundColor);
		buttonPanel.setBackground(backgroundColor);
		textPanel.setBackground(backgroundColor);
		
		textLabel.setFont(new Font(textLabel.getFont().getName(), Font.PLAIN, 25));
		
		okButton = new JButton("View Board");
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				gameFrame.showBoard();
			}
			
		});
		cancelButton = new JButton("Exit");
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
			
		});
		
		textPanel.add(textLabel);
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		
		mainPanel.add(textPanel, BorderLayout.NORTH);
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
