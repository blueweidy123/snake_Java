package sonect;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.text.GapContent;

public class snake extends JPanel implements ActionListener, KeyListener {
	
	int Delay = 250;
	int size = 50;
	int length = 100;
	int[]X = new int[length];
	int[]Y = new int[length];
	int velX = 0;
	int velY = 0;
	int dots;
	int foodX, foodY;
	boolean leftMoving, rightMoving, upMoving, downMoving;
	boolean Food;
	boolean GAMEOVER;
	int Score = 0;
	int T = 2;
	Timer t = new Timer(Delay, this);
	public snake() {
		t.start();
		setBackground(Color.BLACK);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		setStartPoint();
		setFoodPos();
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		scoreCount(g);
		drawFood(g);
		drawSnake(g);
		drawGOV(g);
		drawFood(g);
		drawGOV(g);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		checkGameOver();
		if(T % 2 == 0) {
			if(!GAMEOVER) {
				movSnake();
			}
		}
		repaint();
		System.out.println("| " + X[0] +", " + Y[0] + " |" );
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub	
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int k = e.getKeyCode();
		if(k == KeyEvent.VK_UP) {
			if(!downMoving) {
				goUp();
			}
		}
		if(k == KeyEvent.VK_DOWN) {
			if(!upMoving) {
				goDown();
			}
		}
		if(k == KeyEvent.VK_LEFT) {
			if(!rightMoving) {
				goLeft();
			}
		}
		if(k == KeyEvent.VK_RIGHT) {
			if(!leftMoving) {
				goRight();
			}
		}
		if(k == KeyEvent.VK_ESCAPE) {
			T++;
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	void setStartPoint() {
		dots = 3;
		X[0] = 0;
		Y[0] = 50;
	}
	void goUp() {
		velX = 0;
		velY = -60;
		upMoving = true;
		downMoving = false;
		rightMoving = false;
		leftMoving = false;
	}
	void goDown() {
		velX = 0;
		velY = 60;
		upMoving = false;
		downMoving = true;
		rightMoving = false;
		leftMoving = false;
	}
	void goLeft() {
		velX = -60;
		velY = 0;
		upMoving = false;
		downMoving = false;
		rightMoving = false;
		leftMoving = true;
	}
	void goRight() {
		velX = 60;
		velY = 0;
		upMoving = false;
		downMoving = false;
		rightMoving = true;
		leftMoving = false;
	}
	void movSnake() {
		for(int d = dots; d > 0; d--) {
			X[d] = X[(d - 1)];
			Y[d] = Y[(d - 1)];
		}
		X[0] += velX;
		Y[0] += velY;
	}
	void drawSnake(Graphics g) {
		for(int d = 0; d < dots; d++ ) {
			g.setColor(Color.WHITE);
			g.drawRoundRect(X[d], Y[d], size, size, 20, 20);
			g.fillRoundRect(X[d], Y[d], size, size, 20, 20);
			}
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.black);
		g2d.drawString("( ͡❛ ͜ʖ ͡❛)", (X[0] + 10), (Y[0] + 25));
	}
	void setFoodPos() {
		int randomX = (int)(17.0*Math.random());
		int randomY = (int)(11.0*Math.random());
		foodX = (randomX * 60);
		foodY = (randomY * 60) + 50 ;
	}
	void drawFood(Graphics g) {
		g.setColor(Color.CYAN);
		g.drawRoundRect(foodX, foodY, size, size, 100, 100);
		g.fillRoundRect(foodX, foodY, size, size, 100, 100);
	}
	void scoreCount(Graphics g) {
		if((X[0] == foodX) && (Y[0] == foodY) ) {
			dots ++;
			setFoodPos();
			Delay -= 5;
		}
		g.setColor(Color.WHITE);
		g.drawLine(0, 50, 1015, 50);
		g.drawString("SCORE: ", 800, 30);
		Score = dots - 3;
		String score = Integer.toString(Score);
		g.drawString(score, 850, 30);
	}
	void drawGOV(Graphics g) {
		g.setColor(Color.RED);
		if(GAMEOVER) {
			g.drawRoundRect(X[0], Y[0], size, size, 10, 10);
			g.fillRoundRect(X[0], Y[0], size, size, 10, 10);
		}
	}
	void checkGameOver() {
		for(int i = dots; i > 0; i--) {
			if((i > 3) && (X[0] == X[i]) && (Y[0] == Y[i])) {
				GAMEOVER = true;
				t.stop();
			}
		}
		if((X[0] < 0) || (X[0] >= getWidth())) {
			GAMEOVER = true;
			t.stop();
		}
		if((Y[0] < 50 ) || (Y[0] >= getHeight())) {
			GAMEOVER = true;
			t.stop();
		}
	}
	
}
