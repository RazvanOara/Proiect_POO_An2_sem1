import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Card {
	private int id;
	private int number;
	private String culoare;
	private ImageIcon image;
	private String place;

	public Card() {
	}

	public int get_id() {
		return this.id;
	}

	public void set_id(int id) {
		this.id = id;
	}

	public String get_place() {
		return this.place;
	}

	public int get_number() {
		return this.number;
	}

	public ImageIcon get_image() {
		return this.image;
	}

	public String get_culoare() {
		return this.culoare;
	}

	public void set_number(int number) {
		this.number = number;
	}

	public void set_image(String location) {
		this.image = new ImageIcon(location);

	}

	public void set_culoare(String culoare) {
		this.culoare = culoare;
	}

	public void set_place(String place) {
		this.place = place;
	}
}
