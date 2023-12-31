import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

public class GamePanel extends JPanel {
	private JTextField input = new JTextField(40);
	private ScorePanel scorePanel;
	private EditPanel editPanel;
	private TextSource textSource = new TextSource();
	private Timer timer;
	private ArrayList<JLabel> words = new ArrayList<>();

	public GamePanel(ScorePanel scorePanel, EditPanel editPanel) throws IOException {
		this.scorePanel = scorePanel;
		this.editPanel = editPanel;

		setLayout(null);
		GameGroundPanel gameGroundPanel = new GameGroundPanel();
		gameGroundPanel.setBounds(0, 0, 550, 430);
		add(gameGroundPanel);

		InputPanel inputPanel = new InputPanel();
		inputPanel.setBounds(0, 430, 550, 50);
		add(inputPanel);
		input.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField t = (JTextField) (e.getSource());
				String inWord = t.getText();
				checkAndRemoveWord(inWord);
				t.setText("");
			}
		});
	}

	int addSpeed = 3500;

	public void setStage(int stage){
		if(stage == 1){
			addSpeed = 3500;
			scorePanel.setStage(1);
		} else if(stage == 2) {
			addSpeed = 2000;
			scorePanel.setStage(2);
		} else if(stage == 3) {
			addSpeed = 1000;
			scorePanel.setStage(3);
		}
		if (timer != null && timer.isRunning()) {
			timer.setDelay(addSpeed);
			timer.restart();
		}
	}

	public void startGame() {
		if (timer != null) {
			timer.stop();
		}

		for (JLabel wordLabel : words) {
			remove(wordLabel);
		}
		words.clear();
		scorePanel.lifeInit();
		repaint();

		timer = new Timer(addSpeed, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addNewWord();
			}
		});
		timer.start();
	}

	public void stopGame() {
		if (timer != null) {
			timer.stop();
		}

		for (JLabel wordLabel : words) {
			Timer wordTimer = (Timer) wordLabel.getClientProperty("wordTimer");
			if (wordTimer != null) {
				wordTimer.stop();
			}
		}
		repaint();
	}

	public void restartGame() {
		if(timer != null) {
			timer.stop();
		}
		for(JLabel wordLabel : words) {
			moveWordDown(wordLabel);
		}
		timer = new Timer(addSpeed, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addNewWord();
			}
		});
		timer.start();
	}


	private void addNewWord() {
		String newWord = textSource.get();
		JLabel wordLabel = new JLabel(newWord);
		wordLabel.setOpaque(true);
		if (Math.random() < 0.2) {
			wordLabel.setBackground(Color.YELLOW);
			wordLabel.putClientProperty("item", "bonus1");
		}
		else if (Math.random() < 0.1) {
			wordLabel.setBackground(Color.GREEN);
			wordLabel.putClientProperty("item", "bonus2");
		}
		else if (Math.random() < 0.1) {
			wordLabel.setBackground(Color.PINK);
			wordLabel.putClientProperty("item", "life1");
		}
		else if (Math.random() < 0.03) {
			wordLabel.setBackground(Color.LIGHT_GRAY);
			wordLabel.putClientProperty("item", "life2");
		}
		else {
			wordLabel.setBackground(Color.WHITE);
			wordLabel.putClientProperty("item", "normal");
		}
		wordLabel.setSize(100, 30);

		int randomX = (int) (Math.random() * (this.getWidth() - wordLabel.getWidth()));
		wordLabel.setLocation(randomX, 10);
		words.add(wordLabel);
		add(wordLabel);
		setComponentZOrder(wordLabel, 0);
		moveWordDown(wordLabel);

		revalidate();
		repaint();
	}

	private void moveWordDown(JLabel wordLabel) {
		Timer wordTimer = new Timer(300, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!GamePanel.this.isAncestorOf(wordLabel)) {
					((Timer) e.getSource()).stop();
					return;
				}

				Point location = wordLabel.getLocation();
				location.y += 10;
				wordLabel.setLocation(location);

				int lineY = GamePanel.this.getHeight() - 50;
				if (location.y > lineY - wordLabel.getHeight()) {
					if (wordLabel.getClientProperty("item") != "life2") {
						scorePanel.lifeDecrease();
					}
					if (scorePanel.checkGameOver()){
						stopGame();
						JOptionPane.showMessageDialog(GamePanel.this, "Game Over!");
					}
					remove(wordLabel);
					words.remove(wordLabel);
					repaint();
					((Timer) e.getSource()).stop();
				}
			}
		});
		wordTimer.start();
		wordLabel.putClientProperty("wordTimer", wordTimer);
	}


	private void checkAndRemoveWord(String inputWord) {
		for (int i = 0; i < words.size(); i++) {
			JLabel wordLabel = words.get(i);
			if (wordLabel.getText().equals(inputWord)) {
				remove(wordLabel);
				words.remove(i);
				if (wordLabel.getClientProperty("item") == "bonus1") {
					scorePanel.scoreIncrease(50);
				}
				else if (wordLabel.getClientProperty("item") == "bonus2") {
					scorePanel.scoreIncrease(100);
				}
				else if (wordLabel.getClientProperty("item") == "normal") {
					scorePanel.scoreIncrease(10);
				}
				else if (wordLabel.getClientProperty("item") == "life1") {
					scorePanel.scoreIncrease(10);
					scorePanel.lifeIncrease();
				}
				else if (wordLabel.getClientProperty("item") == "life2") {
					scorePanel.scoreIncrease(10);
					scorePanel.lifeDecrease();
				}
				repaint();
				break;
			}
		}
	}

	class GameGroundPanel extends JPanel {
		private Image background;

		public GameGroundPanel() {
			background = new ImageIcon("./resources/background.jpg").getImage();
			setLayout(null);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);

			Graphics2D g2d = (Graphics2D) g;
			float thickness = 3.0f;
			g2d.setStroke(new BasicStroke(thickness));
			g2d.setColor(Color.RED);
			int lineY = GamePanel.this.getHeight() - 50;
			g2d.drawLine(0, lineY, this.getWidth(), lineY);
		}
	}

	class InputPanel extends JPanel {
		public InputPanel() {
			setLayout(new FlowLayout());
			this.setBackground(Color.CYAN);
			add(input);
		}
	}
}
