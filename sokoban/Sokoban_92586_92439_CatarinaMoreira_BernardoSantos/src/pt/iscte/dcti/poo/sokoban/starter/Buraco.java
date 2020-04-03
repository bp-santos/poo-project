package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Point2D;

public class Buraco extends Objetos {
	
	public Buraco(Point2D position) {
		super(position);
	}

	@Override
	public String getName() {
		return "Buraco";
	}
	
	public boolean sobrepor() {
		return true;
	}
	
}
