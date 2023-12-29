import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

public class GamePanel extends JPanel {
	private JTextField input = new JTextField(40);
	private JLabel text = new JLabel("타이핑해보세요");
	private ScorePanel scorePanel = null;
	private EditPanel editPanel = null;
	private TextSource textSource = new TextSource();

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
