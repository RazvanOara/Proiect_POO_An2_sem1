import java.util.Random;

public class Deck {

	Card cards[] = new Card[37];

	public Deck(String location) {
		for (int i = 1; i < 37; i++) {
			cards[i] = new Card();
			cards[i].set_id(i);
		}

		for (int i = 2; i < 11; i++) {
			cards[i - 1].set_number(i);
			cards[i].set_culoare("inima rosie");
		}

		for (int i = 10; i < 19; i++) {
			cards[i].set_number(i - 8);
			cards[i].set_culoare("inima neagra");
		}
		for (int i = 19; i < 28; i++) {
			cards[i].set_number(i - 17);
			cards[i].set_culoare("trefla");
		}
		for (int i = 28; i < 37; i++) {
			cards[i].set_number(i - 26);
			cards[i].set_culoare("pica");
		}
		for (int i = 1; i < 10; i++)
			cards[i].set_image(location + "\\data\\images\\" + i + ".png");

		for (int i = 10; i < 19; i++)
			cards[i].set_image(location + "\\data\\images\\" + i + ".png");

		for (int i = 19; i < 28; i++)
			cards[i].set_image(location + "\\data\\images\\" + i + ".png");

		for (int i = 28; i < 37; i++)
			cards[i].set_image(location + "\\data\\images\\" + i + ".png");

		for (int i = 1; i < 37; i++)
			cards[i].set_place("");

	}

	public void restart() {
		for (int i = 1; i < 37; i++)
			this.cards[i].set_place("");
	}

	public Card get_random_card() {
		Random r = new Random();
		int low = 1;
		int high = 37;
		int id = r.nextInt(high - low) + low;
		boolean ok = true;
		while (ok) {
			r = new Random();
			low = 1;
			high = 37;
			id = r.nextInt(high - low) + low;
			if (this.cards[id].get_place() != "player" && this.cards[id].get_place() != "house")
				ok = false;
		}
		return this.cards[id];
	}
}
