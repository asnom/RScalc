package runescape;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import runescape.skills.CombatUtil;
import runescape.skills.ExperienceUtil;
import runescape.skills.FishingUtil;
import runescape.skills.MiningUtil;

public class GUI extends JFrame implements ChangeListener, KeyListener {

	private static final int width = 800;
	private static final int height = 1000;
	private JRadioButton CombatButton;
	private JRadioButton ExperienceButton;
	private JRadioButton FishingButton;
	private JRadioButton MiningButton;
	private JRadioButton currentButton;

	private JTextArea resultsLabel;
	private JTextField experienceBox;
	private JSpinner targetLevelSpinner;

	/**
	 * 
	 */
	private static final long serialVersionUID = -2989008923635819617L;

	public GUI() {
		this.setAlwaysOnTop(true);
		this.setSize(width, height);

		JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		Font labelFont = new Font("SansSerif", Font.BOLD, 40);
		Font textFont = new Font("SansSerif", Font.PLAIN, 80);
		Font resultsFont = new Font("SansSerif", Font.PLAIN, 40);
		Font radioFont = new Font("SansSerif", Font.BOLD, 30);

		JLabel experienceLabel = new JLabel("Current Experience:");
		experienceLabel.setFont(labelFont);

		experienceBox = new JTextField("0");
		experienceBox.setFont(textFont);
		experienceBox.setPreferredSize(new Dimension(width - 42, 100));
		experienceBox.setHorizontalAlignment(SwingConstants.RIGHT);
		experienceBox.addKeyListener(this);

		JLabel targetLevelLabel = new JLabel("Target Level:");
		targetLevelLabel.setFont(labelFont);

		targetLevelSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));
		targetLevelSpinner.setFont(textFont);
		targetLevelSpinner.setPreferredSize(new Dimension(width - 42, 100));
		targetLevelSpinner.addChangeListener(this);
		targetLevelSpinner.getEditor().getComponent(0).addKeyListener(this);

		CombatButton = new JRadioButton("Combat");
		CombatButton.setFont(radioFont);
		CombatButton.addChangeListener(this);
		ExperienceButton = new JRadioButton("Experience");
		ExperienceButton.setFont(radioFont);
		ExperienceButton.setSelected(true);
		currentButton = ExperienceButton;
		ExperienceButton.addChangeListener(this);
		FishingButton = new JRadioButton("Fishing");
		FishingButton.setFont(radioFont);
		FishingButton.addChangeListener(this);
		MiningButton = new JRadioButton("Mining");
		MiningButton.setFont(radioFont);
		MiningButton.addChangeListener(this);

		ButtonGroup optionsGroup = new ButtonGroup();
		optionsGroup.add(CombatButton);
		optionsGroup.add(ExperienceButton);
		optionsGroup.add(FishingButton);
		optionsGroup.add(MiningButton);

		mainPanel.add(experienceLabel);
		mainPanel.add(experienceBox);
		mainPanel.add(targetLevelLabel);
		mainPanel.add(targetLevelSpinner);
		mainPanel.add(CombatButton);
		mainPanel.add(ExperienceButton);
		mainPanel.add(FishingButton);
		mainPanel.add(MiningButton);

		double experienceDifference = ExperienceUtil.expUntilLevel((int) targetLevelSpinner.getValue(),
				parseExperience(experienceBox.getText()));

		resultsLabel = new JTextArea(CombatUtil.outputRequired(CombatUtil.AL_KHARID_WARRIOR, experienceDifference,
				(int) targetLevelSpinner.getValue()));
		resultsLabel.setEditable(false);
		resultsLabel.setFont(resultsFont);
		JScrollPane resultsPane = new JScrollPane(resultsLabel);
		resultsPane.setAlignmentY(SwingConstants.TOP);
		resultsPane.setPreferredSize(new Dimension(width - 42, 480));
		resultsLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 10));
		mainPanel.add(resultsPane);
		this.add(mainPanel);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		if (arg0.getSource() instanceof JRadioButton && ((JRadioButton) arg0.getSource()).isSelected()) {
			currentButton = (JRadioButton) arg0.getSource();
		}
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

	private void reloadResults() {
		double experienceDifference;
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

	}

	private double parseExperience(String experience) {
		return Double.parseDouble(experience.replaceAll(",", ""));
	}
}
