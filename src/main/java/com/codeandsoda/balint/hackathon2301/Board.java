package com.codeandsoda.balint.hackathon2301;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Board {
	private int width;
	private int height;
	private Tile[][] tiles;
	private long seed;
	@JsonProperty("extra_tiles")
	private String extraTiles;
	
	public int getWidth() {return width;};
	public void setWidth(int newWidth) {this.width = newWidth;};
	public int getHeight() {return height;};
	public void setHeight(int newHeight) {this.height = newHeight;};
	public Tile[][] getTiles() {return this.tiles;};
	public void setTiles(Tile[][] newTiles) {this.tiles = newTiles;};
	public long getSeed() {return seed;};
	public void setSeed(long newSeed) {this.seed = newSeed;};
	public String getExtraTiles() {return extraTiles;};
	public void setExtraTiles(String newExtraTiles) {this.extraTiles = newExtraTiles;};
}
