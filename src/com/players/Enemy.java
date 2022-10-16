package com.players;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.core.AudioPlayer;
import com.core.Game;
import com.core.ImageLoader;
import com.core.Sprite;
import com.core.TimerClock;

public class Enemy extends Entity {
	
	public static ImageLoader loadLeft, loadRight, loadRunRight, loadRunLeft, loadAttackRight, loadAttackLeft;

	public BufferedImage jogador, jogadorFlip;
	public Sprite RunRight, RunLeft, IdleRight, IdleLeft, AttackRight,
			AttackLeft;
	private boolean theMove = false;
	private int velocidade = 1;
	private int direction = 0;
	public String name;

	private AudioPlayer audioAtack;
	
	private Random random;
	private TimerClock timer;

	Enemy (String name, int maxLife, int powerAttack, int defense, int level) {
		this.name = name;
		this.maxLife = maxLife;
		this.powerAttack = powerAttack;
		this.defense = defense;
		this.level = level;
		
	}
	
	public Enemy() {

		this.right = true;
		this.left = false;
		
		life = 3;
		maxLife = 3;
		powerAttack = 1;
		defense = 0;
		level = 1;
		audioAtack = new AudioPlayer();
		audioAtack.setFile(audioAtack.attackSong2);
		
		random = new Random();
		timer = new TimerClock();

		loadLeft = new ImageLoader();
		loadLeft.loadImage("enemy01Invert");
		IdleLeft = new Sprite();
		IdleLeft.addSprite(loadLeft.crop(66, 6, 16, 13));
		IdleLeft.addSprite(loadLeft.crop(83, 6, 16, 13));
		IdleLeft.addSprite(loadLeft.crop(100, 6, 16, 13));
		IdleLeft.addSprite(loadLeft.crop(117, 6, 16, 13));

		loadRight = new ImageLoader();
		loadRight.loadImage("enemy01");
		IdleRight = new Sprite();
		IdleRight.addSprite(loadRight.crop(0, 6, 17, 13));
		IdleRight.addSprite(loadRight.crop(17, 6, 17, 13));
		IdleRight.addSprite(loadRight.crop(17 * 2, 6, 17, 13));
		IdleRight.addSprite(loadRight.crop(17 * 3, 6, 17, 13));

		scale = 3;
		posX = 500;
		posY = Game.height * Game.scale / 2;
		width = 10 * scale;
		height = 10 * scale;
		running = true;
		this.animation = true;
		this.attack = false;

	}
	
	public void play() {
		audioAtack.play();
	}
	public void stop() {
		audioAtack.stop();
	}

	boolean local = false;
	public boolean posVerification() {
		// checking position
		if( this.getPosX() >= Game.widht*Game.scale -10 || this.getPosX() <= 10 || this.getPosY() >= Game.height*Game.scale ||
				this.getPosY() <= 10 ) {
			local = true;
		}
		return local;
	}
	
	int maxtime=4;
	int count=0;
	public void update() {
//		posVerification();
	}

	int porcentagem, porcentagemShown;
	int greenLifeSize = 100;
	public void render(Graphics g) {

		g.drawImage(IdleLeft.animeSprite(true), posX, posY, width*scale, height*scale, null);
		
		porcentagem = (this.getLife()*100) / this.getMaxLife();
		porcentagemShown = (greenLifeSize / 100) * porcentagem;
		
		g.setColor(Color.RED);
		g.fillRect( this.getPosX(), this.getPosY()-5, greenLifeSize, 7);
		
		g.setColor(Color.GREEN);
		g.fillRect( this.getPosX(), this.getPosY()-5, porcentagemShown, 7);
		
		if (!this.attack) {
			
		}

	}
	
	public void updateStatus( int maxLife, int powerAttack, int defense) {
		this.maxLife += maxLife;
		this.powerAttack += powerAttack;
		this.defense += defense;
	}


}
