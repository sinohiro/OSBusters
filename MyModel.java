import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Dimension;
import java.net.*;

public class MyModel {

	private boolean kleft;  //keyLeft
	private boolean kright;
	private boolean ERight; //EnemyRight
	private boolean ELeft;
	private boolean enemykill;
	private Image player;
	private Image enemy;
	private Image bullet;

	public MyModel(){
		super();
		Toolkit tk = Toolkit.getDefaultToolkit();
		player = tk.getImage("man0.png");
		enemy = tk.getImage("man1.png");
		bullet = tk.getImage("man2.png");
		ELeft = false;
		ERight = true;
		enemykill = false;
	}

	public int getLeftPmove(boolean kleft, int px){
		if (kleft && px > 0){
			px -= 10;
		}
		return px;
	}

	public int getRightPmove(boolean kright, int px){
		if (kright && px < 1280){
			px += 10;
		}
		return px;
	}

	public int getUpPmove(boolean kright, int py){
		if (kright && py > 360){
			py -= 10;
		}
		return py;
	}

	public int getDownPmove(boolean kright, int py){
		if (kright && py < 720 - 100){
			py += 10;
		}
		return py;
	}

	public int getEmove(boolean enemykill, int ex){
		if (!enemykill && ex < 1280 && ERight){
			ELeft = false;
			ex += 10;
		}
		else {
			ELeft = true;
		}

		if (!enemykill && ex > 0 && ELeft){
			ERight = false;
			ex -= 10;
		}
		else {
			ERight = true;
		}

		if (enemykill) {
			ex = 20;
		}
		return ex;
	}

	public int getPoint(int point){
		point += 1;
		return point;
	}

	public Image getPlayer(){
		return this.player;
	}
	public Image getEnemy(){
		return this.enemy;
	}
	public Image getPlayerBullet(){
		return this.bullet;
	}

}

