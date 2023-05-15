
public class Invader extends Ship {

	public Invader(double x, double y, double w, double h) {
		super(x, y, w, h);
	}

	@Override
	public Projectile shootProjectile() {

		Projectile x = new Projectile(this.getX() + this.getWidth() / 2, this.getY(), 5, 10, 5, 0);
		return x;
	}

	@Override
	public boolean colliding(Ship y, Projectile p) {
		if ((p.getWidth() / 2 + y.getWidth() / 2) > Math
				.sqrt((p.getX() - y.getX()) * (p.getX() - y.getX()) + (p.getY() - y.getY()) * (p.getY() - y.getY()))) {
			return true;
		} else {
			return false;
		}
	}

}
