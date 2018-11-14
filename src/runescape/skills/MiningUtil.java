package runescape.skills;

import java.util.HashMap;
import java.util.Set;

public class MiningUtil implements ISkill {
	// Fish, Experience
	private static final HashMap<String, Double> miningMap = new HashMap<String, Double>();
	public static final String CLAY = "Clay";
	public static final String RUNE_ESSENCE = "Rune essence";
	public static final String COPPER = "Copper";
	public static final String TIN = "Tin";
	public static final String IRON = "Iron";
	public static final String SILVER = "Silver";
	public static final String COAL = "Coal";
	public static final String GOLD = "Gold";
	public static final String MITHRIL = "Mithril";
	public static final String ADAMANTITE = "Adamantite";
	public static final String RUNITE = "Runite";

	public static void initialize() {
		if (!initialized) {
			miningMap.put(CLAY, 5.0);
			miningMap.put(RUNE_ESSENCE, 5.0);
			miningMap.put(COPPER, 17.5);
			miningMap.put(TIN, 17.5);
			miningMap.put(IRON, 35.0);
			miningMap.put(SILVER, 40.0);
			miningMap.put(COAL, 50.0);
			miningMap.put(GOLD, 65.0);
			miningMap.put(MITHRIL, 80.0);
			miningMap.put(ADAMANTITE, 95.0);
			miningMap.put(RUNITE, 125.0);
		}
	}

	public static HashMap<String, Integer> calculateAllRequired(double experience) {
		initialize();
		HashMap<String, Integer> returnSet = new HashMap<String, Integer>();
		for (String ore : miningMap.keySet()) {
			returnSet.put(ore, calculateRequired(ore, experience));
		}
		return returnSet;
	}

	public static int calculateRequired(String ore, double experience) {
		initialize();
		return (int) Math.ceil(experience / miningMap.get(ore));
	}

	public static String outputAllRequired(double experience, int targetLevel) {
		String output = "";
		if (experience <= 0) {
			return "Already over level " + targetLevel;
		}
		initialize();
		for (String ore : miningMap.keySet()) {
			output = output + outputRequired(ore, experience, targetLevel) + "\n";
		}
		return output;
	}

	public static String outputRequired(String ore, double experience, int targetLevel) {
		if (experience >= 0) {
			initialize();
			return calculateRequired(ore, experience) + " " + ore + " until " + targetLevel + ".";
		} else {
			return "Already over level " + targetLevel;
		}
	}
	
	@Override
	public Set<String> getOptions() {
		return miningMap.keySet();
	}
}
