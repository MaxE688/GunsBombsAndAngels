package com.gunbombsandangels.engine.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.gunbombsandangels.engine.board.Board;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel {
	
	private final static int TILE_WIDTH_OFFSET = 30;
	
	private double panelWidth;
	private double panelHeight;
	private int tileWidth;
	private int tileHeight;
	private Dimension columnLabelDimension;
	private Dimension rowLabelDimension;
	
	private List<TilePanel> tiles;
	private JPanel tilesPanel;
	private JPanel columnLabel;
	private JPanel rowLabel;
	
	BoardPanel(final GameFrame gameFrame, final Board board){
		super(new BorderLayout());
		
		panelWidth = GameFrame.BOARD_PANEL_DIMENSION.getWidth();
		panelHeight = GameFrame.BOARD_PANEL_DIMENSION.getHeight();
		tileWidth = (int) GameFrame.TILE_PANEL_DIMENSION.getWidth();
		tileHeight = (int) GameFrame.TILE_PANEL_DIMENSION.getHeight();
		columnLabelDimension = new Dimension((int) panelWidth, tileHeight);
		rowLabelDimension = new Dimension (tileWidth - TILE_WIDTH_OFFSET, (int) (panelHeight - tileHeight));
		
		columnLabel = new JPanel(new GridLayout(1,6));
		columnLabel.setBackground(GameFrame.LABEL_BACKGROUND_COLOR);
		columnLabel.setPreferredSize(columnLabelDimension);
		createColumnLabel();
		
		rowLabel = new JPanel(new GridLayout(5,1));
		rowLabel.setBackground(GameFrame.LABEL_BACKGROUND_COLOR);
		rowLabel.setPreferredSize(rowLabelDimension);
		createRowLabel();
		
		tilesPanel = new JPanel(new GridLayout(5,5));
		tilesPanel.setBackground(Color.BLACK);
		tilesPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));
		tiles = new ArrayList<>();
		for(int i = 0; i < Board.NUM_OF_TILES; i++) {
			final TilePanel tilePanel = new TilePanel(gameFrame, board, i);
			
			tiles.add(tilePanel);
			tilesPanel.add(tilePanel);
		}
		
		this.add(tilesPanel, BorderLayout.CENTER);
		this.add(columnLabel, BorderLayout.NORTH);
		this.add(rowLabel, BorderLayout.WEST);
		
		setPreferredSize(GameFrame.BOARD_PANEL_DIMENSION);
		validate();
	}

	private void createRowLabel() {
		String[] row = {"0","a","b","c","d","e"};
		
		for(int i = 1; i < row.length; i++) {
			try {
				
				final BufferedImage image = ImageIO.read(new File("res/label/" + row[i] + ".png"));
				final ImageIcon imageIcon = new ImageIcon(image);
				rowLabel.add(new JLabel(imageIcon));
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void createColumnLabel() {
		
		for(int i = 0; i < 6; i++) {
			try {
				final BufferedImage image = ImageIO.read(new File("res/label/" + i + ".png"));
				final ImageIcon imageIcon = new ImageIcon(image);
				columnLabel.add(new JLabel(imageIcon));
	
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public List<TilePanel> getTilePanels() {
		return this.tiles;
	}
}
