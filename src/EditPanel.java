import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditPanel extends JPanel {
	private JTextField edit = new JTextField(20);
	private JButton addButton = new JButton("add");
	private JButton saveButton = new JButton("save");
	
	public EditPanel() {
		this.setBackground(Color.YELLOW);
		this.setLayout(new FlowLayout());
		add(edit);
		add(addButton);
		add(saveButton);
	}
}
