import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Dimension;

public class MyPanel extends JPanel {
	private MyModel mm;
	private PlayerBullet[] pb;
	private Enemy[] enemy;
	private int px, py;
	private int[] ex, ey;
	private int NUM_BULLET;
	private int NUM_ENEMY;
	private int point;
	private boolean isExprosion;
	private Image playerimage;
	private Image explosionimage;

	public void setPlayerImage(Image playerimage){
		this.playerimage = playerimage;
	}

	public void setExplosionImage(Image explosionimage){
		this.explosionimage = explosionimage;
	}

	public void setPlayerx(int px){
		this.px = px;
	}

	public void setPlayery(int py){
		this.py = py;
	}

	public void setEnemyx(int[] ex){
		this.ex = ex;
	}

	public void setEnemyy(int[] ey){
		this.ey = ey;
	}

	public void setPlayerBullet(PlayerBullet[] pb) {
		this.pb = pb;
	}

	public void setEnemy(Enemy[] enemy) {
		this.enemy = enemy;
	}

	public void setNUM_BULLET(int NUM_BULLET){
		this.NUM_BULLET = NUM_BULLET;
	}

	public void setNUM_ENEMY(int NUM_ENEMY){
		this.NUM_ENEMY = NUM_ENEMY;
	}

	public void setPoint(int point){
		this.point = point;
	}

	public void setIsExprosion(boolean isExprosion){
		this.isExprosion = isExprosion;
	}

	public void setMyModel(MyModel mm){
		this.mm = mm;
	}

	public boolean getIsExplosion(){
		return this.isExprosion;
	}

	public MyPanel(int px, int py) {
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
		g.drawImage(this.playerimage, this.px, this.py, this);

		//playerbullet
		for (int i = 0; i < NUM_BULLET; i++) {
			if (pb[i].isAlive()) {
				pb[i].draw(g);
			}
		}
		String point = String.valueOf(this.point);
		//Playerpoint
		g.setFont(new Font("ＭＳ ゴシック",Font.PLAIN,30));
		g.drawString("Point: " + point, 10, 50);

		for (int i = 0; i < NUM_ENEMY; i++) {
			if (enemy[i].isAlive()){
				enemy[i].draw(g);
			}
		}

		//enemyExprosion
		for (int i = 0; i < NUM_ENEMY; i++){
			if (enemy[i].isCollision()) {
				//System.out.println("Exprosion!! x-> " + enemy[i].getEnemyx() + "y -> " + enemy[i].getEnemyy());
				g.drawImage(this.explosionimage, enemy[i].getEnemyx(), enemy[i].getEnemyy(), this);
			}
		}
		requestFocusInWindow();
	}
}
