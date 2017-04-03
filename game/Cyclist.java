package com.ap.Ozlympic.game;

import java.util.Random;

/**
 * who can compete in cycling only
 * Athlete's subclass
 * 
 *
 */
public class Cyclist extends Athlete {

	public Cyclist(String name,int age,String state) {
		super(name,age,state,"Cyclist");
	}
	/**
	 * randomly generate a time between 500 to 800 seconds for cycling
	 */
	@Override
	public double compete() {
		
		Random r = new Random();
		double newScore = 500 + 300*r.nextDouble();
		this.setScore(newScore);
		return newScore;
	}
}
