import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Graphics;

public class ScorePanel extends JPanel {
	private int score = 0;
	private int stage = 1;
	private int life = 3;
	private JLabel textLabel = new JLabel("점수");
	private JLabel scoreLabel = new JLabel(Integer.toString(score));
	private JLabel stageTextLabel = new JLabel("단계");
	private JLabel stageLabel = new JLabel(Integer.toString(stage));
	private JLabel lifeTextLabel = new JLabel("생명");
	private JLabel lifeLabel = new JLabel(Integer.toString(life));
	private Image backgroundImage;

	public ScorePanel() {
		backgroundImage = new ImageIcon("./resources/side.png").getImage();
		//this.setBackground(Color.YELLOW);
		setLayout(null);

		textLabel.setSize(50, 20);
		textLabel.setLocation(10, 10);
		textLabel.setBackground(Color.YELLOW);
		add(textLabel);

		scoreLabel.setSize(100, 20);
		scoreLabel.setLocation(70, 10);
		add(scoreLabel);

		stageTextLabel.setSize(50, 30);
		stageTextLabel.setLocation(10, 30);
		add(stageTextLabel);

		stageLabel.setSize(100, 30);
		stageLabel.setLocation(70, 30);
		add(stageLabel);

		lifeTextLabel.setSize(50, 30);
		lifeTextLabel.setLocation(10, 55);
		add(lifeTextLabel);

		lifeLabel.setSize(100, 30);
		lifeLabel.setLocation(70, 55);
		add(lifeLabel);
	}

	public void setStage(int i) {
		stage = i;
		stageLabel.setText(Integer.toString(stage));
	}

	public void increase() {
		score += 10;
		scoreLabel.setText(Integer.toString(score));
	}

	public void lifeDecrease() {
		if(life > 0) {
			life -= 1;
		}
		lifeLabel.setText(Integer.toString(life));
	}
	public void specialIncrease() {
		score += 50;
		scoreLabel.setText(Integer.toString(score));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
	}
}
