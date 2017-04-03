package com.ap.Ozlympic.game;

/**
 * 
 * Athlete class
 * 
 * any type of athlete should be its subclass
 * such as cyclist,sprinter,swimmer and super athlete.
 * Which is also a abstract class. Any tpye of athlete 
 * should implement its abstract methods
 *
 */
public abstract class Athlete {

	//personal information is also stored including name, age and the state (of Australia) they represent.
	private double score = 0;
	private int gamePoint = 0;
	private String name = null;
	private int age = 18;
	private String state = null;
	public int getGamePoint() {
		return gamePoint;
	}
	/**
	 * Points will be rewarded to the winners of games. In each game,
	 *  a first place worth 5, a second place attracts 2 points and
	 *   a third place is 1 point. No points for the rest.
	 *    Each athlete might have points carried over from the previous games.
	 * @param gamePoint
	 */
	public void addGamePoint(int gamePoint) {
		this.gamePoint += gamePoint;
	}


	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	private String type = "Athlete";

	public Athlete(String name,int age,String state,String type) {
		this.name = name;
		this.type = type;
		this.age = age;
		this.state = state;
	}
	
	/**
	 * compete method
	 * the subclass should implement this method 
	 * in details to define how they compete their games.
	 * @return
	 */
	public abstract double compete();
	/**
	 * print athlete's information 
	 */
	
	/**
	 * get an athlete's score of a game
	 * @return score
	 */
	public double getScore() {
		return score;
	}
	/**
	 * set an athlete's score for a game
	 * 
	 * @param score
	 */
	protected void setScore(double score) {
		this.score = score;
	}
	public int getAge() {
		return age;
	}
	public String getState() {
		return state;
	}

}
