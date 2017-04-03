package com.ap.Ozlympic.game;

import java.util.Random;

/**
 * who can compete in swimming only
 * @author 
 *
 */
public class Swimmer extends Athlete {

	public Swimmer(String name,int age,String state) {
		super(name,age,state,"Sprinter");
	}
	/**
	 * randomly generate a time between 100 to 200 seconds for swimming
	 */
	@Override
	public double compete() {
		Random r = new Random();
		//100 -200
		double newScore = 100 + 100*r.nextDouble();
		this.setScore(newScore);
		return newScore;
	}


}
