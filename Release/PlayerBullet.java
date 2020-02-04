import java.awt.*;
public class PlayerBullet {

	private static final int SPEED = 8;

	private MyModel mm;
	private int x, y;
	private int px, py;
	private int[] ex, ey;
	private int Hit;
	private int NUM_ENEMY;
	private boolean isAlive;
	private boolean isCollision;

	public void setEnemyx(int[] ex){
		this.ex = ex;
	}

	public void setEnemyy(int[] ey){
		this.ey = ey;
	}

	public void setIsCollision(boolean isCollision){
		this.isCollision = isCollision;
	}

	public void setMyModel(MyModel mm){
		this.mm = mm;
	}

	public void setNUM_ENEMY(int NUM_ENEMY){
		this.NUM_ENEMY = NUM_ENEMY;
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
		for (int i = 0; i < NUM_ENEMY; i++){
			if (isAlive == true && this.x > this.ex[i] && this.x < this.ex[i] + 100 && this.y < this.ey[i] + 100 && this.y > this.ey[i]){
				Hit += 1;
				System.out.println("Enemy[" + i + "] " + " Hit -> " + Hit + " x-> " + this.x + " y -> " + this.y);
				isCollision = true;
				isAlive = false;
			}
		}
	}

	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, 2, 10);
	}
}

