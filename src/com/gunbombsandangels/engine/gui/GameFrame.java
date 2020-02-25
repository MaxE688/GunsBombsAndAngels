package com.gunbombsandangels.engine.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import com.gunbombsandangels.engine.board.Board;
import com.gunbombsandangels.engine.board.Team;


public class GameFrame {
	
	public static int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	static final Dimension OUTER_FRAME_DIMENSION = new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);
	static final Dimension BOARD_PANEL_DIMENSION = new Dimension((int) (SCREEN_WIDTH * 0.80), SCREEN_HEIGHT);
	static final Dimension TILE_PANEL_DIMENSION = new Dimension((int) BOARD_PANEL_DIMENSION.getWidth()/6, (int) BOARD_PANEL_DIMENSION.getHeight()/6 - 10);
	static final Dimension TEAM_PANEL_DIMENSION = new Dimension((int) (SCREEN_WIDTH * 0.20), SCREEN_HEIGHT);
	static final Color DEFAULT_COLOR = Color.decode("#0c465e");
	static final Color MOUSE_OVER_COLOR = Color.decode("#f0c65a");
	static final Color SELECTED_COLOR = Color.decode("#098eaf");
	static final Color TEAM_PANEL_COLOR = Color.decode("#c2edd9");
	static final Color LABEL_BACKGROUND_COLOR = Color.decode("#80c2da");

	private final JFrame gameFrame;
	private TeamContainerPanel teamContainerPanel;
	private BoardPanel boardPanel;
	private Board tileBoard;
	
	public GameFrame(Board board) {
		//tileBoard = new Board();
		this.tileBoard = board;
		this.gameFrame = new JFrame("Guns Bombs & Angels");
		this.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.gameFrame.setLayout(new BorderLayout());
		this.teamContainerPanel = new TeamContainerPanel(this, tileBoard);
		this.boardPanel = new BoardPanel(this, tileBoard);
		
		final JMenuBar tableMenuBar = createTableMenuBar();
		this.gameFrame.setJMenuBar(tableMenuBar);
		gameFrame.add(boardPanel, BorderLayout.CENTER);
		gameFrame.add(teamContainerPanel, BorderLayout.WEST);
		
		this.gameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.gameFrame.setUndecorated(true);
		this.gameFrame.setVisible(true);
		tileBoard.getEffectManager().setGameFrame(this);
		tileBoard.makeNextTurn();
	}

	private JMenuBar createTableMenuBar() {
		
		final JMenuBar tableMenuBar = new JMenuBar();
		tableMenuBar.add(createFileMenu());
		tableMenuBar.add(createOptionsMenu());
		
		return tableMenuBar;
	}

	private JMenu createFileMenu() {
		GameFrame gf = this;
		
		final JMenu fileMenu = new JMenu("File");
		
		final JMenuItem resetBoard = new JMenuItem("Reset Board");
		resetBoard.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				gameFrame.remove(boardPanel);
				gameFrame.remove(teamContainerPanel);
				
				tileBoard = new Board(tileBoard);
				tileBoard.getEffectManager().setGameFrame(gf);
				teamContainerPanel = new TeamContainerPanel(gf, tileBoard);
				boardPanel = new BoardPanel(gf, tileBoard);
				
				gameFrame.add(boardPanel, BorderLayout.CENTER);
				gameFrame.add(teamContainerPanel, BorderLayout.WEST);
				
				tileBoard.isTeamSelected(false);
				tileBoard.makeNextTurn();
				
				gameFrame.validate();
				gameFrame.repaint();
				
			}
		});
		fileMenu.add(resetBoard);
		
		final JMenuItem showAll = new JMenuItem("Show Board");
		showAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showBoard();
			}
		});
		fileMenu.add(showAll);
		
		final JMenuItem hideAll = new JMenuItem("Hide Board");
		hideAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				for(TilePanel tile : boardPanel.getTilePanels()) {
					tile.hideSymbolIcon();
				}
			}
		});
		fileMenu.add(hideAll);
		
		final JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		fileMenu.add(exitMenuItem);
		
		return fileMenu;
	}
	
	public void showBoard() {
		for(TilePanel tile : boardPanel.getTilePanels()) {
			tile.showSymbolIcon();
		}
	}
	
	private JMenu createOptionsMenu() {
		final JMenu optionsMenu = new JMenu("Options");
		final JMenuItem changeIconMenuItem = new JMenuItem("Change Life Icons");
		GameFrame gf = this;
		changeIconMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IconChanger gameSetup = new IconChanger(gameFrame, gf);
				gameSetup.promptUser();
			}
			
		});
		
		optionsMenu.add(changeIconMenuItem);
		
		final JMenuItem changeTeamNameMenuItem = new JMenuItem("Change Team Names");
		changeTeamNameMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
					NameChanger changeName = new NameChanger(gameFrame, gf);
					changeName.promptUser();
			}
		});
		
		optionsMenu.add(changeTeamNameMenuItem);
		
		return optionsMenu;
	}

	public void setLifeIcon(int i) {
		this.teamContainerPanel.updateLifeIcon(i);
	}

	public void redraw() {
		this.teamContainerPanel.redo();
	}
	
	public void makeAttackButtons(int damage) {
		this.teamContainerPanel.makeAttackButtons(damage);
	}

	public void makeTeamSelectButtons() {
		this.teamContainerPanel.makeTeamSelectButtons();
	}

	public void killTeam(int team) {
		this.teamContainerPanel.killTeam(team);
	}
	public TeamContainerPanel getTeamContainerPanel() {
		return this.teamContainerPanel;
	}

	public void gameOver(Team winner) {
		@SuppressWarnings("unused")
		GameOver gameOver = new GameOver(winner, gameFrame, this);
	}
	

}
