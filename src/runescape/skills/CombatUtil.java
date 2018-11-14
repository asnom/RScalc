package runescape.skills;

import java.util.HashMap;
import java.util.Set;

public class CombatUtil implements ISkill {
	// Fish, Experience
	private static final HashMap<String, Double> monsterMap = new HashMap<String, Double>();
	public static final String AL_KHARID_WARRIOR = "Al-Kharid Warrior";
	public static final String HILL_GIANT = "Hill Giant";
	public static final String HOBGOBLIN_32 = "Hobgoblin (lv32)";

	public static void initialize() {
		if (!initialized) {
			monsterMap.put(AL_KHARID_WARRIOR, 19.0 * 4);
			monsterMap.put(HILL_GIANT, 35.0 * 4);
			monsterMap.put(HOBGOBLIN_32, 29.0 * 4);
		}
	}

	public static HashMap<String, Integer> calculateAllRequired(double experience) {
		initialize();
		HashMap<String, Integer> returnSet = new HashMap<String, Integer>();
		for (String mob : monsterMap.keySet()) {
			returnSet.put(mob, calculateRequired(mob, experience));
		}
		return returnSet;
	}

	public static int calculateRequired(String mob, double experience) {
		initialize();
		return (int) Math.ceil(experience / monsterMap.get(mob));
	}

	public static String outputAllRequired(double experience, int targetLevel) {
		String output = "";
		if (experience <= 0) {
			return "Already over level " + targetLevel;
		}
		initialize();
		for (String mob : monsterMap.keySet()) {
			output = output + outputRequired(mob, experience, targetLevel) + "\n";
		}
		return output;
	}

	public static String outputRequired(String mob, double experience, int targetLevel) {
		if (experience >= 0) {
			initialize();
			return calculateRequired(mob, experience) + " " + mob + " until " + targetLevel + ".";
		} else {
			return "Already over level " + targetLevel;
		}
	}

	@Override
	public Set<String> getOptions() {
		return monsterMap.keySet();
	}
}
