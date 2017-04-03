package com.ap.Ozlympic.game.games;

import java.util.ArrayList;

import com.ap.Ozlympic.game.Athlete;
import com.ap.Ozlympic.game.Official;
import com.ap.Ozlympic.game.SuperAthlete;

public abstract class Game {

	public static final int MOST_PARTICIPANT_NUM = 8;//have at most 8 athletes
	private String gameID = null;					//Each game has a unique game ID 
	private boolean finished = false;				//To judge if the current game has finished.
	private Official referee;						//Each game has one official as the referee
	public Official getReferee() {
		return referee;
	}
	protected ArrayList<Athlete> participants = new ArrayList<Athlete>(MOST_PARTICIPANT_NUM);
	ArrayList<Athlete> round1Result = new ArrayList<Athlete>(participants.size());
	ArrayList<Athlete> finalResult = new ArrayList<Athlete>(4);

	public String getGameID() {
		return gameID;
	}


	public Game(String gameID,Official referee) {
		this.gameID = gameID;
		this.referee = referee;
	}
	
	protected void addParticipant(Athlete newPart){
		if(participants.size() == MOST_PARTICIPANT_NUM){
			System.err.print("The operation 'Add new Participant' failed: There should be at most 8 participants for one same game.");
		}else{
			participants.add(newPart);
		}
	}
	public ArrayList<Athlete> getParticipantList(){
		return participants;
	}
	
	public boolean isFinished() {
		return finished;
	}
	
	protected void setFinished(boolean finished){
		this.finished = finished;
		
	}
	/**
	 * start the game 
	 * @return
	 */
	public Athlete startGame() {
		//round 1:
		for(int i = 0 ; i < this.participants.size() ; i ++){
			Athlete curAth = participants.get(i);
			double result = -1;
			if(curAth instanceof SuperAthlete){
				SuperAthlete superAth = (SuperAthlete)curAth;
				result = this.competeForSuperAthlete(superAth);
			}else{
				result = curAth.compete();
			}
			boolean inserted = false;
			for(int j = 0 ; j < round1Result.size() ; j++){
				Athlete comparedAthlete = round1Result.get(j);
				double comparedResult = -1;
				if(comparedAthlete instanceof SuperAthlete){
					SuperAthlete superAth = (SuperAthlete)comparedAthlete;
					comparedResult = this.getSuperAthleteScore(superAth);
				}else{
					comparedResult = comparedAthlete.getScore();
				}
				if(result < comparedResult){
					round1Result.add(j, curAth);
					inserted = true;
					break;
				}
			}
			if(inserted == false){
				round1Result.add(curAth);
			}
		}
		if(round1Result.size()<=4){
			this.finalResult = round1Result;
			printfinalScoreInfo();
			addGamePointsForWinner();
			setFinished(true);
			return finalResult.get(0);
		}
		printRound1ScoreInfo();
		//round 2:
			for(int i = 0 ; i < 4 ; i ++){
				double result = -1;
				Athlete curAth = round1Result.get(i);
				if(curAth instanceof SuperAthlete){
					SuperAthlete superAth = (SuperAthlete)curAth;
					result = this.competeForSuperAthlete(superAth);
				}else{
					result = curAth.compete();
				}
				boolean inserted = false;
				for(int j = 0 ; j < finalResult.size() ; j++){
					Athlete comparedAthlete = finalResult.get(j);
					double comparedResult = -1;
					if(comparedAthlete instanceof SuperAthlete){
						SuperAthlete superAth = (SuperAthlete)comparedAthlete;
						comparedResult = this.getSuperAthleteScore(superAth);
					}else{
						comparedResult = comparedAthlete.getScore();
					}
					if(result < comparedResult){
						finalResult.add(j, curAth);
						inserted = true;
						break;
					}
				}
				if(inserted == false){
					finalResult.add(curAth);
				}
			}
		printfinalScoreInfo();
		addGamePointsForWinner();
		setFinished(true);
		return finalResult.get(0);
	}
	/**
	 * print the 1st round score result
	 */
	private void printRound1ScoreInfo(){
		System.out.println("===================== The Round 1 Result ====================");
		System.out.println("Referee : " + this.referee.getName());
		for(int i = 0 ; i < round1Result.size() ; i ++){
			Athlete athlete = round1Result.get(i);
			double score = -1;
			if(athlete instanceof SuperAthlete){
				SuperAthlete superAth = (SuperAthlete)athlete;
				score = this.getSuperAthleteScore(superAth);
			}else{
				score = athlete.getScore();
			}
			System.out.println("The "+i+"th player: " + "name:"+athlete.getName()+", ----score:"+score+(i<4?"<<pass>>":"<<eliminated>>"));
		}
	}
	/**
	 * print final round result
	 */
	public void printfinalScoreInfo(){
		System.out.println("===================== The Final Result ====================");
		System.out.println("Referee : " + this.referee.getName());
		for(int i = 0 ; i < finalResult.size() ; i ++){
			Athlete athlete = finalResult.get(i);
			double score = -1;
			if(athlete instanceof SuperAthlete){
				SuperAthlete superAth = (SuperAthlete)athlete;
				score = this.getSuperAthleteScore(superAth);
			}else{
				score = athlete.getScore();
			}
			System.out.println("The "+(i+1)+"th player: " + "name:"+athlete.getName()+ "---- score: "+score+" ----"+(i==0?"<<Winner>>":""));
		}
	}
	private void addGamePointsForWinner(){
		Athlete winner = this.finalResult.get(0);
		if(winner != null){
			winner.addGamePoint(5);
		}
		winner = this.finalResult.get(1);
		if(winner != null){
			winner.addGamePoint(2);
		}
		winner = this.finalResult.get(2);
		if(winner != null){
			winner.addGamePoint(1);
		}
	}
	
	abstract protected double getSuperAthleteScore(SuperAthlete superAthlete);
	abstract protected double competeForSuperAthlete(SuperAthlete superAthlete);
}
