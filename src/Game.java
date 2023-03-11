import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.FileInputStream;


public class Game {

	public Game() {
		// TODO Auto-generated constructor stub
	}

	public static JFrame f = new JFrame("21");
	static String folderLocation;
	static Deck deck;
	static Player player;
	static JPanel panel;
	static JLabel points;
	static JLabel coins;
	static boolean bt = true;
	static JLabel houseLabel;
	static int housePoints;
	static Card houseCard;
	static boolean houseBool = true;
	static JMenuBar mb;
	static JMenuItem stop_start_sound;
	static JMenuItem about;
	static boolean sound_on;
	static InputStream sound_path;
	static Clip clip;
	static Clip iesi_afara;
	static Clip niste_bani;
	
	public static void draw() {

		JOptionPane.showMessageDialog(f, "Draw!");
		houseLabel.setIcon(null);
		bt = true;
		deck.restart();
		player.restart();
		panel.removeAll();
		points.setText("0");
		coins.setText("Coins " + player.get_coins());
		housePoints = 0;
		houseBool = true;
		SwingUtilities.updateComponentTreeUI(f);

	}

	public static void restart() {
		houseLabel.setIcon(null);
		bt = true;
		deck.restart();
		player.restart();
		player.set_coins(500);
		panel.removeAll();
		points.setText("0");
		coins.setText("Coins " + player.get_coins());
		housePoints = 0;
		houseBool = true;
		SwingUtilities.updateComponentTreeUI(f);
	}

	public static void check_coins() {
		if (player.get_coins() == 0) {
			
			try {
				
				File musicPath=new File( folderLocation+"\\data\\audio\\iesi_afara.wav");
				if(musicPath.exists())
				{
					AudioInputStream audioInput=AudioSystem.getAudioInputStream(musicPath);	
					iesi_afara=AudioSystem.getClip();
					iesi_afara.open(audioInput);
					iesi_afara.start();

				}
				else
				{
					System.out.println("fail music path");
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
			
			
			
			int a = JOptionPane.showConfirmDialog(f, "You have no coins! Try again?");
			if (a == JOptionPane.NO_OPTION || a == JOptionPane.CANCEL_OPTION)
				f.dispose();
			else
				restart();

		}
	}


	
	public static void lost() {
		player.set_coins(player.get_coins() - player.get_coins_bet());
		JOptionPane.showMessageDialog(f, "You lost!");
		houseLabel.setIcon(null);
		bt = true;
		deck.restart();
		player.restart();
		panel.removeAll();
		points.setText("0");
		coins.setText("Coins " + player.get_coins());
		housePoints = 0;
		houseBool = true;
		SwingUtilities.updateComponentTreeUI(f);
		check_coins();
		

	}

	public static void won() {
		player.set_coins(player.get_coins() + player.get_coins_bet());
		JOptionPane.showMessageDialog(f, "You won!");
		houseLabel.setIcon(null);
		bt = true;
		deck.restart();
		player.restart();
		panel.removeAll();
		points.setText("0");
		coins.setText("Coins " + player.get_coins());
		housePoints = 0;
		houseBool = true;
		SwingUtilities.updateComponentTreeUI(f);

	}

	public static void show_deck(String loc)// 5 carti cu spatele
	{
		String location = loc + "\\data\\images\\spate.jpg";
		ImageIcon image = new ImageIcon(location);
		for (int i = 0; i < 20; i++) {
			JLabel label = new JLabel();
			label.setIcon(image);
			label.setBounds(1200 - i * 10, 15, 200, 200);
			label.setVisible(true);
			f.getContentPane().add(label);
		}
	}

	public static void play_music(String musicLocation)
	{
		try {
			
			File musicPath=new File( musicLocation);
			if(musicPath.exists())
			{
				AudioInputStream audioInput=AudioSystem.getAudioInputStream(musicPath);	
				clip=AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				clip.loop(clip.LOOP_CONTINUOUSLY);
				//JOptionPane.showConfirmDialog(null, "stop");
			}
			else
			{
				System.out.println("fail music path");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception {

		folderLocation = JOptionPane.showInputDialog(f,
				"What is your folder path?", "C:\\Users\\razva\\eclipse-workspace\\Proiect_minigame");
		if(folderLocation==null)
			throw new Exception();
		
		f.getContentPane().setBackground(new java.awt.Color(55, 139, 27));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		play_music(folderLocation+"\\data\\audio\\Mo-Pipo Papo Pi Po Pepo.wav");
		SwingUtilities.updateComponentTreeUI(f);

		houseLabel = new JLabel();
		houseLabel.setBounds(500, 100, 200, 200);
		houseLabel.setVisible(true);
		JButton hit = new JButton("Hit me"); //
		JButton stop = new JButton("Stop");
		stop.setBounds(300, 100, 100, 50);
		hit.setBounds(150, 100, 100, 50);
		stop.setVisible(true);
		hit.setVisible(true);
		f.add(hit);
		f.add(stop);

		mb = new JMenuBar();
		
		about = new JMenuItem("About");
		
		mb.add(about);
		
		stop_start_sound = new JMenuItem("Stop sound");
		
		mb.add(stop_start_sound);
		
		f.setJMenuBar(mb);

		points = new JLabel("0");
		points.setBounds(700, 80, 300, 100);
		points.setFont(new Font("Serif", Font.PLAIN, 100));
		points.setVisible(true);
		f.getContentPane().add(points);

		show_deck(folderLocation);

		panel = new JPanel();
		panel.setBackground(new java.awt.Color(55, 139, 27));
		panel.setBounds(0, 550, 1625, 200);
		panel.setVisible(true);

		coins = new JLabel();
		coins.setBounds(1200, 250, 300, 300);
		coins.setForeground(new java.awt.Color(255, 255, 255));
		coins.setVisible(true);
		coins.setFont(new Font("Serif", Font.TRUETYPE_FONT, 50));
		
		f.getContentPane().add(coins);
		deck = new Deck(folderLocation);
		player = new Player();
		coins.setText("Coins "+player.get_coins());
		
		hit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				while (bt) {

					try {
						
						File musicPath=new File( folderLocation+"\\data\\audio\\niste_bani1.wav");
						if(musicPath.exists())
						{
							AudioInputStream audioInput=AudioSystem.getAudioInputStream(musicPath);	
							niste_bani=AudioSystem.getClip();
							niste_bani.open(audioInput);
							niste_bani.start();

						}
						else
						{
							System.out.println("fail music path");
						}
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
					
					
					
					
					Integer[] options = { 50, 100, 250, 500, 1000, player.get_coins() };
					player.set_coins_bet((Integer) JOptionPane.showInputDialog(null, "Bet",
							"Coins", JOptionPane.QUESTION_MESSAGE, null, options, options[1]));
					if (player.get_coins_bet() <= player.get_coins()) {
						bt = false;
						SwingUtilities.updateComponentTreeUI(f);
					} else
						JOptionPane.showMessageDialog(f, "Not enough coins!");
				}
				if (houseBool) {
					houseBool = false;
					housePoints = 0;
					houseCard = new Card();
					houseCard = deck.get_random_card();
					houseCard.set_place("house");
					housePoints += houseCard.get_number();
					houseLabel.setIcon(houseCard.get_image());
					f.getContentPane().add(houseLabel);
				} else {
					houseCard = new Card();
					houseCard = deck.get_random_card();
					houseCard.set_place("house");
					housePoints += houseCard.get_number();
				}
				player.add_card(deck.get_random_card(), deck);
				points.setText(Integer.toString(player.get_pts()));
				player.show_cards(deck, panel);

				SwingUtilities.updateComponentTreeUI(f);
				if (player.get_pts() > 21)
					lost();
				if (player.get_pts() == 21)
					won();

			}
		});

		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (player.get_number_of_cards() > 1) {
				
				
					if (player.get_pts() > 21 && housePoints > 21 || player.get_pts() == housePoints) {
						draw();
					} else if (player.get_pts() < 21 && housePoints > 21)// win
					{
						won();
					} else if (player.get_pts() > 21 && housePoints < 21) {
						lost();
					} else if (21 - player.get_pts() < 21 - housePoints)
						won();
					else
						lost();
				}
			}
		});

		about.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JFrame fr = new JFrame("About");
				fr.setBounds(200, 200, 500, 500);
				JLabel lab = new JLabel();
				lab.setBounds(0, -40, 500, 500);
				lab.setText(
						"<html>How to play:<br/>Try to reach 21 points or have a closer value to 21 than the house.<br/>If you have above 21 you lost<br/><br/>Coins that are bet are  doubled or lost<br/><br/><br/><br/><br/>   credits: Oara Razvan,30222:<html>");
				lab.setFont(new Font("Serif", Font.PLAIN, 30));
				lab.setVisible(true);
				fr.getContentPane().add(lab);
				fr.setLayout(null);
				fr.setVisible(true);
			}
		});
		 stop_start_sound.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				if(sound_on)
				{
					sound_on=false;
					clip.stop();
					play_music(folderLocation+"\\data\\audio\\Mo-Pipo Papo Pi Po Pepo.wav");
					stop_start_sound.setText("Stop sound");
				}
				else
				{
				sound_on=true;
				clip.stop();
				clip.close();
				stop_start_sound.setText("Start sound");
				}
				
			}
		});
		f.add(panel);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.setLayout(null);
		f.setVisible(true);
	}

}
