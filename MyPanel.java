import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Dimension;

public class MyPanel extends JPanel {
	private MyModel mm;
	private PlayerBullet[] pb;
	private int px, py;
	private int ex, ey;
	private int NUM_BULLET;
	private int point;

	public void setPlayerx(int px){
		this.px = px;
	}

	public void setPlayery(int py){
		this.py = py;
	}

	public void setEnemyx(int ex){
		this.ex = ex;
	}

	public void setEnemyy(int ey){
		this.ey = ey;
	}

	public void setPlayerBullet(PlayerBullet[] pb) {
		this.pb = pb;
	}

	public void setNUM_BULLET(int NUM_BULLET){
		this.NUM_BULLET = NUM_BULLET;
	}

	public void setPoint(int point){
		this.point = point;
	}

	public MyPanel(int px, int py) {
		//super();
		this.mm = new MyModel();
		this.px = px;
		this.py = py;
		this.ex = ex;
		this.ey = ey;
		setBackground(Color.black);
		setPreferredSize(new Dimension(1280, 720));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Toolkit.getDefaultToolkit().sync();

		//borderLine
		g.drawLine(0, 360, 1280, 360);
		//player
		g.drawImage(mm.getPlayer(), this.px, this.py, this);

		//playerbullet
		for (int i = 0; i < NUM_BULLET; i++) {
			if (pb[i].isAlive()) {
				pb[i].draw(g);
			}
		}
		String point = String.valueOf(this.point);
		//Playerpoint
		g.drawString("Point: " + point, 10, 50);

		//enemy
		g.drawImage(mm.getEnemy(), this.ex, this.ey, this);

		requestFocusInWindow();
	}
}
