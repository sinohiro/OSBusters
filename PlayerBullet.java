import java.awt.*;
public class PlayerBullet {

	private static final int SPEED = 8;

	private MyModel mm;
	private int x, y;
	private int ex;
	private boolean isAlive;
	private boolean isCollision;

	public void setEnemyx(int ex){
		this.ex = ex;
	}

	public void setIsCollision(boolean isCollision){
		this.isCollision = isCollision;
	}

	public PlayerBullet() {
		this.mm = new MyModel();
		isAlive = false;
		isCollision = false;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public boolean isCollision() {
		return isCollision;
	}

	public void set(int x, int y) {
		this.x = x + 79 / 2;
		this.y = y + SPEED;
		isAlive = true;
	}

	public void move() {
		if (y < -100) {
			isAlive = false;
		} else {
			y -= SPEED;
		}
	}

	public void CollisionDetection() {
		//if (this.x > this.ex && this.x < this.ex + 79 && this.y < 20 + 79 && this.y > 20){
		if (isAlive == true && this.x > this.ex && this.x < this.ex + 79 && this.y < 20){
			System.out.println("Hit");
			isCollision = true;
			isAlive = false;
		} else {
			isCollision = false;
		}
	}

	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, 2, 10);
	}
}

