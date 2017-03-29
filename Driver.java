package com.ap.Ozlympic;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import com.ap.Ozlympic.game.Athlete;
import com.ap.Ozlympic.game.Cyclist;
import com.ap.Ozlympic.game.Official;
import com.ap.Ozlympic.game.Sprinter;
import com.ap.Ozlympic.game.SuperAthlete;
import com.ap.Ozlympic.game.Swimmer;
import com.ap.Ozlympic.game.games.Cycling;
import com.ap.Ozlympic.game.games.Game;
import com.ap.Ozlympic.game.games.Running;
import com.ap.Ozlympic.game.games.Swimming;

/**
 * 	driver class
 *  display a simple menu system.
 *   The menu should be numbered, 
 *   with the user selecting an option 
 *   by entering the number that
 *    corresponds to the function.
 */
public class Driver {
	private static final int SELECT_GAME_TO_RUN = 1;
	private static final int PREDICT_WINNER = 2;
	private static final int START_THE_GAME = 3;
	private static final int DISPLAY_RESULTS = 4;
	private static final int DISPLAY_POINTS = 5;
	private static final int EXIT = 6;
	
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private ArrayList<Game> gameList = new ArrayList<Game>();
	private ArrayList<Athlete> athleteList = new ArrayList<Athlete>();
	public Driver() {
	}
	
	private boolean isExit = false;
	

	private Game selected = null;
	private Athlete predictedWinner = null;
	public int displayMainMenu(){
		int option = -1;
		//display menu:
		System.out.println();
		System.out.println();
		System.out.println("Olympic Game");
		System.out.println("=========================");
		if(selected == null){
			System.out.println("1. Select a game to run.");
		}else{
			System.out.println("1. Change another game to run.(current seleted game ID: "+selected.getGameID()+" )");
		}
		if(predictedWinner == null){
			System.out.println("2. Predict the winner of the game");
		}else{
			System.out.println("2. Change predicted winner of the game.(current seleted predicted winner: "+predictedWinner.getName()+")");
		}
		System.out.println("3. Start the game");
		System.out.println("4. Display the final results of all games");
		System.out.println("5. Display the points of all athletes");
		System.out.println("6. Exit");
		try {
			String optStr = br.readLine();
			option = Integer.parseInt(optStr);
			if(option < 1 || option > 6){
				System.err.println("Input Error: Please input number between 1 ~ 6.");
				return displayMainMenu();
			}
		} catch (Exception e) {
			System.err.println("Input Error: Please input number between 1 ~ 6.");
			return displayMainMenu();
		}
		
		return option;
	}
	/**
	 *  display game list menu
	 * @return
	 */
	public int displayGameListMenu(){
		int option = -1;
		//display menu:
		System.out.println();
		System.out.println();
		System.out.println("Game List");
		System.out.println("=========================");
		int i = 0;
		for(; i < gameList.size() ; i ++){
			Game currentGame = gameList.get(i);
			String type = currentGame instanceof Swimming ? "Swimming" : currentGame instanceof Running ? "Running" : "Cycling";
			System.out.println((i+1)+". Game ID : +"+currentGame.getGameID()+" , Game Type: " + type + (currentGame.isFinished()?"(finished)":""));
		}
		System.out.println((i+1)+". Back");
		try {
			String optStr = br.readLine();
			option = Integer.parseInt(optStr);
			if(option == gameList.size()+1){
				return option;
			}else if(option < 1 || option > gameList.size()+1){
				System.err.println("Input Error: Please input number between 1 ~ "+(gameList.size()+1)+".");

				return displayGameListMenu();
			} else if (gameList.get(option-1).isFinished()){
				System.err.println("This game ha1"
						+ "s finished , please choose another one.");
				return displayGameListMenu();
			}
		} catch (Exception e) {
			System.err.println("Input Error: Please input number between 1 ~ "+(gameList.size()+1)+".");
			return displayGameListMenu();
		}
		
		return option;
	}
	/**
	 * init method for driver.
	 * 
	 */
	public void init(){
		createData();
	}
	
	/**
	 * create some data for running games.
	 *  which are imported through a generic interface as mentioned above. 
	 *  The data should include a few different types of games ready to run.
	 */
	private void createData(){
		//create officials as referees
		Official of1 = new Official("Official1");
		Official of2 = new Official("Official2");
		Official of3 = new Official("Official3");
		//create games:
		Swimming s01 = new Swimming("S01",of1);
		this.gameList.add(s01);
		 Running r01 = new Running("R01",of2);
		this.gameList.add(r01);
		 Cycling c01 = new Cycling("C01",of3);
		this.gameList.add(c01);
		String[] states = {"Dampieria","Victoria","Tasmania",
				"Nuytsland","Carpentaria","Flindersland","Torresia",
				"Cooksland","Guelphia","Van Diemen's Land"};
		//create athletes:
		Random r = new Random();
		int age = 18;
		String state = "";
		age = 18+r.nextInt(10);
		state = states[r.nextInt(10)];
		SuperAthlete superAthlete = new SuperAthlete("SuerperMan",age,state);
		this.athleteList.add(superAthlete);
		s01.addParticipant(superAthlete);
		c01.addParticipant(superAthlete);
		r01.addParticipant(superAthlete);
		
		for(int i = 0 ; i < 7 ; i ++){
			age = 18+r.nextInt(10);
			state = states[r.nextInt(10)];
			Swimmer swimmer = new Swimmer("swimmer"+(i+1),age,state);
			s01.addParticipant(swimmer);
			this.athleteList.add(swimmer);
			age = 18+r.nextInt(10);
			state = states[r.nextInt(10)];
			Sprinter sprinter = new Sprinter("sprinter"+(i+1),age,state);
			r01.addParticipant(sprinter);
			this.athleteList.add(sprinter);
			age = 18+r.nextInt(10);
			state = states[r.nextInt(10)];
			Cyclist cyclist = new Cyclist("cyclist"+(i+1),age,state);
			c01.addParticipant(cyclist);
			this.athleteList.add(cyclist);
		}
		
		

	}
	
	/**
	 * main run method
	 * to control game progress
	 */
	public void run(){
		while(!isExit){
			int opt = displayMainMenu();
			switch(opt){
			case SELECT_GAME_TO_RUN:
				selectGameToRun();
				break;
			case PREDICT_WINNER:
				predictWinner();
				break;
			case START_THE_GAME:
				startGame();
				break;
			case DISPLAY_RESULTS:
				displayFinalResultForAllGames();
				break;
			case DISPLAY_POINTS:
				displayAllAthletesPoints();
				break;
			case EXIT:
				isExit = true;
				break;
			default:
				break;
			}
		}
	}
	/**
	 * main menu item 1 function: select a game to run
	 */
	private void selectGameToRun(){
		int opt = displayGameListMenu()-1;
		if(opt >= gameList.size()){
			return;
		}else{
			this.selected = gameList.get(opt);
		}
	}
	/**
	 * main menu item 2 function: predict a winner of the game
	 */
	private void predictWinner(){
		if(selected == null){
			System.out.println("You should select a game, before predict a winner!");
			return;
		}
		predictedWinner = selectPredictedWinner();
		return;
		
	}
	/**
	 * select predicted winner
	 * A user can predict the winner for each game. 
	 * User’s prediction is limited to only one athlete in one game. 
	 * If the prediction is correct, then a congratulation message 
	 * will be generated.
	 * 
	 * @return
	 */
	private Athlete selectPredictedWinner(){
		int option = -1;
		//display menu:
		System.out.println();
		System.out.println();
		System.out.println("Participant List:");
		System.out.println("=========================");
		displayParticipantList(selected);
		try {
			String optStr = br.readLine();
			option = Integer.parseInt(optStr);
			if(option < 1 || option > selected.getParticipantList().size()){
				System.err.println("Input Error: Please input number between 1 ~ "+selected.getParticipantList().size()+".");
				return selectPredictedWinner();
			}
		} catch (Exception e) {
			System.err.println("Input Error: Please input number between 1 ~ "+selected.getParticipantList().size()+".");
			return selectPredictedWinner();
		}
		
		return selected.getParticipantList().get(option-1);
	}
	/**
	 * display all participants from a game
	 * @param game
	 */
	private void displayParticipantList(Game game){
		ArrayList<Athlete> participants = game.getParticipantList();
			for(int i = 0 ; i < participants.size() ; i ++){
				Athlete athlete = participants.get(i);
				System.out.println((i+1)+". Athlete Name : +"+athlete.getName()+"     ,Age: "+athlete.getAge()+"     ,State(of Aus): "+athlete.getState()+"     ,Athlete Type : " + athlete.getType());
			}
			
	}
	/**
	 * main menu item 3 function: start the game
	 */
	private void startGame(){
		Athlete winner = this.selected.startGame();
		if(this.predictedWinner != null){
			if(predictedWinner.equals(winner)){
				System.out.println("congratulation! You guess right!!!");
			}else{
				System.out.println("congratulation! You guess wrong!!!");
			}
		}
		this.selected = null;
		this.predictedWinner = null;
	}
	
	/**
	 * display final results for all games
	 */
	private void displayFinalResultForAllGames(){
		System.out.println("===============    All Games Final Results    ===============");
		System.out.println();
		System.out.println();
		for(int i = 0 ; i < this.gameList.size() ; i ++){
			Game game = gameList.get(i);
			System.out.println("----"+game.getGameID()+"----");
			if(game.isFinished()){
				game.printfinalScoreInfo();
			}else{
				System.out.println();
				System.out.println("not finished yet!!!!");
				System.out.println();
			}
		}
	}
	
	/**
	 * display all athletes game points and information
	 */
	private void displayAllAthletesPoints(){
		
		
			System.out.println("===============    Game Points Rank For All Athletes    ===============");
			System.out.println();
			System.out.println();
			//rank firstly
			for(int i = 0 ; i < this.athleteList.size()-1; i ++){
				Athlete highestOne = athleteList.get(i);
				for(int j = i+1 ; j < this.athleteList.size() ; j ++){
					Athlete curOne = athleteList.get(j);
					if(highestOne.getGamePoint() < curOne.getGamePoint()){
						highestOne = curOne;
					}
				}
				athleteList.remove(highestOne);
				athleteList.add(i,highestOne);
			}
			//print:
			int i = 0;
			for(Athlete athlete : athleteList){
				System.out.println((i+1)+". Name: " + athlete.getName() + "     ,Age: "+athlete.getAge()+"     ,State(of Aus): "+athlete.getState()+"      ,Type: " + athlete.getType()+"      ,Game Points: " + athlete.getGamePoint());
				i++;
			}
			
	}
}

	
