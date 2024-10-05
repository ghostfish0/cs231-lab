import java.awt.Graphics;
import java.util.Random;

public abstract class Agent {
	protected double x;
	protected double y;
	protected boolean moved;
	protected int radius;
	protected static Random rand;

	public Agent(double x, double y) {
		this.x = x;
		this.y = y;
		rand = new Random();
	}
	public Agent(double x, double y, int radius) {
		this(x, y);
		this.radius = radius;
	}
	public double getX() { return this.x; }
	public double getY() { return this.y; }
	public int getRadius() { return this.radius; }
	public boolean getMoved() { return this.moved; }
	public void setX(double newX) { this.x = newX; }
	public void setY(double newY) { this.y = newY; }
	public void setRadius(int newRadius) { this.radius = newRadius; }
	public String toString() { return "(" + this.x + ", " + this.y + ")"; }
	protected void attemptMove(Landscape scape) {
		double x_ = this.x + rand.nextInt(21) - 10;
		double y_ = this.y + rand.nextInt(21) - 10;
		if (x_ < 0 || y_ < 0 || x_ > scape.getWidth() || y_ > scape.getHeight()) {
			this.moved = false;
			return;
		}
		this.x = x_;
		this.y = y_;
		this.moved = true;
	}
	public boolean booleanUpdateState(Landscape scape) {
		updateState(scape);
		return this.moved;
	}
	public abstract void updateState(Landscape scape);
	public abstract void draw(Graphics g);
}
