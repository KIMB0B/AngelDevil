import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel {
	private int score = 0;
	private JLabel textLabel = new JLabel("점수");
	private JLabel scoreLabel = new JLabel(Integer.toString(score));
	
	public ScorePanel() {
		this.setBackground(Color.YELLOW);
		setLayout(null);
		
		textLabel.setSize(50, 20);
		textLabel.setLocation(10, 10);
		add(textLabel);
		
		scoreLabel.setSize(100, 20);
		scoreLabel.setLocation(70, 10);
		add(scoreLabel);		
	}
	
	public void increase() {
		score += 10;
		scoreLabel.setText(Integer.toString(score));
	}
}
