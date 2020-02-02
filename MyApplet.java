import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.MediaTracker;
import java.net.*;

public class MyApplet extends JApplet implements KeyListener, ActionListener {

	private static final int NUM_BULLET = 100;
	private static final int NUM_ENEMY = 20;

	private JPanel cardPanel;
	private JButton selplayerbutton0;
	private JButton selplayerbutton1;
	private JButton selplayerbutton2;
	private JButton selenemybutton0;
	private JButton selenemybutton1;
	private JButton selenemybutton2;
	private JButton startbutton;
	private JButton goSelmenubutton;
	private JLabel selmenulabel;
	private CardLayout layout;
	private MyPanel mp;
	private MyModel mm;
	private PlayerBullet[] pb;
	private Enemy[] enemy;
	private Title title;
	private Image player;
	private Timer timer;
	private ImageIcon apple_icon = new ImageIcon("image/apple_logo.png");
	private ImageIcon linux_icon = new ImageIcon("image/linux_logo.png");
	private ImageIcon windows_icon = new ImageIcon("image/windows_logo.png");
	private int px, py;
	private int[] ex;
	private int[] ey;
	private int cbulletx, cbullety;
	private int cenemyx, cenemyy;
	private int point;
	private int flame;
	private int ExprosionTime;
	private boolean kleft, kright;
	private boolean isLeft;
	private boolean isRight;
	private boolean isUp;
	private boolean isDown;
	private boolean isShot;

	public void init() {
		this.selplayerbutton0 = new JButton(apple_icon);
		this.selplayerbutton1 = new JButton(linux_icon);
		this.selplayerbutton2 = new JButton(windows_icon);
		this.selenemybutton0 = new JButton(apple_icon);
		this.selenemybutton1 = new JButton(linux_icon);
		this.selenemybutton2 = new JButton(windows_icon);
		this.startbutton = new JButton("GameStart");
		this.startbutton.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 32));
		this.goSelmenubutton = new JButton("Start");
		this.goSelmenubutton.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 32));
		this.selmenulabel = new JLabel("Select Player & Enemy");
		this.selmenulabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 40));
		px = 640;
		py = 600;
		point = 0;
		flame = 0;
		cbulletx = cbullety = 0;
		cenemyx = cenemyy = 0;
		ExprosionTime = 0;
		this.ex = new int[NUM_ENEMY];
		this.ey = new int[NUM_ENEMY];
		this.kleft = this.kright = false;
		isLeft = isRight = isUp = isDown = isShot = false;
		timer = new Timer(40, this);
		this.timer.stop();
		this.mm = new MyModel();
		this.mp = new MyPanel(px, py);
		this.pb = new PlayerBullet[NUM_BULLET];
		for (int i = 0 ; i < NUM_BULLET ; i++){
			pb[i] = new PlayerBullet();
			pb[i].setMyModel(this.mm);
			pb[i].setNUM_ENEMY(NUM_ENEMY);
		}

		this.enemy = new Enemy[NUM_ENEMY];
		for (int i = 0 ; i < NUM_ENEMY ; i++){
			enemy[i] = new Enemy();
			enemy[i].setMyModel(this.mm);
		}
		this.title = new Title();

		mp.setPlayerBullet(pb);
		mp.setEnemy(enemy);
		mp.setNUM_BULLET(NUM_BULLET);
		mp.setNUM_ENEMY(NUM_ENEMY);
		mp.setMyModel(this.mm);
		title.setMyModel(this.mm);

		mp.addKeyListener(this);

		JPanel selplayer = new JPanel();
		selplayer.setLayout(new FlowLayout());
		selplayer.add(this.selplayerbutton0);
		selplayer.add(this.selplayerbutton1);
		selplayer.add(this.selplayerbutton2);

		JPanel selenemy = new JPanel();
		selenemy.setLayout(new FlowLayout());
		selenemy.add(this.selenemybutton0);
		selenemy.add(this.selenemybutton1);
		selenemy.add(this.selenemybutton2);

		JPanel selbutton = new JPanel();
		selbutton.setLayout(new BoxLayout(selbutton, BoxLayout.Y_AXIS));
		selbutton.add(selplayer);
		selbutton.add(selenemy);

		JPanel selmenu = new JPanel();
		selmenu.setLayout(new BorderLayout());
		selmenu.add("North", selmenulabel);
		selmenu.add("Center", selbutton);
		selmenu.add("South", this.startbutton);

		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new BorderLayout());
		titlePanel.add("North", this.title);
		titlePanel.add("South", this.goSelmenubutton);

		JPanel field = new JPanel();
		field.setLayout(new BoxLayout(field, BoxLayout.PAGE_AXIS));
		field.add(this.mp);

		cardPanel = new JPanel();
		layout = new CardLayout();
		cardPanel.setLayout(layout);
		cardPanel.add(titlePanel, "title");
		cardPanel.add(selmenu, "selmenu");
		cardPanel.add(field, "game");

		getContentPane().add(cardPanel);

		this.selplayerbutton0.addActionListener(this);
		this.selplayerbutton1.addActionListener(this);
		this.selplayerbutton2.addActionListener(this);
		this.selenemybutton0.addActionListener(this);
		this.selenemybutton1.addActionListener(this);
		this.selenemybutton2.addActionListener(this);
		this.startbutton.addActionListener(this);
		this.goSelmenubutton.addActionListener(this);
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
		if (e.getSource() == this.selplayerbutton0) {
			mm.setPlayerImage(0);
		}
		if (e.getSource() == this.selplayerbutton1) {
			mm.setPlayerImage(1);
		}
		if (e.getSource() == this.selplayerbutton2) {
			mm.setPlayerImage(2);
		}
		if (e.getSource() == this.selenemybutton0) {
			mm.setEnemyImage(0);
		}
		if (e.getSource() == this.selenemybutton1) {
			mm.setEnemyImage(1);
		}
		if (e.getSource() == this.selenemybutton2) {
			mm.setEnemyImage(2);
		}
		if (e.getSource() == this.startbutton) {
			layout.show(cardPanel, "game");
			this.timer.start();
		}
		if (e.getSource() == this.goSelmenubutton) {
			layout.show(cardPanel, "selmenu");
		}

	//flame_count
		flame += 1;
		//playermove
		playermove();

		//enemymove
		for (int i = 0; i < NUM_ENEMY; i++) {
			if (!enemy[i].isAlive()){
				enemy[i].set();
			}
			if (enemy[i].isAlive()){
				enemy[i].move();
			}
			ex[i] = enemy[i].getEnemyx();
			ey[i] = enemy[i].getEnemyy();
			mp.setEnemyx(ex);
			mp.setEnemyy(ey);
		}

		//playerbulletmove
		for (int i = 0; i < NUM_BULLET; i++) {
			if (pb[i].isAlive()){
				pb[i].setEnemyx(ex);
				pb[i].setEnemyy(ey);
				pb[i].move();
			}
			pb[i].setNUM_ENEMY(NUM_ENEMY);
			pb[i].CollisionDetection();
		}

		//playerbulletcollision
		for (int i = 0; i < NUM_BULLET; i++) {
			if (pb[i].isCollision()){
				point = mm.getPoint(point);
				cbulletx = pb[i].getCollisionx();
				cbullety = pb[i].getCollisiony();
				pb[i].setIsCollision(false);
				mp.setPoint(point);
				mp.setIsExprosion(true);
			}
		}

		//CollisionSync
		for (int i = 0; i < NUM_ENEMY; i++){
			cenemyx = enemy[i].getEnemyx();
			cenemyy = enemy[i].getEnemyy();
			if (cenemyx < cbulletx && cbulletx < cenemyx + 100 && cenemyy < cbullety && cbullety < cenemyy + 100){
				enemy[i].setIsCollision(true);
				cbulletx = cbullety = 0;
			}
		}
		//Exprosion_prosess
		if (mp.getIsExplosion()) {
			if ((flame % 20) == 0) {
				for (int i = 0; i < NUM_ENEMY; i++){
					if(enemy[i].isCollision()){
						enemy[i].setExprosionTime();
					}
				}
			}
		}
		mp.setPlayerBullet(pb);
		mp.setEnemy(enemy);

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
			case KeyEvent.VK_ESCAPE:
				layout.show(cardPanel, "title");
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
