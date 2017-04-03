package com.ap.Ozlympic.game.games;

import com.ap.Ozlympic.game.Cyclist;
import com.ap.Ozlympic.game.Official;
import com.ap.Ozlympic.game.SuperAthlete;

public class Cycling extends Game {

	public Cycling(String gID, Official referee) {
		super(gID,referee);
	}

	public void addParticipant(Cyclist newPart){
		super.addParticipant(newPart);
	}
	
	public void addParticipant(SuperAthlete newPart){
		super.addParticipant(newPart);
	}

	@Override
	protected double competeForSuperAthlete(SuperAthlete superAthlete) {
		// TODO Auto-generated method stub
		return superAthlete.cyclingCompete();
	}

	@Override
	protected double getSuperAthleteScore(SuperAthlete superAthlete) {
		// TODO Auto-generated method stub
		return superAthlete.getCyclingScore();
	}
	

}
