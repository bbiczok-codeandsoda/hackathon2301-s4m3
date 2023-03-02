package com.codeandsoda.balint.hackathon2301;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ClusterService {
	
	static final String boardAPI = "http://localhost:2345/api/board";
	
	public static void bestSolution(int width, int height, RestTemplate restTemplate, int steps) {
		Coordinate coordinate = new Coordinate();
	    coordinate.setX(ThreadLocalRandom.current().nextInt(0, width + 1));
	    coordinate.setY(ThreadLocalRandom.current().nextInt(0, height + 1));
	    System.out.println(steps + "-th move: "+ coordinate.getX()+" "+ coordinate.getY());
	    restTemplate.postForEntity(boardAPI, coordinate, BoardResponse.class);
	}
		
	public static void clusterFlow () {
		try {
//			int steps = 0;
			RestTemplate restTemplate = new RestTemplate();
			MatchCalculator matchCalculator = new MatchCalculator(restTemplate, boardAPI);
			while (true) {
				ResponseEntity<String> response = restTemplate.getForEntity(boardAPI, String.class);
				ObjectMapper objectMapper = new ObjectMapper();
				BoardResponse boardResponse = objectMapper.readValue(response.getBody(), BoardResponse.class);		
				if (!boardResponse.isGameOver()) {
					matchCalculator.setBoard(boardResponse.getBoard());
					matchCalculator.calculateMatches();
//					Board currentBoard = boardResponse.getBoard();
//					bestSolution(currentBoard.getWidth(), currentBoard.getHeight(), restTemplate, steps);
//					steps++;
				    System.out.println("Points: " + boardResponse.getPoints());
				} else {
					System.out.println("=== GAME OVER ===");
					System.out.println("Points: " + boardResponse.getPoints());
					break;
				}
			}
			} catch (Exception e) {
				e.printStackTrace();
		}
	}	
}