package runescape.skills;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class PlayerXPUtil {

	private static final String[] skillsOrder = new String[] {
			"Overall",
			"Attack",
			"Defense",
			"Strength",
			"HP",
			"Ranged",
			"Prayer",
			"Magic",
			"Cooking",
			"Woodcutting",
			"Fletching",
			"Fishing",
			"Firemaking",
			"Crafting",
			"Smithing",
			"Mining",
			"Herblore",
			"Agility",
			"Thieving",
			"Slayer",
			"Farming",
			"Runecraft",
			"Hunter",
			"Construction"
	};
	
	private static final String rsAPIString = "https://secure.runescape.com/m=hiscore_oldschool/index_lite.ws";

	private static HashMap<String, Double> playerXP;
	public static HashMap<String, Double> getPlayerXP(String playerName) throws MalformedURLException, IOException {
		//if (playerXP != null) {
		//	return PlayerXPUtil.playerXP;
		//}
		playerXP = new HashMap<String, Double>();
		HttpURLConnection rsAPI = (HttpURLConnection) new URL(rsAPIString + "?player=" + playerName).openConnection();
		try {
		     BufferedInputStream in = new BufferedInputStream(rsAPI.getInputStream());
		     BufferedReader br = new BufferedReader(new InputStreamReader(in));
		     String line = null;
		     int i = 0;
		     while (br.ready()) {
		    	 line = br.readLine();
		    	 if (i < skillsOrder.length){
		    		 playerXP.put(skillsOrder[i++], Double.parseDouble(line.split(",")[2] + ".0"));
		    	 }
		     }
		   } finally {
			   rsAPI.disconnect();
		   }
		return playerXP;
	}

}
