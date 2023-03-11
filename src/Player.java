import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Player {

	private int pts;
	private int numberOfCards;
	private int coins;
	private int coins_bet;

	public Player() {
		pts = 0;
		numberOfCards = 0;
		coins = 500;
	}

	public int get_coins() {
		return this.coins;
	}

	public void set_coins_bet(int n) {
		this.coins_bet = n;
	}

	public int get_coins_bet() {
		return this.coins_bet;
	}

	public void set_coins(int coins) {
		this.coins = coins;
	}

	public int get_pts() {
		return this.pts;
	}

	public int get_number_of_cards() {
		return this.numberOfCards;
	}

	public void add_card(Card card, Deck deck) {
		deck.cards[card.get_id()].set_place("player");
		this.pts += card.get_number();
		this.numberOfCards++;
	}

	public void show_cards(Deck deck, JPanel f) {
		f.removeAll();
		int nr = 0;
		for (int i = 1; i < 37; i++) {
			if (deck.cards[i].get_place() == "player") {
				JLabel label = new JLabel();
				label.setBounds(900 - nr * 10, 500, 200, 200);
				nr++;
				label.setVisible(true);
				label.setIcon(deck.cards[i].get_image());
				f.add(label);
			}
		}
	}

	public void restart() {
		this.numberOfCards = 0;
		this.pts = 0;
	}
}
