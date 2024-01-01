import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class EditPanel extends JPanel {
	private JTextField edit = new JTextField(20);
	private JButton addButton = new JButton("단어 추가");
	private JButton saveButton = new JButton("단어 저장");
	private Image backgroundImage;
	
	public EditPanel() {
		this.setLayout(new FlowLayout());
		add(edit);
		add(addButton);
		add(saveButton);

		backgroundImage = new ImageIcon("./resources/edit.png").getImage();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
	}
}
