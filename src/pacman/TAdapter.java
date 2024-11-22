package pacman;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TAdapter extends KeyAdapter {
	
	Model model;
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (model.inGame) {
			
			if (key == KeyEvent.VK_LEFT) {
				model.req_dx = -1;
				model.req_dy = 0;
			}
			
			else if (key == KeyEvent.VK_RIGHT) {
				model.req_dx = 1;
				model.req_dy = 0;
			}
			
			else if (key == KeyEvent.VK_UP) {
				model.req_dx = 0;
				model.req_dy = -1;
			}
			
			else if (key == KeyEvent.VK_DOWN) {
				model.req_dx = 0;
				model.req_dy = 1;
			}
			
			else if (key == KeyEvent.VK_ESCAPE && model.timer.isRunning()) {
				model.inGame = false;
			}
		} else {
			if (key == KeyEvent.VK_SPACE) {
				model.inGame = true;
				model.initGame();
			}
		}
	}
}
