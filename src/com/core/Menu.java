package com.core;

import java.awt.Color;
import java.awt.Graphics;

import com.players.Player;
import com.text.TextFont;
import java.awt.image.BufferedImage;

import javax.sound.sampled.Clip;

//https://encycolorpedia.com/
public class Menu {

	TextFont font = new TextFont();
	Player player = new Player();
	BufferedImage fundoMenu, guiLuz, guiNormal;
	BufferedImage[] gui = new BufferedImage[3];

	ImageLoader loader;
	Input input;
	TimerClock timer;
	
	AudioPlayer music;
	MenuManager manager;

	public Menu() {
		font = new TextFont();
//		font.readFontPath("groovy.ttf");
		font.readFontPath("Garamond.ttf");
		font.setColor(Color.white);
		loader = new ImageLoader();
		loader.loadImage("ui/papelVelho", 0, 0, Game.widht * Game.scale, Game.height * Game.scale);
		fundoMenu = loader.getImage();
		timer = new TimerClock();

		loader.loadImage("gui01+");
		gui[0] = loader.crop(10, 0, 350, 130);
		gui[1] = loader.crop(10, 0, 350, 130);
		gui[2] = loader.crop(10, 0, 350, 130);
		guiNormal = loader.crop(10, 0, 350, 130);
		guiLuz = loader.crop(380, 0, 350, 130);
		
		music = new AudioPlayer();
		music.setFile(AudioPlayer.battleMusic);
		music.setVolume(0.2F);
		
		manager = new MenuManager(player);
	}

	public void playerHouse () {
		
	}

	int options = 1, frames = 0, maxFrames = 5;
	boolean limitador = false;

	public void update() {
		if(! music.isRunning()) {
			music.play();
		}
		frames++;
		if (frames >= maxFrames) {
			limitador = false;
		}
		if(!limitador) {
			
			if (input.S || input.DOWN) {
				options++;
				if (options > 3) {
					options = 1;
				}
			}
			if (input.W || input.UP) {
				options--;
				if (options < 1) {
					options = 3;
				}
			}
			if(input.ENTER) {
				switch(options) {
				case 1: Game.numEvent = 0;
				break;
				case 2:
					Game.numEvent = 5;
					break;
				case 3:
					System.exit(1);
				}
			}
			switch (options) {
			case 1:
				gui[0] = guiLuz;
				gui[1] = guiNormal;
				gui[2] = guiNormal;
				limitador = false;
				frames = 0;
				break;
			case 2:
				gui[0] = guiNormal;
				gui[1] = guiLuz;
				gui[2] = guiNormal;
				limitador = false;
				frames = 0;
				break;
			case 3:
				gui[0] = guiNormal;
				gui[1] = guiNormal;
				gui[2] = guiLuz;
				limitador = false;
				frames = 0;
	
			}
			limitador = true;
		}
		
	}

	int posX = 220;
	int posY = 50;
	int width = 550;
	int height = 120;

	public void render(Graphics g) {

		g.drawImage(fundoMenu, -12, -10, Game.widht * Game.scale + 10, Game.height * Game.scale - 22, null);
		g.drawImage(gui[0], posX, posY, width, height, null);
		g.drawImage(gui[1], posX, posY * 4, width, height, null);
		g.drawImage(gui[2], posX, posY * 7, width, height, null);

//		g.setColor(Color.black);
		font.setText("Novo Jogo", posX + 163, posY + 80);
		font.render(g);
		font.setText("Continue", posX + 175, posY * 4 + 80);
		font.render(g);
		font.setText("sair", posX + 240, posY * 7 + 80);
		font.render(g);
	}

}
