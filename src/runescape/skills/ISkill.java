package runescape.skills;

import java.util.Set;

public interface ISkill {
	boolean initialized = false;
	Set<?> getOptions();

}
