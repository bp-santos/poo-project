package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Point2D;

public class Wall extends AbstractObjects{
	
	public Wall(Point2D position) {
		super(position);
	}

	@Override
	public String getName() {
		return "Wall";
	}

	@Override
	public int getLayer() {
		return 4;
	}
	
}
