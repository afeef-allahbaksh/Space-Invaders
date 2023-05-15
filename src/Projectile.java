
public class Projectile implements Comparable<Projectile> {

	private double x;
	private double y;
	private double w;
	private double h;
	private double speed;
	private double type;

	public Projectile(double x, double y, double w, double h, double speed, double type) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getWidth() {
		return w;
	}

	public double getHeight() {
		return h;
	}

	public void setSpeed(double newSpeed) {
		this.speed = newSpeed;
	}

	public void Move() {
		this.y += speed;
	}

	@Override
	public int compareTo(Projectile other) {
		if (other == null) {
			throw new NullPointerException("Invalid projectile");
		}
		if (this.type > other.type) {
			return 1;
		}
		if (this.type < other.type) {
			return -1;
		} else {
			return 0;
		}
	}

}
