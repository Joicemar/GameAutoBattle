package com.text;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.core.Game;
import com.players.Enemy;
import com.players.Player;

public class Ui {

	public static Player player;
	Enemy enemy;
	Game game;
	TextFont font;
	public int x, y, width, height;
	
	public Ui() {
		font = new TextFont();
	}
	
	public Ui( Player p) {
		font = new TextFont();
		font.readFontPath("zabdilus.ttf");
//		font.readFontPath("Yaahowu.ttf");
		this.player = p;
	}

	public Ui( int x, int y, int width, int height) {
		this.x = x; 
		this.y = y; 
		this.width = width;
		this.height = height;
		
		font = new TextFont();
		enemy = new Enemy();
		player = new Player( );
	}
	
	int greenLifeSize=300;
	int porcentagem = 100;
	int porcentagemShown;
	
	public void render(Graphics g) {
		
		porcentagem = (player.getLife()*100) / player.getMaxLife();
		porcentagemShown = (greenLifeSize / 100) * porcentagem;

		g.setColor(Color.RED);
		g.fillRect( 40, 20, 300, 40);
		
		g.setColor(Color.GREEN);
		g.fillRect( 40, 20, porcentagemShown, 40);
	
		g.setColor(Color.white);
		g.setFont(font.getFont());//1,30
		g.drawString(player.getLife() + " / " + player.getMaxLife(), 144 - descontoPosLife(), 53);

	}
	
	private int descontoPosLife() {
		//desconto para a posição da vida na tela ficar centralizada quando o HP for alterado
		if(player.getLife() < 100 ) return 5;
		else if(player.getLife() < 1000) return 25;
		else if(player.getLife() < 10000) return 35;
		else if(player.getLife() < 100000) return 55;
		else if(player.getLife() < 1000000) return 70;
		return 1;
	}
	
	public void drawDamagePoints(Graphics g, Enemy en) {
		g.setColor(Color.white);
		g.setFont(new Font("Comic Sans MS", 25, 25));
		g.drawString(""+this.player.getPowerAtack(), en.getPosX()+15, en.getPosY()+20 -(player.TimeAnimPoints));
	}
	public void drawString(Graphics g, String msg, int posX, int posY, int width, int height) {
		g.setColor(Color.white);
		g.setFont(new Font("Comic Sans MS", width, height));
		g.drawString(msg, posX , posY);
	}
	
}
