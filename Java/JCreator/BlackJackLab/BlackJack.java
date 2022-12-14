/* BlackJack Lab 
 * BlackJack
 * John Luke Denny
 */
 
package BlackJack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import javax.swing.JPanel;

import BlackJack.Card;
import BlackJack.BlackJackCard;
import BlackJack.Deck;
import BlackJack.AbstractPlayer;
import BlackJack.Player;
import BlackJack.Dealer;

public class BlackJack extends JPanel implements KeyListener{
	
   	private Dealer dealer;
	private Player player;
	private boolean start;
	private boolean hit;
	private boolean finish;
	private boolean show;
	private Font font;

	public BlackJack(){
		setBackground(Color.WHITE);
		font = new Font("TAHOMA",Font.BOLD,12);
				
		this.addKeyListener(this);

		dealer = new Dealer();
		player = new Player();
	}

	public void paintComponent(Graphics window){
		super.paintComponent(window);
		
		window.setColor(Color.BLUE);
		window.setFont(font);
		window.drawString("BlackJack GUI", 25, 50 );
		window.drawString("PRESS B to start/restart the game.",25,100);
		window.drawString("PRESS H to hit - PLAYER",325,100);
		window.drawString("PRESS F to finish(DEALER hits).",25,130);
		window.drawString("PRESS X to see the results.",325,130);

		if(start){
			//reset the dealer and player hands
			
			
			player.resetHand();
			dealer.resetHand();
			
			
			dealer.shuffle();
			//add two cards to dealer and player
			player.addCardToHand(dealer.deal());
			dealer.addCardToHand(dealer.deal());
			
			player.addCardToHand(dealer.deal());
			dealer.addCardToHand(dealer.deal());
			
			dealer.getCardFromHand(0).setHidden();
			start = false;
		}
		else if(hit){
			//add a card to player
			player.addCardToHand(dealer.deal());
			hit = false;
		}
		else if(finish){
			//hit dealer until he says to stop
			while(dealer.hit()){
				dealer.addCardToHand(dealer.deal());
			}
	      	finish = false;
		}
		else if(show){
			//determine who won the game
			dealer.getCardFromHand(0).setUnHidden();
			
			if(player.getHandValue() > 21 && dealer.getHandValue() >21 )
				window. drawString("Both players bust!! " + player.getHandValue() + " - " + dealer.getHandValue(), 25, 550);
			else if(dealer.getHandValue() < 22 && player.getHandValue() > 21){
				window. drawString("Dealer Wins and Player Busts!! " + player.getHandValue() + " - " + dealer.getHandValue(), 25, 550);
				dealer.setWinCount(dealer.getWinCount() + 1);
			}
			else if(player.getHandValue() < 22 && dealer.getHandValue() > 21){
				window. drawString("Player Wins and Dealer Busts!! " + player.getHandValue() + " - " + dealer.getHandValue(), 25, 550);
				player.setWinCount(player.getWinCount() + 1);
			}
			else if(dealer.getHandValue() > player.getHandValue() && dealer.getHandValue() < 22){
				window. drawString("Dealer Wins!! " + player.getHandValue() + " - " + dealer.getHandValue(), 25, 550);
				dealer.setWinCount(dealer.getWinCount() + 1);
			}
			else if(player.getHandValue() > dealer.getHandValue() && player.getHandValue() < 22){
				window. drawString("Player Wins!! " + player.getHandValue() + " - " + dealer.getHandValue(), 25, 550);
				player.setWinCount(player.getWinCount() + 1);
			}
			else
				window. drawString("Both PUSH!! " + player.getHandValue() + " - " + dealer.getHandValue(), 25, 550);
				
	
				
		
		
		
		
		}
		
		window.drawString("DEALER ",50,190);
		dealer.drawHand(window,0,190);

		window.drawString("PLAYER ",50,365);
		player.drawHand(window,0,365);
		
		window. drawString("Player Wins = " + player.getWinCount(), 625, 100);		
		window. drawString("Dealer Wins = " + dealer.getWinCount(), 625, 130);		
	}

   	public void keyTyped(KeyEvent e){
		if(e.getKeyChar() == 'b' || e.getKeyChar() == 'B'){
			start = true;
			repaint();
		}
		else if(e.getKeyChar() == 'h' || e.getKeyChar() == 'H'){
			hit = true;
			repaint();
		}
		else if(e.getKeyChar() == 'f' || e.getKeyChar() == 'F'){
			finish = true;
			repaint();
		}
		else if(e.getKeyChar() == 'x' || e.getKeyChar() == 'X'){
			show = true;
			repaint();
		}
	}

    public void keyReleased(KeyEvent e){
	}

	public void keyPressed(KeyEvent e){
	}	
}