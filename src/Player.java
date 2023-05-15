public class Player extends Ship {

	public Player(double x, double y, double w, double h) {
		super(x, y, w, h);
	}

	@Override
	public Projectile shootProjectile() {
		Projectile p = new Projectile(this.getX() + this.getWidth() / 2, this.getY(), 20, 20, 15, 5);
		p.setSpeed(-15);
		return p;
	}

	@Override
	public boolean colliding(Ship x, Projectile p) {
		if (Math.sqrt((p.getX() - x.getX()) * (p.getX() - x.getX())
				+ (p.getY() - x.getY()) * (p.getY() - x.getY())) < (p.getWidth() / 2 + x.getWidth() / 2)) {
			return true;
		} else {
			return false;
		}
	}

}
