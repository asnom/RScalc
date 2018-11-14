package runescape.gui;

import runescape.skills.ExperienceUtil;

public class ExperienceTab extends AbstractGUITab {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExperienceTab(int width,double currentXP) {
		super(width,currentXP);
	}

	@Override
	protected void reloadResults() {
		try {
			resultsLabel.setText(ExperienceUtil.outputExpUntilLevel((int) targetLevelSpinner.getValue(),
					parseExperience(experienceBox.getText())));
		} catch (Exception e) {
			resultsLabel.setText("0");
		}
	}

	
}
