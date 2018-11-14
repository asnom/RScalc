package runescape.gui;

import runescape.skills.ExperienceUtil;
import runescape.skills.MiningUtil;

public class MiningTab extends AbstractGUITab {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MiningTab(int width,double currentXP) {
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
		resultsLabel.setText(MiningUtil.outputAllRequired(experienceDifference, (int) targetLevelSpinner.getValue()));
	}

	
}
