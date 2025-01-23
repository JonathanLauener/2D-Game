package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;



public class Enemy extends Entity{
	
	int screenX;
	int screenY;

	int strength;
	public int health;
	public boolean dead = false;
	
	public String name;
	public boolean collision = false;
	
	public Enemy(GamePanel gp) {
		super(gp);
		name = "En";
		this.gp = gp;
		direction = "down";
		speed = 1;
		isboss = false;
		

		worldX = 10*gp.getTileSize();
		worldY = 4*gp.getTileSize();
		getPlayerImage();

		worldX = 4*gp.getTileSize();
		worldY = 16*gp.getTileSize();
		getPlayerImage();
		

	}
	
	private void getPlayerImage() {

		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/g1.R.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/g2.R.png"));
			up3 = ImageIO.read(getClass().getResourceAsStream("/player/g3.R.png"));
			up4 = ImageIO.read(getClass().getResourceAsStream("/player/g4.R.png"));
			up5 = ImageIO.read(getClass().getResourceAsStream("/player/g5.R.png"));
			up6 = ImageIO.read(getClass().getResourceAsStream("/player/g6.R.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/g1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/g2.png"));
			down3 = ImageIO.read(getClass().getResourceAsStream("/player/g3.png"));
			down4 = ImageIO.read(getClass().getResourceAsStream("/player/g4.2.png"));
			down5 = ImageIO.read(getClass().getResourceAsStream("/player/g5.2.png"));
			down6 = ImageIO.read(getClass().getResourceAsStream("/player/g6.2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/g1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/g2.png"));
			left3 = ImageIO.read(getClass().getResourceAsStream("/player/g3.png"));
			left4 = ImageIO.read(getClass().getResourceAsStream("/player/g4.2.png"));
			left5 = ImageIO.read(getClass().getResourceAsStream("/player/g5.2.png"));
			left6 = ImageIO.read(getClass().getResourceAsStream("/player/g6.2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/g1.R.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/g2.R.png"));
			right3 = ImageIO.read(getClass().getResourceAsStream("/player/g3.R.png"));
			right4 = ImageIO.read(getClass().getResourceAsStream("/player/g4.R.png"));
			right5 = ImageIO.read(getClass().getResourceAsStream("/player/g5.R.png"));
			right6 = ImageIO.read(getClass().getResourceAsStream("/player/g6.R.png"));
			egg1 = ImageIO.read(getClass().getResourceAsStream("/player/e1.png"));
			egg2 = ImageIO.read(getClass().getResourceAsStream("/player/e2.png"));
			egg3 = ImageIO.read(getClass().getResourceAsStream("/player/e3.png"));
			egg4 = ImageIO.read(getClass().getResourceAsStream("/player/e4.png"));
			egg5 = ImageIO.read(getClass().getResourceAsStream("/player/e5.png"));
			egg7 = ImageIO.read(getClass().getResourceAsStream("/player/e7.png"));
			egg8 = ImageIO.read(getClass().getResourceAsStream("/player/e8.png"));
			egg9 = ImageIO.read(getClass().getResourceAsStream("/player/e9.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//public void update() {
		 //screenX = worldX - gp.player.worldX + gp.player.screenX;
		 //screenY = worldY - gp.player.worldY + gp.player.screenY; 
		 //System.out.println(screenX+","+screenY);
	//}
	
	public void setAction() {
		//if(onPath == true) {
			int goalCol = (gp.player.worldX+gp.player.solidAreaDefaultX)/gp.getTileSize();
			int goalRow = (gp.player.worldY+gp.player.solidAreaDefaultY)/gp.getTileSize();
			
			searchPath(goalCol, goalRow);
			//}
		/*if(actionLockCounter >= 50) {
			actionLockCounter = 0;
		
		Random random = new Random();
		int i = random.nextInt(100)+1;
		
		if(i <= 25) {
			direction = "up";
		}
		if(i > 25 && i <= 50) {
			direction = "down";
		}
		if(i > 50 && i <= 75) {
			direction = "left";
		}
		if(i > 75 && i <= 100) {
			direction = "right";
		//}
		}
		}
	*/}
		
		
	
	
	public void attack(Entity e) {
		
		
	}
	
	
	
	//public void draw(Graphics2D g2) {
		//BufferedImage image = null;
		//try {
			//image = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
		//} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		//g2.drawImage(image, screenX, screenY, 48, 48, null);
	//}

}
