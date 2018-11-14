package runescape.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import runescape.skills.ExperienceUtil;

abstract class AbstractGUITab extends JPanel implements ChangeListener, KeyListener {

	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;

	protected JTextArea resultsLabel;
	protected JTextField experienceBox;
	protected JSpinner targetLevelSpinner;
	protected double currentXP;

	public AbstractGUITab(int width,double currentXP) {
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		Font labelFont = new Font("SansSerif", Font.BOLD, 40);
		Font textFont = new Font("SansSerif", Font.PLAIN, 80);
		Font resultsFont = new Font("SansSerif", Font.PLAIN, 40);

		JLabel experienceLabel = new JLabel("Current Experience:");
		experienceLabel.setFont(labelFont);

		experienceBox = new JTextField("0");
		experienceBox.setFont(textFont);
		experienceBox.setPreferredSize(new Dimension(width - 42, 100));
		experienceBox.setHorizontalAlignment(SwingConstants.RIGHT);
		experienceBox.addKeyListener(this);
		experienceBox.setText(String.valueOf((int)currentXP));

		JLabel targetLevelLabel = new JLabel("Target Level:");
		targetLevelLabel.setFont(labelFont);
		int nextLevel = 1;
		try {
			nextLevel = ExperienceUtil.findCurrentLevel(parseExperience(experienceBox.getText())) + 1;				
		} catch (Exception e) {}
		
		targetLevelSpinner = new JSpinner(new SpinnerNumberModel(nextLevel, 1, 99, 1));
		targetLevelSpinner.setFont(textFont);
		targetLevelSpinner.setPreferredSize(new Dimension(width - 42, 100));
		targetLevelSpinner.addChangeListener(this);
		targetLevelSpinner.getEditor().getComponent(0).addKeyListener(this);
		
		this.add(experienceLabel);
		this.add(experienceBox);
		this.add(targetLevelLabel);
		this.add(targetLevelSpinner);

		resultsLabel = new JTextArea("");
		resultsLabel.setEditable(false);
		resultsLabel.setFont(resultsFont);
		JScrollPane resultsPane = new JScrollPane(resultsLabel);
		resultsPane.setAlignmentY(SwingConstants.TOP);
		resultsPane.setPreferredSize(new Dimension(width - 42, 770));
		resultsLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 10));
		this.add(resultsPane);
		reloadResults();
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		reloadResults();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == experienceBox) {
			try {
				int nextLevel = ExperienceUtil.findCurrentLevel(parseExperience(experienceBox.getText())) + 1;
				if (nextLevel > (int) targetLevelSpinner.getValue()) {
					targetLevelSpinner.setValue(nextLevel);
				}
			} catch (Exception e) {
				if (experienceBox.getText().equals("")) {
					experienceBox.setText("0");
				}
			}
			reloadResults();
		} else if (arg0.getSource() == targetLevelSpinner) {
			reloadResults();
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	abstract protected void reloadResults();
	//public void reloadResults() {
		/*		double experienceDifference;
		try {
			experienceDifference = ExperienceUtil.expUntilLevel((int) targetLevelSpinner.getValue(),
					parseExperience(experienceBox.getText()));
		} catch (Exception e) {
			experienceDifference = 0;
		}
		switch (currentButton.getText()) {
		case "Mining":
			resultsLabel
					.setText(MiningUtil.outputAllRequired(experienceDifference, (int) targetLevelSpinner.getValue()));
			break;
		case "Combat":
			resultsLabel
					.setText(CombatUtil.outputAllRequired(experienceDifference, (int) targetLevelSpinner.getValue()));
			break;
		case "Fishing":
			resultsLabel
					.setText(FishingUtil.outputAllRequired(experienceDifference, (int) targetLevelSpinner.getValue()));
			break;
		case "Experience":
			try {
				resultsLabel.setText(ExperienceUtil.outputExpUntilLevel((int) targetLevelSpinner.getValue(),
						parseExperience(experienceBox.getText())));
			} catch (Exception e) {
				resultsLabel.setText("0");
			}
			break;
		}

	}*/

	public double parseExperience(String experience) {
		return Double.parseDouble(experience.replaceAll(",", ""));
	}
	
	public void updateCurrentXP(double experience) {
		experienceBox.setText(String.valueOf((int)experience));
		reloadResults();
	}
}
