package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Entity {
	
	public int worldX, worldY;
	public int speed;
	GamePanel gp;
	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, up3, up4, down3, down4, left3, left4, right3, right4, up5, up6, down5, down6, left5, left6, right5, right6, egg1, egg2, egg3, egg4, egg5, egg6, egg7, egg8, egg9; 
	public String direction;
	public boolean onPath = false;
	public boolean isboss;
	protected int spriteCounter = 0;
	protected int spriteNum = 1;
	public boolean dead = false;
	public Rectangle solidArea = new Rectangle(8, 16, 32, 32);
	public Rectangle solidDamageArea = new Rectangle(0, 0, 48, 48);
	public int solidDamageAreaDefaultX;
	public int solidDamageAreaDefaultY;
	public int actionLockCounter;
	public boolean gotdamage = false;
	public int health = 3;
	
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	public boolean isEgg = true;
	public boolean completeAnimation = false;
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	public void checkCollision() {
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		
		
		
	}

	

	public void update() {
		actionLockCounter++;

		
			
		if(isboss) {
			setAction();
			if(!collisionOn) {
				
				switch(direction) {	
					case "up": worldY -= speed;	break;
					case "down": worldY += speed; break;
					case "left": worldX -= speed; break;
					case "right": worldX += speed; break;
				}
			}
			
		}else if(!isEgg && completeAnimation) {
			
			setAction();
		
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			if(!collisionOn) {
				
				switch(direction) {	
					case "up": worldY -= speed;	break;
					case "down": worldY += speed; break;
					case "left": worldX -= speed; break;
					case "right": worldX += speed; break;
				}
			
			}
			//System.out.println(spriteNum);
			if (spriteNum == 9) {
				spriteNum = 1;
			}
			spriteCounter++;
			if (spriteCounter > 1) {
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
				else if(spriteNum == 9){
				spriteCounter = 0;
			}
				spriteCounter = 0;
			}
		}
		}
			

	
	public void setAction() {}
	
	public void draw(Graphics2D g2) {
		
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		BufferedImage image = null;
		
		// Tiles nur zeichnen, wenn sichtbar => Performance (Aufgabe?)
		if (worldX + gp.getTileSize()> gp.player.worldX - gp.player.screenX &&
				worldX - gp.getTileSize() < gp.player.worldX + gp.player.screenX &&
				worldY + gp.getTileSize()> gp.player.worldY - gp.player.screenY &&
				worldY - gp.getTileSize() < gp.player.worldY + gp.player.screenY) {
			if (!isEgg) {
				if(!completeAnimation) {
				spriteCounter++;
				if (spriteCounter > 1) {
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
						spriteNum = 7;
					}
					else if (spriteNum == 7) {
						spriteNum = 8;
					}
					else if (spriteNum == 8) {
						spriteNum = 9;
					}
					spriteCounter = 0;
					
				}
				if (spriteNum == 1) {
					image = egg1;
				}
				if (spriteNum == 2) {
					image = egg2;
				}
				if (spriteNum == 3) {
					image = egg3;
				}
				if (spriteNum == 4) {
					image = egg4;
				}
				if (spriteNum == 5) {
					image = egg5;
				}
				
				if (spriteNum == 7) {
					image = egg7;
				}
				if (spriteNum == 8) {
					image = egg8;
				}
				if (spriteNum == 9) {
					image = egg9;
					completeAnimation = true;
					//System.out.println("animation completed: " + completeAnimation);
				}
				}
				if(completeAnimation) {
					//System.out.println(spriteNum);
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
			} else {
				image = egg1;
			}
			g2.setColor(Color.green);
			if(dead == false) {
				g2.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null); 
				//g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
				
			}
			}
 			
 			
	}
	
	public void searchPath(int goalCol, int goalRow) {
		
		int startCol = (worldX+ solidArea.x)/gp.getTileSize();
		//System.out.println(worldX);
		int startRow = (worldY+ solidArea.y)/gp.getTileSize();
		//System.out.println(worldY);
		
		gp.pFinder.setNode(startCol, startRow, goalCol, goalRow);
		if(gp.pFinder.search() == true) {
			int nextX = gp.pFinder.pathList.get(0).col * gp.getTileSize();
			int nextY = gp.pFinder.pathList.get(0).row * gp.getTileSize();
			
			int enLeftX = worldX +solidArea.x;
			int enRightX = worldX +solidArea.x + solidArea.width;
			int enTopY = worldY +solidArea.y;
			//System.out.println("this:" + solidArea.x);
			//System.out.println(enTopY + "," + nextY);
			//System.out.println("2:"+ solidArea.x);
			//System.out.println("3:"+enRightX + "," + nextX);
			int enBottomY = worldY +solidArea.y + solidArea.height;
			if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.getTileSize()) {
				direction = "up";
			}
			else if(enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.getTileSize()) {
				direction = "down";
			}
			else if(enTopY >= nextY && enBottomY < nextY + gp.getTileSize()) {
				direction = "up";
				if(enLeftX > nextX) {
					direction = "left";
				}
				if(enLeftX < nextX) {
					direction = "right";
				}
			}
			else if(enTopY > nextY && enLeftX > nextX) {
				direction = "up";
				checkCollision();
				if(collisionOn == true) {
					direction = "left";
				}
			}
			else if (enTopY > nextY && enLeftX < nextX) {
				direction = "up";
				checkCollision();
				if(collisionOn == true) {
					direction = "right";
				}
				
			}
			else if (enTopY < nextY && enLeftX > nextX) {
				direction = "down";
				checkCollision();
				if(collisionOn == true) {
					direction = "left";
				}
				
			}
			else if (enTopY < nextY && enLeftX < nextX) {
				direction = "down";
				checkCollision();
				if(collisionOn == true) {
					direction = "right";
				}
				
			}
			int nextCol = gp.pFinder.pathList.get(0).col;
			int nextRow = gp.pFinder.pathList.get(0).row;
			if(nextCol == goalCol && nextRow == goalRow) {
				onPath = false;
			}
		}
	}

}
