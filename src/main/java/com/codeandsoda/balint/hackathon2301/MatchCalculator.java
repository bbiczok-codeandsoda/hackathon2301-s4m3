package com.codeandsoda.balint.hackathon2301;

import org.springframework.web.client.RestTemplate;

public class MatchCalculator {

	private String boardAPI;
	private Tile [][] tiles;
	private int width;
	private int height;
	private RestTemplate restTemplate;
	private int x;
	private int y;
	private boolean matchFound;
	private int steps;
	
	public MatchCalculator (RestTemplate restTemplate, String boardAPI) {
		this.boardAPI = boardAPI;

		this.restTemplate = restTemplate;
		this.x = 0;
		this.y = 0;
		this.steps = 0;
		this.matchFound = false;
	}
	
	public void setBoard(Board board) {
		this.tiles = board.getTiles();
		this.width = board.getWidth();
		this.height = board.getHeight();
	}
	
	public void calculateMatches() {
		Coordinate coordinate = new Coordinate();
		calculateHoriz();
		if (!matchFound) {
			calculateVert();
			}
		if (!matchFound) {
			calculateLShape();
		}
		matchFound = false;
		steps++;
		coordinate.setX(x);
		coordinate.setY(y);
		System.out.println(steps + "-th move: "+ coordinate.getX()+" "+ coordinate.getY());
		restTemplate.postForEntity(boardAPI, coordinate, BoardResponse.class);
	}
	
	private void calculateHoriz() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width -2; j++) {					
				if (!isEmptyTile(i, j)) {
					if (isMatchThreeHoriz(i, j)){
						x = j;
						y = i;
						System.out.println("The tile clicked is: "+ tiles[i][j].getSign());
						matchFound = true;
						break;
					}
				}
			}
			if (matchFound) {
				break;
			}
			matchFound = false;
		}
	}
	
	private boolean isEmptyTile(int i, int j) {return tiles[i][j].getSign().equals(" ");}
	private boolean isMatchThreeHoriz(int i, int j) {return tiles[i][j].getSign().equals(tiles[i][j+1].getSign()) && tiles[i][j+1].getSign().equals(tiles[i][j+2].getSign());}
	
	private void calculateVert() {
		for (int i = 0; i < height-2; i++) {
			for (int j = 0; j < width; j++) {					
				if (!isEmptyTile(i, j)) {
					if (isMatchThreeVert(i, j)){
						x = j;
						y = i;
						System.out.println("The tile clicked is: "+ tiles[i][j].getSign());
						matchFound = true;
						break;
					}
				}
			}
			if (matchFound) {
				break;
			}
			matchFound = false;
		}
	}
	
	private boolean isMatchThreeVert(int i, int j) {return tiles[i][j].getSign().equals(tiles[i+1][j].getSign()) && tiles[i+1][j].getSign().equals(tiles[i+2][j].getSign());}
	
	private void calculateLShape() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {					
					try {
						if (!isEmptyTile(i, j) &&
								(isMatchThreeLOne(i, j) || isMatchThreeLTwo(i, j) || isMatchThreeThree(i, j))) {
							x = j;
							y = i;
							System.out.println("The tile clicked is: "+ tiles[i][j].getSign());
							matchFound = true;
							break;
						} else if (isMatchThreeFour(i, j) && !isEmptyTile(i, j+1)) {
							x = j+1;
							y = i;
							System.out.println("The tile clicked is: "+ tiles[i][j+1].getSign());
							matchFound = true;
							break;
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						System.out.println("Out of bounds!");
					}
				}
			if (matchFound) {
				break;
			}
			matchFound = false;
		}		
	}
	
	private boolean isMatchThreeLOne(int i, int j) {return tiles[i][j].getSign().equals(tiles[i][j+1].getSign()) && tiles[i][j+1].getSign().equals(tiles[i+1][j+1].getSign());}
	private boolean isMatchThreeLTwo(int i, int j) {return tiles[i][j].getSign().equals(tiles[i+1][j].getSign()) && tiles[i][j].getSign().equals(tiles[i][j+1].getSign());}
	private boolean isMatchThreeThree(int i, int j) {return tiles[i][j].getSign().equals(tiles[i+1][j].getSign()) && tiles[i+1][j].getSign().equals(tiles[i+1][j+2].getSign());}
	private boolean isMatchThreeFour(int i, int j) {return tiles[i][j+1].getSign().equals(tiles[i+1][j+1].getSign()) && tiles[i+1][j+1].getSign().equals(tiles[i+1][j].getSign());}
}
