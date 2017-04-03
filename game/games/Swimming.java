package com.ap.Ozlympic.game.games;

import com.ap.Ozlympic.game.Official;
import com.ap.Ozlympic.game.SuperAthlete;
import com.ap.Ozlympic.game.Swimmer;

public class Swimming extends Game {

	public Swimming(String gID, Official referee) {
		super(gID,referee);
	}
	
	public void addParticipant(Swimmer newPart){
		super.addParticipant(newPart);
	}
	
	public void addParticipant(SuperAthlete newPart){
		super.addParticipant(newPart);
	}

	@Override
	protected double competeForSuperAthlete(SuperAthlete superAthlete) {
		// TODO Auto-generated method stub
		return superAthlete.swimmingCompete();
	}

	@Override
	protected double getSuperAthleteScore(SuperAthlete superAthlete) {
		// TODO Auto-generated method stub
		return superAthlete.getSwimmingScore();
	}

}
