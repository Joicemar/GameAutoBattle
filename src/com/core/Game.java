package com.core;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.players.Enemy;
import com.players.Entity;
import com.players.Player;
import com.text.Ui;

public class Game {

	ImageLoader load;
	public Player player;
	public static List<Enemy> enemies;

	public Ui ui;
	AudioPlayer audio;
	AudioPlayer audioAtack;

	private TimerClock timer;
	public static int velocidade = 2;
	public static int widht = 200;
	public static int height = 114;
	public static int scale = 5;

	public boolean timeDamage = true;
	public Enemy enemy;
	private Menu menu = new Menu();
	private MenuManager manager;

	public static void main(String[] args) {

		Engine engine = new Engine("Game 01", widht * scale, height * scale);
		engine.start();
	}

	Enemy e1;
	Enemy e2;
	Enemy e3;

	int bloco1 = 150;
	int bloco2 = 250;
	int bloco3 = 350;

	public Game() {

		player = new Player();
		novaBatalha();

		ui = new Ui(player);
		manager = new MenuManager(player);
		timer = new TimerClock();

		audio = new AudioPlayer();
		audio.setFile(audio.attackSong);
		audio.setVolume(0.1f);

	}

	int bonusXp = 0;
	int enemyLevel = 1;

	public void novaBatalha() {
		enemy = new Enemy();
		e1 = new Enemy();
		e1.setPosY(bloco1);
		e1.setPosX(widht * scale);
		e1.setID("01");
		e2 = new Enemy();
		e2.setPosY(bloco2);
		e2.setPosX(widht * scale);
		e2.setID("02");
		e3 = new Enemy();
		e3.setPosY(bloco3);
		e3.setPosX(widht * scale);
		e3.setID("03");

		enemies = new ArrayList<Enemy>();
		enemies.add(e2);
		enemies.add(e1);
		enemies.add(e3);
		if (player.getLife() != player.getMaxLife()) {
			player.life = player.getMaxLife();
		}
		bonusXp = enemies.size() * enemies.get(0).level;
		player.setPosY(bloco1 + 80);
		player.setPosX(-133);
	}

	private void entradaBatalha() {
		player.running = true;
		player.right = true;
		player.posX += 2;
		for (Enemy en : enemies) {
			Enemy localEnemy = en;
			localEnemy.posX -= 2;
		}
	}

	private boolean tempoDosTurnos() {
		timer.contarSegundos(1);
		if (timer.getSegundos() == 1) {
			return true;
		}
		return false;
	}

	private void verificaEstados() {
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).getLife() <= 0) {
				System.out.println( "life"+enemies.get(i).getLife());
				timer.contarSegundos(1);
				if (timer.getSegundos() == 1) {
					enemies.remove(enemies.get(i));
				}
			}
		}
	}

	private void buscarInimigo() {
		if (player.getPosY() > enemies.get(0).getPosY() - 27) {
			player.moveY(-player.velocidade);
			player.running = true;
		}
		if (player.getPosY() < enemies.get(0).getPosY() - 29) {

			player.moveY(+player.velocidade);
			player.running = true;
		} else
			player.running = false;
		/*
		 * foi necessário diferenciar em 2 pixels nas verificações para que não valide
		 * os 2 'if's'.
		 */
	}

	int numEnemies = 0;
	public static int numEvent = 0;

	public void update() {

		if (enemies.size() > 0) {
			buscarInimigo();
		}
		switch (numEvent) {
		case 0: // Start turns and movement for battle
			if (enemies.size() <= 0) {
				novaBatalha();
			}
			if (player.getPosX() < 370 && enemies.get(0).getPosX() > 480) {
				entradaBatalha();
			} else
				numEvent = 1;
			break;
		case 1: // Player turn
			player.running = false;
			if (enemies.size() > 0) {
				if (tempoDosTurnos()) {
					player.atacar();
					enemies.get(0).addDamage(player.powerAttack);
					numEvent = 2;
					timer.setSegundos(0);
				}
			}
			break;
		case 2: // Interval turn
			verificaEstados();
			System.out.println("interval");
			timer.contarSegundos(2);
			if (timer.getSegundos() >= 1) {
				timer.setSegundos(0);
				numEvent = 3;
			}
			if(timer.getSegundos() > 2) {
			}

			break;
		case 3: // Enemy turn
			if (enemies.size() > 0) {
				if (numEnemies < enemies.size()) {
					timer.contarSegundos(2);
					if (timer.getSegundos() >= 2) {
						enemies.get(numEnemies).play();
						player.addDamage(enemies.get(numEnemies).powerAttack);
						numEnemies++;
						timer.setSegundos(0);
					}
				} else {
					numEnemies=0;
					numEvent = 1;
				}
			} else {
//				System.out.println("Player ex: " + player.experience);
//				System.out.println("Bonus XP: " + bonusXp + 5);
				player.experience += bonusXp + 5;
				if (player.experience >= player.nextExperienceToUp) {
					if (player.experience > player.nextExperienceToUp) {
						int resto = player.experience - player.nextExperienceToUp;
						System.out.println("resto: "+resto);
						player.experience = 0;
						player.experience += resto;
						player.level += 1;
						player.pontos += 3;
						player.nextExperienceToUp = player.nextExperienceToUp + (player.nextExperienceToUp / 10) + player.getLevel();
					} else {
						System.out.println("resto de experiencia : " + (player.experience - player.nextExperienceToUp));
						player.level += 1;
						bonusXp = 0;
					}
				}
				numEvent = 5;
			}
			break;
		case 4:
			menu.update();
			break;
		case 5:
			manager.update();
			break;
		case 6:
			menu.update();
			break;

		}

	}

	public void render(Graphics g) {

		if (numEvent != 4) {
			for (int i = 0; i < enemies.size(); i++) {
				enemies.get(i).render(g);
				if (player.attack) {
					ui.drawString(g, "" + player.powerAttack, enemies.get(0).getPosX() + 30,
							enemies.get(0).getPosY() + 20, 33, 33);
				}
			}

			player.render(g);
			ui.render(g);
		}
		if (numEvent == 4) {
			menu.render(g);
		}
		if (numEvent == 5) {
			manager.render(g);
		} else {
		}

	}

}