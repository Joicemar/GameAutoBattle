package com.core;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageLoader {
	
	private static BufferedImage image;
	private BufferedImage imageFlip;
	
	public int posX, posY, width, height;
	
	public BufferedImage loadImage(String path) {
		
		try {
			
			String folder = System.getProperty("user.dir");

			if (new File(folder + "\\imagens\\" + path + ".png").exists()) {

				image = ImageIO.read((new File(folder + "\\imagens\\" + path + ".png")));
			} else {
				image = ImageIO.read((new File(folder + "\\imagens\\" + path)));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		imageFlip = image;
		return image;
	}
	
	public BufferedImage loadImage(String path, int posX, int posY, int width, int height) {
		
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		
		try {
			
			String folder = System.getProperty("user.dir");
			
			if (new File(folder + "\\imagens\\" + path + ".png").exists()) {
				
				image = ImageIO.read((new File(folder + "\\imagens\\" + path + ".png")));
			} else {
				image = ImageIO.read((new File(folder + "\\imagens\\" + path)));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		imageFlip = image;
		return image;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public BufferedImage flipImage (BufferedImage img ) {
		
		AffineTransform tx  = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-img.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		imageFlip = op.filter((BufferedImage) img, null);
		return imageFlip;
		
	}
	public void flipImage ( ) {
		
		AffineTransform tx  = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-image.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		imageFlip = op.filter((BufferedImage) image, null);
		this.imageFlip =  image;
		
	}

	public static BufferedImage crop(int x, int y, int width, int height) {
		return image.getSubimage(x, y, width, height);
	
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

}
