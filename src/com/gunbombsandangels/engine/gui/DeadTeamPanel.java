package com.gunbombsandangels.engine.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import com.gunbombsandangels.engine.board.Team;

@SuppressWarnings("serial")
public class DeadTeamPanel extends TeamPanel {
	
	public DeadTeamPanel(Team team) {
		super(new BorderLayout(), team);
		setBackground(Color.BLACK);
		//deadTeamPanel = new JPanel();
		try {
			
			final BufferedImage image = ImageIO.read(new File("res/DeadTeam.png"));
			final ImageIcon imageIcon = new ImageIcon(image.getScaledInstance((int)GameFrame.TILE_PANEL_DIMENSION.getWidth(), (int)GameFrame.TILE_PANEL_DIMENSION.getHeight(), Image.SCALE_SMOOTH));
			this.add(new JLabel(imageIcon), BorderLayout.CENTER);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void redo() {}

	@Override
	public void makeAttackButton(int damage) {}

	@Override
	public Container getButtonPanel() {
		return null;
	}

	@Override
	public void makeTeamSelectButton() {}

	@Override
	public void updateLifeIcon(int i) {}

	@Override
	public void updateName() {}

	@Override
	public Container getGameButtonPanel() {
		return null;
	}

	@Override
	public JButton getGameButton() {
		return null;
	}

}
