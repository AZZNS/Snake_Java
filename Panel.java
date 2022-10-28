package Snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class Panel extends JPanel implements Runnable, KeyListener{

 private static final long serialVersionUID = 1L;

 public static final int WIDTH = 500, HEIGHT = 500;
 
 private Thread thread;
 private boolean running;
 private SnakeBody sb;
 private ArrayList<SnakeBody> snake;
 private Apple apple;
 private ArrayList<Apple> apples;
 private Random r;
 private int xCoor = 10, yCoor = 10, size = 5;
 private int ticks = 0;
 private boolean right = true, left = false, up = false, down = false;
 int score = 0;

 	public Panel() {
 		
 		setFocusable(true);
 		setPreferredSize(new Dimension(WIDTH, HEIGHT));
 		addKeyListener(this);
 		
 		snake = new ArrayList<SnakeBody>();
 		apples = new ArrayList<Apple>();
 		r = new Random();
 		start();
 	}
 	
 	public void start() {
 		
 		running = true;
 		Thread thread = new Thread(this);
 		thread.start();
 		
 	}
 	
 	public void stop() {
 		
 		running = false;
 		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
 		
 	}
 	
 	public void tick() {
 		
 		if(snake.size() == 0) {
 			sb = new SnakeBody(xCoor, yCoor, 10);
 			snake.add(sb);
 		}
 		ticks++;
 		if(ticks > 900000) {
 			if(right) xCoor++;
 			if(left)xCoor--;
 			if(up)yCoor--;
 			if(down)yCoor++;
 			ticks = 0;
 			sb = new SnakeBody(xCoor, yCoor, 10);
 			snake.add(sb);
 			
 			if(snake.size() > size) {
 				snake.remove(0);
 			}
 			
 			if(apples.size() == 0) {
 	 			int xCoor = r.nextInt(49);
 	 			int yCoor = r.nextInt(49);
 	 			apple = new Apple(xCoor, yCoor, 10);
 	 			apples.add(apple);
 	 		}
 			
 		}
 		
 			for(int i = 0; i < apples.size(); i++) {
 				if(xCoor == apples.get(i).getxCoor() && yCoor == apples.get(i).getyCoor()) {
 					size = size + 3;
 					apples.remove(i);
 					i++;
 					score++;
 				}
 			}
 			
 			for(int i = 0 ; i < snake.size(); i++) {
 				if(xCoor == snake.get(i).getxCoor() && yCoor == snake.get(i).getyCoor()) {
 					if(i !=snake.size()- 1) {
 						stop();
 					}
 				}
 			}
 		
 		}
 	
 	@Override
 	public void paint(Graphics g) {
 		
 		super.paint(g);
 		Font urscore = new Font("Tahoma", Font.PLAIN, 20);
 		Font urdead = new Font("Tahoma", Font.BOLD + Font.PLAIN, 40);
 		g.clearRect(0, 0, WIDTH, HEIGHT);
 		g.setColor(Color.cyan);
 		g.fillRect(0, 0, WIDTH, HEIGHT);
 		
 		 for(int i = 0; i < WIDTH/10 ; i++) {
 			   g.drawLine(i * 10, 0, i *10, HEIGHT);
 			  }
 		 
 		 for(int i = 0; i < HEIGHT/10 ; i++) {
 			 g.drawLine(0, i * 10 , HEIGHT, i * 10);
 	 }
 		
 		 for(int i = 0; i < snake.size(); i++) {
 			 snake.get(i).draw(g);
 		 }
 		 
 		 for(int i = 0; i < apples.size(); i++) {
 			 apples.get(i).draw(g);
 		 }
 		
 		 if(xCoor > 49) {
 			 xCoor = 0;
 		 }
 		 
 		 if(xCoor < 0) {
 			 xCoor = 49;
 		 }
 		 
 		 if(yCoor > 49) {
 			 yCoor = 0;
 		 }
 		 
 		 if(yCoor < 0) {
 			 yCoor = 49;
 		 }
 		 
 		 g.setFont(urscore);
 		 g.drawString("Score : " + score, 400, 490);
 		 
 		 if(running == false) {
 			 g.setFont(urdead);
 			 g.drawString("T'as perdu :(", 125, 60);
 		 }
 		 
 	}

	@Override
	public void run() {
		
		while(running == true) {
			tick();
			repaint();
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_UP && !down) {
			up = true;
			right = false;
			left = false;
		}
		
		if(key == KeyEvent.VK_DOWN && !up) {
			down = true;
			right = false;
			left = false;
		}
		
		if(key == KeyEvent.VK_RIGHT && !left) {
			down = false;
			up = false;
			right = true;
		}
		
		if(key == KeyEvent.VK_LEFT && !right) {
			down = false;
			up = false;
			left = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {}
 
}



