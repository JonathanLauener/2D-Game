package main;

import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import object.Key;

public class UI {
	
	GamePanel gp;
	Font ariel_40;
	BufferedImage keyImage;
	private Graphics2D g2;
	public String doorComment = "Press E to Open the Door";
	public BufferedImage up1;
	public String keys;
	
	
	public UI(GamePanel gp) {
		
		this.gp = gp;
		ariel_40 = new Font("Ariel",Font.PLAIN,40);
		Key k = new Key();
		keyImage = k.image;
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
//		gp.gamestate = gp.startstate;
		this.g2 = g2;
		
//		if (gp.gamestate == gp.playstate) {
			
			g2.setFont(ariel_40);
			g2.setColor(Color.white);
			g2.drawImage(keyImage, gp.getTileSize()/2, gp.getTileSize()/2, gp.getTileSize(), gp.getTileSize(),null);
			keys = String.valueOf(gp.player.hasKey);
			if(gp.mapnum == 1) {
			String health = String.valueOf(gp.boss[gp.mapnum][0].health);
			g2.drawString("Boss Health:", 485, 63);
			g2.drawString(health, 724, 64);
			}
			g2.drawString(keys, 74, 63);
		//Game States:
//		}
		if (gp.gamestate == gp.dialogstate) {
			drawdialogscreen();

			
			
		}
		if (gp.gamestate == gp.startstate) {
			
			drawpausescreen();
			
		}

			//gp.gamestate = gp.playstate;		
			
	}

		
		
	
	public void drawdialogscreen() {
		int x = gp.getTileSize()*2;
		int y = gp.getTileSize()/2;
		int width = gp.screenWidth - (gp.getTileSize()*4);
		int height = gp.getTileSize()*5;
		drawsubwindow(x,y, width, height);
		x += gp.getTileSize();
		y += gp.getTileSize();
		g2.drawString(doorComment, x, y);
	}
	public void drawsubwindow(int x, int y, int width, int height) {
		Color c = new Color(0,0,0, 200);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		c = new Color(255,255,255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
	}
	public void drawpausescreen() {
		g2.drawImage(up1, 100, 100, 100, 100, 100, 100, 100, 100, gp);
		}

	

}
