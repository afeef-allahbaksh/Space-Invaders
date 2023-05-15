
public abstract class Ship implements Collision, Comparable<Ship> {

	private double x;
	private double y;
	private double w;
	private double h;
	private int shootingRate;

	public Ship(double x, double y, double w, double h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
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

	public void setWidth(double w) {
		this.w = w;
	}

	public double getHeight() {
		return h;
	}

	public void setHeight(double h) {
		this.h = h;
	}

	public int getshootingRate() {
		return shootingRate;
	}

	public void setFireRate(int newSR) {
		shootingRate = newSR;
	}

	@Override
	public int compareTo(Ship other) {
		if (other == null) {
			throw new NullPointerException("Invalid ship");
		}
		return 0;
	}

	public abstract Projectile shootProjectile();
}
