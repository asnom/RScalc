package runescape.gui;

import runescape.skills.ExperienceUtil;
import runescape.skills.WoodcuttingUtil;

public class WoodcuttingTab extends AbstractGUITab {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WoodcuttingTab(int width,double currentXP) {
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
		resultsLabel.setText(WoodcuttingUtil.outputAllRequired(experienceDifference, (int) targetLevelSpinner.getValue()));
	}

	
}
