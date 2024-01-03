import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;

public class GameFrame extends JFrame {
	private ImageIcon normalIcon = new ImageIcon("resources/normal.GIF");
	private ImageIcon pressedIcon = new ImageIcon("resources/pressed.GIF");
	private ImageIcon overIcon = new ImageIcon("resources/over.GIF");
	
	private JMenuItem startItem = new JMenuItem("start");
	private JMenuItem stopItem = new JMenuItem("stop");
	private JButton startBtn = new JButton(normalIcon);
	private JButton stopBtn = new JButton("stop");
	
	private ScorePanel scorePanel = new ScorePanel();
	private EditPanel editPanel = new EditPanel();
	private GamePanel gamePanel = new GamePanel(scorePanel, editPanel);
	
	public GameFrame() throws IOException {
		setTitle("타이핑 게임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		splitPane();
		makeMenu();
		makeToolBar();
		setResizable(false);
		setVisible(true);
	}

	private void splitPane() {
		JSplitPane hPane = new JSplitPane();
		getContentPane().add(hPane, BorderLayout.CENTER);
		hPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		hPane.setDividerLocation(550);
		hPane.setEnabled(false);
		hPane.setLeftComponent(gamePanel);
		
		JSplitPane pPane = new JSplitPane();
		pPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		pPane.setDividerLocation(300);
		pPane.setTopComponent(scorePanel);
		pPane.setBottomComponent(editPanel);
		hPane.setRightComponent(pPane);
	}
	private void makeMenu() {
		JMenuBar mBar = new JMenuBar();
		setJMenuBar(mBar);
		JMenu fileMenu = new JMenu("Game");
		fileMenu.add(startItem);
		fileMenu.add(stopItem);
		fileMenu.addSeparator();
		fileMenu.add(new JMenuItem("exit"));
		JMenu stageMenu = new JMenu("Stage");
		stageMenu.add(new JMenuItem("stage1"));
		stageMenu.add(new JMenuItem("stage2"));
		stageMenu.add(new JMenuItem("stage3"));
		mBar.add(fileMenu);
		mBar.add(stageMenu);
		
		startItem.addActionListener(new StartAction());
	}
	
	private void makeToolBar() {
		JToolBar tBar = new JToolBar();
		tBar.add(startBtn);
		tBar.add(stopBtn);
		getContentPane().add(tBar, BorderLayout.NORTH);
		
		startBtn.addActionListener(new StartAction());
		stopBtn.addActionListener(new StopAction());
		
		startBtn.setRolloverIcon(overIcon);
		startBtn.setPressedIcon(pressedIcon);
	}
	
	private class StartAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			gamePanel.startGame();
		}
	}

	private class StopAction implements ActionListener {
		private boolean gameStopped = false;
		public void actionPerformed(ActionEvent e) {
			if(!gameStopped) {
				gamePanel.stopGame();
				stopBtn.setText("Restart");
			}
			else {
				gamePanel.restartGame();
				stopBtn.setText("Stop");
			}
			gameStopped = !gameStopped;
		}
	}
	
}
