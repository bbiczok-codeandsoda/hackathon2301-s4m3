package com.codeandsoda.balint.hackathon2301;

public class BoardResponse {
	private Board board;
	private boolean gameOver;
	private int points;
	
	public Board getBoard() {return board;};
	public void setBoard(Board newBoard) {this.board = newBoard;};
	public boolean isGameOver() {return gameOver;};
	public void setGameover(boolean newGameOver) {this.gameOver = gameOver;};
	public int getPoints() {return points;};
	public void setPoints(int newPoints) {this.points = newPoints;};	
}
