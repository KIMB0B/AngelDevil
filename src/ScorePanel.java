import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Graphics;

public class ScorePanel extends JPanel {
	private int score = 0;
	private JLabel textLabel = new JLabel("점수");
	private JLabel scoreLabel = new JLabel(Integer.toString(score));
	private Image backgroundImage;

	public ScorePanel() {
		backgroundImage = new ImageIcon("./resources/side.png").getImage();
		//this.setBackground(Color.YELLOW);
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
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
	}
}
