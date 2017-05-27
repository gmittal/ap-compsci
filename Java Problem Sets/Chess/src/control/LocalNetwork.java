package control;

import java.util.ArrayList;

public class LocalNetwork extends Network{

	public LocalNetwork() {
		GAME_PIN = "local";
	}
	
	public void sendLocalChange(String move) {
		state.add(move);
	}
	
	public ArrayList<String> listenForNetworkChange() {
		return state;
	}

}
