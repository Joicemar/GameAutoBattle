package com.players;

import java.awt.Graphics;
import java.util.LinkedList;

public class Manipulador {
	
	LinkedList<Entity> object = new LinkedList<>();
	private boolean right = false, left = false, up = false, down = false;
	
	public void tick() {

		for (int i = 0; i < object.size(); i++) {
			Entity tempObject = object.get(i);

//			tempObject.update();
		}
	}

	public void render(Graphics g) {

		for (int i = 0; i < object.size(); i++) {
			Entity tempObject = object.get(i);

//			tempObject.render(g);
		}
	}
	
	public void addObject (Entity tempObject) {
		object.add(tempObject);
	}
	
	public void removeObject(Entity tempObject) {
		object.remove(tempObject);
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

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}
}



