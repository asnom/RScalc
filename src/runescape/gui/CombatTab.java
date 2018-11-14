package runescape.gui;

import runescape.skills.CombatUtil;
import runescape.skills.ExperienceUtil;

public class CombatTab extends AbstractGUITab {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CombatTab(int width,double currentXP) {
		super(width,currentXP);
	}

	@Override
	protected void reloadResults() {
		double experienceDifference;
		try {
			experienceDifference = ExperienceUtil.expUntilLevel((int) targetLevelSpinner.getValue(),
					parseExperience(experienceBox.getText()));
		} catch (Exception e) {
			experienceDifference = 0;
		}
		resultsLabel.setText(CombatUtil.outputAllRequired(experienceDifference, (int) targetLevelSpinner.getValue()));
	}

	
}
