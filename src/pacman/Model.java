package pacman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Model extends JPanel implements ActionListener {

	private Dimension d;
	private final Font smallFont = new Font("Times New Roman", Font.BOLD, 16);
	protected boolean inGame = false;
	private boolean dying = false;

	private final int BLOCK_SIZE = 24;
	private final int N_BLOCKS = 15;
	private final int SCREEN_SIZE = N_BLOCKS * BLOCK_SIZE;
	private final int MAX_GHOSTS = 12;
	private final int PACMAN_SPEED = 6;

	private int N_GHOSTS = 6;
	private int lives, score;
	private int[] dx, dy;
	private int[] ghost_x, ghost_y, ghost_dx, ghost_dy, ghostSpeed;

	private Image heart, ghost;
	private Image up, down, left, right;

	private int pacman_x, pacman_y, pacmand_x, pacmand_y;
	protected int req_dx, req_dy;

	private final int validSpeed[] = { 1, 2, 3, 4, 6, 8 };
	private final int maxSpeed = 6;

	private int currentSpeed = 3;
	private short[] screenData;
	protected Timer timer;

	private final short levelData[] = { 19, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22, 17, 16, 16, 16, 16,
			24, 16, 16, 16, 16, 16, 16, 16, 16, 20, 25, 24, 24, 24, 28, 0, 17, 16, 16, 16, 16, 16, 16, 16, 20, 0, 0, 0,
			0, 0, 0, 17, 16, 16, 16, 16, 16, 16, 16, 20, 19, 18, 18, 18, 18, 18, 16, 16, 16, 16, 24, 24, 24, 24, 20, 17,
			16, 16, 16, 16, 16, 16, 16, 16, 20, 0, 0, 0, 0, 21, 17, 16, 16, 16, 16, 16, 16, 16, 16, 20, 0, 0, 0, 0, 21,
			17, 16, 16, 16, 24, 16, 16, 16, 16, 20, 0, 0, 0, 0, 21, 17, 16, 16, 20, 0, 17, 16, 16, 16, 16, 18, 18, 18,
			18, 20, 17, 24, 24, 28, 0, 25, 24, 24, 16, 16, 16, 16, 16, 16, 20, 21, 0, 0, 0, 0, 0, 0, 0, 17, 16, 16, 16,
			16, 16, 20, 17, 18, 18, 22, 0, 19, 18, 18, 16, 16, 16, 16, 16, 16, 20, 17, 16, 16, 20, 0, 17, 16, 16, 16,
			16, 16, 16, 16, 16, 20, 17, 16, 16, 20, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 20, 25, 24, 24, 24, 26, 24,
			24, 24, 24, 24, 24, 24, 24, 24, 28 };

	public Model() {
		loadImages();
		initVariables();
		addKeyListener(new TAdapter());
		setFocusable(true);
		initGame();
	}

	private void loadImages() {
		down = new ImageIcon("../images/down.gif").getImage();
		up = new ImageIcon("../images/up.gif").getImage();
		left = new ImageIcon("../images/left.gif").getImage();
		right = new ImageIcon("../images/right.gif").getImage();

		heart = new ImageIcon("../images/heart.png").getImage();
		ghost = new ImageIcon("../images/ghost.gif").getImage();

	}

	private void initVariables() {
		screenData = new short[N_BLOCKS * N_BLOCKS];
		d = new Dimension(400, 400);
		ghost_x = new int[MAX_GHOSTS];
		ghost_dx = new int[MAX_GHOSTS];
		ghost_y = new int[MAX_GHOSTS];
		ghost_dy = new int[MAX_GHOSTS];
		ghostSpeed = new int[MAX_GHOSTS];
		dx = new int[4];
		dy = new int[4];

		timer = new Timer(40, this);
		timer.restart();
	}

	protected void initGame() {
		lives = 3;
		score = 0;
		initLevel();
		N_GHOSTS = 6;
		currentSpeed = 3;
	}

	private void initLevel() {

		for (int i = 0; i < N_BLOCKS * N_BLOCKS; i++) {
			screenData[i] = levelData[i];
		}
	}
	
	private void playGame(Graphics2D g2d) {
		
	}

	private void continueLevel() {
		int dy = 1;
		int random;

		for (int i = 0; i < N_GHOSTS; i++) {

			ghost_y[i] = 4 * BLOCK_SIZE;
			ghost_x[i] = 4 * BLOCK_SIZE;

			ghost_dy[i] = 0;
			ghost_dx[i] = dx;
			dx = -dx;

			random = (int) (Math.random() * (currentSpeed + 1));

			if (random > currentSpeed) {
				random = currentSpeed;
			}

			ghostSpeed[i] = validSpeed[random];
		}
		
		pacman_x = 7 * BLOCK_SIZE;
		pacman_y = 11 * BLOCK_SIZE;
		pacmand_x = 0;
		pacmand_y = 0;
		req_dx = 0;
		req_dy = 0;
		dying = false;
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setBackground(Color.BLACK);
		g2d.fillRect(0, 0, d.width, d.height);  
		
		drawMaze(g2d);
		drawScore(g2d);
		
		if (inGame) {
			playGame(g2d);
		} else {
			showIntroScreen(g2d);
		}
		
		Toolkit.getDefaultToolkit().sync();
	}
	
	
	
	
	
	

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
