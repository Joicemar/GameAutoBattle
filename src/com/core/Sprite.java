package com.core;

import java.util.ArrayList;
import java.awt.image.BufferedImage;

public class Sprite {

	private ArrayList<BufferedImage> sprites = new ArrayList<>();


	private int frames = 0, maxFrames = 8, index = 0;
	public int posX, posY, width, height;

	public Sprite() {

	}

	public void addSprite(BufferedImage img) {

		sprites.add(img);

	}

	public BufferedImage animeSprite( boolean isTrue ) {

		if (isTrue) {
			frames++;
			if (frames >= maxFrames) {
				frames = 0;
				index++;
				if (index == sprites.size()) {
					index = 0;
				}
			}
			return (sprites.get(index));
		} else {
			return sprites.get(0);

		}
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
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

	public int getFrames() {
		return frames;
	}

	public void setFrames(int frames) {
		this.frames = frames;
	}

	public int getMaxFrames() {
		return maxFrames;
	}

	public void setMaxFrames(int maxFrames) {
		this.maxFrames = maxFrames;
	}

	public ArrayList<BufferedImage> getSprites() {
		return sprites;
	}
	
	public void setSprites(ArrayList<BufferedImage> sprites) {
		this.sprites = sprites;
	}
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
}
