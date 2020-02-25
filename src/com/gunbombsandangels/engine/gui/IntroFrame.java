package com.gunbombsandangels.engine.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.gunbombsandangels.engine.board.Board;

@SuppressWarnings("serial")
public class IntroFrame extends JFrame {
	
	private JPanel mainPanel;
	private JPanel buttonPanel;
	private JPanel playButton;
	private JPanel exitButton;
	private JPanel titlePanel;
	private JLabel titleLabel;
	private String unselectedPlayButton;
	private String selectedPlayButton;
	private String unselectedExitButton;
	private String selectedExitButton;
	
	public IntroFrame() {
		super();
		new SoundManager();
		setBackground(GameFrame.DEFAULT_COLOR);
		unselectedPlayButton = "res/buttons/PlayUnselected.png";
		selectedPlayButton = "res/buttons/PlaySelected.png";
		unselectedExitButton = "res/buttons/ExitUnselected.png";
		selectedExitButton = "res/buttons/ExitSelected.png";
		
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(GameFrame.DEFAULT_COLOR);
		
		titlePanel = new JPanel();
		titlePanel.setBackground(GameFrame.DEFAULT_COLOR);
		titleLabel = new JLabel("Guns Bombs & Angels");
		titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.PLAIN, 100));
		titleLabel.setForeground(Color.WHITE);
		titlePanel.add(titleLabel);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
		playButton = new JPanel();
		exitButton = new JPanel();
		
		buttonPanel.setBackground(GameFrame.DEFAULT_COLOR);
		playButton.setBackground(GameFrame.DEFAULT_COLOR);
		exitButton.setBackground(GameFrame.DEFAULT_COLOR);
		
		playButton.setMaximumSize(new Dimension(150, 105));
		exitButton.setMaximumSize(new Dimension(150, 105));
		
		setButtonImage(playButton, unselectedPlayButton);
		setButtonImage(exitButton, unselectedExitButton);
		
		IntroFrame frame = this;
		playButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				@SuppressWarnings("unused")
				GameSetup gs = new GameSetup(frame);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				setButtonImage(playButton, selectedPlayButton);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				setButtonImage(playButton, unselectedPlayButton);
			}

			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
			
		});
		
		exitButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				setButtonImage(exitButton, selectedExitButton);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				setButtonImage(exitButton, unselectedExitButton);
			}

			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
			
		});
		
		buttonPanel.add(playButton);
		buttonPanel.add(exitButton);
		
		mainPanel.add(titlePanel, BorderLayout.NORTH);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		this.add(mainPanel);
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setVisible(true);
	}
	
	public void startGame(Board board, int lifeIcon) {
		GameFrame gf = new GameFrame(board);
		gf.setLifeIcon(lifeIcon);
		setVisible(false);
	}
	
	private void setButtonImage(JPanel panel, String fileName) {
		try {
			panel.removeAll();
			final BufferedImage image = ImageIO.read(new File(fileName));
			final ImageIcon imageIcon = new ImageIcon(image);
			
			panel.add(new JLabel(imageIcon));
			validate();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
