import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;

public class Enemy {

	private MyModel mm;
	private PlayerBullet[] pb;
	private int ex, ey;
	private int ExprosionTime;
	private int NUM_BULLET;
	private boolean isAlive;
	private boolean isCollision;
	private boolean ERight; //EnemyRight
	private boolean ELeft;

	public void setEnemyx(int ex){
		this.ex = ex;
	}

	public void setIsCollision(boolean isCollision){
		this.isCollision = isCollision;
	}

	public void setExprosionTime(){
		if (ExprosionTime < 1){
			this.ExprosionTime += 1;
		}else{
			this.isCollision = false;
			this.ExprosionTime = 0;
		}
	}

	public void setMyModel(MyModel mm){
		this.mm = mm;
	}

	public void setNUM_BULLET(int NUM_BULLET){
		this.NUM_BULLET = NUM_BULLET;
	}

	public void setPlayerBullet(PlayerBullet[] pb) {
		this.pb = pb;
	}

	public int getEnemyx(){
		return this.ex;
	}

	public int getEnemyy(){
		return this.ey;
	}

	public Enemy() {
		this.ExprosionTime = 0;
		isAlive = false;
		int r = (int)(Math.random() * 50) + 1;
		if (r <= 25){
			ELeft = false;
			ERight = true;
		}else{
			ELeft = true;
			ERight = false;
		}
	}

	public boolean isAlive() {
		return isAlive;
	}

	public boolean isCollision(){
		return this.isCollision;
	}

	public void set() {
		ex = (int)(Math.random() * 1150) + 1;
		ey = (int)(Math.random() * 260) + 1;
		isAlive = true;
	}

	public void move() {
		if (ex < 1280 -79 && ERight){
			ELeft = false;
			ex += 10;
		}else {
			ELeft = true;
		}

		if (ex > 0 && ELeft){
			ERight = false;
			ex -= 10;
		}else {
			ERight = true;
		}
	}

	public void draw(Graphics g) {
		g.drawImage(mm.getEnemy(), this.ex, this.ey, null);
	}

	public void exprosiondraw(Graphics g) {
		g.drawImage(mm.getEnemy(), this.ex, this.ey, null);
	}

}

