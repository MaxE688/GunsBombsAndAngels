package com.gunbombsandangels.engine.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.gunbombsandangels.engine.Tiles.Symbol;
import com.gunbombsandangels.engine.Tiles.Symbol.SymbolType;
import com.gunbombsandangels.engine.gui.GameOver;
import com.gunbombsandangels.engine.Tiles.Tile;


@SuppressWarnings("unused")
public class Board {
	
	public static final int NUM_OF_TILES = 25;
	private static final int NUM_OF_SMALL_GUNS = 10;
	private static final int NUM_OF_BIG_GUNS = 7;
	private static final int NUM_OF_BOMBS = 4;
	private static final int NUM_OF_ANGELS = 4;
	
	private List<Tile> tiles;
	private List<Team> teams;
	private List<Symbol> symbols;
	private List<String> teamNames;
	private Team currentTeam;
	private Team winningTeam;
	private EffectManager effectManager;
	private boolean isTeamSelected;
	private int numOfTeams;
	private int numOfLives;
	
	public Board(int numOfTeams, int numOfLives, List<String> teamNames) {
		this.numOfTeams = numOfTeams;
		this.numOfLives = numOfLives;
		this.teamNames = teamNames;
		this.symbols = createSymbols();
		this.tiles = createTiles(symbols);
		this.teams = createTeams();
		effectManager = new EffectManager(this);
		isTeamSelected = false;
	}
	
	public Board(Board oldBoard) {
		this.numOfLives = oldBoard.getNumberOfLives();
		this.numOfTeams = oldBoard.getNumberOfTeams();
		this.teamNames = oldBoard.getTeamNames();
		this.symbols = createSymbols();
		this.tiles = createTiles(symbols);
		this.teams = oldBoard.getTeams();
		this.effectManager = new EffectManager(this);
		this.isTeamSelected = oldBoard.isTeamSelected();
	}
	
	private List<String> getTeamNames() {
		return this.teamNames;
	}

	private int getNumberOfLives() {
		return this.numOfLives;
	}
	
	private List<Team> createTeams() {
		List<Team> teams = new ArrayList<>();
		
		for(int i = 0; i < teamNames.size(); i++) {
			teams.add(new Team(i, numOfLives, teamNames.get(i)));
		}
		
		return teams;
	}


	private List<Symbol> createSymbols() {
		List<Symbol> symbols = new ArrayList<>();
		int smallGuns = 0;
		int bigGuns = 0;
		int bombs = 0;
		int angels = 0;
		Random rng = new Random();
		int symbolID;
		
		while(symbols.size() < NUM_OF_TILES) {
			symbolID = rng.nextInt(4);
			if(symbolID == 0 && smallGuns < NUM_OF_SMALL_GUNS) {
				symbols.add(new Symbol(SymbolType.SmallGun));
				smallGuns++;
			}
			else if(symbolID == 1 && bigGuns < NUM_OF_BIG_GUNS) {
				symbols.add(new Symbol(SymbolType.BigGun));
				bigGuns++;
			}
			else if(symbolID == 2 && bombs < NUM_OF_BOMBS) {
				symbols.add(new Symbol(SymbolType.Bomb));
				bombs++;
			}
			else if(symbolID == 3 && angels < NUM_OF_ANGELS) {
				symbols.add(new Symbol(SymbolType.Angel));
				angels++;
			}
		}
		
		return symbols;
	}


	private List<Tile> createTiles(final List<Symbol> symbols) {
		List<Tile> tiles = new ArrayList<>();
		
		for(int i = 0; i < NUM_OF_TILES; i++) {
			tiles.add(new Tile(i, symbols.get(i)));
		}
		
		return tiles;
	}

	public List<Tile> getTiles() {
		return this.tiles;
	}
	
	public Team getCurrentTeam() {
		return currentTeam;
	}


	public void makeTurn(Tile tile) {
		Symbol symbol = tile.getSymbol();
		
		if(symbol.getSymbolType() == SymbolType.SmallGun) {
			effectManager.executeSmallGun();
		}
		else if(symbol.getSymbolType() == SymbolType.BigGun) {
			effectManager.executeBigGun();
		}
		else if(symbol.getSymbolType() == SymbolType.Bomb) {
			effectManager.executeBomb();
		}
		else if(symbol.getSymbolType() == SymbolType.Angel) {
			effectManager.executeAngel();
		}
		
	}


	public List<Team> getTeams() {
		return this.teams;
	}


	public EffectManager getEffectManager() {
		return this.effectManager;
	}


	public void makeNextTurn() {
		effectManager.selectTeam();
	}


	public void setCurrentTeam(Team team) {
		this.currentTeam = team;
		
	}

	public boolean isTeamSelected() {
		return isTeamSelected;
	}
	
	public void isTeamSelected(boolean b) {
		this.isTeamSelected = b;
	}


	public boolean isGameOver() {
		int livingCount = 0;
		Team livingTeam = null;;
		for(Team team : teams) {
			if(team.isAlive()) {
				livingCount++;
				livingTeam = team;
			}
		}
		if(livingCount == 1) {
			System.out.println("Game Over! " + livingTeam.getTeamName() + " Wins!");
			winningTeam = livingTeam;
			return true;
		}
		else {
			//livingTeam = null;
			return false;
		}
	}
	
	public Team getWinner() {
		return winningTeam;
	}

	public int getNumberOfTeams() {
		return this.numOfTeams;
	}

}
