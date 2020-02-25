package com.gunbombsandangels.engine.gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.gunbombsandangels.engine.Tiles.Tile;
import com.gunbombsandangels.engine.board.Board;

@SuppressWarnings("serial")
public class TilePanel extends JPanel {
	private SoundManager sm;
	private Tile tile;
	private JLabel imageIconLabel;
	
	TilePanel(final GameFrame gameFrame, final Board board, final int tileID){
		sm = new SoundManager();
		this.tile = board.getTiles().get(tileID);
		setBackground(GameFrame.DEFAULT_COLOR);
		setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		
		
		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(board.isTeamSelected()) {
					System.out.println("Symbol: " + tile.getSymbol().getSymbolType().getFileName());
					tile.setSelected(true);
					setBackground(GameFrame.SELECTED_COLOR);
					showSymbolIcon();
					//TODO rename method
					board.makeTurn(tile);
					
					gameFrame.redraw();
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(!board.getTiles().get(tileID).isSelected()) {
					sm.playTick();
					setBackground(GameFrame.MOUSE_OVER_COLOR);
				}
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				if(!board.getTiles().get(tileID).isSelected()) {
					setBackground(GameFrame.DEFAULT_COLOR);
				}
			}

			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
			
		});
	
		validate();
	}
	
	public void showSymbolIcon() {
		try {
			
			final BufferedImage image = ImageIO.read(new File(tile.getSymbol().getSymbolType().getFileName()));
			final ImageIcon imageIcon = new ImageIcon(image.getScaledInstance((int)GameFrame.TILE_PANEL_DIMENSION.getWidth(), (int)GameFrame.TILE_PANEL_DIMENSION.getHeight(), Image.SCALE_SMOOTH));
			imageIconLabel = new JLabel(imageIcon);
			this.add(imageIconLabel);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		validate();
	}

	public void hideSymbolIcon() {
		if(!tile.isSelected()) {
			this.remove(imageIconLabel);
		}
		repaint();
	}
}
