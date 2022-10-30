package com.text;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;

public class TextFont {

	private InputStream stream;
	public Font font;
	private Color color = null;
	public String text = null;
	public int posX, posY;
	private float fontSize = 48f;

	private String folder = System.getProperty("user.dir");
	private String akuilah = "fontes/aAkuilah.ttf";

	public String retro = folder + "retro.ttf";
	public  String yeOldeOak ="yeOldeOak.ttf";
	public String gatel = "gatel.TTF";
	public String gabriele = "gabriele.ttf";
	public String gabrielebr = "gabrielebr.TTF";
	public String zabdilus = "zabdilus.fft";

	public TextFont() {

	}
	
	public TextFont( String path, int fontSize) {
		this.fontSize = fontSize;
		readFontPath(path);
	}
	

	public void readFontPath(String path) {
		try {
			Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("fontes/"+path)).deriveFont(fontSize);
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    //register the font
		    ge.registerFont(customFont);
		    font = customFont;
		} catch (IOException | FontFormatException e) {
			System.out.println("Arquivo ttf não encontrado ou inválido.");
			e.printStackTrace();
		}
	}

	public void setText(String txt, int posX, int posY) {
		this.text = txt;
		this.posX = posX;
		this.posY = posY;
	}

	public Font getFont() {
		return font;
	}
	
	public void setFontSize(float size) {
		fontSize=size;
	}

	public void render(Graphics g) {
		g.setFont(font);
		if (this.color == null) {
			g.setColor(Color.black);
		} else {
			g.setColor(color);
		}
		g.drawString(text, posX, posY);
	}
	public void render(Graphics g, String txt) {
		if (this.color == null) {
			g.setColor(Color.black);
		} else {
			g.setColor(color);
		}
		g.setFont(font);
		g.drawString(text, posX, posY);
	}

	public void setColor(Color color) {
		this.color = color;
	}
	public void setColor(int red, int green, int blue) {
		this.color = new Color( red , green , blue );
	}


	// deriveFont = Creates a new Font object by replicating the current Font object
	// and applying a new size to it.
	//
//	public String gabriola = "gabriola";
//	public String consolas = "consolas";
//	public String candara = "candara";
//	public String comic = "Comic Sans MS";
//	public String costantia = "costantia";
//	public String courier = "courier new";
//	public String fixedsys = "fixedsys";
//	public String inkfree = "ink free";
}
