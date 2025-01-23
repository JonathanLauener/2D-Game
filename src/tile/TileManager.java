package tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	GamePanel gp;
	public Tile tile[];
	public int mapTileNum[][][];
	boolean drawPath;
	//public int mapnum = 1;
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[10];
		
		mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		
		loadMap("/maps/map01.txt", 0);
		
		loadMap("/maps/map02.txt", 1);	
	}
		
	
	
	// Map laden: Die übergebene Texdatei wird in ein zweidimensionales Array konvertiert
	public void loadMap(String filePath, int map) {
		try {
			
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			// solange nicht die unterste rechte Kachel ereicht wurde...
			while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
				
				String line = br.readLine(); // Datei zeilenweise einlesen
				while (col < gp.maxWorldCol) { // solange nicht die letzte Spalte der Map erreicht wurde...
					
					String numbers[] = line.split(" "); // eingelesene Zeile in einzelne Zeichen zerlegen, ...
					int num = Integer.parseInt(numbers[col]); // ... in Zahl umwandeln und zwischenspeichern.
					
					mapTileNum[map][col][row] = num; //... dann im Array speichern
					col++; // weiter mit nächster Spalte der Map
				}
				// wenn die letzte Spalte erreicht wurde, gehe an den Anfang der nächsten Zeile
				if (col == gp.maxWorldCol) {
					col = 0;
					row++;
				}		
			}
			// Reader schließen
			br.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	// Bilder laden
	public void getTileImage() {
		
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			tile[1].collision = true;
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/floor3.png"));
			tile[2].collision = true;
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/brick2.png"));
			tile[4].collision = true;
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/floor2.png"));
			
			tile[6] = new Tile();
			tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/brick4.png"));
			tile[6].collision = true;
			
			tile[7] = new Tile();
			tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/brick3.png"));
			tile[7].collision = true;
			
			tile[8] = new Tile();
			tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/geöffnete tür.png"));
			
			tile[9] = new Tile();
			tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/floor2.png"));
			tile[9].isEnd = true;
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		
		int worldCol = 0;
		int worldRow = 0;

		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum =  mapTileNum[gp.mapnum][worldCol][worldRow];		
			
			// TODO: Erklärung 
			int worldX = worldCol * gp.getTileSize();			
			int worldY = worldRow * gp.getTileSize();
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			// Tiles nur zeichnen, wenn sichtbar => Performance (Aufgabe?)
			if (worldX + gp.getTileSize()> gp.player.worldX - gp.player.screenX &&
					worldX - gp.getTileSize() < gp.player.worldX + gp.player.screenX &&
					worldY + gp.getTileSize()> gp.player.worldY - gp.player.screenY &&
					worldY - gp.getTileSize() < gp.player.worldY + gp.player.screenY) {
				
	 			g2.drawImage(tile[tileNum].image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
			}
			
 			worldCol++; // nächste Spalte

			// wenn die letzte Spalte erreicht wurde, gehe an den Anfang der nächsten Zeile
			if (worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}	
		}	
		if(gp.keyH.drawPath == true) {
			g2.setColor(new Color(255,0,0,70));
			for(int i = 0; i < gp.pFinder.pathList.size(); i++) {
				int worldX = gp.pFinder.pathList.get(i).col * gp.getTileSize();			
				int worldY = gp.pFinder.pathList.get(i).row * gp.getTileSize();
				int screenX = worldX - gp.player.worldX + gp.player.screenX;
				int screenY = worldY - gp.player.worldY + gp.player.screenY;
				g2.fillRect(screenX, screenY, gp.getTileSize(), gp.getTileSize());
				
			}
		}
	}
}
