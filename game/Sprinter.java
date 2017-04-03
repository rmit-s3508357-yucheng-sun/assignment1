package com.ap.Ozlympic.game;

import java.util.Random;

/**
 * who can compete in running only
 * @author 
 *
 */
public class Sprinter extends Athlete {

	public Sprinter(String name,int age,String state) {
		super(name,age,state,"Sprinter");
	}
	/**
	 * randomly generate a time between 10 to 20 seconds for running
	 */
	@Override
	public double compete() {
		Random r = new Random();
		double newScore = 10 + 10*r.nextDouble();
		this.setScore(newScore);
		return newScore;
	}


}
