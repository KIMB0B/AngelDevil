import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

public class GameFrame extends JFrame {
	private ImageIcon normalIcon = new ImageIcon("resources/normal.GIF");
	private ImageIcon pressedIcon = new ImageIcon("resources/pressed.GIF");
	private ImageIcon overIcon = new ImageIcon("resources/over.GIF");
	
	private JMenuItem startItem = new JMenuItem("start");
	private JMenuItem stopItem = new JMenuItem("stop");
	private JMenuItem stage1Item = new JMenuItem("stage1");
	private JMenuItem stage2Item = new JMenuItem("stage2");
	private JMenuItem stage3Item = new JMenuItem("stage3");

	private JButton startBtn = new JButton(normalIcon);
	private JButton stopBtn = new JButton("stop");
	
	private ScorePanel scorePanel = new ScorePanel();
	private EditPanel editPanel = new EditPanel();
	private GamePanel gamePanel = new GamePanel(scorePanel, editPanel);
	private boolean gameStopped = false;
	
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
		JMenu exitSubMenu = new JMenu("exit");
		fileMenu.add(exitSubMenu);
		JMenuItem exitItem = new JMenuItem("Exit Game");
		exitSubMenu.add(exitItem);
		JMenu stageMenu = new JMenu("Stage");
		stageMenu.add(stage1Item);
		stageMenu.add(stage2Item);
		stageMenu.add(stage3Item);
		mBar.add(fileMenu);
		mBar.add(stageMenu);
		
		startItem.addActionListener(new StartAction());
		stage1Item.addActionListener(new Stage1());
		stage2Item.addActionListener(new Stage2());
		stage3Item.addActionListener(new Stage3());

		mBar.add(fileMenu);
		mBar.add(stageMenu);

		startItem.addActionListener(new StartAction());
		stopItem.addActionListener(new StopAction());
		exitItem.addActionListener(new ExitAction());
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

	private class Stage1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {gamePanel.setStage(1);}
	}
	private class Stage2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {gamePanel.setStage(2);}
	}
	private class Stage3 implements ActionListener {
		public void actionPerformed(ActionEvent e) {gamePanel.setStage(3);}
	}

	private class StopAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(!gameStopped) {
				gamePanel.stopGame();
				stopBtn.setText("restart");
				stopItem.setText("restart");
			}
			else {
				gamePanel.restartGame();
				stopBtn.setText("stop");
				stopItem.setText("stop");
			}
			gameStopped = !gameStopped;
		}
	}

	private class ExitAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			confirmExit();
		}
	}

	private void confirmExit() {
		int result = JOptionPane.showConfirmDialog(GameFrame.this, "게임을 정말로 종료하시겠습니까?", "게임 종료", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
}
