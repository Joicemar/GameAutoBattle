package com.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.players.Player;
import com.text.TextFont;

public class MenuManager {

	TextFont font = new TextFont();
	TextFont text = new TextFont();
	Player player;
	BufferedImage fundoMenu, caixaAtributos, caixaInimigos, maoIco;
	BufferedImage[] gui = new BufferedImage[3];

	ImageLoader loader;
	Input input;
	TimerClock timer;
	int largura = Game.width * Game.scale;
	int altura = Game.height * Game.scale;

	AudioPlayer clickSom;

	public MenuManager(Player player) {
		this.player = player;

		clickSom = new AudioPlayer();
		clickSom.setFile(".//audio//menuSom.wav");
		clickSom.setVolume(0.8F);
		
		font = new TextFont();
//		font.readFontPath("groovy.ttf");
		font.setFontSize(30);
		font.readFontPath("Garamond.ttf");
		font.setColor(Color.white);
		
		text = new TextFont();
		text.setFontSize(26);
		text.readFontPath("Garamond.ttf");
		text.setColor(new Color(200,200,200));

		loader = new ImageLoader();
		loader.loadImage("tabelaGrande", 0, 0, largura, altura);
		fundoMenu = loader.getImage();

		loader.loadImage("tabelaVertical", 0, 0, largura, altura);
		caixaAtributos = loader.getImage();

		loader.loadImage("tabelaMenor", 0, 0, largura, altura);
		caixaInimigos = loader.getImage();

		loader.loadImage("mao", 0, 0, largura, altura);
		maoIco = loader.crop(0, 0, 48, 48);

		timer = new TimerClock();
		var l = largura - 16; // 984
		var a = altura - 41; // 529
		System.out.println(l + " / " + a);

	}

	public void playerHouse() {

	}

	int options = 1, frames = 0, maxFrames = 6;
	boolean limitador = false;
	int timeForClick = 0;
	int maxTimeForClick = 15;
	boolean limitadorClick = true;

	int inimigosQuantidade = 1;
	int Inimigoslevel = 1;
	int icoPosX = 280;
	int icoPosY = 62;

	private boolean menuBox1 = true;

	public void update() {
		font.setFontSize(1);
		frames++;
		if (frames >= maxFrames) {
			limitador = false;
		}
		if (!limitadorClick) {
			timeForClick++;
			if (timeForClick > 25) {
				limitadorClick = true;
				timeForClick = 0;
			}
		}

		if (!limitador) {

			/* Navegar pelo menu */
			if (menuBox1) {
				if (input.S || input.DOWN) {
					clickSom.play();
					options++;
					limitador = true;
					if (options > 5) {
						options = 1;
					}
					frames = 0;
				}
				if (input.W || input.UP) {
					clickSom.play();
					options--;
					limitador = true;
					if (options < 1) {
						options = 5;
					}
					frames = 0;
				}
				if (input.D || input.RIGHT) {
					clickSom.play();
					icoPosX = 758;
					options = 1;
					menuBox1 = false;
//				Game.numEvent=0;
				}
			}
			/* No Menu 2, box 2 */
			if (!menuBox1) {
				if (input.A || input.LEFT) {
					clickSom.play();
					icoPosX = 280;
					menuBox1 = true;
//					Game.numEvent=0;
				}
				if (input.S || input.DOWN) {
					clickSom.play();
					options++;
					limitador = true;
					if (options > 2) {
						options = 1;
					}
					frames = 0;
				}
				if (input.W || input.UP) {
					clickSom.play();
					options--;
					limitador = true;
					if (options < 1) {
						options = 2;
					}
					frames = 0;
				}
				if (input.ENTER || input.MousePressed) {
					clickSom.play();
					switch (options) {
					case 1:
						if(Inimigoslevel < player.getLevel()) {
							Inimigoslevel++;
						}
						if(Inimigoslevel >= player.getLevel()) {
							Inimigoslevel = 1;
						}
						break;
					case 2:
						Game.numEvent=0;
						break;
					}
				}
			}

			// parte do código que distribui pontos ao personagem
			if (input.ENTER || input.MousePressed) {
				clickSom.play();
				if (player.pontos > 0 && limitadorClick) {
					switch (options) {
					case 1:
						player.updatAttack(1);
						player.pontos--;
						limitadorClick = false;
						break;
					case 2:
						player.updatDef(1);
						player.pontos--;
						limitadorClick = false;
						break;
					case 3:
						player.maxLife++;
						player.pontos--;
						limitadorClick = false;
						break;
					case 4:
						player.updatCritical(1);
						player.pontos--;
						limitadorClick = false;
						break;
					case 5:
						player.updatSorte(1);
						player.pontos--;
						limitadorClick = false;
						break;
					}
				}
			}
			if(Input.L) {
				clickSom.play();
				if(MenuBackground.music.isRunning()){
					MenuBackground.music.stop();
				}else {
					MenuBackground.music.play();
				}
			}
			/*Mudando a position do icone com calculos*/
			switch (options) {
			case 1:
				icoPosY = posY * 3;
				break;
			case 2:
				icoPosY = posY * 4;
				break;
			case 3:
				icoPosY = posY * 5;
				break;
			case 4:
				icoPosY = posY * 6;
				break;
			case 5:
				icoPosY = posY * 7;
				break;
			}
			limitador = true;
		}

	}

	int posX = 30;
	int posY = 60;
	int width = 550;
	int height = 120;
	PointBase point = new PointBase();

	public void render(Graphics g) {

		g.drawImage(fundoMenu, 0, 1, 984, 529, null);
		g.drawImage(caixaAtributos, 25, 7, 450, 500, null);
		g.drawImage(caixaInimigos, 480, 80, 450, 200, null);
		g.drawImage(caixaInimigos, 480, 300, 450, 200, null);
		g.drawImage(maoIco, icoPosX, icoPosY, 50, 50, null);
//		g.setColor(Color.black);
		font.setText("Level 	  ( " + player.getLevel() + " )", posX * 2, posY - 5);
		font.render(g);
		font.setText("Experiência ( " + player.experience + " / " + player.nextExperienceToUp + " )", 514, posY - 5);
		font.render(g);

		font.setText("Pontos de habilidades ( " + player.getPontos() + " )", posX * 2, posY * 2);
		font.render(g);
		font.setText("Ataque       ( " + player.getPowerAtack() + " )", posX * 2, posY * 3);
		font.render(g);
		font.setText("Defesa       ( " + player.getDefense() + " )", posX * 2, posY * 4);
		font.render(g);
		font.setText("Vida         ( " + player.getMaxLife() + " )", posX * 2, posY * 5);
		font.render(g);
		font.setText("Dano Crítico ( " + player.getCriticalPower() + " )", posX * 2, posY * 6);
		font.render(g);
		font.setText("Sorte        ( " + player.getCriticalRate() + " )", posX * 2, posY * 7);
		font.render(g);

		// Enemies
//		font.setText("Quantidade de Inimigos ( " + inimigosQuantidade + " )", 514, posY * 2);
//		font.render(g);
		font.setText("Level dos inimigos ( " + Inimigoslevel + " )", 514, posY * 3);
		font.render(g);
		font.setText("Lutar ", 594, posY * 4);
		font.render(g);
		
		text.setText("Use as teclas W, A, S, D para mover", 514, posY * 6);
		text.render(g);
		text.setText("Aperte ENTER para Escolher ", 514, posY * 7);
		text.render(g);
		text.setText(" L para pausar ou Tocar a Música ", 514, (posY * 8) - 5);
		text.render(g);

	}

}
