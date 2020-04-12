package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Box extends AbstractObjects implements AnimatedObjects{

	private SokobanGame game;

	public Box(Point2D position,SokobanGame game) {
		super(position);
		this.game=game;
	}

	@Override
	public String getName() {
		return "Box";
	}

	@Override
	public int getLayer() {
		return 2;
	}

	@Override
	public void move(int lastKeyPressed) {
		Point2D newPosition = getPosition().plus(Direction.directionFor(lastKeyPressed).asVector());
		for(AbstractObjects object : game.getObjects()) {
			if(object.getPosition().equals(newPosition) && object.getLayer()<getLayer())
				setPosition (newPosition);
		}

	}

}