package com.ap.Ozlympic.game;

/**
 * 
 * super athlete
 * 
 * who can compete in all three games.
 * To implement all functions of a super athlete,
 * a proxy model is used in this class.
 * @author 
 *
 */
public class SuperAthlete extends Athlete {
	
	private Sprinter sprinter = null;
	private Swimmer swimmer = null;
	private Cyclist cyclist = null;
	public SuperAthlete(String name,int age,String state) {
		super(name,age,state,"Super Athlete");
		sprinter = new Sprinter(name,age,state);
		swimmer = new Swimmer(name,age,state);
		cyclist = new Cyclist(name,age,state);
	}

	@Override
	public double compete() {
		return 0;
	}

	
	public double swimmingCompete(){
		return swimmer.compete();
	}
	
	public double runningCompete(){
		return sprinter.compete();
	}
	
	public double cyclingCompete(){
		return cyclist.compete();
	}
	
	public double getSwimmingScore(){
		return swimmer.getScore();
	}
	
	public double getRunningScore(){
		return sprinter.getScore();
	}
	
	public double getCyclingScore(){
		return cyclist.getScore();
	}

}
