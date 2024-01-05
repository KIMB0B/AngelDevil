import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditPanel extends JPanel {
	private JTextField edit = new JTextField(20);
	private JButton addButton = new JButton("단어 추가");
	private JButton saveButton = new JButton("단어 저장");
	private Image backgroundImage;
	private TextSource textSource; //TextSource 이용해서 단어장에 단어 읽어옴
	
	public EditPanel() {
		this.setLayout(new FlowLayout());
		add(edit);
		add(addButton);
		add(saveButton);

		backgroundImage = new ImageIcon("./resources/edit.png").getImage();

		try {
			textSource = new TextSource();
		}
		catch(IOException e) {
			e.printStackTrace();
		}

		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addWord();
			}
		});

		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveWord();
			}
		});
	}

	//단어 추가
	private void addWord() {
		String word = edit.getText();
		if (!word.isEmpty()) {
			textSource.addWord(word);
			System.out.println("단어 추가: " + word);
			edit.setText("");
		}
	}

	//단어 저장
	private void saveWord() {
		try {
			textSource.saveWords();
			System.out.println("단어 저장");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
	}
}
