package main;
 
import java.awt.Color;
import entity.Entity;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.image.BufferedImage;


import javax.imageio.ImageIO;
import javax.swing.JPanel;

import ai.PathFinder;
import entity.Boss;
import entity.Enemy;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

// Das Gamepanel stellt das Spielfeld dar
public class GamePanel extends JPanel implements Runnable {

	// Einstellungen für das Spielfeld (GamePanel)
	final int originalTileSize = 16; // 16x16 Tile
    final int scale = 3;
    public BufferedImage start, pause, died, ende;

    private final int tileSize = originalTileSize * scale; // 48x48
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 px
    public final int screenHeight = tileSize * maxScreenRow; // 576 px
    public boolean end = false;
    public final int maxMap = 10;
    public int mapnum = 0;

    // Einstellungen der Spielwelt -> Die Spielwelt ist >> als das Spielfeld!
    public final int maxWorldCol = 60;
    public final int maxWorldRow = 40;
    
    // Framerate
    int FPS = 60;
    
    

    // Thread für das Spiel erzeugen
    Thread gameThread;
    
    // Der Kollisionprüfer ist dafür zuständig, das Entitys (z.B. der Spieler) nicht durch solide Kacheln
    // z. B. Wände durchlaufen kann.
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public PathFinder pFinder = new PathFinder(this);
    // 
    public TileManager tileM = new TileManager(this);
    
    // Überprüfuung und Behandlung der Tastatureingaben, z.B. Steuerung des Spielers
    public KeyHandler keyH = new KeyHandler();
    Sound music = new Sound();
    Sound se = new Sound();
    
    //	Entities und Objekte
    public Player player = new Player(this,keyH);

    public SuperObject obj[][] = new SuperObject[maxMap][10];
    public Enemy monster = new Enemy(this); 
    public Enemy en[][] = new Enemy[maxMap][10]; 
    public Boss boss[][] = new Boss[maxMap][1];

   
    //public Enemy monster = new Enemy(this); 
  

    
    //Game State
    public int gamestate;
    public final int playstate = 0;
    public final int dialogstate = 1;
    public final int pausestate = 2;
    public final int startstate = 3;
    public final int deadstate = 4;
    public final int endstate = 5;
    

    public GamePanel () 
    {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        gamestate = startstate;
    }
    
    // Getter/Setter
    public int getTileSize() 
    {
    	return this.tileSize;
    }
    
    public void setupGame() {
    	aSetter.setObject();
    	aSetter.setEnemys();
    	playMusic(0);
    	
    }
    
    // Methode, um den Thread für das Spiel zu starten
    public void startGameThread () 
    {
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    // Delta-Method
    //Die Run-Methode wird aufgerufen, sobald der Thread gestartet wird
    @Override
    public void run() {
    	
    	double drawIntervall = 1000000000/FPS; // 0,01666 s
    	double delta = 0;
    	long lastTime = System.nanoTime();
    	long currentTime;
    	long timer = 0;
    	int drawCount = 0;
    	
    	while(gameThread != null) { // solange der Thread exisitert wiederhole folgendes:
    		
    		currentTime = System.nanoTime(); // merke die aktuelle Zeit am Anfang der Schleife (sehhhhhr genau!)
    		
    		//  delta speichert wie viel Zeit eines Zyklus (Drawintervalls) vergangen ist.
    		delta  += (currentTime - lastTime) / drawIntervall; 
    		// nur für die Anzeige der FPS
//    		timer += (currentTime - lastTime);
    		// Lasttime wür den nächsten (!) schleifendurchgang festlegen
    		lastTime = currentTime;
    		
    		// wenn delta 1 ist, wenn also ein Intervall vollständig ist, wird mit der Arbeit begonnen
    		if (delta >= 1) {
    			
        		update(); // alles aktualisieren (Methodenaufruf)
        		repaint(); // alles neu zeichnen (Methodenaufruf)
        		
        		delta--; // delta wieder auf 0 setzen
        		drawCount++; // es wurde ein Frame gezeichent, also drawcount inkrementieren
    		}
    		
//    		// Anzeige der FPS
    		if (timer >= 1000000000) { // wenn eine Sekunde vergangen ist...
    			System.out.println("FPS: " + drawCount); // Ausgabe der Anzahl der Intervalle
    			drawCount = 0; 
    			timer = 0;
    		}
        }
    }

    public void update() {
    	if (gamestate == playstate) {
    		player.update();
    		
    	if(keyH.escpressed) {
    		gamestate = pausestate;
    	}
    	//monster.update();
    	for(int i = 0; i < boss[1].length; i++) {
    		if(boss[mapnum][i] != null) {
    		if(boss[mapnum][i].dead == false) {
    			boss[mapnum][i].update();
    		}else {
    			gamestate = endstate;
    		}
    		}
    	}
    	for(int i = 0; i < en[1].length; i++) {
    		if(en[mapnum][i] != null) {
    		if(en[mapnum][i].dead == false) {
    			en[mapnum][i].update();
    		}else {
    			en[mapnum][i].worldX = 9999;
				en[mapnum][i].worldY = 9999;
			
    		}
    		
    		}
    	}
    	
    	if(player.dead) {
    		gamestate = deadstate;
    	}
    	}
    	if(gamestate == pausestate) {
    		
    		if(!keyH.escpressed) {
    			gamestate = playstate;
    		}
    		}
    	
    	if(gamestate == deadstate) {
    		if(keyH.escpressed) {
    			System.exit(0);
    		}
    	}
    	if(end) {
    		gamestate = endstate;
    	}
    	//if(gamestate == endstate) {
    		//mapnum = 1;
    		//tileM.loadMap("/maps/map2.txt", 1);
        	//gamestate = playstate;
    	//}
    	
    }

    public void paintComponent(Graphics g) {
    	Graphics2D g2 = (Graphics2D) g;
    	if (gamestate == playstate) {
    	super.paintComponent(g);
        
        
        //DEBUG
        long drawStart = System.nanoTime();
        
        // Welt zeichnen
        tileM.draw(g2);
        
        //Objekte
        for(int i = 0; i<obj[1].length;i++){
        	if(obj[mapnum][i] != null) {
        		obj[mapnum][i].draw(g2, this);
        	}
        	
        }
        for(int i = 0; i<boss[1].length;i++){
        	if(boss[mapnum][i] != null) {
        		boss[mapnum][i].draw(g2);
        	}
        	
        }//Enemy Zeichnen
        //System.out.println(en.length);

        for(int i = 0; i<en[1].length;i++){
        	if(en[mapnum][i] != null) {
        		if(!en[mapnum][i].dead) {
        		en[mapnum][i].draw(g2);
        	}
        	}
        	
        }
        
        // Spieler zeichnen
        player.draw(g2);
        //monster.draw(g2);

        ui.draw(g2);
        
        long drawEnd = System.nanoTime();
        long passed = drawEnd - drawStart;
        if (keyH.drawTime) {
        	g2.setColor(Color.white);
        	g2.drawString("Drawtime: "+ passed,	10,400);
        	System.out.println("Drawtime: "+ passed);
        	g2.dispose();
        	}
        }
        if (gamestate == startstate) {
        	drawstart(g2);
        	if (keyH.enterpressed) {
        		gamestate = playstate;
        	}
        	
        }
        if (gamestate == pausestate) {
        	drawPause(g2);
        	if (keyH.enterpressed) {
        		gamestate = playstate;
        	}
        	
        }
        if (gamestate == deadstate) {
        	drawDead(g2);
        	if(keyH.escpressed) {
    			System.exit(0);
    		}
        }
        if (gamestate == endstate) {
        	
        	
        	drawEnd(g2);
        	if(keyH.escpressed) {
    			System.exit(0);
    		}
        }
        
    	

        	
        
    }
    
    
    public void playMusic(int i) {
    	music.setFile(i);
    	music.play();
    	music.loop();
    	
    }
    public void  stopMusic(int i) {
    	music.stop();
    	
    }
    
    public void playSE(int i) {
    	se.setFile(i);
    	se.play();
    }
    public void drawstart(Graphics g2) {
    	try {
    		start = ImageIO.read(getClass().getResourceAsStream("/maps/start.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	g2.drawImage(start, 10, 10, 768, 576, null);
    	
    }
    public void drawPause(Graphics g2) {
    	try {
    		pause = ImageIO.read(getClass().getResourceAsStream("/maps/Pause.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	g2.drawImage(pause, 0, 0, 768, 576, null);
    }
    public void drawDead(Graphics g2) {
    	try {
    		died = ImageIO.read(getClass().getResourceAsStream("/maps/Tot.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	g2.drawImage(died, 0, 0, 768, 576, null);
    }
    public void drawEnd(Graphics g2) {
    	try {
    		ende = ImageIO.read(getClass().getResourceAsStream("/maps/Ende.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	g2.drawImage(ende, 0, 0, 768, 576, null);
    }
    
    public void changeMap(int map, int col, int row) {
    	mapnum = map;
    	player.worldX = col * getTileSize();
    	
    	
    	
    	player.worldY = row * getTileSize();
    }

}
