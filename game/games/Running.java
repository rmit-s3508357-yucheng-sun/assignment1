package com.ap.Ozlympic.game.games;

import com.ap.Ozlympic.game.Official;
import com.ap.Ozlympic.game.Sprinter;
import com.ap.Ozlympic.game.SuperAthlete;

public class Running extends Game {

	public Running(String gID, Official referee) {
		super(gID,referee);
	}

	public void addParticipant(Sprinter newPart){
		super.addParticipant(newPart);
	}
	
	public void addParticipant(SuperAthlete newPart){
		super.addParticipant(newPart);
	}

	@Override
	protected double competeForSuperAthlete(SuperAthlete superAthlete) {
		// TODO Auto-generated method stub
		return superAthlete.runningCompete();
	}

	@Override
	protected double getSuperAthleteScore(SuperAthlete superAthlete) {
		// TODO Auto-generated method stub
		return superAthlete.getRunningScore();
	}
}
