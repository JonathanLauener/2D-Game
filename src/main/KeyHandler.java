package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, rightPressed, leftPressed, epressed, enterpressed, escpressed, fpressed, lpressed, colissionVissible, drawPath, drawTime;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {

    	// Numme der gedrückten Taste 
        int code = e.getKeyCode();
        
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true; 
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_E) {
        	epressed = true;
        }
        if (code == KeyEvent.VK_ENTER) {
        	enterpressed = true;
        }
        if (code == KeyEvent.VK_F) {
        	fpressed = true;
        }
        if (code == KeyEvent.VK_L) {
        	lpressed = true;
        }
        
        
        //DEBUG
        if (code == KeyEvent.VK_C) {
        	if (colissionVissible) {
        		colissionVissible = false;
        	} else {
        		colissionVissible = true;
        	}
        }
        if (code == KeyEvent.VK_T) {
        	if (drawTime) {
        		drawTime = false;
        	} else {
        		drawTime = true;
        	}
        }
        if (code == KeyEvent.VK_P) {
        	if (drawPath) {
        		drawPath = false;
        	} else {
        		drawPath = true;
        	}
        }
        if (code == KeyEvent.VK_ESCAPE) {
        	if(escpressed) {
        	escpressed = false;
        	}else {
        		escpressed = true;
        	}
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    	
        int code = e.getKeyCode();
        
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false; 
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_E) {
        	epressed = false;
        }
        if (code == KeyEvent.VK_ENTER) {
        	enterpressed = false;
        }
        if (code == KeyEvent.VK_F) {
        	fpressed = false;
        }
        if (code == KeyEvent.VK_L) {
        	lpressed = false;
        }
        


    }
}
