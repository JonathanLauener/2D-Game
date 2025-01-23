package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

	KeyHandler keyH;
	public int hasKey;
	
	public final int screenX;
	public final int screenY;
	public boolean dead;
	public int count;

	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
		this.gp = gp;
		this.keyH = keyH;
		this.hasKey= hasKey ;
		
		
		solidArea = new Rectangle(8,16,32,32);
		solidDamageArea = new Rectangle(-40,-28,128,128);
		screenX = gp.screenWidth/2 - (gp.getTileSize()/2);
		screenY = gp.screenHeight/2  - (gp.getTileSize()/2);
		
		// Standardwerte des soliden Bereiches für CheckObject
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidDamageAreaDefaultX = solidDamageArea.x;
		solidDamageAreaDefaultY = solidDamageArea.y;
		
		setDefaultValues();
		getPlayerImage();
		 
	}

	private void setDefaultValues() {
		worldX = gp.getTileSize()*4;
		worldY = gp.getTileSize()*2;
		speed = 4;
		direction = "down"; 
	}

	// Sprites (Bilder) für den Player laden
	private void getPlayerImage() {

		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			up3 = ImageIO.read(getClass().getResourceAsStream("/player/schlag o1.png"));
			up4 = ImageIO.read(getClass().getResourceAsStream("/player/schlag o2.png"));
			up5 = ImageIO.read(getClass().getResourceAsStream("/player/schlag o3.png"));
			up6 = ImageIO.read(getClass().getResourceAsStream("/player/schlag o5.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			down3 = ImageIO.read(getClass().getResourceAsStream("/player/schlag1.png"));
			down4 = ImageIO.read(getClass().getResourceAsStream("/player/schlag2.png"));
			down5 = ImageIO.read(getClass().getResourceAsStream("/player/schlag3.png"));
			down6 = ImageIO.read(getClass().getResourceAsStream("/player/schlag4.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			left3 = ImageIO.read(getClass().getResourceAsStream("/player/schlag l1.png"));
			left4 = ImageIO.read(getClass().getResourceAsStream("/player/schlag l2.png"));
			left5 = ImageIO.read(getClass().getResourceAsStream("/player/schlag l3.png"));
			left6 = ImageIO.read(getClass().getResourceAsStream("/player/schlag l4.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
			right3 = ImageIO.read(getClass().getResourceAsStream("/player/schlag r1.png"));
			right4 = ImageIO.read(getClass().getResourceAsStream("/player/schlag r2.png"));
			right5 = ImageIO.read(getClass().getResourceAsStream("/player/schlag r3.png"));
			right6 = ImageIO.read(getClass().getResourceAsStream("/player/schlag r4.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		
			
			
		
		if (keyH.epressed) {              //Schlaganimation
		
			spriteCounter++;
			if (spriteCounter > 4) {
				if (spriteNum < 3) {
					spriteNum = 3;
				}
			else if (spriteNum == 3) {
					spriteNum = 4;
				} else if (spriteNum == 4) {
					spriteNum = 5;
				} else if (spriteNum == 5) {
					spriteNum = 6;
				}
				else if(spriteNum == 6) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			
		}
		}

		
		int enIndex = gp.cChecker.checkEnemy(this, true);
		EnemyInteract(enIndex);
		
		int enCloseIndex = gp.cChecker.checkCloseEnemy(this, true);
		
		EnemyCloseInteract(enCloseIndex);
		int bossIndex = gp.cChecker.checkBoss(this, true);
		BossInteract(bossIndex);
		int ClosebossIndex = gp.cChecker.checkCloseBoss(this, true);
		BossCloseInteract(ClosebossIndex);
		
		
		//System.out.println(direction);
		if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
			if (!keyH.epressed) {
			if (keyH.upPressed) {
				direction = "up";
			}
			if (keyH.downPressed) {
				direction = "down";

			}
			if (keyH.leftPressed) {
				direction = "left";
			}
			if (keyH.rightPressed) {
				direction = "right";
			}
			//if (keyH.epressed) {
				//direction = "right";
			//}
			
			// Überprüfen, ob man gegen eine Wand läuft
			collisionOn = false;
			gp.cChecker.checkTile(this); 
			
			// Kollison mit Objekt 
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			
			
			
			if(!collisionOn) {
				switch(direction) {	
					case "up": worldY -= speed;	break;
					case "down": worldY += speed; break;
					case "left": worldX -= speed; break;
					case "right": worldX += speed; break;
				}
			}
			
		
			
			spriteCounter++;
			if (spriteCounter > 10) {
				if (spriteNum == 1) {
					spriteNum = 2;
				} else if (spriteNum > 1) {
					spriteNum = 1;
				} else if (keyH.epressed) {
					spriteNum = 3;
				}
				spriteCounter = 0;
			}
		}
		}
	}
	
	public void pickUpObject(int index) {
		if (index != 999) {
			String objectName = gp.obj[gp.mapnum][index].name;
			switch(objectName) {
			case "Key":
				gp.playSE(1);
				gp.obj[gp.mapnum][index] = null;
				hasKey++;
				System.out.println("Key:"+hasKey);
				break;
			case "Door":
				System.out.println("Press F to open");
				//drawCommand();
				if(hasKey > 0) {
					if(keyH.fpressed) {
					gp.obj[gp.mapnum][index] = null;
					hasKey--;
					System.out.println("Key:"+hasKey);
					}
				}
			}
			
			
		}
	}
	public void EnemyInteract(int index) {
		if (index != 999) {
			String objectName = gp.en[gp.mapnum][index].name;
			switch(objectName) {
			case "En":
				//System.out.println("boss");
				//gp.playSE(1);
				if(gp.en[gp.mapnum][index].isEgg == true) {
					gp.en[gp.mapnum][index].isEgg = false;
				}
				if(keyH.epressed) {
					if(spriteNum == 5 || spriteNum == 6) {
					gp.en[gp.mapnum][index].dead = true;
					}
				}
				
				//System.out.println("You have touched the enemy");
				break;
			case "Boss":
				
				//System.out.println("touched boss");
				if(keyH.epressed) {
					if(gp.boss[gp.mapnum][index].health > 0) {
					if(spriteNum == 5 || spriteNum == 6) {
						if(count > 100) {gp.boss[gp.mapnum][index].health = gp.boss[gp.mapnum][index].health -1; System.out.println("health minus 1");}
						count++;
						
						}
					} else {gp.boss[gp.mapnum][index].dead = true;}
				}
				}
				}
			}
			
	public void EnemyCloseInteract(int index) {
		if (index != 999) {
			String objectName = gp.en[gp.mapnum][index].name;
			switch(objectName) {
			case "En":
				//gp.playSE(1);
				if(gp.en[gp.mapnum][index].isEgg == false) {
					
					dead = true;
				}
				
				
				//System.out.println("You have touched the enemy");
				break;
				

			case "Boss":
				//System.out.println("boss touched");
				break;
					}
			
				}
			}
			
	public void BossInteract(int index) {
		if (index != 999) {
			String objectName = gp.boss[gp.mapnum][index].name;
			switch(objectName) {
			case "En":
				//System.out.println("boss");
				//gp.playSE(1);
				if(gp.en[gp.mapnum][index].isEgg == true) {
					gp.en[gp.mapnum][index].isEgg = false;
				}
				if(keyH.epressed) {
					if(spriteNum == 5 || spriteNum == 6) {
					gp.en[gp.mapnum][index].dead = true;
					}
				}
				
				//System.out.println("You have touched the enemy");
				break;
			case "Boss":
				//System.out.println("touched boss");
				if(keyH.epressed) {
					System.out.println("boss");
					if(gp.boss[gp.mapnum][index].health > 0) {
					if(spriteNum == 5 || spriteNum == 6) {
						//System.out.println("hit");
						//int currhealth = gp.boss[gp.mapnum][index].health;
						if(count > 10) {gp.boss[gp.mapnum][index].health = gp.boss[gp.mapnum][index].health -1; System.out.println("health minus 1");count = 0;}
						count++;
						
						}
					} else {gp.boss[gp.mapnum][index].dead = true;}
				}
				}
				}
			}
		
	public void BossCloseInteract(int index) {
		if (index != 999) {
			String objectName = gp.boss[gp.mapnum][index].name;
			switch(objectName) {
			case "En":
				//gp.playSE(1);
				if(gp.en[gp.mapnum][index].isEgg == false) {
					
					dead = true;
				}
				
				
				//System.out.println("You have touched the enemy");
				break;
				

			case "Boss":
				//System.out.println("boss close touched");
				dead = true;
				break;
					}
			
				}
			}

	public void draw(Graphics2D g2) {

		BufferedImage image = null;
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

		g2.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);

		if(keyH.colissionVissible) {
			g2.setColor(Color.magenta);
			g2.draw(gp.cChecker.tileTopLeft);
			g2.draw(gp.cChecker.tileTopRight);
			g2.draw(gp.cChecker.tileBottomLeft);
			g2.draw(gp.cChecker.tileBottomRight);
			g2.setColor(Color.red);
			g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
			g2.drawRect(screenX + solidDamageArea.x, screenY + solidDamageArea.y, solidDamageArea.width, solidDamageArea.height);
		}

	}
	public void drawCommand() {
		gp.gamestate = gp.dialogstate;
		try {
			wait(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gp.gamestate = gp.playstate;
	}
}
