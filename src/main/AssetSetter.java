package main;

import entity.Boss;
import entity.Enemy;
import entity.Entity;
import object.Door;
import object.Key;

public class AssetSetter {
	
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		
		this.gp = gp;
	}
	
	public void setObject() {
		int mapNum = 0;
		gp.obj[mapNum][0] = new Key();
		gp.obj[mapNum][0].worldX = 18 * gp.getTileSize();
		gp.obj[mapNum][0].worldY = 13 * gp.getTileSize();

		
		
		gp.obj[mapNum][4] = new Key();
		gp.obj[mapNum][4].worldX = 56 * gp.getTileSize();
		gp.obj[mapNum][4].worldY = 8 * gp.getTileSize();
		
		
		
		
		
		
		gp.obj[mapNum][1] = new Key();
		gp.obj[mapNum][1].worldX = 7 * gp.getTileSize();
		gp.obj[mapNum][1].worldY = 33 * gp.getTileSize();
		
		gp.obj[mapNum][2] = new Door();
		gp.obj[mapNum][2].worldX = 4 * gp.getTileSize();
		gp.obj[mapNum][2].worldY = 17 * gp.getTileSize();
		
		gp.obj[mapNum][3] = new Door();
		gp.obj[mapNum][3].worldX = 12 * gp.getTileSize();
		gp.obj[mapNum][3].worldY = 32 * gp.getTileSize();
		
		gp.obj[mapNum][5] = new Door();
		gp.obj[mapNum][5].worldX = 27 * gp.getTileSize();
		gp.obj[mapNum][5].worldY = 2 * gp.getTileSize();
		
		gp.obj[mapNum][6] = new Door();
		gp.obj[mapNum][6].worldX = 31 * gp.getTileSize();
		gp.obj[mapNum][6].worldY = 31 * gp.getTileSize();
	



	}
	
	public void setEnemys() {
		int mapNum = 0;
		gp.en[mapNum][0] = new Enemy(gp);
		gp.en[mapNum][0].worldX = 4 * gp.getTileSize();
		gp.en[mapNum][0].worldY = 14 * gp.getTileSize();
		
		gp.en[mapNum][1] = new Enemy(gp);
		gp.en[mapNum][1].worldX = 5 * gp.getTileSize();
		gp.en[mapNum][1].worldY = 14 * gp.getTileSize();
	
		gp.en[mapNum][2] = new Enemy(gp);
		gp.en[mapNum][2].worldX = 6 * gp.getTileSize();
		gp.en[mapNum][2].worldY = 33 * gp.getTileSize();

		gp.en[mapNum][3] = new Enemy(gp);
		gp.en[mapNum][3].worldX = 8 * gp.getTileSize();
		gp.en[mapNum][3].worldY = 33 * gp.getTileSize();
		
		gp.en[mapNum][4] = new Enemy(gp);
		gp.en[mapNum][4].worldX = 31 * gp.getTileSize();
		gp.en[mapNum][4].worldY = 26 * gp.getTileSize();

		gp.en[mapNum][5] = new Enemy(gp);
		gp.en[mapNum][5].worldX = 28 * gp.getTileSize();
		gp.en[mapNum][5].worldY = 25 * gp.getTileSize();
		
		
		mapNum = 1;
		gp.boss[mapNum][0] = new Boss(gp);
		gp.boss[mapNum][0].worldX = 4*gp.getTileSize();
		
	}
	

}
