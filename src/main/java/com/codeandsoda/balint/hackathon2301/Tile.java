package com.codeandsoda.balint.hackathon2301;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tile {
	@JsonProperty("Sign")
	private String sign;
	@JsonProperty("Color")
	private String color;
	
	public String getSign() {return sign;};
	public void setSign(String newSign) {this.sign = newSign;};
	public String getColor() {return color;};
	public void setColor(String newColor) {this.color = newColor;};	
}
