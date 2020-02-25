package com.gunbombsandangels.engine.Tiles;


public class Tile {
	
	private int tileCoordinate;
	private Symbol symbol;
	private boolean isSelected;
	
	public Tile(final int tileCoordinate, final Symbol symbol) {
		this.tileCoordinate = tileCoordinate;
		this.symbol = symbol;
		this.isSelected = false;
	}

	public int getTileCoordinate() {
		return tileCoordinate;
	}

	public Symbol getSymbol() {
		return symbol;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	
	
}
