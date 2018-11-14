package runescape.skills;

import java.util.HashMap;

public class ExperienceUtil {

	private static final HashMap<Integer, Double> experienceMap = new HashMap<Integer, Double>();
	private static boolean initialized = false;

	public static void initialize() {
		if (!initialized) {
			experienceMap.put(1, 0.0);
			experienceMap.put(2, 83.0);
			experienceMap.put(3, 174.0);
			experienceMap.put(4, 276.0);
			experienceMap.put(5, 388.0);
			experienceMap.put(6, 512.0);
			experienceMap.put(7, 650.0);
			experienceMap.put(8, 801.0);
			experienceMap.put(9, 969.0);
			experienceMap.put(10, 1154.0);
			experienceMap.put(11, 1358.0);
			experienceMap.put(12, 1584.0);
			experienceMap.put(13, 1833.0);
			experienceMap.put(14, 2107.0);
			experienceMap.put(15, 2411.0);
			experienceMap.put(16, 2746.0);
			experienceMap.put(17, 3115.0);
			experienceMap.put(18, 3523.0);
			experienceMap.put(19, 3973.0);
			experienceMap.put(20, 4470.0);
			experienceMap.put(21, 5018.0);
			experienceMap.put(22, 5624.0);
			experienceMap.put(23, 6291.0);
			experienceMap.put(24, 7028.0);
			experienceMap.put(25, 7842.0);
			experienceMap.put(26, 8740.0);
			experienceMap.put(27, 9730.0);
			experienceMap.put(28, 10824.0);
			experienceMap.put(29, 12031.0);
			experienceMap.put(30, 13363.0);
			experienceMap.put(31, 14833.0);
			experienceMap.put(32, 16456.0);
			experienceMap.put(33, 18247.0);
			experienceMap.put(34, 20224.0);
			experienceMap.put(35, 22406.0);
			experienceMap.put(36, 24815.0);
			experienceMap.put(37, 27473.0);
			experienceMap.put(38, 30408.0);
			experienceMap.put(39, 33648.0);
			experienceMap.put(40, 37224.0);
			experienceMap.put(41, 41171.0);
			experienceMap.put(42, 45529.0);
			experienceMap.put(43, 50339.0);
			experienceMap.put(44, 55649.0);
			experienceMap.put(45, 61512.0);
			experienceMap.put(46, 67983.0);
			experienceMap.put(47, 75127.0);
			experienceMap.put(48, 83014.0);
			experienceMap.put(49, 91721.0);
			experienceMap.put(50, 101333.0);
			experienceMap.put(51, 111945.0);
			experienceMap.put(52, 123660.0);
			experienceMap.put(53, 136594.0);
			experienceMap.put(54, 150872.0);
			experienceMap.put(55, 166636.0);
			experienceMap.put(56, 184040.0);
			experienceMap.put(57, 203254.0);
			experienceMap.put(58, 224466.0);
			experienceMap.put(59, 247886.0);
			experienceMap.put(60, 273742.0);
			experienceMap.put(61, 302288.0);
			experienceMap.put(62, 333804.0);
			experienceMap.put(63, 368599.0);
			experienceMap.put(64, 407015.0);
			experienceMap.put(65, 449428.0);
			experienceMap.put(66, 496254.0);
			experienceMap.put(67, 547953.0);
			experienceMap.put(68, 605032.0);
			experienceMap.put(69, 668051.0);
			experienceMap.put(70, 737627.0);
			experienceMap.put(71, 814445.0);
			experienceMap.put(72, 899257.0);
			experienceMap.put(73, 992895.0);
			experienceMap.put(74, 1096278.0);
			experienceMap.put(75, 1210421.0);
			experienceMap.put(76, 1336443.0);
			experienceMap.put(77, 1475581.0);
			experienceMap.put(78, 1629200.0);
			experienceMap.put(79, 1798808.0);
			experienceMap.put(80, 1986068.0);
			experienceMap.put(81, 2192818.0);
			experienceMap.put(82, 2421087.0);
			experienceMap.put(83, 2673114.0);
			experienceMap.put(84, 2951373.0);
			experienceMap.put(85, 3258594.0);
			experienceMap.put(86, 3597792.0);
			experienceMap.put(87, 3972294.0);
			experienceMap.put(88, 4385776.0);
			experienceMap.put(89, 4842295.0);
			experienceMap.put(90, 5346332.0);
			experienceMap.put(91, 5902831.0);
			experienceMap.put(92, 6517253.0);
			experienceMap.put(93, 7195629.0);
			experienceMap.put(94, 7944614.0);
			experienceMap.put(95, 8771558.0);
			experienceMap.put(96, 9684577.0);
			experienceMap.put(97, 10692629.0);
			experienceMap.put(98, 11805606.0);
			experienceMap.put(99, 13034431.0);
			initialized = true;
		}
	}

	public static double expToNextLevel(double experience) {
		initialize();
		return experienceMap.get(findCurrentLevel(experience) + 1) - experience;
	}

	public static double expUntilLevel(int targetLevel, double experience) {
		initialize();
		return experienceMap.get(targetLevel) - experience;
	}

	public static int findCurrentLevel(double experience) {
		initialize();
		for (int i = 1; i < 100; i++) {
			if (experienceMap.get(i) > experience) {
				return i - 1;
			}
		}
		return -1;
	}
	
	public static String outputExpUntilLevel(int targetLevel, double experience) {
		initialize();
		if (experience >= 0) {
			initialize();
			return expUntilLevel(targetLevel, experience) + " experience until level " + targetLevel + ".";
		} else {
			return "Already over level " + targetLevel;
		}
	}

}
