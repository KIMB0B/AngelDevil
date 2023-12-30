import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.Timer;

import javax.swing.*;

public class GamePanel extends JPanel {
	private JTextField input = new JTextField(40);
	private JLabel text = new JLabel("타이핑해보세요");
	private ScorePanel scorePanel = null;
	private EditPanel editPanel = null;
	private TextSource textSource = new TextSource();
	private Timer timer;

	public GamePanel(ScorePanel scorePanel, EditPanel editPanel) throws IOException {
		this.scorePanel = scorePanel;
		this.editPanel = editPanel;

		setLayout(new BorderLayout());
		add(new GameGroundPanel(), BorderLayout.CENTER);
		add(new InputPanel(), BorderLayout.SOUTH);
		input.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField t = (JTextField)(e.getSource());
				String inWord = t.getText();
				if(text.getText().equals(inWord)) {
					scorePanel.increase();
					startGame();
					t.setText("");
				}
			}
		});
	}

	public void startGame() {
		String newWord = textSource.get();
		text.setText(newWord);
		text.setBackground(Color.GREEN);
		text.setOpaque(true);
		int randomX = (int) (Math.random() * (this.getWidth() - text.getWidth()));
		text.setLocation(randomX, 10);

		// 타이머를 여기에서 시작
		if (timer != null) {
			timer.stop(); // 이전 타이머가 있으면 중지
		}
		timer = new Timer(300, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveWordDown();
			}
		});
		timer.start();
	}

	private void moveWordDown() {
		Point location = text.getLocation();
		location.y += 10;
		text.setLocation(location);

		int lineY = this.getHeight() - 50;
		if(location.y > lineY - text.getHeight()) {
			timer.stop();
			JOptionPane.showMessageDialog(this, "Game Over!");
		}

	}

	class GameGroundPanel extends JPanel {
		private Image background;

		public GameGroundPanel() {
			background = new ImageIcon("./resources/background.jpg").getImage();
			setLayout(null);
			text.setSize(100, 30);
			text.setLocation(100, 10);
			add(text);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);

			Graphics2D g2d = (Graphics2D) g;

			// 선의 굵기 설정
			float thickness = 3.0f; // 선의 굵기를 원하는 값으로 조절
			g2d.setStroke(new BasicStroke(thickness));

			// 선 그리기
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