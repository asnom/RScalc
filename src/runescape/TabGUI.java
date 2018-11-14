package runescape;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import runescape.gui.CombatTab;
import runescape.gui.ExperienceTab;
import runescape.gui.FishingTab;
import runescape.gui.MiningTab;
import runescape.gui.WoodcuttingTab;
import runescape.skills.CombatUtil;
import runescape.skills.ExperienceUtil;
import runescape.skills.FishingUtil;
import runescape.skills.MiningUtil;
import runescape.skills.PlayerXPUtil;

public class TabGUI extends JFrame implements ChangeListener, KeyListener, ActionListener {

	private static final int width = 800;
	private static final int tabWidth = width - 175;
	private static final int height = 1200;
	private static final String playerName = "red827";
	private JRadioButton currentButton;

	private JTextArea resultsLabel;
	private JTextField experienceBox;
	private JSpinner targetLevelSpinner;
	private Font tabFont = new Font("SansSerif", Font.BOLD, 25);
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem refresh;
	private JMenuItem grandExchange;
	
	private HashMap<String,Double> xp = null;
	private ExperienceTab experienceTab;
	private CombatTab attackTab;
	private CombatTab strengthTab;
	private CombatTab defenseTab;
	private FishingTab fishingTab;
	private MiningTab miningTab;
	private WoodcuttingTab wcTab;

	/**
	 * 
	 */
	private static final long serialVersionUID = -2989008923635819617L;

	public TabGUI() {
		this.setAlwaysOnTop(true);
		this.setSize(width, height);
		menuBar = new JMenuBar();
		menu = new JMenu("Settings");
		refresh = new JMenuItem("Refresh XP");
		refresh.addActionListener(this);
		menu.add(refresh);
		grandExchange = new JMenuItem("Grand Exchange");
		grandExchange.addActionListener(this);
		menu.add(grandExchange);
		menuBar.add(menu);
		this.setJMenuBar(menuBar);
		JTabbedPane mainPane = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.SCROLL_TAB_LAYOUT);
		mainPane.setFont(tabFont);
		try {
			xp = PlayerXPUtil.getPlayerXP(playerName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		experienceTab = new ExperienceTab(tabWidth,0);
		mainPane.addTab("Experience", experienceTab);
		attackTab = new CombatTab(tabWidth,xp.get("Attack"));
		mainPane.addTab("Attack", attackTab);
		strengthTab = new CombatTab(tabWidth,xp.get("Strength"));
		mainPane.addTab("Strength", strengthTab);
		defenseTab = new CombatTab(tabWidth,xp.get("Defense"));
		mainPane.addTab("Defense", defenseTab);
		fishingTab = new FishingTab(tabWidth,xp.get("Fishing"));
		mainPane.addTab("Fishing", fishingTab);
		miningTab = new MiningTab(tabWidth,xp.get("Mining"));
		mainPane.addTab("Mining", miningTab);
		wcTab = new WoodcuttingTab(tabWidth,xp.get("Woodcutting"));
		mainPane.addTab("Woodcutting", wcTab);


		
		this.add(mainPane);
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == refresh) {
			try {
				xp = PlayerXPUtil.getPlayerXP(playerName);
				attackTab.updateCurrentXP(xp.get("Attack"));
				strengthTab.updateCurrentXP(xp.get("Strength"));
				defenseTab.updateCurrentXP(xp.get("Defense"));
				fishingTab.updateCurrentXP(xp.get("Fishing"));
				miningTab.updateCurrentXP(xp.get("Mining"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else { //if (arg0.getSource() == 
			System.out.println("other");
		}
		
	}
}
