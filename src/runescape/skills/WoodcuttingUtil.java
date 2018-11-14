package runescape.skills;

import java.util.HashMap;
import java.util.Set;

public class WoodcuttingUtil implements ISkill {
	// Fish, Experience
	private static final HashMap<String, Double> woodcuttingMap = new HashMap<String, Double>();
	public static final String NORMAL = "Normal";
	//public static final String ACHEY = "Achey";
	public static final String OAK = "Oak";
	public static final String WILLOW = "Willow";
	//public static final String TEAK = "Teak";
	public static final String MAPLE = "Maple";
	//public static final String HOLLOW = "Hollow";
	//public static final String MAHOGANY = "Mahogany";
	//public static final String ARCTIC_PINE = "Arctic pine";
	public static final String YEW = "Yew";
	//public static final String SULLIUSCEP = "Sulliuscep";
	//public static final String MAGIC = "Magic";
	//public static final String REDWOOD = "Redwood";

	public static void initialize() {
		if (!initialized) {
			woodcuttingMap.put(NORMAL, 25.0);
			//woodcuttingMap.put(ACHEY, 25.0);
			woodcuttingMap.put(OAK, 37.5);
			woodcuttingMap.put(WILLOW, 67.5);
			//woodcuttingMap.put(TEAK, 85.0);
			woodcuttingMap.put(MAPLE, 100.0);
			//woodcuttingMap.put(HOLLOW, 82.5);
			//woodcuttingMap.put(MAHOGANY, 125.0);
			//woodcuttingMap.put(ARCTIC_PINE, 40.0);
			woodcuttingMap.put(YEW, 175.0);
			//woodcuttingMap.put(SULLIUSCEP, 127.0);
			//woodcuttingMap.put(MAGIC, 250.0);
			//woodcuttingMap.put(REDWOOD, 380.0);
		}
	}

	public static HashMap<String, Integer> calculateAllRequired(double experience) {
		initialize();
		HashMap<String, Integer> returnSet = new HashMap<String, Integer>();
		for (String log : woodcuttingMap.keySet()) {
			returnSet.put(log, calculateRequired(log, experience));
		}
		return returnSet;
	}

	public static int calculateRequired(String log, double experience) {
		initialize();
		return (int) Math.ceil(experience / woodcuttingMap.get(log));
	}

	public static String outputAllRequired(double experience, int targetLevel) {
		String output = "";
		if (experience <= 0) {
			return "Already over level " + targetLevel;
		}
		initialize();
		for (String log : woodcuttingMap.keySet()) {
			output = output + outputRequired(log, experience, targetLevel) + "\n";
		}
		return output;
	}

	public static String outputRequired(String log, double experience, int targetLevel) {
		if (experience >= 0) {
			initialize();
			return calculateRequired(log, experience) + " " + log + " until " + targetLevel + ".";
		} else {
			return "Already over level " + targetLevel;
		}
	}

	@Override
	public Set<String> getOptions() {
		return woodcuttingMap.keySet();
	}
}
