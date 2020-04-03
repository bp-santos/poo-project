package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Point2D;

public class Bateria extends Objetos{
	
	public Bateria(Point2D position){
		super(position);
	}
	
	@Override
	public String getName() {
		return "Bateria";
	}

	public boolean sobrepor() {
		return true;
	}
	
}
