package com.ap.Ozlympic;

/**
 *  main start-up class
 *  There are three sports in the Ozlympic: swimming, cycling and running. 
 *  There are four types of athletes, swimmers, cyclists, sprinters (who can
 *   compete in swimming, cycling and running respectively) and superAthletes
 *    who can compete in all three games.
 *
 */
public class Ozlympic {

	public Ozlympic() {
		
	}

	/**
	 * main method 
	 * the entrance of our program
	 * @param args
	 */
	public static void main(String[] args) {
		
			Driver driver = new Driver();
			driver.init();
			driver.run();
	}

}
