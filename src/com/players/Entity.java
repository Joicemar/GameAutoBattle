package com.players;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.core.Sprite;


public abstract class Entity {
	
	public int posX, posY, width, height, velocity, scale, 
	life, maxLife, powerAttack, defense, criticalRate, criticalPower, honorPoints, level;
	
	public static boolean up, down;

	public boolean right;

	public boolean left;

	public boolean attack;

	public static boolean running;

	public static boolean animation;
	
	public String ID;
	
	
//	public abstract void update();
//	public abstract void render(Graphics g);
	
	public void updatAttack(int value) {
		powerAttack+=value;
	}
	public void updatDef(int value) {
		defense+=value;
	}
	public void updatLife(int value) {
		life+= value;
	}
	public void updatCritical(int value) {
		criticalPower+=value;
	}
	public void updatSorte(int value) {
		criticalRate+=value;
	}
	
	public void moveX( int value ) {
		posX += value;
	}
	
	public void moveY( int value ) {
		posY += value;
	}
	
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public boolean collisionCheck(Entity e1, Entity e2) {
		Rectangle e1Mask = new Rectangle(e1.getPosX(), e1.getPosY(), e1.width, e1.height);
		Rectangle e2Mask = new Rectangle(e2.getPosX(), e2.getPosY(), e2.width, e2.height);

		return e1Mask.intersects(e2Mask);
	}

	public boolean collisionCheck(Sprite e1, Entity e2) {
		Rectangle e1Mask = new Rectangle(e1.getPosX(), e1.getPosY(), e1.width, e1.height);
		Rectangle e2Mask = new Rectangle(e2.getPosX(), e2.getPosY(), e2.width, e2.height);
		
		return e1Mask.intersects(e2Mask);
	}
	
	public void addDamage( int damage ) {
		this.life -= damage;
	}
	
	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getVelocity() {
		return velocity;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getMaxLife() {
		return maxLife;
	}

	public void setMaxLife(int maxLife) {
		this.maxLife = maxLife;
	}

	public int getPowerAtack() {
		return powerAttack;
	}

	public void setPowerAtack(int powerAtack) {
		this.powerAttack = powerAtack;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getCriticalRate() {
		return criticalRate;
	}

	public void setCriticalRate(int criticalRate) {
		this.criticalRate = criticalRate;
	}

	public int getCriticalPower() {
		return criticalPower;
	}

	public void setCriticalPower(int criticalPower) {
		this.criticalPower = criticalPower;
	}

	public int getHonorPoints() {
		return honorPoints;
	}

	public void setHonorPoints(int honorPoints) {
		this.honorPoints = honorPoints;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public static boolean isUp() {
		return up;
	}

	public static void setUp(boolean up) {
		up = up;
	}

	public static boolean isDown() {
		return down;
	}

	public static void setDown(boolean down) {
		down = down;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isAttack() {
		return attack;
	}

	public void setAttack(boolean attack) {
		this.attack = attack;
	}

	public static boolean isRunning() {
		return running;
	}

	public static void setRunning(boolean running) {
		running = running;
	}

	public static boolean isAnimation() {
		return animation;
	}

	public static void setAnimation(boolean animation) {
		animation = animation;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}
	
}


	