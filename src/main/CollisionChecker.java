package main;

import java.awt.Rectangle;

import entity.Entity;

public class CollisionChecker {
	
	// Attribute
	GamePanel gp;
	public Rectangle tileTopLeft = new Rectangle();
	public Rectangle tileTopRight = new Rectangle();
	public Rectangle tileBottomLeft = new Rectangle();
	public Rectangle tileBottomRight = new Rectangle();
	

	//Konstruktor
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity entity) {
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		int entityLeftWorldXDamage = entity.worldX + entity.solidDamageArea.x;
		int entityRightWorldXDamage = entity.worldX + entity.solidDamageArea.x + entity.solidDamageArea.width;
		int entityTopWorldYDamage = entity.worldY + entity.solidDamageArea.y;
		int entityBottomWorldYDamage = entity.worldY + entity.solidDamageArea.y + entity.solidDamageArea.height;
		
		int entityLeftCol = entityLeftWorldX/gp.getTileSize();
		int entityRightCol = entityRightWorldX/gp.getTileSize();
		int entityTopRow = entityTopWorldY/gp.getTileSize();
		int entityBottomRow = entityBottomWorldY/gp.getTileSize();
		
		int entityDamageLeftCol = entityLeftWorldXDamage/gp.getTileSize();
		int entityDamageRightCol = entityRightWorldXDamage/gp.getTileSize();
		int entityDamageTopRow = entityTopWorldYDamage/gp.getTileSize();
		int entityDamageBottomRow = entityBottomWorldYDamage/gp.getTileSize();
		
		
		
		int tileNum1, tileNum2;
		
		switch (entity.direction) {
		case "up":				
			entityTopRow = (entityTopWorldY - entity.speed)/gp.getTileSize();
			tileNum1 = gp.tileM.mapTileNum[gp.mapnum][entityLeftCol][entityTopRow]; 
			tileNum2 = gp.tileM.mapTileNum[gp.mapnum][entityRightCol][entityTopRow]; 
			if (gp.tileM.tile[tileNum1].isEnd || gp.tileM.tile[tileNum2].isEnd) {
				gp.changeMap(1, 15, 15);
			}
			if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
				entity.collisionOn = true;
			}
			break;
		case "down":	
			entityBottomRow = (entityBottomWorldY + entity.speed)/gp.getTileSize();
			tileNum1 = gp.tileM.mapTileNum[gp.mapnum][entityLeftCol][entityBottomRow]; 
			tileNum2 = gp.tileM.mapTileNum[gp.mapnum][entityRightCol][entityBottomRow]; 
			if (gp.tileM.tile[tileNum1].isEnd || gp.tileM.tile[tileNum2].isEnd) {
				gp.changeMap(1, 15, 15);}
			if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
				entity.collisionOn = true;
			}
			break;
		case "left":	
			entityLeftCol = (entityLeftWorldX - entity.speed)/gp.getTileSize();
			tileNum1 = gp.tileM.mapTileNum[gp.mapnum][entityLeftCol][entityTopRow]; 
			tileNum2 = gp.tileM.mapTileNum[gp.mapnum][entityLeftCol][entityBottomRow]; 
			if (gp.tileM.tile[tileNum1].isEnd || gp.tileM.tile[tileNum2].isEnd) {
				gp.changeMap(1, 15, 15);			}
			if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
				entity.collisionOn = true;
			}
			break;
		case "right":	
			entityRightCol = (entityRightWorldX + entity.speed)/gp.getTileSize();
			tileNum1 = gp.tileM.mapTileNum[gp.mapnum][entityRightCol][entityTopRow]; 
			tileNum2 = gp.tileM.mapTileNum[gp.mapnum][entityRightCol][entityBottomRow]; 
			if (gp.tileM.tile[tileNum1].isEnd || gp.tileM.tile[tileNum2].isEnd) {
				gp.changeMap(1, 15, 15);			}
			if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
				entity.collisionOn = true;
			}
			break;
			
		}
		
		// Anpassungen,um die für die Kollisionserkennung abgefragten Kacheln anzuzeigen.
		tileTopLeft.y = entityTopRow*48 - gp.player.worldY + gp.player.screenY;
		tileTopLeft.x = entityLeftCol*48 - gp.player.worldX + gp.player.screenX;
		tileTopLeft.width = 48;
		tileTopLeft.height = 48;
		
		tileTopRight.y = entityTopRow*48 - gp.player.worldY + gp.player.screenY;
		tileTopRight.x = entityRightCol*48 - gp.player.worldX + gp.player.screenX;
		tileTopRight.width = 48;
		tileTopRight.height = 48;
		
		tileBottomLeft.y = entityBottomRow*48 - gp.player.worldY + gp.player.screenY;
		tileBottomLeft.x = entityLeftCol*48 - gp.player.worldX + gp.player.screenX;
		tileBottomLeft.width = 48;
		tileBottomLeft.height = 48;
		
		tileBottomRight.y = entityBottomRow*48 - gp.player.worldY + gp.player.screenY;
		tileBottomRight.x = entityRightCol*48 - gp.player.worldX + gp.player.screenX;
		tileBottomRight.width = 48;
		tileBottomRight.height = 48;
		

	}
	//Die Methode überprüft, ob der Player ein Objekt berührt. 
	//Es wird die Position des "getroffenen" Objektes als index zurückgegeben.
	public int checkObject(Entity entity, boolean player) {
		int index = 999;
		
		// Objekt-Array traversieren
		for(int i=0; i < gp.obj[1].length; i++) {
		
			if (gp.obj[gp.mapnum][i] != null) {
				// Get entity's solid area position 
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;				
				// Get object's solid area position 
				
				gp.obj[gp.mapnum][i].solidArea.x = gp.obj[gp.mapnum][i].worldX + gp.obj[gp.mapnum][i].solidArea.x;
				gp.obj[gp.mapnum][i].solidArea.y = gp.obj[gp.mapnum][i].worldY + gp.obj[gp.mapnum][i].solidArea.y;	
				
				// Bewegung Simulieren und überprüfen, wo nach der Bewegung ist:
				switch(entity.direction) {
				case "up":
					entity.solidArea.y -= entity.speed;
					if(entity.solidArea.intersects(gp.obj[gp.mapnum][i].solidArea)) {
						if (gp.obj[gp.mapnum][i].collision) {
							entity.collisionOn = true;
						}
						if (player) {
							index = i;
							
						}
					}
					break;
				case "down":
					entity.solidArea.y += entity.speed;
					if(entity.solidArea.intersects(gp.obj[gp.mapnum][i].solidArea)) {
						
						if (gp.obj[gp.mapnum][i].collision) {
							entity.collisionOn = true;
						}
						if (player) {
							index = i;
							
						}					}
					break;
				case "left":
					entity.solidArea.x -= entity.speed;
					if(entity.solidArea.intersects(gp.obj[gp.mapnum][i].solidArea)) {
						if (gp.obj[gp.mapnum][i].collision) {
							entity.collisionOn = true;
						}
						if (player) {
							index = i;
							
						}					}
					break;
				case "right":
					entity.solidArea.x += entity.speed;
					if(entity.solidArea.intersects(gp.obj[gp.mapnum][i].solidArea)) {
						if (gp.obj[gp.mapnum][i].collision) {
							entity.collisionOn = true;
						}
						if (player) {
							index = i;
							
						}					}
					break;
				}
				
					
				
				// Koordinaten zurücksetzen
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gp.obj[gp.mapnum][i].solidArea.x = gp.obj[gp.mapnum][i].solidAreaDefaultX;
				gp.obj[gp.mapnum][i].solidArea.y = gp.obj[gp.mapnum][i].solidAreaDefaultY;	
				
			}
		}
	
		return index;
	} 
	
	
	public int checkEnemy(Entity entity, boolean player) {
		int index = 999;
		
		// Objekt-Array traversieren
		for(int i=0; i < gp.en[1].length; i++) {
		
			if (gp.en[gp.mapnum][i] != null) {
				// Get entity's solid area position 
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				entity.solidDamageArea.x = entity.worldX + entity.solidDamageArea.x;
				entity.solidDamageArea.y = entity.worldY + entity.solidDamageArea.y;
				// Get object's solid area position 
				gp.en[gp.mapnum][i].solidArea.x = gp.en[gp.mapnum][i].worldX + gp.en[gp.mapnum][i].solidArea.x;
				gp.en[gp.mapnum][i].solidArea.y = gp.en[gp.mapnum][i].worldY + gp.en[gp.mapnum][i].solidArea.y;	
				if(entity.solidDamageArea.intersects(gp.en[gp.mapnum][i].solidArea)) {System.out.println("enemy touched");}
				// Bewegung Simulieren und überprüfen, wo nach der Bewegung ist:
				switch(entity.direction) {
				case "up":
					//System.out.println("ja");
					entity.solidArea.y -= entity.speed;
					if(entity.solidDamageArea.intersects(gp.en[gp.mapnum][i].solidArea)) {
						//System.out.println("nein");
						if (gp.en[gp.mapnum][i].collision) {
							entity.collisionOn = true;
							//System.out.println("executed");
						}
						if (player) {
							index = i;
							//System.out.println("executed");
						}
					}
					break;
				case "down":
					entity.solidArea.y += entity.speed;
					if(entity.solidDamageArea.intersects(gp.en[gp.mapnum][i].solidArea)) {
						//System.out.println("ja");
						if (gp.en[gp.mapnum][i].collision) {
							entity.collisionOn = true;
							//System.out.println("executed");
						}
						if (player) {
							index = i;
							//System.out.println("executed");
						}					}
					break;
				case "left":

					//System.out.println("lala");
					entity.solidArea.x -= entity.speed;
					if(entity.solidDamageArea.intersects(gp.en[gp.mapnum][i].solidArea)) {
						//System.out.println("ja");
						if (gp.en[gp.mapnum][i].collision) {
							entity.collisionOn = true;
							//System.out.println("executed");
						}
						if (player) {
							index = i;
							//System.out.println("executed");
						}					}
					break;
				case "right":
					entity.solidArea.x += entity.speed;
					if(entity.solidDamageArea.intersects(gp.en[gp.mapnum][i].solidArea)) {
						//System.out.println("ja");
						if (gp.en[gp.mapnum][i].collision) {
							entity.collisionOn = true;
							//System.out.println("executed");
						}
						if (player) {
							index = i;
							//System.out.println("executed");
						}					}
					break; 

				default:
					if(entity.solidArea.intersects(gp.obj[gp.mapnum][i].solidArea)) {
						if (gp.obj[gp.mapnum][i].collision) {
							entity.collisionOn = true;
						}
						if (player) {
							index = i;
						}
					}
				}
				
				// Koordinaten zurücksetzen
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				entity.solidDamageArea.x = entity.solidDamageAreaDefaultX;
				entity.solidDamageArea.y = entity.solidDamageAreaDefaultY;
				gp.en[gp.mapnum][i].solidArea.x = gp.en[gp.mapnum][i].solidAreaDefaultX;
				gp.en[gp.mapnum][i].solidArea.y = gp.en[gp.mapnum][i].solidAreaDefaultY;	
				
			}
		}
	
		return index;
	} 
	
	public int checkCloseEnemy(Entity entity, boolean player) {
		int index = 999;
		
		// Objekt-Array traversieren
		for(int i=0; i < gp.en[1].length; i++) {
		
			if (gp.en[gp.mapnum][i] != null) {
				// Get entity's solid area position 
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				entity.solidDamageArea.x = entity.worldX + entity.solidDamageArea.x;
				entity.solidDamageArea.y = entity.worldY + entity.solidDamageArea.y;
				// Get object's solid area position 
				gp.en[gp.mapnum][i].solidArea.x = gp.en[gp.mapnum][i].worldX + gp.en[gp.mapnum][i].solidArea.x;
				gp.en[gp.mapnum][i].solidArea.y = gp.en[gp.mapnum][i].worldY + gp.en[gp.mapnum][i].solidArea.y;	
				//if(entity.solidDamageArea.intersects(gp.en[i].solidArea)) {System.out.println("executed");}
				// Bewegung Simulieren und überprüfen, wo nach der Bewegung ist:
				switch(entity.direction) {
				case "up":
					//System.out.println("ja");
					entity.solidArea.y -= entity.speed;
					if(entity.solidArea.intersects(gp.en[gp.mapnum][i].solidArea)) {
						//System.out.println("nein");
						if (gp.en[gp.mapnum][i].collision) {
							entity.collisionOn = true;
							//System.out.println("executed");
						}
						if (player) {
							index = i;
							//System.out.println("executed");
						}
					}
					break;
				case "down":
					entity.solidArea.y += entity.speed;
					if(entity.solidArea.intersects(gp.en[gp.mapnum][i].solidArea)) {
						//System.out.println("ja");
						if (gp.en[gp.mapnum][i].collision) {
							entity.collisionOn = true;
							//System.out.println("executed");
						}
						if (player) {
							index = i;
							//System.out.println("executed");
						}					}
					break;
				case "left":
					entity.solidArea.x -= entity.speed;
					if(entity.solidArea.intersects(gp.en[gp.mapnum][i].solidArea)) {
						//System.out.println("ja");
						if (gp.en[gp.mapnum][i].collision) {
							entity.collisionOn = true;
							//System.out.println("executed");
						}
						if (player) {
							index = i;
							//System.out.println("executed");
						}					}
					break;
				case "right":
					entity.solidArea.x += entity.speed;
					if(entity.solidArea.intersects(gp.en[gp.mapnum][i].solidArea)) {
						System.out.println("ja");
						if (gp.en[gp.mapnum][i].collision) {
							entity.collisionOn = true;
							//System.out.println("executed");
						}
						if (player) {
							index = i;
							//System.out.println("executed");
						}					}
					break;

				default:
					System.out.println("default case");
					if(entity.solidArea.intersects(gp.obj[gp.mapnum][i].solidArea)) {
						if (gp.obj[gp.mapnum][i].collision) {
							entity.collisionOn = true;
						}
						if (player) {
							index = i;
						}
					}
				}
				
				// Koordinaten zurücksetzen
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				entity.solidDamageArea.x = entity.solidDamageAreaDefaultX;
				entity.solidDamageArea.y = entity.solidDamageAreaDefaultY;
				gp.en[gp.mapnum][i].solidArea.x = gp.en[gp.mapnum][i].solidAreaDefaultX;
				gp.en[gp.mapnum][i].solidArea.y = gp.en[gp.mapnum][i].solidAreaDefaultY;	
				
			}
		}
	
		return index;
	}
	
	public int checkBoss(Entity entity, boolean player) {
		int index = 999;
		
		// Objekt-Array traversieren
		for(int i=0; i < gp.boss[1].length; i++) {
		
			if (gp.boss[gp.mapnum][i] != null) {
				// Get entity's solid area position 
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				entity.solidDamageArea.x = entity.worldX + entity.solidDamageArea.x;
				entity.solidDamageArea.y = entity.worldY + entity.solidDamageArea.y;
				// Get object's solid area position 
				gp.boss[gp.mapnum][i].solidArea.x = gp.boss[gp.mapnum][i].worldX + gp.boss[gp.mapnum][i].solidDamageArea.x;
				gp.boss[gp.mapnum][i].solidArea.y = gp.boss[gp.mapnum][i].worldY + gp.boss[gp.mapnum][i].solidDamageArea.y;	
				if(entity.solidDamageArea.intersects(gp.boss[gp.mapnum][i].solidDamageArea)) {System.out.println("boss  out touched");}
				// Bewegung Simulieren und überprüfen, wo nach der Bewegung ist:
				switch(entity.direction) {
				case "up":
					//System.out.println("ja");
					entity.solidArea.y -= entity.speed;
					if(entity.solidDamageArea.intersects(gp.boss[gp.mapnum][i].solidArea)) {
						//System.out.println("nein");
						if (gp.boss[gp.mapnum][i].collision) {
							entity.collisionOn = true;
							//System.out.println("executed");
						}
						if (player) {
							index = i;
							//System.out.println("executed");
						}
					}
					break;
				case "down":
					entity.solidArea.y += entity.speed;
					if(entity.solidDamageArea.intersects(gp.boss[gp.mapnum][i].solidArea)) {
						//System.out.println("ja");
						if (gp.boss[gp.mapnum][i].collision) {
							entity.collisionOn = true;
							//System.out.println("executed");
						}
						if (player) {
							index = i;
							//System.out.println("executed");
						}					}
					break;
				case "left":

					//System.out.println("lala");
					entity.solidArea.x -= entity.speed;
					if(entity.solidDamageArea.intersects(gp.boss[gp.mapnum][i].solidArea)) {
						//System.out.println("ja");
						if (gp.boss[gp.mapnum][i].collision) {
							entity.collisionOn = true;
							//System.out.println("executed");
						}
						if (player) {
							index = i;
							//System.out.println("executed");
						}					}
					break;
				case "right":
					entity.solidArea.x += entity.speed;
					if(entity.solidDamageArea.intersects(gp.boss[gp.mapnum][i].solidArea)) {
						//System.out.println("ja");
						if (gp.boss[gp.mapnum][i].collision) {
							entity.collisionOn = true;
							//System.out.println("executed");
						}
						if (player) {
							index = i;
							//System.out.println("executed");
						}					}
					break; 

				default:
					if(entity.solidArea.intersects(gp.obj[gp.mapnum][i].solidArea)) {
						if (gp.obj[gp.mapnum][i].collision) {
							entity.collisionOn = true;
						}
						if (player) {
							index = i;
						}
					}
				}
				
				// Koordinaten zurücksetzen
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				entity.solidDamageArea.x = entity.solidDamageAreaDefaultX;
				entity.solidDamageArea.y = entity.solidDamageAreaDefaultY;
				gp.boss[gp.mapnum][i].solidArea.x = gp.boss[gp.mapnum][i].solidAreaDefaultX;
				gp.boss[gp.mapnum][i].solidArea.y = gp.boss[gp.mapnum][i].solidAreaDefaultY;	
				
			}
		}
	
		return index;
	}
	
	public int checkCloseBoss(Entity entity, boolean player) {
		int index = 999;
		
		// Objekt-Array traversieren
		for(int i=0; i < gp.boss[1].length; i++) {
		
			if (gp.boss[gp.mapnum][i] != null) {
				// Get entity's solid area position 
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				entity.solidDamageArea.x = entity.worldX + entity.solidDamageArea.x;
				entity.solidDamageArea.y = entity.worldY + entity.solidDamageArea.y;
				// Get object's solid area position 
				gp.boss[gp.mapnum][i].solidArea.x = gp.boss[gp.mapnum][i].worldX + gp.boss[gp.mapnum][i].solidArea.x;
				gp.boss[gp.mapnum][i].solidArea.y = gp.boss[gp.mapnum][i].worldY + gp.boss[gp.mapnum][i].solidArea.y;	
				//if(entity.solidDamageArea.intersects(gp.boss[i].solidArea)) {System.out.println("executed");}
				// Bewegung Simulieren und überprüfen, wo nach der Bewegung ist:
				switch(entity.direction) {
				case "up":
					//System.out.println("ja");
					entity.solidArea.y -= entity.speed;
					if(entity.solidArea.intersects(gp.boss[gp.mapnum][i].solidArea)) {
						//System.out.println("nein");
						if (gp.boss[gp.mapnum][i].collision) {
							entity.collisionOn = true;
							//System.out.println("executed");
						}
						if (player) {
							index = i;
							//System.out.println("executed");
						}
					}
					break;
				case "down":
					entity.solidArea.y += entity.speed;
					if(entity.solidArea.intersects(gp.boss[gp.mapnum][i].solidArea)) {
						//System.out.println("ja");
						if (gp.boss[gp.mapnum][i].collision) {
							entity.collisionOn = true;
							//System.out.println("executed");
						}
						if (player) {
							index = i;
							//System.out.println("executed");
						}					}
					break;
				case "left":
					entity.solidArea.x -= entity.speed;
					if(entity.solidArea.intersects(gp.boss[gp.mapnum][i].solidArea)) {
						System.out.println("ja");
						if (gp.boss[gp.mapnum][i].collision) {
							entity.collisionOn = true;
							//System.out.println("executed");
						}
						if (player) {
							index = i;
							//System.out.println("executed");
						}					}
					break;
				case "right":
					entity.solidArea.x += entity.speed;
					if(entity.solidArea.intersects(gp.boss[gp.mapnum][i].solidArea)) {
						//System.out.println("ja");
						if (gp.boss[gp.mapnum][i].collision) {
							entity.collisionOn = true;
							//System.out.println("executed");
						}
						if (player) {
							index = i;
							//System.out.println("executed");
						}					}
					break;

				default:
					System.out.println("default case");
					if(entity.solidArea.intersects(gp.obj[gp.mapnum][i].solidArea)) {
						if (gp.obj[gp.mapnum][i].collision) {
							entity.collisionOn = true;
						}
						if (player) {
							index = i;
						}
					}
				}
				
				// Koordinaten zurücksetzen
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				entity.solidDamageArea.x = entity.solidDamageAreaDefaultX;
				entity.solidDamageArea.y = entity.solidDamageAreaDefaultY;
				gp.boss[gp.mapnum][i].solidArea.x = gp.boss[gp.mapnum][i].solidAreaDefaultX;
				gp.boss[gp.mapnum][i].solidArea.y = gp.boss[gp.mapnum][i].solidAreaDefaultY;	
				
			}
		}
	
		return index;
	}
	
	}

