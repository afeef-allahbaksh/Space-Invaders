import java.util.ArrayList;

import processing.core.PApplet;

public class drawSpaceInv extends PApplet {
	public ArrayList<Projectile> playerProjectile;
	public ArrayList<Projectile> invaderProjectile;
	private Player p;
	private ArrayList<Invader> invaders;
	private boolean gameOver = false;
	private int lefttoright = 1;

	public static void main(String[] args) {
		PApplet.main("drawSpaceInv");
	}

	@Override
	public void setup() {
		playerProjectile = new ArrayList<Projectile>();
		invaderProjectile = new ArrayList<Projectile>();
		noCursor();
		frameRate(20);
		p = new Player(500, 675, 100, 10);
		invaders = new ArrayList<Invader>();
		for (int i = 100; i < 301; i += 100) {
			for (int j = 60; j < 961; j += 100) {
				Invader a = new Invader(j, i, 40, 40);
				invaders.add(a);
			}
		}
		drawPlayer();
		drawInvaders();
	}

	@Override
	public void settings() {
		fullScreen();
	}

	@Override
	public void draw() {
		if (!gameOver) {
			background(255, 99, 71);
			createInvaderProjectile();
			updateInvaders();
			updatePlayerProjectile();
			updateInvaderProjectile();
			updateCollisions();
			drawPlayer();
			drawInvaders();
			drawPlayerProjectile();
			drawInvaderProjectile();
		}

		else if (gameOver && invaders.size() == 0) {

			fill(128);
			textAlign(CENTER);
			textSize(30);
			text("YOU WON!.", 500, 500);

		} else {
			background(0, 255, 255);
			fill(128);
			textAlign(CENTER);
			textSize(30);
			text("YOU LOST! TRY AGAIN NEXT TIME.", 500, 500);

		}
	}

	public void drawPlayer() {
		fill(165, 42, 42);
		rect((float) (p.getX()), (float) (p.getY()), (float) (p.getWidth()), (float) (p.getHeight()));
	}

	public void drawPlayerProjectile() {
		if (playerProjectile.size() > 0) {
			for (int k = 0; k < playerProjectile.size(); k++) {
				Projectile pp = playerProjectile.get(k);
				fill(139, 0, 0);
				rect((float) (pp.getX()), (float) (pp.getY()), (float) (pp.getWidth()), (float) (pp.getHeight()));
			}
		}
	}

	public void updatePlayerProjectile() {
		for (int i = 0; i < playerProjectile.size(); i++) {
			playerProjectile.get(i).Move();
			if (playerProjectile.get(i).getY() < 0) {
				playerProjectile.remove(playerProjectile.get(i));
			}
		}
	}

	public void createInvaderProjectile() {

		for (int k = 0; k < invaders.size(); k++) {
			Invader i = invaders.get(k);
			int random = (int) (Math.random() * 200);
			if (random == 1) {
				Projectile x = new Projectile(i.getX(), i.getY(), 15, 15, 7, 1);
				invaderProjectile.add(x);
			}
		}
	}

	public void drawInvaderProjectile() {
		if (invaderProjectile.size() > 0) {

			for (int k = 0; k < invaderProjectile.size(); k++) {
				Projectile x = invaderProjectile.get(k);
				fill(255, 192, 203);
				rect((float) (x.getX()), (float) (x.getY()), (float) (x.getWidth()), (float) (x.getHeight()));
			}
		}
	}

	public void updateInvaderProjectile() {

		for (int i = 0; i < invaderProjectile.size(); i++) {
			invaderProjectile.get(i).setY(invaderProjectile.get(i).getY() + 10);
			if (invaderProjectile.get(i).getY() > 750) {
				invaderProjectile.remove(invaderProjectile.get(i));
			}
		}

	}

	public void drawInvaders() {

		for (int k = 0; k < invaders.size(); k++) {
			Invader i = invaders.get(k);
			fill(173, 216, 230);
			ellipse((float) (i.getX()), (float) (i.getY()), (float) (i.getWidth()), (float) (i.getHeight()));
		}
	}

	public void updateInvaders() {
		int speed = 5;

		if (invaders.size() == 0) {
			gameOver = true;
		}

		for (int k = 0; k < invaders.size(); k++) {
			Invader anInvader = invaders.get(k);
			if (invaders.get(0).getX() <= 50) {
				lefttoright = 1;
				invaders.get(0).setX(invaders.get(0).getX() + (speed * 2));
			}
			if (invaders.get(invaders.size() - 1).getX() >= 1300) {
				lefttoright = 0;
			}

			if (lefttoright == 0) {
				anInvader.setX(anInvader.getX() - speed);

			}

			if (lefttoright == 1) {
				anInvader.setX(anInvader.getX() + speed);
			}

		}
	}

	@Override
	public void keyPressed() {
		if (keyPressed && key == CODED) {
			if (p.getX() > 50 && keyCode == LEFT) {
				p.setX(p.getX() - 50);
			}
			if (p.getX() < 1200 && keyCode == RIGHT) {
				p.setX(p.getX() + 50);
			}
			if (keyCode == UP) {
				playerProjectile.add(p.shootProjectile());
			} else {
				return;
			}
		}

	}

	public void updateCollisions() {
		ArrayList<Invader> invadersToRemove = new ArrayList<>();
		ArrayList<Projectile> projectilesToRemove = new ArrayList<>();

		for (Invader invader : invaders) {
			for (Projectile projectile : playerProjectile) {
				if (invader.colliding(invader, projectile)) {
					projectilesToRemove.add(projectile);
					invadersToRemove.add(invader);
				}
			}
		}

		invaders.removeAll(invadersToRemove);
		playerProjectile.removeAll(projectilesToRemove);

		if (invaders.size() == 0) {
			gameOver = true;
		}

		for (Projectile invaderProjectile : invaderProjectile) {
			if (p.colliding(p, invaderProjectile)) {
				gameOver = true;
				break; // No need to continue checking once the game is over
			}
		}
	}
}
