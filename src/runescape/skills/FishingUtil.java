package runescape.skills;

import java.util.HashMap;
import java.util.Set;

public class FishingUtil implements ISkill {
	// Fish, Experience
	private static final HashMap<String, Double> fishingMap = new HashMap<String, Double>();
	public static final String SHRIMP = "Shrimp";
	public static final String ANCHOVIES = "Anchovies";
	public static final String TROUT = "Trout";
	public static final String HERRING = "Herring";
	public static final String LOBSTER = "Lobster";
	public static final String PIKE = "Pike";
	public static final String SALMON = "Salmon";
	public static final String SARDINE = "Sardine";
	public static final String SWORDFISH = "Swordfish";
	public static final String TUNA = "Tuna";

	public static void initialize() {
		if (!initialized) {
			fishingMap.put(SHRIMP, 10.0);
			fishingMap.put(SARDINE, 20.0);
			fishingMap.put(HERRING, 30.0);
			fishingMap.put(ANCHOVIES, 40.0);
			fishingMap.put(TROUT, 50.0);
			fishingMap.put(PIKE, 60.0);
			fishingMap.put(SALMON, 70.0);
			fishingMap.put(TUNA, 80.0);
			fishingMap.put(LOBSTER, 90.0);
			fishingMap.put(SWORDFISH, 100.0);
		}
	}

	public static HashMap<String, Integer> calculateAllRequired(double experience) {
		initialize();
		HashMap<String, Integer> returnSet = new HashMap<String, Integer>();
		for (String fish : fishingMap.keySet()) {
			returnSet.put(fish, calculateRequired(fish, experience));
		}
		return returnSet;
	}

	public static int calculateRequired(String fish, double experience) {
		initialize();
		return (int) Math.ceil(experience / fishingMap.get(fish));
	}

	public static String outputAllRequired(double experience, int targetLevel) {
		String output = "";
		if (experience <= 0) {
			return "Already over level " + targetLevel;
		}
		initialize();
		for (String fish : fishingMap.keySet()) {
			output = output + outputRequired(fish, experience, targetLevel) + "\n";
		}
		return output;
	}

	public static String outputRequired(String fish, double experience, int targetLevel) {
		if (experience >= 0) {
			initialize();
			return calculateRequired(fish, experience) + " " + fish + " until " + targetLevel + ".";
		} else {
			return "Already over level " + targetLevel;
		}
	}
	
	@Override
	public Set<String> getOptions() {
		return fishingMap.keySet();
	}
}
