package entity;
import java.awt.Graphics2D;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Boss extends Entity {
	public boolean gotdamage;
	public String name;
	public boolean collision = false;
	public int health = 3;
	public Rectangle solidArea = new Rectangle(0, 0, 102, 102);
	
	public BufferedImage image = null;
	public Boss(GamePanel gp) {
		super(gp);
		
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidDamageAreaDefaultX = solidDamageArea.x;
		solidDamageAreaDefaultY = solidDamageArea.y;
		
		isboss = true;
		speed = 1;
		direction = "up";
		// TODO Auto-generated constructor stub
		name = "Boss";
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boss o1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boss o2.png"));
			up3 = ImageIO.read(getClass().getResourceAsStream("/player/boss o3.png"));
			up4 = ImageIO.read(getClass().getResourceAsStream("/player/boss o4.png"));
			up5 = ImageIO.read(getClass().getResourceAsStream("/player/boss o5.png"));
			up6 = ImageIO.read(getClass().getResourceAsStream("/player/boss o6.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boss u1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boss u2.png"));
			down3 = ImageIO.read(getClass().getResourceAsStream("/player/boss u3.png"));
			down4 = ImageIO.read(getClass().getResourceAsStream("/player/boss u4.png"));
			down5 = ImageIO.read(getClass().getResourceAsStream("/player/boss u5.png"));
			down6 = ImageIO.read(getClass().getResourceAsStream("/player/boss u6.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boss l1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boss l2.png"));
			left3 = ImageIO.read(getClass().getResourceAsStream("/player/boss l3.png"));
			left4 = ImageIO.read(getClass().getResourceAsStream("/player/boss l4.png"));
			left5 = ImageIO.read(getClass().getResourceAsStream("/player/boss l5.png"));
			left6 = ImageIO.read(getClass().getResourceAsStream("/player/boss l6.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boss r1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boss r2.png"));
			right3 = ImageIO.read(getClass().getResourceAsStream("/player/boss r3.png"));
			right4 = ImageIO.read(getClass().getResourceAsStream("/player/boss r4.png"));
			right5 = ImageIO.read(getClass().getResourceAsStream("/player/boss r5.png"));
			right6 = ImageIO.read(getClass().getResourceAsStream("/player/boss r6.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void setAction() {
		//if(onPath == true) {
			int goalCol = (gp.player.worldX+gp.player.solidAreaDefaultX)/gp.getTileSize();
			int goalRow = (gp.player.worldY+gp.player.solidAreaDefaultY)/gp.getTileSize();
			
			searchPath(goalCol, goalRow);
	}
	
	public void draw(Graphics2D g2) {
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		g2.drawImage(image, screenX, screenY, gp.getTileSize()*2, gp.getTileSize()*2, null); 
		g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
		g2.drawRect(screenX + solidDamageArea.x, screenY + solidDamageArea.y, solidDamageArea.width, solidDamageArea.height);
		
		//spriteCounter = 0;
		spriteCounter++;
		if (spriteCounter > 3) {
			if (spriteNum == 1) {
				spriteNum = 2;
			} else if (spriteNum == 2) {
				spriteNum = 3;
			}
			else if (spriteNum == 3) {
				spriteNum = 4;
			}
			else if (spriteNum == 4) {
				spriteNum = 5;
			}
			else if (spriteNum == 5) {
				spriteNum = 6;
			}
			else if (spriteNum == 6) {
				spriteNum = 1;
			} 
			
			spriteCounter = 0;
		}
		//image = left1;
		switch (direction) {
		case "up":
			if (spriteNum == 1) {
				image = up1;
			}
			if (spriteNum == 2) {
				image = up2;
			}
			if (spriteNum == 3) {
				image = up3;
			}
			if (spriteNum == 4) {
				image = up4;
			}
			if (spriteNum == 5) {
				image = up5;
			}
			if (spriteNum == 6) {
				image = up6;
			}
			break;
		case "down":
			if (spriteNum == 1) {
				image = down1;
			}
			if (spriteNum == 2) {
				image = down2;
			}
			if (spriteNum == 3) {
				image = down3;
			}
			if (spriteNum == 4) {
				image = down4;
			}
			if (spriteNum == 5) {
				image = down5;
			}
			if (spriteNum == 6) {
				image = down6;
			}
			break;
		case "left":
			if (spriteNum == 1) {
				image = left1;
			}
			if (spriteNum == 2) {
				image = left2;
			}
			if (spriteNum == 3) {
				image = left3;
			}
			if (spriteNum == 4) {
				image = left4;
			}
			if (spriteNum == 5) {
				image = left5;
			}
			if (spriteNum == 6) {
				image = left6;
			}
			break;
		case "right":
			if (spriteNum == 1) {
				image = right1;
			}
			if (spriteNum == 2) {
				image = right2;
			}
			if (spriteNum == 3) {
				image = right3;
			}
			if (spriteNum == 4) {
				image = right4;
			}
			if (spriteNum == 5) {
				image = right5;
			}
			if (spriteNum == 6) {
				image = right6;
			}
		

			break;
		}
	}
	
	

}