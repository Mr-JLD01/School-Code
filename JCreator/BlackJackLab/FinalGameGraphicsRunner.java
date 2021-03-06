/* BlackJack Lab 
 * FinalGameGraphicsRunner
 */
 
package BlackJack;

import javax.swing.JFrame;
import java.awt.Component;
import BlackJack.BlackJack;

public class FinalGameGraphicsRunner extends JFrame{
	
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;

	public FinalGameGraphicsRunner(){
		super("BLACKJACK");
		setSize(WIDTH,HEIGHT);

		BlackJack FinalGameRunner = new BlackJack();
		((Component)FinalGameRunner).setFocusable(true);

		getContentPane().add(FinalGameRunner);

		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args){
		FinalGameGraphicsRunner run = new FinalGameGraphicsRunner();
	}
}