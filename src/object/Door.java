package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Door extends SuperObject{
	
	public Door( ) {
		name = "Door";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/brick5.png"));
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
		collision = true;
	}

}
