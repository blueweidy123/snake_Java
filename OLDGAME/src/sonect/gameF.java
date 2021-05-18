package sonect;

import javax.swing.JFrame;

public class gameF extends JFrame{

	public static void main(String[] args) {
		JFrame game = new JFrame("Sờ Na Ke");
		snake Snake = new snake();
		game.add(Snake);
		game.setSize(1015, 730);
		game.setVisible(true);
		game.setResizable(false);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setLocationRelativeTo(null);
		
	}

}
