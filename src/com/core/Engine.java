package com.core;

import java.awt.Canvas;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

//import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * @author Joicemar da Silva Morais
 * 18/09/2022 
 */
public class Engine extends Canvas implements Runnable, KeyListener, MouseListener, 
	MouseMotionListener {

	
	private static final long serialVersionUID = 1L;
	JFrame frame;
	private Thread thread;
	private boolean isRunning;

	private static int width = 800;
	private static int height = 510 ;
	public static int SCALE = 1;
	private static String title = "Game 00";

	private BufferedImage image;
	
	//Image icon = Toolkit.getDefaultToolkit().getImage("images\\icon.png");
	/*Novo Código?*/
	
	public static Game game;
	
	/*Novo Código?*/
	
	/*Construtor sem par�metros*/
	public Engine() {
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		game = new Game();
		
		
		createDisplay();
	}
	/*Contrutor com 3 par�metros*/
	public Engine(String title, int width, int height) {
		Engine.title = title;
		Engine.width = width;
		Engine.height = height;
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		createDisplay();
		game = new Game();
	}
	/*Contrutor com 4 par�metros*/
	public Engine(String title, int width, int height, int scale) {
		Engine.title = title;
		Engine.width = width;
		Engine.height = height;
		Engine.SCALE = scale;
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		game = new Game();
		createDisplay();
	}

	public void tick() {
		/**
		 * @return Usado para atualizar a l�gica matem�tica que esta com esta classe
		 */
		game.update();
	}

	public void render() {

		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.createGraphics();

		g = bs.getDrawGraphics(); // Cria um contexto gr�fico para o desenho buffe
//		//////////////////////////////////////
//		g.setColor(new Color(33, 33, 33));
//		g.setColor(Color.GREEN);
		g.fillRect(0, 0, width*SCALE, height*SCALE);
		
		//New Code here:
//		g.drawImage(img, 112, 112, 144,144, null);
		game.render(g);
		
		bs.show();
		g.dispose();
//		bs.dispose(); // somente se for 2 e de buffer
	}
	/**Responsavel por criar a janela JFrame e suas configura��es**/
	public void createDisplay() {
		
		frame = new JFrame();
		addKeyListener(this);
		addMouseListener(this);
		frame.add(this);
		frame.setSize(width , height );
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setTitle(title);
//		frame.setIconImage(new ImageIcon("images/icon.png").getImage());
	}

	@Override
	public void run() {
		requestFocus();
		int fps = 60;
		double timePerTick = 1000000000 / fps; // = 1.6666666E7

		long lastTime = System.nanoTime();
		long now;
		double delta = 0;

		while (isRunning) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;

			if (delta >= 1) {
				tick();
				render();
				delta--;
			}
		}
		if (!isRunning) {
			stop();
		}

	}

	/*  Faz com que esse segmento inicie e execute a máquina virtual Java, que chama o
	 *  método run deste encadeamento (desta classe).  
	 * */
	public synchronized void start() {
	/* A thread verifica no (this) se extendemos a classe Runnable.
	 * Então ela invoca o método run. */
		thread = new Thread(this); 
		isRunning = true;
		thread.start();

	}

	public synchronized void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {

		Input.MousePressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		Input.MousePressed = false;
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		//////////////////////////////////
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			Input.RIGHT = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			Input.LEFT = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			Input.UP = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			Input.DOWN = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_W) {
			Input.W = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			Input.A = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			Input.S = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			Input.D = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			Input.SPACE = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
			Input.RIGHT = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			Input.ESCAPE = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			Input.ENTER = true;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_L) {
			Input.L = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			Input.RIGHT = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			Input.LEFT = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			Input.UP = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			Input.DOWN = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_W) {
			Input.W = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			Input.A = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			Input.S = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			Input.D = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			Input.SPACE = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
			Input.RIGHT = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			Input.ESCAPE = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			Input.ENTER = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_L) {
			Input.L = false;
		}

	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



}

/**
 * Ordem de execuc�o: Construtor, Display, m�todo createDisplay, m�todo start,
 * m�todo run, m�todos chamados dentro do run.
 */
