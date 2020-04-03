package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Point2D;

public class Parede extends Objetos{
	
	public Parede(Point2D position) {
		super(position);
	}

	@Override
	public String getName() {
		return "Parede";
	}

	@Override							///ESTOU NA DUVIDA QUANTO AO LAYER
	public int getLayer() {
		return 3;
	}
	
}
