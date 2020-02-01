import java.awt.*;
public class PlayerBullet {

	private static final int SPEED = 8;

	private MyModel mm;
	private int x, y;
	private int px, py;
	private int ex, ey;
	private int Hit;
	private int NUM_BULLET;
	private boolean isAlive;
	private boolean isCollision;

	public void setEnemyx(int ex){
		this.ex = ex;
	}

	public void setEnemyy(int ey){
		this.ey = ey;
	}

	public void setIsCollision(boolean isCollision){
		this.isCollision = isCollision;
	}

	public void setMyModel(MyModel mm){
		this.mm = mm;
	}

	public void setNUM_BULLET(int NUM_BULLET){
		this.NUM_BULLET = NUM_BULLET;
	}

	public int getCollisionx(){
		return this.x;
	}

	public int getCollisiony(){
		return this.y;
	}

	public PlayerBullet() {
		Hit = 0;
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
		this.x = x + 100 / 2;
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

	public void init(int px, int py) {
		this.x = px;
		this.y = py;
	}

	public void CollisionDetection() {
		//if (this.x > this.ex && this.x < this.ex + 79 && this.y < 20 + 79 && this.y > 20){
		if (isAlive == true && this.x > this.ex && this.x < this.ex + 100 && this.y < this.ey + 100 && this.y > this.ey){
			Hit += 1;
			System.out.println("Hit x-> " + this.x + " y -> " + this.y);
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

