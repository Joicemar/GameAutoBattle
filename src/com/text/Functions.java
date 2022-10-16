package com.text;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Functions {

	int x, y, life, maxLife ;
	
	public String[] options = { "Novo Jogo", "Carregar Jogo", "Sair" };
	public String text;
	
	public Functions( String texto, int x, int y) {
		text = texto;
		this.x = x;
		this.y = y;
	}
	public Functions() {
	}

	public void setText(String txt) {
		this.text = txt;
	}
	public String getText() {
		return text;
	}
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(0, 0, 0, 100));
		g2.fillRect(0, 0, x, x);
		
		g.setColor(Color.RED);
		g.setFont(new Font("arial", 0, 56));
		
		g.drawString("10.000 A.C", x / 2 - 140, 70);
		
		g.setFont(new Font("arial", 1, 36));
		g.setColor(Color.white);
		g.setColor(Color.RED);
		g.fillRect(8, 5, 100, 10);
		
		g.setColor(Color.GREEN);
		g.fillRect(8, 5, life * 100 / maxLife, 10);
		g.setColor(Color.white);
		g.setFont(new Font("arial", 1, 10));
		g.drawString(life + " / " + maxLife, 37, 14);
	}
}





















