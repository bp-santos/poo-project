package pt.iscte.dcti.poo.sokoban.starter;

import java.awt.event.KeyEvent;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Player extends AbstractObjects implements AnimatedObjects{

	private String imageName;
	private SokobanGame game;

	public Player(Point2D initialPosition, SokobanGame game){
		super(initialPosition);
		this.game = game;
		imageName = "Player_U";
	}

	@Override
	public String getName() {
		return imageName;
	}

	@Override
	public int getLayer() {
		return 2;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public void move(int lastKeyPressed) {
		
		Point2D newPosition = getPosition().plus(Direction.directionFor(lastKeyPressed).asVector());
		
		if (Direction.isDirection(lastKeyPressed)) {
			switch(lastKeyPressed){
			case KeyEvent.VK_LEFT:
				imageName = "Player_L";
				break;
			case KeyEvent.VK_UP:
				imageName = "Player_U";
				break;
			case KeyEvent.VK_RIGHT:
				imageName ="Player_R";
				break;
			case KeyEvent.VK_DOWN:
				imageName ="Player_D";
				break;
			}

			for(AbstractObjects object: game.getObjects()) {
				if (newPosition.getX()>=0 && newPosition.getX()<10 && newPosition.getY()>=0 
						&& newPosition.getY()<10 ) {
					if(object.getPosition().equals(newPosition) && object.getLayer()<getLayer()) {
						setPosition(newPosition);
						game.setSteps(game.getSteps() + 1);
						game.setEnergy(game.getEnergy() - 1);
					}
					if(object.getPosition().equals(newPosition) && object.getName()=="Box")
						((Box)object).move(lastKeyPressed);
				}
			}
			System.out.println(getPosition() + " " + imageName + " " + lastKeyPressed);
		}			
	}
}