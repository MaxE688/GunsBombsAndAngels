package com.gunbombsandangels.engine.Tiles;

public class Symbol {
	
	private SymbolType symbolType;
	
	public Symbol(final SymbolType symbolType) {
		this.symbolType = symbolType;
	}
	
	public SymbolType getSymbolType() {
		return symbolType;
	}
	
	
	public enum SymbolType {
		
		SmallGun("SmallGun") {
			@Override
			public String getFileName() {
				return "res/smallGun.png";
			}
		},
		BigGun("BigGun") {
			@Override
			public String getFileName() {
				return "res/bigGun.png";
			}
		},
		Bomb("Bomb") {
			@Override
			public String getFileName() {
				return "res/bomb.png";
			}
		},
		Angel("Angel") {
			@Override
			public String getFileName() {
				return "res/angel.png";
			}
		};
		
		@SuppressWarnings("unused")
		private final String name;
		
		SymbolType(String name) {
			this.name = name;
		}
		
		public abstract String getFileName();

	}


	
	
}
