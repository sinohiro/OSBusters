import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.MediaTracker;
import java.net.*;

public class MyApplet extends JApplet implements KeyListener, ActionListener {

	private static final int NUM_BULLET = 100;

	private MyPanel mp;
	private MyModel mm;
	private PlayerBullet[] pb;
	private Image player;
	private Timer timer;
	private int px, py, ex, ey;
	private int point;
	private boolean kleft, kright;
	private boolean isLeft;
	private boolean isRight;
	private boolean isUp;
	private boolean isDown;
	private boolean isShot;

	public void init() {
		px = 640;
		py = 600;
		ex = 20;
		ey = 20;
		point = 0;
		this.kleft = this.kright = false;
		isLeft = isRight = isUp = isDown = isShot = false;
		timer = new Timer(40, this);
		this.mm = new MyModel();
		this.mp = new MyPanel(px, py);
		this.pb = new PlayerBullet[NUM_BULLET];
		for (int i = 0 ; i < NUM_BULLET ; i++){
			pb[i] = new PlayerBullet();
		}

		mp.setPlayerBullet(pb);
		mp.setNUM_BULLET(NUM_BULLET);
		mp.addKeyListener(this);

		JPanel field = new JPanel();
		field.setLayout(new BoxLayout(field, BoxLayout.PAGE_AXIS));
		field.add(this.mp);

		this.timer.start();
		getContentPane().add(field);
	}

	public void playermove() {
		if (isLeft) {
			px = mm.getLeftPmove(true, px);
			mp.setPlayerx(px);
		}
		if (isRight) {
			px = mm.getRightPmove(true, px);
			mp.setPlayerx(px);
		}
		if (isUp) {
			py = mm.getUpPmove(true, py);
			mp.setPlayery(py);
		}
		if (isDown) {
			py = mm.getDownPmove(true, py);
			mp.setPlayery(py);
		}
		if (isShot) {
			for (int i = 0; i < NUM_BULLET; i++) {
				if (!pb[i].isAlive()) {
					pb[i].set(px, py);
					break;
				}
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		//playermove
		playermove();

		//enemymove
		ex = mm.getEmove(false, ex);
		mp.setEnemyx(ex);
		for (int i = 0; i < NUM_BULLET; i++) {
			pb[i].setEnemyx(ex);
		}

		//playerbulletmove
		for (int i = 0; i < NUM_BULLET; i++) {
			if (pb[i].isAlive()) {
				pb[i].move();
				pb[i].CollisionDetection();
			}
		}

		//playerbulletcollision
		for (int i = 0; i < NUM_BULLET; i++) {
			if (pb[i].isCollision()){
				point = mm.getPoint(point);
				pb[i].setIsCollision(false);
				mp.setPoint(point);
			}
		}
		mp.setPlayerBullet(pb);

		this.mp.repaint();
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_A:
				isLeft = true;
				break;
			case KeyEvent.VK_D:
				isRight = true;
				break;
			case KeyEvent.VK_W:
				isUp = true;
				break;
			case KeyEvent.VK_S:
				isDown = true;
				break;
			case KeyEvent.VK_SPACE:
				isShot = true;
				break;
		}
		this.mp.repaint();
	}

	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_A:
				isLeft = false;
				break;
			case KeyEvent.VK_D:
				isRight = false;
				break;
			case KeyEvent.VK_W:
				isUp = false;
				break;
			case KeyEvent.VK_S:
				isDown = false;
				break;
			case KeyEvent.VK_SPACE:
				isShot = false;
				break;
		}
	}

	public void keyTyped(KeyEvent e) {}
}
