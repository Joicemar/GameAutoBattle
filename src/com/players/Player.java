package com.players;

import static com.core.AudioPlayer.attackSong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.core.AudioPlayer;
import com.core.Game;
import com.core.ImageLoader;
import com.core.Input;
import com.core.Sprite;
import com.core.TimerClock;
import com.text.Ui;

public final class Player extends Entity {

	public static ImageLoader loadLeft, loadRight, loadRunRight, loadRunLeft, loadAttackRight, loadAttackLeft;

	public BufferedImage jogador, jogadorFlip;
	public Sprite jogadorRunRight, jogadorRunLeft, jogadorIdleRight, jogadorIdleLeft, jogadorAttackRight,
			jogadorAttackLeft;

	public int velocidade = 3;
	public long totalExperience;
	public int pontos, ecoins, experience;
	private AudioPlayer audioAtack;
	AudioPlayer damageCorte;
	
	public static int expToUp = 10;
	public static int nextExperienceToUp = expToUp + expToUp * 10 / 100;
	
	Game game;
	Enemy enemy;
	Ui ui;

	private boolean validDamage = false, areaDeAttack = false;

	public Player( ) {

		right = true;
		left = false;

		maxLife = 10;
		this.life = maxLife;
		powerAttack = 2;
		defense = 0;
		level = 1;
		pontos = 0;
		experience = 0;
		totalExperience = 0;

		scale = 5;
		posX = 100;
		posY = Game.height * Game.scale / 2;
		width = 17 * scale;
		height = 22 * scale;
		this.animation = true;
		this.attack = false;
		this.running = false;

		ui = new Ui(this);
		audioAtack = new AudioPlayer();
		audioAtack.setFile(attackSong);

		damageCorte = new AudioPlayer();
		damageCorte.setFile(AudioPlayer.audioDamage);
		damageCorte.setVolume(0.04f);

		loadLeft = new ImageLoader();
		loadLeft.loadImage("playerSpritesInvert");
		jogadorIdleLeft = new Sprite();
		jogadorIdleLeft.addSprite(loadLeft.crop(31, 0, 17, 22));
		jogadorIdleLeft.addSprite(loadLeft.crop(48, 0, 17, 22));
		jogadorIdleLeft.addSprite(loadLeft.crop(48 + 17, 0, 17, 22));
		jogadorIdleLeft.addSprite(loadLeft.crop(48 + 17 * 2, 0, 17, 22));
		jogadorIdleLeft.addSprite(loadLeft.crop(48 + 17 * 3, 0, 17, 22));
		jogadorIdleLeft.addSprite(loadLeft.crop(48 + 17 * 4, 0, 17, 22));

		loadRight = new ImageLoader();
		loadRight.loadImage("playerSprites");
		jogadorIdleRight = new Sprite();
		jogadorIdleRight.addSprite(loadRight.crop(0, 0, 17, 22));
		jogadorIdleRight.addSprite(loadRight.crop(17, 0, 17, 22));
		jogadorIdleRight.addSprite(loadRight.crop(17 * 2, 0, 17, 22));
		jogadorIdleRight.addSprite(loadRight.crop(17 * 3, 0, 17, 22));
		jogadorIdleRight.addSprite(loadRight.crop(17 * 4, 0, 17, 22));
		jogadorIdleRight.addSprite(loadRight.crop(17 * 5, 0, 17, 22));

		loadRunRight = new ImageLoader();
		loadRunRight.loadImage("playerSprites");
		jogadorRunRight = new Sprite();
		jogadorRunRight.addSprite(loadRight.crop(0, 22, 17, 22));
		jogadorRunRight.addSprite(loadRight.crop(17, 22, 17, 22));
		jogadorRunRight.addSprite(loadRight.crop(17 * 2, 22, 17, 22));
		jogadorRunRight.addSprite(loadRight.crop(17 * 3, 22, 17, 22));
		jogadorRunRight.addSprite(loadRight.crop(17 * 4, 22, 17, 22));
		jogadorRunRight.addSprite(loadRight.crop(17 * 5, 22, 17, 22));

		loadRunLeft = new ImageLoader();
		loadRunLeft.loadImage("playerSpritesInvert");
		jogadorRunLeft = new Sprite();
		jogadorRunLeft.addSprite(loadLeft.crop(48 + 17 * 4, 22, 17, 22));
		jogadorRunLeft.addSprite(loadLeft.crop(48 + 17 * 3, 22, 17, 22));
		jogadorRunLeft.addSprite(loadLeft.crop(48 + 17 * 2, 22, 17, 22));
		jogadorRunLeft.addSprite(loadLeft.crop(48 + 17, 22, 17, 22));
		jogadorRunLeft.addSprite(loadLeft.crop(48, 22, 17, 22));
		jogadorRunLeft.addSprite(loadLeft.crop(31, 22, 17, 22));

		loadAttackRight = new ImageLoader();
		loadAttackRight.loadImage("playerSprites");
		jogadorAttackRight = new Sprite();
		jogadorAttackRight.addSprite(loadAttackRight.crop(0, 47, 34, 24));
		jogadorAttackRight.addSprite(loadAttackRight.crop(34, 47, 35, 24));
		jogadorAttackRight.addSprite(loadAttackRight.crop(69, 47, 34, 24));
		jogadorAttackRight.addSprite(loadAttackRight.crop(99, 47, 33, 24));

		loadAttackLeft = new ImageLoader();
		loadAttackLeft.loadImage("playerSpritesInvert");
		jogadorAttackLeft = new Sprite();
		jogadorAttackLeft.addSprite(loadAttackLeft.crop(99, 47, 33, 24));
		jogadorAttackLeft.addSprite(loadAttackLeft.crop(64, 47, 34, 24));
		jogadorAttackLeft.addSprite(loadAttackLeft.crop(34, 47, 30, 24));
		jogadorAttackLeft.addSprite(loadAttackLeft.crop(0, 47, 34, 24));

	}

	boolean local = false;
	
	public void atacar( ) {
		if ( !attack) {
			this.attack = true;
			audioAtack.play();
//			enemy.addDamage(this.powerAttack);
			damagePoints = true;
			damageCorte.stop();
			damageCorte.play();
		}
	}

	public boolean posWindowVerification() {
//		System.out.println("x: "+this.posX+" / y: "+this.posY);
//		System.out.println("w: "+Game.widht*Game.scale+" / h: "+Game.height*Game.scale);
		if (this.getPosX() <= 10 || this.getPosX() >= 920 || this.getPosY() >= 450 || this.getPosY() <= 10) {
			System.out.println("fora da area");
			local = true;
		} else {
			local = false;
		}
		return local;
	}

	public int TimeAnimPoints = 1;
	int contTimeAnim = 0;
	int contTimeShowPoints = 0;
	boolean damagePoints = false;

	public void update() {
		

//		if (Input.W && Input.D || Input.W && Input.A || Input.A && Input.S || Input.S && Input.D) {
//			velocidade = 2;
//		} else {
//			velocidade = 3;
//		}


	}

	public void render(Graphics g) {

		if (!this.attack) {

			if (right && running) {
				g.drawImage(jogadorRunRight.animeSprite(animation), posX, posY, width, height, null);
			} else if (left && running) {
				g.drawImage(jogadorRunLeft.animeSprite(animation), posX, posY, width, height, null);
			}

			else if (left && !running) {
				g.drawImage(jogadorIdleLeft.animeSprite(animation), posX, posY, width, height, null);
			} else if (right && !running) {
				g.drawImage(jogadorIdleRight.animeSprite(animation), posX, posY, width, height, null);
			}

		}
		if (attack) {

			if (right) {
				left = false;
				g.drawImage(jogadorAttackRight.animeSprite(animation), posX - 15, posY, 34 * scale, 24 * scale, null);

				if (jogadorAttackRight.getIndex() >= jogadorAttackRight.getSprites().size() - 1) {

					if (jogadorAttackRight.getFrames() >= jogadorAttackRight.getMaxFrames() - 1) {
						this.attack = false;
						jogadorAttackRight.setIndex(0);
					}
				}
			} else if (left) {
				right = false;
				g.drawImage(jogadorAttackLeft.animeSprite(animation), posX - 25, posY, 34 * scale, 24 * scale, null);

				if (jogadorAttackLeft.getIndex() >= jogadorAttackLeft.getSprites().size() - 1) {

					if (jogadorAttackLeft.getFrames() >= jogadorAttackLeft.getMaxFrames() - 1) {
						this.attack = false;
						jogadorAttackLeft.setIndex(0);
					}
				}
			}
		}
		
		int porcentagem = (getLife()*100) / getMaxLife();
		int porcentagemShown = (100 / 100) * porcentagem;

		g.setColor(Color.RED);
		g.fillRect( getPosX()-5, getPosY()-5, 100, 7);
		
		g.setColor(Color.GREEN);
		g.fillRect( getPosX()-5, getPosY()-5, porcentagemShown, 7);
		
//		if (AttackCheck()) {
//			ui.drawDamagePoints(g, enemy);
//			contTimeShowPoints++;
//			contTimeAnim++;
//			if (contTimeAnim >= 10) {
//				contTimeAnim = 0;
//				TimeAnimPoints += 2;
//			}
//			if (contTimeShowPoints >= 100) {
//				contTimeShowPoints = 0;
//				TimeAnimPoints = 0;
//				damagePoints = false;
//			}
//		}

	}

	public boolean areaDeAttack() {
		int x1 = this.getPosX();
		int x2 = game.enemy.getPosX();
		int distanceLeftToRight = x2 - x1; // positivo se a direita do inimigo
//		int distanceRightToLeft = x1 - x2; // negativo se a direita do inimigo
//		System.out.println("distanceLeftToRight: "+distanceLeftToRight+" / distanRightToLeft: "+distanceRightToLeft);
		int y1 = this.getPosY();
		int y2 = game.enemy.getPosY();
		int distanceUpToDown = y2 - y1;
		int distanceDownToUp = y1 - y2;

		if (distanceDownToUp <= 22 && distanceUpToDown <= 45) {
			if (distanceLeftToRight <= 80 && distanceLeftToRight > 0
					|| distanceLeftToRight >= -80 && distanceLeftToRight < 0) {
				areaDeAttack = true;
			}
		} else {
			areaDeAttack = false;
		}
		return areaDeAttack;
	}

	TimerClock timer = new TimerClock();

	public boolean AttackCheck() {
		// Most hard part of this project
		// Verifica se a o momento do ataque está enconstando no inimigo. Em determinado tamanho antigo das imagens.
//		for(int i = 0; i < Game.enemies.size(); i ++) {
//			Enemy intanciaInimigo = Game.enemies.get(i);
//		}
		for (Entity en : Game.enemies) {
			enemy = (Enemy) en;

			int x1 = this.getPosX();
			int x2 = enemy.getPosX();
			int distanceLeftToRight = x2 - x1; // positivo se a direita do inimigo
//		int distanceRightToLeft = x1 - x2; // negativo se a direita do inimigo
			int y1 = this.getPosY();
			int y2 = enemy.getPosY();
			int distanceUpToDown = y2 - y1;
			int distanceDownToUp = y1 - y2;
			/*
			 * * use only positive values ​​for calculations and checks, negative ones don't
			 * work in the common subtraction calculation, at least I couldn't here.
			 */
			if (this.attack) {
				if (distanceDownToUp <= 16 && distanceUpToDown <= 63) {
					if (distanceLeftToRight <= 79 && distanceLeftToRight > 0
							|| distanceLeftToRight >= -70 && distanceLeftToRight < 0) {

						if (this.right && this.getPosX() <= enemy.getPosX()) {
							return validDamage = true;
						} else if (this.left && this.getPosX() >= enemy.getPosX()) {
							return validDamage = true;
						}
					} else
						return validDamage = false;
				}
			} else
				return validDamage = false;
		}
		return validDamage;

	}

	public void updateStatus(int maxLife, int powerAttack, int defense, int criticalPower, int criticalRate) {
		this.maxLife += maxLife;
		this.powerAttack += powerAttack;
		this.defense += defense;
		this.criticalPower = criticalPower;
		this.criticalRate = criticalRate;
	}

	public void updateMaxLife(int num) {
		this.maxLife += num;
	}
	public void updatPontos(int value) {
		pontos+=value;
	}

	public long getTotalExperience() {
		return totalExperience;
	}

	public void addTotalExperience(int experience) {
		totalExperience += experience;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	public void addPontos(int pontos) {
		this.pontos += pontos;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public void addExperience(int experience) {
		this.experience += experience;
	}

}
